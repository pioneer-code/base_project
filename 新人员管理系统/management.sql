/*
Navicat MySQL Data Transfer

Source Server         : aliyun
Source Server Version : 50728
Source Host           : 119.23.241.76:3306
Source Database       : management

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2022-02-08 17:06:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `company_name` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`company_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '1');
INSERT INTO `company` VALUES ('11', '1');
INSERT INTO `company` VALUES ('111', '111111');
INSERT INTO `company` VALUES ('122', '111111');
INSERT INTO `company` VALUES ('122122', '122122');
INSERT INTO `company` VALUES ('123', '123456');
INSERT INTO `company` VALUES ('123456', '123456');
INSERT INTO `company` VALUES ('22', '222222');
INSERT INTO `company` VALUES ('33', '2wwwwww');
INSERT INTO `company` VALUES ('赵乾淳', '123456');
INSERT INTO `company` VALUES ('软件开发科技有限公司', '123456');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `company_name` varchar(50) NOT NULL,
  `department_name` varchar(50) NOT NULL,
  `plan_amount` int(11) DEFAULT NULL,
  `already_amount` int(11) DEFAULT NULL,
  `requirements` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`company_name`,`department_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('??????????,??????????', '???', '30', '10', '???');
INSERT INTO `department` VALUES ('wee', 'wee', '52', '33', 'wocao');
INSERT INTO `department` VALUES ('我', '是', '1', '1', '1');
INSERT INTO `department` VALUES ('江西理工大学', 'ss', '2', '2', 'aa');
INSERT INTO `department` VALUES ('软件开发科技有限公司', '前端部门', '21', '17', '身份');
INSERT INTO `department` VALUES ('软件开发科技有限公司', '后端开发', '50', '20', '精通JAVA语言、MYSQL等编程语言');
INSERT INTO `department` VALUES ('软件开发科技有限公司', '测试部', '55', '23', '好');
INSERT INTO `department` VALUES ('软件开发科技有限公司', '赵赵部门', '10', '10', '是人就行');
INSERT INTO `department` VALUES ('软件开发科技有限公司,软件开发科技有限公司', '前端部', '30', '10', '啥都会');
INSERT INTO `department` VALUES ('软件开发设计有限公司', 'wee啊啊', '5212', '3322', 'wocao');
INSERT INTO `department` VALUES ('软件开发设计有限公司', '全栈1', '6', '3', '技术过硬');
INSERT INTO `department` VALUES ('软件开发设计有限公司', '全栈2', '55', '6', '技术好');
INSERT INTO `department` VALUES ('软件开发设计有限公司', '全栈22', '5', '2', 'hao');
INSERT INTO `department` VALUES ('软件开发设计有限公司', '前端', '51', '44', '技术好');
INSERT INTO `department` VALUES ('软件开发设计有限公司', '后端1', '11', '2', '牛');
INSERT INTO `department` VALUES ('软件开发设计有限公司', '后端部门1', '55', '36', '牛逼');
INSERT INTO `department` VALUES ('软件开发设计有限公司', '运维', '55', '23', '牛');

-- ----------------------------
-- Table structure for interview
-- ----------------------------
DROP TABLE IF EXISTS `interview`;
CREATE TABLE `interview` (
  `id_number` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `graduate_school` varchar(20) DEFAULT NULL,
  `educational` varchar(20) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  `intention` varchar(20) DEFAULT NULL,
  `company` varchar(20) DEFAULT NULL,
  `department` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `evaluation` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of interview
-- ----------------------------
INSERT INTO `interview` VALUES ('123456', '鲁班七号', '广东工业大学', '本科', '土木工程', '建房子', '软件开发科技有限公司', '前端部门', '1', '面试结果非常好');
INSERT INTO `interview` VALUES ('123457', '百里守约', '军官大学', '专科', '射击类', '狙击枪营', '软件开发科技有限公司', '后台开发', '0', '还没有面试呢');
INSERT INTO `interview` VALUES ('123458', '王昭君', '蜜雪冰城学院', '专科', '冰雪工程', '冰雕营', '软件开发科技有限公司', '大部门', '1', '面试结果非常好');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id_number` varchar(20) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `participant` varchar(50) DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `summary` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('440527200104230001', '1', '吕子乔、诸葛大力、曾小贤', '2021-02-02', '0', '完成人才管理系统开发', '写的非常好');
INSERT INTO `project` VALUES ('440527200104230002', '2', '吕子乔、诸葛大力、曾小贤', '2021-02-02', '0', '完成人才管理系统开发', '写的非常好');
INSERT INTO `project` VALUES ('440527200104230003', '3', '吕子乔、诸葛大力、曾小贤', '2021-02-02', '0', '完成人才管理系统开发', '写的非常好');
INSERT INTO `project` VALUES ('440527200104230001', '6', '吕子乔、诸葛大力、曾小贤', '2021-11-10', '1', '开发一套系统', '系统开发，大家表现很棒');
INSERT INTO `project` VALUES ('130927', '8', '赵乾淳，刘焕德', '2021-11-10', '1', '完成人员管理系统的开发', '很好');
INSERT INTO `project` VALUES ('154613248346343', '9', '刘焕德、赵乾淳', '2021-11-01', '1', '完成人才管理系统开发', '好');
INSERT INTO `project` VALUES ('130927', '10', '41414', '2021-11-01', '1', '阿斯蒂芬', '啊发生的');

-- ----------------------------
-- Table structure for reward_punish
-- ----------------------------
DROP TABLE IF EXISTS `reward_punish`;
CREATE TABLE `reward_punish` (
  `id_number` varchar(20) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sign` int(11) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `reason` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reward_punish
-- ----------------------------
INSERT INTO `reward_punish` VALUES ('1', '-1', '1', '2021-03-02', 'sss');
INSERT INTO `reward_punish` VALUES ('440527200104230001', '1', '1', '2021-03-02', '为公司拉了一单业务，奖励一根棒棒糖');
INSERT INTO `reward_punish` VALUES ('440527200104230002', '2', '1', '2021-03-02', '为公司拉了一单业务，奖励一根棒棒糖');
INSERT INTO `reward_punish` VALUES ('440527200104230003', '3', '1', '2021-03-02', '为公司拉了一单业务，奖励一根棒棒糖');
INSERT INTO `reward_punish` VALUES ('440527200104230004', '4', '1', '2021-03-02', '为公司拉了一单业务，奖励一根棒棒糖');
INSERT INTO `reward_punish` VALUES ('440527200104230006', '6', '1', '2021-03-02', '为公司拉了一单业务，奖励一根棒棒糖');
INSERT INTO `reward_punish` VALUES ('440527200104230007', '7', '1', '2021-03-02', '为公司拉了一单业务，奖励一根棒棒糖');
INSERT INTO `reward_punish` VALUES ('440527200104230008', '8', '1', '2021-03-02', '为公司拉了一单业务，奖励一根棒棒糖');
INSERT INTO `reward_punish` VALUES ('440527200104230009', '9', '1', '2021-03-02', '为公司拉了一单业务，奖励一根棒棒糖');
INSERT INTO `reward_punish` VALUES ('440527200104230001', '10', '0', '2021-03-03', '迟到一次');
INSERT INTO `reward_punish` VALUES ('440527200104230003', '12', '0', '2021-03-03', '迟到一次');
INSERT INTO `reward_punish` VALUES ('440527200104230004', '13', '0', '2021-03-03', '迟到一次');
INSERT INTO `reward_punish` VALUES ('440527200104230005', '14', '0', '2021-03-03', '迟到一次');
INSERT INTO `reward_punish` VALUES ('440527200104230006', '15', '0', '2021-03-03', '迟到一次');
INSERT INTO `reward_punish` VALUES ('440527200104230007', '16', '0', '2021-03-03', '迟到一次');
INSERT INTO `reward_punish` VALUES ('440527200104230008', '17', '0', '2021-03-03', '迟到一次');
INSERT INTO `reward_punish` VALUES ('440527200104230009', '18', '0', '2021-03-03', '迟到一次');
INSERT INTO `reward_punish` VALUES ('1', '20', '1', '2020-01-01', 'yufgdf撒');
INSERT INTO `reward_punish` VALUES ('1', '21', '1', '2020-01-01', 'yufgdf撒');
INSERT INTO `reward_punish` VALUES ('1', '23', '1', '2020-01-01', 'aaa');
INSERT INTO `reward_punish` VALUES ('115234522452', '24', '1', '2021-11-14', 'fasffadf');
INSERT INTO `reward_punish` VALUES ('440527200104230009', '28', '0', '2021-03-04', '迟到一次');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `id_number` varchar(20) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `native_place` varchar(50) DEFAULT NULL,
  `educational` varchar(50) DEFAULT NULL,
  `graduate_school` varchar(50) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL,
  `department_name` varchar(50) DEFAULT NULL,
  `entry_time` date DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `employ` int(11) DEFAULT NULL,
  `technique` varchar(50) DEFAULT NULL,
  `evaluation` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('1', '1', '1111111', '1', '1', '1', '1', '1', '1', '1', '1', '2020-01-01', '1', '1', '1', '1');
INSERT INTO `staff` VALUES ('11', '1', '1', '男', '110', '55', 'dd', 'ds', 'D', 'D', 'F', '2020-01-01', '11000', '1', 'H', 'H');
INSERT INTO `staff` VALUES ('111', '1', '1', '男', '110', '55', 'dd', 'ds', 'D', 'D', 'F', '2020-01-01', '11000', '1', 'H', 'H');
INSERT INTO `staff` VALUES ('1111', '1', '1', '男', '110', '55', 'dd', 'ds', 'D', 'D', 'F', '2020-01-01', '11000', '1', 'H', 'H');
INSERT INTO `staff` VALUES ('11111', '1', '1', '男', '110', '55', 'dd', 'ds', 'D', 'D', 'F', '2020-01-01', '11000', '1', 'H', 'H');
INSERT INTO `staff` VALUES ('115234522452', '刘焕德', '222222', '男', '1102', '5511@qq.com', '江西', '大学', '江理', 'BD', '后端部门,后端部门', '2020-01-01', '2000000', '1', 'H1呃', 'H1轻资');
INSERT INTO `staff` VALUES ('122', '122', '122122', '男', '2', '3229@qq.com', '1', '1', '1', '1', '1', '2020-01-01', '1', '2', '1', '1');
INSERT INTO `staff` VALUES ('123', '23', '111111', '男', '2', '2', '2', '2', '2', '2', '2', '2002-01-01', '22', '2', '2', '2');
INSERT INTO `staff` VALUES ('130927', '赵乾淳', '1600141', '男', '181', '322@qq.com', '河北', '大学', '江理', '软件开发科技有限公司', '前端开发', '2021-11-01', '20000', '1', '前端', '技术好个pfdsa ');
INSERT INTO `staff` VALUES ('130927200201160014', '???', '160014', '?', '18172784481', '3229456898@qq.com', '???', '??', '??', '??', '??', '2022-12-12', '11000', '1', '?', '?');
INSERT INTO `staff` VALUES ('1759237582934', '王同旭', '123456', '半男不女', '252435234', '1341@qq.com', '山东', '大学', '江理', '软件开发科技有限公司', 'adsf', '2000-01-01', '22222', '0', 'adfa', 'adsfa');
INSERT INTO `staff` VALUES ('2523489750928375', '刘焕德', '135423', '男', '15634', '45341@qq.com', '江西', '大学', '江理', '软件开发科技有限公司', '后端开发', '2021-11-01', '20000', '0', '后端', '牛逼');
INSERT INTO `staff` VALUES ('32135', '1阿斯顿', '1223423', '男', '13831709976', '3229456898@qq.com', '章贡区江西理工大学红旗大道', '大学', '江理', '软件开发设计有限公司 ', 'we', '2021-09-11', '20000', '0', '编程', 'ok');
INSERT INTO `staff` VALUES ('440527200104230001', '吕子乔', '123456', '男', '123456789', '123456@qq.com', '广东深圳', '本科', '中山大学', '无业游行', '前端部', '2020-11-14', '2000000', '1', '撩妹', '评价');
INSERT INTO `staff` VALUES ('440527200104230002', '诸葛大力', '123456', '女', '123456789', '123456@qq.com', '广东广州', '硕士', '华南理工大学', '软件开发科技有限公司', '后端', '2020-02-02', '22222', '2', '发送', '发顺丰');
INSERT INTO `staff` VALUES ('440527200104230003', '曾小贤', '123456', '男', '123456789', '123456@qq.com', '广东湛江', '本科', '广东工业大学', '软件开发科技有限公司', '测试部', '2021-02-02', '15000', '1', '精通各种前端编程语言技巧', '自我感觉良好');
INSERT INTO `staff` VALUES ('440527200104230004', '张伟', '123456', '男', '123456789', '123456@qq.com', '广东深圳', '本科', '中山大学', '软件开发科技有限公司', '前端开发', '2021-02-02', '30000', '1', '精通各种前端编程语言技巧', '自我感觉良好');
INSERT INTO `staff` VALUES ('440527200104230005', '胡一菲', '123456', '女', '123456789', '123456@qq.com', '广东广州', '硕士', '华南理工大学', '软件开发科技有限公司', '后端开发', '2021-02-02', '40000', '1', '精通各种前端编程语言技巧', '自我感觉良好');
INSERT INTO `staff` VALUES ('440527200104230007', '悠悠', '123456', '女', '123456789', '123456@qq.com', '广东深圳', '本科', '中山大学', '软件开发科技有限公司', '前端开发', '2021-02-02', '30000', '1', '精通各种前端编程语言技巧', '自我感觉良好');
INSERT INTO `staff` VALUES ('440527200104230009', '小龙', '123456', '男', '123456789', '123456@qq.com', '广东湛江', '本科', '广东工业大学', '软件开发科技有限公司', '测试部', '2021-02-02', '15000', '1', '精通各种前端编程语言技巧', '自我感觉良好');
INSERT INTO `staff` VALUES ('440527200104230010', '小龙', '123456', '男', '123456789', '123456@qq.com', '广东湛江', null, null, null, null, null, '0', '0', null, '?');
INSERT INTO `staff` VALUES ('440527200104230012', '小龙', '123456', '男', '123456789', '123456@qq.com', '广东韶关', null, null, null, null, null, null, null, null, null);
INSERT INTO `staff` VALUES ('可以用中文把', '就这？', '1ed', '男', '110', '55', 'dd', 'ds', 'D', 'D', 'F', '2020-01-01', '11000', '1', 'H', 'H');

-- ----------------------------
-- Table structure for trial
-- ----------------------------
DROP TABLE IF EXISTS `trial`;
CREATE TABLE `trial` (
  `id_number` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `term` varchar(20) DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `company` varchar(20) DEFAULT NULL,
  `department` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `evaluation` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trial
-- ----------------------------
INSERT INTO `trial` VALUES ('123459', '庄周', '3个月', '2020-02-05', '2020-05-05', '划水师', '软件开发科技有限公司', '测试部', '1', '这鱼摸得非常好');
INSERT INTO `trial` VALUES ('123460', '妲己', '3个月', '2020-02-05', '2020-05-05', '划水师', '软件开发科技有限公司', '前端部', '0', '这鱼摸得非常好');
INSERT INTO `trial` VALUES ('123461', '兰陵王', '3个月', '2020-02-05', '2020-05-05', '划水师', '软件开发科技有限公司', '摸鱼部', '1', '这鱼摸得非常好');
