CREATE TABLE tblStock (
	stock_seq	number	NOT NULL,
	member_seq	number	NOT NULL,
	Field	varchar2(500)	NULL,
	Field2	number	NULL
);

CREATE TABLE tblKospi (
	kospi_seq	number	NOT NULL,
	stockName	varchar2(500)	NULL,
	nowPrice	number	NULL,
	rate	varchar2(100)	NULL,
	volume	number	NULL,
	per	varchar2(100)	NULL,
	roe	varchar2(100)	NULL
);

CREATE TABLE tblKosdaq (
	kosdaq_seq	number	NOT NULL,
	stockName	varchar2(500)	NULL,
	nowPrice	number	NULL,
	rate	varchar2(100)	NULL,
	volume	number	NULL,
	per	varchar2(100)	NULL,
	Field5	varchar2(100)	NULL
);

CREATE TABLE tblMember (
	member_seq	number	NOT NULL,
	name	varchar2(50)	NULL,
	id	varchar2(50)	NULL,
	pw	varchar2(50)	NULL,
	tel	varchar2(20)	NULL,
	birth	date	NULL,
	email	varchar2(50)	NULL
);

CREATE TABLE tblAdmin (
	admin_seq	number	NOT NULL,
	name	varchar2(50)	NULL,
	id	varchar2(50)	NULL,
	pw	varchar2(50)	NULL,
	tel	varchar2(20)	NULL,
	email	varchar2(50)	NULL
);

CREATE TABLE tblAccount (
	account_seq	number	NOT NULL,
	member_seq	number	NOT NULL,
	totalAccount	number	NULL,
	availAccount	number	NULL
);

CREATE TABLE tblLike (
	like_seq	number	NOT NULL,
	member_seq	number	NOT NULL,
	likeStock	varchar2(500)	NULL
);

CREATE TABLE tblTrading (
	history_seq	number	NOT NULL,
	member_seq	number	NOT NULL,
	stockName	varchar2(500)	NULL,
	price	number	NULL,
	quantity	number	NULL,
	totalPrice	number	NULL,
	tradDate	date	NULL,
	sort	varchar2(50)	NULL
);

ALTER TABLE tblStock ADD CONSTRAINT PK_TBLSTOCK PRIMARY KEY (
	stock_seq,
	member_seq
);

ALTER TABLE tblKospi ADD CONSTRAINT PK_TBLKOSPI PRIMARY KEY (
	kospi_seq
);

ALTER TABLE tblKosdaq ADD CONSTRAINT PK_TBLKOSDAQ PRIMARY KEY (
	kosdaq_seq
);

ALTER TABLE tblMember ADD CONSTRAINT PK_TBLMEMBER PRIMARY KEY (
	member_seq
);

ALTER TABLE tblAdmin ADD CONSTRAINT PK_TBLADMIN PRIMARY KEY (
	admin_seq
);

ALTER TABLE tblAccount ADD CONSTRAINT PK_TBLACCOUNT PRIMARY KEY (
	account_seq,
	member_seq
);

ALTER TABLE tblLike ADD CONSTRAINT PK_TBLLIKE PRIMARY KEY (
	like_seq,
	member_seq
);

ALTER TABLE tblTrading ADD CONSTRAINT PK_TBLTRADING PRIMARY KEY (
	history_seq,
	member_seq
);

ALTER TABLE tblStock ADD CONSTRAINT FK_tblMember_TO_tblStock_1 FOREIGN KEY (
	member_seq
)
REFERENCES tblMember (
	member_seq
);

ALTER TABLE tblAccount ADD CONSTRAINT FK_tblMember_TO_tblAccount_1 FOREIGN KEY (
	member_seq
)
REFERENCES tblMember (
	member_seq
);

ALTER TABLE tblLike ADD CONSTRAINT FK_tblMember_TO_tblLike_1 FOREIGN KEY (
	member_seq
)
REFERENCES tblMember (
	member_seq
);

ALTER TABLE tblTrading ADD CONSTRAINT FK_tblMember_TO_tblTrading_1 FOREIGN KEY (
	member_seq
)
REFERENCES tblMember (
	member_seq
);

