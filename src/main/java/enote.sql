/*CREATE DATABASE eNote ON PRIMARY
( NAME = eNote_data, FILENAME = 'D:\eNote_data.mdf', SIZE = 10, MAXSIZE = 1000, FILEGROWTH = 10)
LOG ON
( NAME = eNote_log, FILENAME = 'D:\eNote_log.ldf', SIZE = 10, FILEGROWTH = 5)*/

USE eNote
GO

--DROP TABLE ACCOUNT
CREATE TABLE ACCOUNT (
                         Username varchar(12) NOT NULL PRIMARY KEY,
                         Password varchar(100) NOT NULL,
)

DELETE FROM ACCOUNT

    insert into ACCOUNT values('username','1234')

SELECT * FROM ACCOUNT
