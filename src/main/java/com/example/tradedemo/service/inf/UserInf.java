package com.example.tradedemo.service.inf;

import java.util.List;

import com.example.tradedemo.domain.TransactionInfo;
import com.example.tradedemo.domain.UserPortfolioObj;
import com.example.tradedemo.domain.UserTrnxReq;

public interface UserInf {

	UserPortfolioObj getUserPortfolio(String user);

	List<TransactionInfo> getUserTrnx(UserTrnxReq userReq);

}
