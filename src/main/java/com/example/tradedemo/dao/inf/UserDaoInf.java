package com.example.tradedemo.dao.inf;

import java.util.List;

import com.example.tradedemo.domain.UserTrnxReq;
import com.example.tradedemo.orm.TradeTransaction;
import com.example.tradedemo.orm.UserAccnt;
import com.example.tradedemo.orm.UserPortfolio;

public interface UserDaoInf {

	UserAccnt getUserInfo(String user);

	List<UserPortfolio> getUserPortFolioList(String user);

	List<TradeTransaction> getUserTrnx(UserTrnxReq userReq);

}
