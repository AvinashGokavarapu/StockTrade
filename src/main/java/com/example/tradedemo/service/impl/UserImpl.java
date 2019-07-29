package com.example.tradedemo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tradedemo.dao.inf.BaseSetterDaoInf;
import com.example.tradedemo.dao.inf.OrderDaoInf;
import com.example.tradedemo.dao.inf.UserDaoInf;
import com.example.tradedemo.domain.PortfolioInfo;
import com.example.tradedemo.domain.TransactionInfo;
import com.example.tradedemo.domain.UserPortfolioObj;
import com.example.tradedemo.domain.UserTrnxReq;
import com.example.tradedemo.orm.CompanyCurrentStockView;
import com.example.tradedemo.orm.TradeTransaction;
import com.example.tradedemo.orm.UserAccnt;
import com.example.tradedemo.orm.UserPortfolio;
import com.example.tradedemo.service.inf.UserInf;


@Service
public class UserImpl implements UserInf {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserDaoInf userDaoService;
	
	@Autowired
	BaseSetterDaoInf baseServiceDao;

	@Override
	public UserPortfolioObj getUserPortfolio(String user) {
		
		
		UserPortfolioObj userPortFolioObj = new UserPortfolioObj();
		 List<PortfolioInfo> userportfolioList = new ArrayList<PortfolioInfo>();
		
		
		// get User info
		UserAccnt userAcnt = this.userDaoService.getUserInfo(user);
		
		userPortFolioObj.setName(userAcnt.getUserId());
		userPortFolioObj.setMail(userAcnt.getMail());
		userPortFolioObj.setAmount(userAcnt.getAmount());
		userPortFolioObj.setContact(userAcnt.getContact());
		
		
		//get User Portfolio
		List<UserPortfolio> portfolioList = this.userDaoService.getUserPortFolioList(user);
		logger.info("portfolio list from DB "+ portfolioList);
		
		List<String> symbolList = portfolioList.stream().map(userPortfolio -> userPortfolio.getSymbol()).collect(Collectors.toList());
		logger.info("company list for user is " + symbolList);
		
		
		
		//Get current share prices
		List<CompanyCurrentStockView> currentStockvalueList =  this.baseServiceDao.getCurrentShareValue(symbolList);
		Map<Object, List<CompanyCurrentStockView>> companyShareValueMap = currentStockvalueList.stream().collect(Collectors.groupingBy(view -> view.getSymbol()));
		logger.info("company share value map is" + companyShareValueMap);
		
		
		// Iterating user portfolio list from DB
		for(UserPortfolio userPortfolio: portfolioList) {
			
			PortfolioInfo portfolioInfo = new PortfolioInfo();
			String userId = userPortfolio.getUserId();
			String symbol = userPortfolio.getSymbol();
			Long sharesVailable = userPortfolio.getSharesAvailable();
			
			Long currentPrice = companyShareValueMap.get(symbol).get(0).getCurrentPrice();
			Long totalvalue = sharesVailable * currentPrice;
			
			portfolioInfo.setShares(sharesVailable);
			portfolioInfo.setShareValue(currentPrice);
			portfolioInfo.setTotalValue(totalvalue);
			portfolioInfo.setSymbol(symbol);
			
			userportfolioList.add(portfolioInfo);
			
			
		}
		
		
		userPortFolioObj.setPortfolioList(userportfolioList);
		
		
		return userPortFolioObj;
	}

	@Override
	public List<TransactionInfo> getUserTrnx(UserTrnxReq userReq) {
		
		List<TransactionInfo> resultList = new ArrayList<TransactionInfo>();
		 List<TradeTransaction> trnxList = this.userDaoService.getUserTrnx(userReq);
		 
		 for(TradeTransaction trnx : trnxList) {
			 
			 TransactionInfo trnxInfo = new TransactionInfo();
			 
			 trnxInfo.setTransactionId(trnx.getTrnxId());
			 trnxInfo.setSymbol(trnx.getSymbol());
			 trnxInfo.setShares(trnx.getSharesTransferred());
			 trnxInfo.setBuyer(trnx.getBuyer());
			 trnxInfo.setSeller(trnx.getSeller());
			 trnxInfo.setSharePrice(trnx.getTrnxSharePrice());
			 trnxInfo.setDate(trnx.getCreate_date());
			 
			 resultList.add(trnxInfo);
			 
			 
		 }
		
		return resultList;
	}
	
	

}
