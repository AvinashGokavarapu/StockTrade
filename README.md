# StockTrade
REST APIs for trading platform to buy and sell stocks using (spring boot + hibernate)

API end points 
http://localhost:8080/swagger-ui.html#/


DB Table scripts :-

Tables :-

1) trading_company

CREATE TABLE IF NOT EXISTS trade_company (
    id INT PRIMARY KEY AUTO_INCREMENT,
    symbol VARCHAR(15) NOT NULL UNIQUE,
    company_name VARCHAR(20) NOT NULL ,
    head_quaters VARCHAR(25) NOT NULL,
    listed_date DATE not null
)
AUTO_INCREMENT = 1; 

INSERT INTO trade_company
VALUES(0,'wipro', 'Wipro','India', curdate());


2) company_share_value_log
CREATE TABLE IF NOT EXISTS company_share_value_log (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    symbol VARCHAR(15) NOT NULL  ,
    create_date TIMESTAMP not null,
    value INT ,
	FOREIGN KEY fk_log_symbol(symbol)
   REFERENCES trade_company(symbol)
)
AUTO_INCREMENT = 1; 

insert into company_share_value_log values(0,'csco',now(),57);
insert into company_share_value_log values(0,'intu',now(),283);
select * from company_share_value_log where symbol='intu' order by create_date desc limit 1;


3) user_info

CREATE TABLE IF NOT EXISTS user_info (
    seq_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(15) NOT NULL UNIQUE,
    mail VARCHAR(20) NOT NULL,
    contact VARCHAR(25) ,
    amount int not null
)
AUTO_INCREMENT = 1; 


insert into user_info values(0,'kohli','kohli@gmail.com','+91-9247435456',12000);




4) order_info

CREATE TABLE IF NOT EXISTS order_info (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    shares INT not null,
    order_type varchar(1),
    symbol VARCHAR(15) NOT NULL ,
    quote_price int not null,
    user_id VARCHAR(15) NOT NULL ,
    order_status varchar(2) not null,
    adm_flag varchar(1) not null,
    create_date TIMESTAMP not null,
    update_date TIMESTAMP not null,
    FOREIGN KEY fk_order_symbol(symbol)
    REFERENCES trade_company(symbol)
)
AUTO_INCREMENT = 1; 



5) trade_transaction 

 CREATE TABLE IF NOT EXISTS trade_transaction (
    trnx_id INT PRIMARY KEY AUTO_INCREMENT,
    shares INT not null,
    buy_order_id int,
    sell_order_id int,
    buyer_user_id varchar(15) not null,
    seller_user_id varchar(15) not null,
    symbol VARCHAR(15) NOT NULL ,
    trnx_share_price int not null,
    create_date TIMESTAMP not null,
    FOREIGN KEY fk_trnx_symbol(symbol)
    REFERENCES trade_company(symbol),
    FOREIGN KEY fk1_trnx_order(buy_order_id)
    REFERENCES order_info(order_id),
    FOREIGN KEY fk2_trnx_order(sell_order_id)
    REFERENCES order_info(order_id),
    FOREIGN KEY fk1_trnx_user(buyer_user_id)
    REFERENCES user_info(user_id),
    FOREIGN KEY fk2_trnx_user(seller_user_id)
    REFERENCES user_info(user_id)
)
AUTO_INCREMENT = 1;


6) user_portfolio

CREATE TABLE IF NOT EXISTS user_portfolio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(15) NOT NULL ,
    symbol VARCHAR(15) NOT NULL ,
    shares int not null,
    amount int not null,
    create_date TIMESTAMP not null,
    update_date TIMESTAMP not null,
    FOREIGN KEY fk_portfolio_user(user_id)
    REFERENCES user_info(user_id),
    FOREIGN KEY fk_portfolio_symbol(symbol)
    REFERENCES trade_company(symbol),
    CONSTRAINT uc_user_symbol UNIQUE (user_id , symbol)
)
AUTO_INCREMENT = 1;
