package com.example.tradedemo.dao.impl;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.tradedemo.dao.inf.OrderDaoInf;
import com.example.tradedemo.domain.OrderInfo;
import com.example.tradedemo.orm.CompanyShareLog;
import com.example.tradedemo.orm.Order;
import com.example.tradedemo.orm.TradeCompany;
import com.example.tradedemo.orm.TradeTransaction;
import com.example.tradedemo.orm.UserAccnt;
import com.example.tradedemo.orm.UserPortfolio;
import com.example.tradedemo.util.TradeConstants;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDaoInf{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Order placeOrder(OrderInfo order) {
		
		logger.info("inside place order" );
		Session session = sessionFactory.getCurrentSession();
		LocalDateTime currTime = LocalDateTime.now();
		
		try {
			
			Order orderObj = new Order();
			orderObj.setUserId(order.getUserId());
			orderObj.setShares(order.getShares());
			orderObj.setOrderType(order.getType());
			orderObj.setSymbol(order.getSymbol());
			orderObj.setQuotePrice(order.getQuotePrice());
			orderObj.setOrderStatus(TradeConstants.ORDER_QUEUED);
			orderObj.setAdmFlag(TradeConstants.ORDER_VALID);
			orderObj.setCreateDate(currTime);
			orderObj.setUpdateDate(currTime);
			
			session.save(orderObj);
			return orderObj;
			
		} catch(Exception ex) {
			
			logger.error("Exception occured in placing order " + ex.getMessage());
			ex.printStackTrace();
			return null;
			
		}
		
	}

	@Override
	public void processOrder(Order placedOrder) {
		
		logger.info("start processing order" );
		Session session = sessionFactory.getCurrentSession();
		Transaction trnx1 = session.beginTransaction();
		
		LocalDateTime currTime = LocalDateTime.now();
		
		String placedOrderSymbol = placedOrder.getSymbol();
		Long quotePrice = placedOrder.getQuotePrice();
		String placedOrderType = placedOrder.getOrderType();
		
		int noOfRowsUpdated = 0;
		
		try {
			
			// Lock the order by changing status to In-progress
			placedOrder.setOrderStatus(TradeConstants.ORDER_INPROGRESS);
			session.update(placedOrder);
			
			
			// Locking the opposite order-type orders to In-progress to prevent deletion of order while processing...
			if (TradeConstants.SELL_ORDER_TYPE.equalsIgnoreCase(placedOrderType)) {  // If order is of type Sell
				
			Query query = session
					.createQuery("update Order set orderStatus = :changeStatus  where symbol = :symbol and orderType= :orderType and admFlag= :admFlag and orderStatus= :status and quotePrice>= :quotePrice");
			query.setParameter("changeStatus", TradeConstants.ORDER_INPROGRESS);
			query.setParameter("symbol", placedOrderSymbol);
			query.setParameter("orderType", TradeConstants.BUY_ORDER_TYPE);
			query.setParameter("admFlag", "V");
			query.setParameter("status", TradeConstants.ORDER_QUEUED);
			query.setParameter("quotePrice", quotePrice);
			noOfRowsUpdated = query.executeUpdate();
			
			} else { // If order is of type Buy
				
				Query query = session
						.createQuery("update Order set orderStatus = :changeStatus  where symbol = :symbol and orderType= :orderType and admFlag= :admFlag and orderStatus= :status and quotePrice<= :quotePrice");
				query.setParameter("changeStatus", TradeConstants.ORDER_INPROGRESS);
				query.setParameter("symbol", placedOrderSymbol);
				query.setParameter("orderType", TradeConstants.SELL_ORDER_TYPE);
				query.setParameter("admFlag", "V");
				query.setParameter("status", TradeConstants.ORDER_QUEUED);
				query.setParameter("quotePrice", quotePrice);
				noOfRowsUpdated = query.executeUpdate();
				
			}
			
			logger.info("No of rows updated "+ noOfRowsUpdated);
			
			if(noOfRowsUpdated >= 1) {
				
				// Now select all the orders of status I order by create date desc
				List<Order> orderList = session.createCriteria(Order.class)
						.add(Restrictions.eq("orderStatus", TradeConstants.ORDER_INPROGRESS)).list();
				
				trnx1.commit();
				
				
				
				createTransactions(orderList);
				
				
				
			}else {
				
				trnx1.rollback();
				
			}
			
		} catch(Exception ex){
			
			logger.error("Exception occured "+ ex.getMessage());
			
			
		}
		
		
	}

	
	// main logic starts here  and it can be separate micro service
	private void createTransactions(List<Order> lockedOrderList) {

		System.out.println(lockedOrderList.toString());
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;

		Queue<Order> buyQueue = new LinkedList<Order>();
		Queue<Order> sellQueue = new LinkedList<Order>();

		// Segregation of buy and sell orders
		for (Order order : lockedOrderList) {

			if (TradeConstants.BUY_ORDER_TYPE.equalsIgnoreCase(order.getOrderType())) {

				buyQueue.add(order);

			} else {

				sellQueue.add(order);
			}

		}

		while (!buyQueue.isEmpty() && !sellQueue.isEmpty()) {

			tx =  session.beginTransaction();
			
			Order sellOrder = sellQueue.peek();
			Order buyOrder = buyQueue.peek();
			
			String symbol = sellOrder.getSymbol();
			long trnxPricePerShare = sellOrder.getQuotePrice();
			
			String sellerUserId = sellOrder.getUserId();
			String buyerUserId = buyOrder.getUserId();

			long sellShares = sellOrder.getShares();
			long buyShares = buyOrder.getShares();

			long sharesTransferred = Math.min(sellShares, buyShares);

			// create trnx with minimum value...
			System.out.println("Transaction for no of shares " + sharesTransferred);
			
			try {
			
			// Create Transaction 
			TradeTransaction tradeTrnx = new TradeTransaction();
			
			tradeTrnx.setSharesTransferred(sharesTransferred);
			tradeTrnx.setBuyOrderId(buyOrder.getOrderId());
			tradeTrnx.setSellOrderId(sellOrder.getOrderId());
			tradeTrnx.setBuyer(buyOrder.getUserId());
			tradeTrnx.setSeller(sellOrder.getUserId());
			tradeTrnx.setSymbol(symbol);
			tradeTrnx.setTrnxSharePrice(trnxPricePerShare);
			tradeTrnx.setCreate_date(LocalDateTime.now());
			
			session.save(tradeTrnx);
			
			
			// Now change portFolios for no of shares for buyer and seller
				Criteria sellerPortFolioCrit = session.createCriteria(UserPortfolio.class)
						.add(Restrictions.eq("symbol", symbol)).add(Restrictions.eq("userId", sellerUserId));
				UserPortfolio sellerPortFolio = (UserPortfolio) sellerPortFolioCrit.uniqueResult();
				
				System.out.println("seller portfolio is "+ sellerPortFolio);

				Long shareAvaiable = sellerPortFolio.getSharesAvailable();
				sellerPortFolio.setSharesAvailable(shareAvaiable - sharesTransferred);
				sellerPortFolio.setUpdateDate(LocalDateTime.now());
				session.update(sellerPortFolio);
				

				Criteria buyerPortFolioCrit = session.createCriteria(UserPortfolio.class)
						.add(Restrictions.eq("symbol", symbol)).add(Restrictions.eq("userId", buyerUserId));
				UserPortfolio buyerPortFolio = (UserPortfolio) buyerPortFolioCrit.uniqueResult();
				
				System.out.println("buyer portfolio is "+ buyerPortFolio);
				
				if(buyerPortFolio == null) {
					
					buyerPortFolio = new UserPortfolio();
					buyerPortFolio.setSharesAvailable(sharesTransferred);
					buyerPortFolio.setUserId(buyerUserId);
					buyerPortFolio.setSymbol(symbol);
					buyerPortFolio.setCreateDate(LocalDateTime.now());
					buyerPortFolio.setUpdateDate(LocalDateTime.now());
					
					session.save(buyerPortFolio);
					
				} else {
					
					Long sharesAvailable = buyerPortFolio.getSharesAvailable();
					buyerPortFolio.setSharesAvailable(sharesAvailable + sharesTransferred);
					buyerPortFolio.setUpdateDate(LocalDateTime.now());
					
					session.update(buyerPortFolio);
				}
				
				
				
				Long totalAmountTranferred = sharesTransferred * trnxPricePerShare;
				
				// Now change amount available for buyer and seller
				Criteria sellerAccntCrit = session.createCriteria(UserAccnt.class)
											.add(Restrictions.eq("userId", sellerUserId));
				UserAccnt sellerAccnt = (UserAccnt) sellerAccntCrit.uniqueResult();
				
				long sellerAmountAvilable = sellerAccnt.getAmount();
				sellerAccnt.setAmount(sellerAmountAvilable + totalAmountTranferred);
				sellerAccnt.setUpdateDate(LocalDateTime.now());
				session.update(sellerAccnt);
				
				
				
				
				Criteria buyerAccntCrit = session.createCriteria(UserAccnt.class)
						.add(Restrictions.eq("userId", buyerUserId));
				UserAccnt buyerAccnt = (UserAccnt) buyerAccntCrit.uniqueResult();

				long buyerAmountAvilable = buyerAccnt.getAmount();
				buyerAccnt.setAmount(buyerAmountAvilable - totalAmountTranferred);
				buyerAccnt.setUpdateDate(LocalDateTime.now());
				session.update(buyerAccnt);
				
				
				// Now log updated company share price
				TradeCompany tradeCompany = (TradeCompany) session.createCriteria(TradeCompany.class).add(Restrictions.eq("symbol", symbol)).uniqueResult();
				
				CompanyShareLog companyShareLog = new CompanyShareLog();
				companyShareLog.setShareValue(trnxPricePerShare);
				companyShareLog.setSymbol(tradeCompany);
				companyShareLog.setTimeStamp(LocalDateTime.now());
				
				session.save(companyShareLog);
				
				
				
				if (sellShares == sharesTransferred) {

					sellOrder.setOrderStatus(TradeConstants.ORDER_COMPLETED);
					sellQueue.poll();
					buyOrder.setShares(buyShares - sharesTransferred);
					
				}

				if (buyShares == sharesTransferred) {

					buyOrder.setOrderStatus(TradeConstants.ORDER_COMPLETED);
					buyQueue.poll();
					sellOrder.setShares(sellShares - sharesTransferred);
				}
			
			
			tx.commit();
			
			//send Notification for the buyer and seller and it can be separate micro service...
			
			
			} catch(Exception ex) {
				
				tx.rollback();
				logger.error("Exception occured in transaction "+ ex.getMessage());
				ex.printStackTrace();
				
				//If Trnx failed then break the loop and set all the orders in queues status to R -> Re-try
			}
			
			

		} // End of while it means one queue is empty...
		
		
		// Now make the remaining orders queued again which are left in any of the queue...
		while(!sellQueue.isEmpty()) {
			
			Order sellOrder = sellQueue.poll();
			sellOrder.setOrderStatus(TradeConstants.ORDER_QUEUED);
		}
		
		while (!buyQueue.isEmpty()) {
			
			Order buyOrder = buyQueue.poll();
			buyOrder.setOrderStatus(TradeConstants.ORDER_QUEUED);
			
		}
		

	}

}
