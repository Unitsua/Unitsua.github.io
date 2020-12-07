/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50528
Source Host           : 192.168.93.88:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-08-22 15:14:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_channel`;
CREATE TABLE `t_channel` (  --频道
  `cid` int(11) NOT NULL AUTO_INCREMENT,    -- 频道id
  `cname` varchar(255) NOT NULL,        -- 频道名称
  `description` varchar(800) DEFAULT NULL,    -- 频道描述
  PRIMARY KEY (`cid`)     -- 频道id做主键
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_channel
-- ----------------------------
