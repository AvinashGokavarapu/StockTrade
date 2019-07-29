package com.example.tradedemo.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.example.tradedemo.dao.inf.BaseSetterDaoInf;
import com.example.tradedemo.domain.CompanyInfo;
import com.example.tradedemo.domain.CompanySharesDistribution;
import com.example.tradedemo.domain.LotteryObj;
import com.example.tradedemo.orm.CompanyCurrentStockView;
import com.example.tradedemo.orm.CompanyShareLog;
import com.example.tradedemo.orm.TradeCompany;
import com.example.tradedemo.orm.UserPortfolio;


@Repository
@Transactional
public class BaseSetterDaoImpl implements BaseSetterDaoInf {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String listCompany(CompanyInfo compInfo) {
		
		logger.info("inside set company listing" );
		Session session = sessionFactory.getCurrentSession();
		
		
		try {
			
			
			LocalDateTime currentTime = LocalDateTime.now();
			
			// List new company in exchange
			TradeCompany company = new TradeCompany();
			company.setSymbol(compInfo.getSymbol());
			company.setCompanyName(compInfo.getCompanyName());
			company.setHeadQuaters(compInfo.getHeadQuaters());
			company.setListedDate(currentTime);
			
		
			
			// After listing in exchange initialize starting price
			CompanyShareLog shareLog = new CompanyShareLog();
			shareLog.setShareValue(compInfo.getInitialStockPrice());
			shareLog.setSymbol(company);
			shareLog.setTimeStamp(currentTime);
			
			List<CompanyShareLog> shareLogList  = new ArrayList<CompanyShareLog>();
			shareLogList.add(shareLog);
			company.setShareValueLog(shareLogList);
			
			session.save(company);
			
			
			return "Successful";
			
		} catch(Exception ex) {
			
			
			logger.error("Exception occured in listing company "+ ex.getMessage());
			return "Fail";
			
		}
		
		
		
		
	}

	@Override
	public TradeCompany getCompanyLog(String symbol) {
		
		logger.info("inside get company log" );
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			TradeCompany company = (TradeCompany) session.createCriteria(TradeCompany.class)
					.add(Restrictions.eq("symbol", symbol)).uniqueResult();
			
			System.out.println("result list size is "+ company.getShareValueLog().size());
			return company;
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			logger.error("Exception occured in fetching company log list for company "+ symbol );
			return null;
			
		}
		
	}

	@Override
	public String distributeShares(CompanySharesDistribution shareDist) {
		
		logger.info("inside distribute shares");
		LocalDateTime currTime = LocalDateTime.now();
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			String symbol = shareDist.getSymbol();
			List<LotteryObj> lotteryList = shareDist.getLotteryList();
			
			for(LotteryObj userSharesMap : lotteryList) {
				
				String userId = userSharesMap.getUserId();
				Long shares = userSharesMap.getShares();
				
				UserPortfolio userPortfolio = new UserPortfolio();
				userPortfolio.setUserId(userId);
				userPortfolio.setSharesAvailable(shares);
				userPortfolio.setSymbol(symbol);
				userPortfolio.setCreateDate(currTime);
				userPortfolio.setUpdateDate(currTime);
				
				session.save(userPortfolio);
				
			}
			
			return "Successful";
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			logger.error("Exception occured in distributing shares " + ex.getMessage());
			return "Failed";
			
		}
		 
		
	}

	@Override
	public List<CompanyCurrentStockView> getCurrentShareValue(List<String> compList) {
		
		logger.info("Inside get current share value");
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			List<CompanyCurrentStockView> resultList = session.createCriteria(CompanyCurrentStockView.class)
					.add(Restrictions.in("symbol", compList.toArray(new String[0]))).list();
			
			System.out.println("result list is "+ resultList.toString());
			return resultList;

		
		}catch(Exception ex) {
			
			logger.error("Exception occured in fetching current value of cpmpany " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
		
	}

}
