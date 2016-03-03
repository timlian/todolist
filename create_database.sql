/***********************************************************
* Create the todolist database
************************************************************/

DROP DATABASE IF EXISTS todolist;

CREATE DATABASE todolist;

USE todolist;

CREATE TABLE user (
  userid INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY(userid)
);

INSERT INTO user(username, password) VALUES ('365107844@qq.com', '123');
