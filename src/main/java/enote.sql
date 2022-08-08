/*CREATE DATABASE eNote ON PRIMARY
( NAME = eNote_data, FILENAME = 'D:\eNote_data.mdf', SIZE = 10, MAXSIZE = 1000, FILEGROWTH = 10)
LOG ON
( NAME = eNote_log, FILENAME = 'D:\eNote_log.ldf', SIZE = 10, FILEGROWTH = 5)*/

USE eNote
GO

/*
DROP TABLE NOTE
DROP TABLE ACCOUNT
$2a$13$Efytw0A9HP/4lnLSmDSFaOKC0VEU1yh3CfjJHEi64rsBK8H.VC7sK
*/

CREATE TABLE ACCOUNT (
                         Username VARCHAR(100),
                         Password VARCHAR(100)
                         CONSTRAINT PK_Username PRIMARY KEY CLUSTERED (Username)
)

ON [PRIMARY]
GO

INSERT INTO ACCOUNT values('username','$2a$13$Efytw0A9HP/4lnLSmDSFaOKC0VEU1yh3CfjJHEi64rsBK8H.VC7sK')

GO

CREATE TABLE NOTE (
                      ID VARCHAR(50),
                      Type VARCHAR(50),
                      Content NVARCHAR(100),
                      Username_ID VARCHAR(100),
					  Create_Time DATETIME,
                      CONSTRAINT PK_ID PRIMARY KEY CLUSTERED (ID)
)

ALTER TABLE NOTE WITH NOCHECK
	ADD FOREIGN KEY (Username_ID) REFERENCES ACCOUNT(Username)
GO



SELECT * FROM ACCOUNT
SELECT * FROM NOTE

