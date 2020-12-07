/*
Navicat MySQL Data Transfer

Source Server         : myDB
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2019-09-01 17:52:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `cid` int(11) NOT NULL,
  PRIMARY KEY (`aid`),
  KEY `cid` (`cid`),
  CONSTRAINT `cid` FOREIGN KEY (`cid`) REFERENCES `t_channel` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1', 'Java架构', 'Java架构师', '虎', 'www.xalead.com', '2019-09-01 17:46:39', '20');
INSERT INTO `t_article` VALUES ('2', 'JBPM实例', '要知道为什么这么设计', '杨小虎', 'www.baidu.com', '2019-09-01 17:49:40', '20');
INSERT INTO `t_article` VALUES ('3', 'Java前端', '页面设计', '杨小虎', 'www.yahoo.com', '2019-09-01 17:50:38', '19');
