package com.example.tradedemo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.tradedemo.dao.inf.UserDaoInf;
import com.example.tradedemo.domain.UserTrnxReq;
import com.example.tradedemo.orm.TradeTransaction;
import com.example.tradedemo.orm.UserAccnt;
import com.example.tradedemo.orm.UserPortfolio;

@Repository
@Transactional
public class UserDaoImpl implements UserDaoInf {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public UserAccnt getUserInfo(String user) {

		logger.info("Inside get user info dao impl");
		Session session = sessionFactory.getCurrentSession();

		try {

			UserAccnt userAccnt = (UserAccnt) session.createCriteria(UserAccnt.class)
					.add(Restrictions.eq("userId", user)).uniqueResult();
			return userAccnt;

		} catch (Exception ex) {

			logger.error("Exception occured in fetching user info" + ex.getMessage());
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public List<UserPortfolio> getUserPortFolioList(String user) {

		logger.info("Inside get user portfloio list for user " + user);
		Session session = sessionFactory.getCurrentSession();

		try {

			List<UserPortfolio> userPortfolioList = session.createCriteria(UserPortfolio.class)
					.add(Restrictions.eq("userId", user)).list();
			return userPortfolioList;

		} catch (Exception ex) {

			logger.error("Exception occured in fetching user portfolio " + ex.getMessage());
			ex.printStackTrace();
			return null;

		}

	}

	@Override
	public List<TradeTransaction> getUserTrnx(UserTrnxReq userReq) {

		logger.info("inside trade get trade trnx");
		Session session = sessionFactory.getCurrentSession();

		String user = userReq.getUser();
		String role = userReq.getRole(); // Buyer or Seller
		List<TradeTransaction> trnxList = new ArrayList<TradeTransaction>();

		try {

			if ("buyer".equalsIgnoreCase(role)) {

				trnxList = session.createCriteria(TradeTransaction.class).add(Restrictions.eq("buyer", user))
						.addOrder(Order.desc("trnxId")).list();

			} else {

				trnxList = session.createCriteria(TradeTransaction.class).add(Restrictions.eq("seller", user))
						.addOrder(Order.desc("trnxId")).list();

			}

			return trnxList;

		} catch (Exception e) {

			logger.error("Exception occured in getting trnxs " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

}
