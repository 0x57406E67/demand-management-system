/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : itsys

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2017-01-05 21:06:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_anaylze`
-- ----------------------------
DROP TABLE IF EXISTS `t_anaylze`;
CREATE TABLE `t_anaylze` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opinion` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fk4` (`order_id`),
  CONSTRAINT `FK_fk4` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_anaylze
-- ----------------------------
INSERT INTO `t_anaylze` VALUES ('1', 'sdfdsfsdf', '3');
INSERT INTO `t_anaylze` VALUES ('2', '需求分析意见需求分析意见需求分析意见需求分析意见', '3');

-- ----------------------------
-- Table structure for `t_attach`
-- ----------------------------
DROP TABLE IF EXISTS `t_attach`;
CREATE TABLE `t_attach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attach_name` varchar(200) DEFAULT NULL,
  `server_name` varchar(200) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `upload_man` int(11) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `tag` varchar(20) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fk5` (`upload_man`),
  KEY `FK_fk2` (`order_id`),
  CONSTRAINT `FK_fk2` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`),
  CONSTRAINT `FK_fk5` FOREIGN KEY (`upload_man`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attach
-- ----------------------------
INSERT INTO `t_attach` VALUES ('1', 'sqlserver2008链接.txt', '20161225195228310.txt', '/upload', null, null, '7', null, '0');
INSERT INTO `t_attach` VALUES ('2', '陈丰5157101264.doc', '20161226100031201.doc', '/upload', null, null, '8', null, '0');
INSERT INTO `t_attach` VALUES ('3', '解决安装SQl_2008为SQL_Server代理服务提供的凭据无效.doc', '20161226103010654.doc', '/upload', null, null, null, null, '0');
INSERT INTO `t_attach` VALUES ('4', '实验2 E-R图设计与关系模式转换.doc', '20161226103125390.doc', '/upload', null, null, '9', null, '0');
INSERT INTO `t_attach` VALUES ('5', 'SQL server 2008 安装手册.doc', '20161226104559205.doc', '/upload', null, null, '11', null, '0');
INSERT INTO `t_attach` VALUES ('6', '实验2 E-R图设计与关系模式转换.doc', '20161227154041349.doc', '/upload', null, null, '16', null, '0');
INSERT INTO `t_attach` VALUES ('7', '04 表和数据完整性.ppt', '20161227161850215.ppt', '/upload', null, null, '17', null, '0');
INSERT INTO `t_attach` VALUES ('8', '陈丰5157101264.doc', '20161228163633686.doc', '/upload', null, null, '18', null, '0');
INSERT INTO `t_attach` VALUES ('9', '数据库.docx', '20161229104524771.docx', '/upload', null, null, '20', null, '0');
INSERT INTO `t_attach` VALUES ('10', '04 表和数据完整性.ppt', '20161229104607107.ppt', '/upload', null, null, '3', null, '1');
INSERT INTO `t_attach` VALUES ('11', 'A308-30-5157101264-陈丰.doc', '20161229104946128.doc', '/upload', null, null, '3', null, '1');
INSERT INTO `t_attach` VALUES ('12', '解决安装SQl_2008为SQL_Server代理服务提供的凭据无效.doc', '20161229105404089.doc', '/upload', null, null, '23', null, '0');
INSERT INTO `t_attach` VALUES ('13', '解决安装SQl_2008为SQL_Server代理服务提供的凭据无效.doc', '20161229105446698.doc', '/upload', null, null, '3', null, '1');
INSERT INTO `t_attach` VALUES ('14', 'sqlserver2008链接.txt', '20161229105712558.txt', '/upload', null, null, '3', null, '1');
INSERT INTO `t_attach` VALUES ('15', 'sqlserver2008链接.txt', '20161229105856516.txt', '/upload', null, null, '3', null, '1');
INSERT INTO `t_attach` VALUES ('16', '实验2 E-R图设计与关系模式转换.doc', '20161229113019022.doc', '/upload', null, null, '3', null, '1');
INSERT INTO `t_attach` VALUES ('17', '数据库复习-15级_36171.doc', '20161229113204602.doc', '/upload', null, null, '3', null, '1');
INSERT INTO `t_attach` VALUES ('18', '实验2 E-R图设计与关系模式转换.doc', '20161229114005243.doc', '/upload', null, null, '3', null, '1');
INSERT INTO `t_attach` VALUES ('19', '参考文献.txt', '20161229174800746.txt', '/upload', null, null, '25', null, '0');
INSERT INTO `t_attach` VALUES ('20', '陈丰个人简历.doc', '20161229174830446.doc', '/upload', null, null, '3', null, '1');
INSERT INTO `t_attach` VALUES ('21', '陈丰个人简历.doc', '20161229175333381.doc', '/upload', null, null, '3', null, '1');
INSERT INTO `t_attach` VALUES ('22', 'itsys.sql', '20170103011500971.sql', '/upload', null, null, null, null, '0');
INSERT INTO `t_attach` VALUES ('23', 'itsys.sql', '20170103011704118.sql', '/upload', null, null, '26', null, '0');
INSERT INTO `t_attach` VALUES ('32', '材料3_题目审批表.doc', '20170104205254940.doc', '/upload', null, null, '27', null, '0');
INSERT INTO `t_attach` VALUES ('33', '材料4_任务书.doc', '20170104225634586.doc', '/upload', null, null, null, null, '0');
INSERT INTO `t_attach` VALUES ('34', '材料8_成绩评定表（评阅老师）.doc', '20170104225643944.doc', '/upload', null, null, null, null, '0');
INSERT INTO `t_attach` VALUES ('35', '材料10_答辩成绩评定表.doc', '20170104225716541.doc', '/upload', null, null, null, null, '0');

-- ----------------------------
-- Table structure for `t_company`
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` int(11) NOT NULL,
  `company_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company
-- ----------------------------
INSERT INTO `t_company` VALUES ('1', '福建富士通');
INSERT INTO `t_company` VALUES ('2', '福建诺基亚');

-- ----------------------------
-- Table structure for `t_confirm`
-- ----------------------------
DROP TABLE IF EXISTS `t_confirm`;
CREATE TABLE `t_confirm` (
  `id` int(11) NOT NULL,
  `predict_time` datetime DEFAULT NULL,
  `pre_work` varchar(10) DEFAULT NULL,
  `init_work` varchar(10) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fk3` (`order_id`),
  CONSTRAINT `FK_fk3` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_confirm
-- ----------------------------

-- ----------------------------
-- Table structure for `t_depart`
-- ----------------------------
DROP TABLE IF EXISTS `t_depart`;
CREATE TABLE `t_depart` (
  `id` int(11) NOT NULL,
  `dept_name` varchar(200) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_depart
-- ----------------------------
INSERT INTO `t_depart` VALUES ('1', '市场部', '0');
INSERT INTO `t_depart` VALUES ('2', '客服部', '0');
INSERT INTO `t_depart` VALUES ('3', '战略部', '0');
INSERT INTO `t_depart` VALUES ('4', '市场部-营销部1', '1');
INSERT INTO `t_depart` VALUES ('5', '市场部-营销部2', '1');
INSERT INTO `t_depart` VALUES ('6', '客服部-部门1', '2');
INSERT INTO `t_depart` VALUES ('7', '客服部-部门2', '2');
INSERT INTO `t_depart` VALUES ('8', '客服部-部门3', '2');
INSERT INTO `t_depart` VALUES ('9', '战略部-综合部', '3');
INSERT INTO `t_depart` VALUES ('10', '战略部-策划部', '3');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_name` varchar(500) DEFAULT NULL,
  `sys_no` varchar(50) DEFAULT NULL COMMENT '系统编号',
  `order_scope` varchar(2) DEFAULT NULL COMMENT '需求大小',
  `oa_no` varchar(50) DEFAULT NULL COMMENT 'OA工单号',
  `sys_app` int(11) DEFAULT NULL COMMENT '归属系统',
  `order_dept` varchar(200) DEFAULT NULL COMMENT '需求部门/单位',
  `order_apply_man` varchar(200) DEFAULT NULL,
  `develop_man` varchar(200) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `order_detail` varchar(2000) DEFAULT NULL COMMENT '需求描述',
  `status` varchar(2) DEFAULT NULL,
  `order_create_man` int(11) DEFAULT NULL,
  `date` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fk1` (`company_id`),
  KEY `FK_fk6` (`order_create_man`),
  CONSTRAINT `FK_fk1` FOREIGN KEY (`company_id`) REFERENCES `t_company` (`id`),
  CONSTRAINT `FK_fk6` FOREIGN KEY (`order_create_man`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('2', '需求名称666', 'CMFJ20140606XQ0033', '2', 'OA工单号', '2', '需求部门', '需求提出人', '开发负责人', '1', ' 需求描述 需求描述 需求描述 需求描述', '0', null, null);
INSERT INTO `t_order` VALUES ('3', '需求名称444', 'CMFJ20140606XQ0033', '2', 'OA工单号', '2', '需求部门', '需求提出人', '开发负责人', '1', '需求描述需求描述需求描述需求描述', '2', '1', null);
INSERT INTO `t_order` VALUES ('4', '需求名称', 'CMFJ20140606XQ0033', '2', 'OA工单号4444', '2', '需求部门', ' 需求提出人', '开发负责人', '1', '执行厂商', '0', '1', null);
INSERT INTO `t_order` VALUES ('5', '需求名称', 'CMFJ20140606XQ0033', '2', 'OA工单号4444', '2', '需求部门', ' 需求提出人', '开发负责人', '1', '执行厂商', '2', '1', null);
INSERT INTO `t_order` VALUES ('6', '需求名称', 'CMFJ20140606XQ0033', '2', 'OA工单号', '2', '需求部门/单位', '需求提出人', '开发负责人', '1', ' 需求描述 需求描述 需求描述', '0', '1', null);
INSERT INTO `t_order` VALUES ('7', ' 需求名称', 'CMFJ20140606XQ0033', '2', 'OA工单号', '1', '需求部门', '需求提出人', '开发负责人', '2', '需求描述需求描述需求描述', '1', '1', null);
INSERT INTO `t_order` VALUES ('8', '需求名称', 'CMFJ20140606XQ0033', '2', 'OA工单号66666', '2', '需求部门', '需求提出人', '开发负责人', '1', '需求描述需求描述需求描述需求描述需求描述', '0', '1', null);
INSERT INTO `t_order` VALUES ('9', '需求名称', 'CMFJ20140606XQ0033', '2', ' OA工单号', '1', '需求部门', '张三', ' 开发负责人', '1', '需求描述需求描述需求描述需求描述', '1', '1', null);
INSERT INTO `t_order` VALUES ('10', 'QQ群', 'CMFJ20140606XQ0033', '1', '11111', '2', '222', '111', '33', '1', '444', '0', '1', null);
INSERT INTO `t_order` VALUES ('11', 'QQ群', 'CMFJ20140606XQ0033', '1', '11111', '2', '222', '111', '33', '2', '444', '0', '1', null);
INSERT INTO `t_order` VALUES ('12', '都是佛山', 'CMFJ20140606XQ0033', '2', '00000', '2', '777', '999', '看空间', '1', '你', '0', '1', null);
INSERT INTO `t_order` VALUES ('13', '都是佛山', 'CMFJ20140606XQ0033', '2', '00000', '2', '777', '999', '看空间', '1', '你', '0', '1', null);
INSERT INTO `t_order` VALUES ('14', '需求名称', 'CMFJ20140606XQ0033', '2', '222', '1', '3234', '3242', '3242', '1', '234234', '0', '1', null);
INSERT INTO `t_order` VALUES ('15', '需求名称', 'CMFJ20140606XQ0033', '2', '222', '1', '3234', '3242', '3242', '1', '234234', '1', '1', null);
INSERT INTO `t_order` VALUES ('16', '需求名称', 'CMFJ20140606XQ0033', '2', 'OA工单号', '1', '需求部门', '需求提出人', '开发负责人', '1', '需求描述', '1', '1', null);
INSERT INTO `t_order` VALUES ('17', ' 需求名称', 'CMFJ20140606XQ0033', '2', 'OA工单号', '2', '需求部门', '需求提出', '开发负责人', '2', ' 需求描述 需求描述 需求描述1111', '1', '1', null);
INSERT INTO `t_order` VALUES ('18', '需求名称', 'CMFJ20140606XQ0033', '2', ' OA工单号555', '1', '需求部门666', '需求提出人', '开发负责人33', '1', '需求描述需求描述需求描述需求描述需求描述', '1', '1', null);
INSERT INTO `t_order` VALUES ('19', '需求名称', 'CMFJ20140606XQ0033', '2', 'OA工单号333', '2', '需求部门', ' 需求提出人', '开发负责人', '1', '需求描述eeee', '0', '1', null);
INSERT INTO `t_order` VALUES ('20', '需求名称', 'CMFJ20140606XQ0033', '2', 'OA工单号333', '2', '需求部门', ' 需求提出人', '开发负责人', '1', '需求描述eeee', '1', '1', null);
INSERT INTO `t_order` VALUES ('21', '需求名称', 'CMFJ20140606XQ0033', '2', 'OA工单号333', '1', '需求部门需求部门ggg', '需求提出人', '开发负责人', '1', '需求描述需求描述ffff', '0', '1', null);
INSERT INTO `t_order` VALUES ('22', '需求名称', 'CMFJ20140606XQ0033', '2', 'OA工单号333', '1', '需求部门需求部门ggg', '需求提出人', '开发负责人', '2', '需求描述需求描述ffff', '0', '1', null);
INSERT INTO `t_order` VALUES ('23', '需求名称', 'CMFJ20140606XQ0033', '2', 'OA工单号333', '1', '需求部门需求部门ggg', '需求提出人', '开发负责人', '1', '需求描述需求描述ffff', '1', '1', null);
INSERT INTO `t_order` VALUES ('24', '需求名称hfhfghfg', 'CMFJ20140606XQ0033', '2', 'OA工单号', '2', '需求部门', '需求提出人', '开发负责人33', '1', '', '1', '1', null);
INSERT INTO `t_order` VALUES ('25', '需求名称44455', 'CMFJ20140606XQ0033', '2', 'OA工单号09', '1', '需求部门', '需求提出人333', '开发负责人', '2', '需求描述需求描述需求描述需求描述', '1', '1', null);
INSERT INTO `t_order` VALUES ('26', '需求名称需求名称6', 'CMFJ20140606XQ0033', '2', 'OA工单号77', '1', '需求部门77', '需求提出人55', '开发负责人88', '1', '需求描述需求描述需求描述', '1', '1', null);
INSERT INTO `t_order` VALUES ('27', 'dg', 'CMFJ20140606XQ0033', '1', 'fd', '1', 'f', 'fd', 'fg', '1', 'fgfgfgfgffggfgffg', '0', '1', null);
INSERT INTO `t_order` VALUES ('28', '', 'CMFJ20140606XQ0033', '1', '', '2', '', '', '', '1', '', '0', '1', null);

-- ----------------------------
-- Table structure for `t_resource`
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL,
  `res_name` varchar(100) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('1', '填写需求确认', null, null);

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '需求起草人');
INSERT INTO `t_role` VALUES ('2', '广商项目经理');
INSERT INTO `t_role` VALUES ('3', '开发负责人');
INSERT INTO `t_role` VALUES ('4', '需求管理员');

-- ----------------------------
-- Table structure for `t_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resource`;
CREATE TABLE `t_role_resource` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fk10` (`role_id`),
  KEY `FK_fk11` (`resource_id`),
  CONSTRAINT `FK_fk10` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_fk11` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) DEFAULT NULL,
  `login_password` varchar(64) DEFAULT NULL,
  `real_name` varchar(200) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fk7` (`dept_id`),
  CONSTRAINT `FK_fk7` FOREIGN KEY (`dept_id`) REFERENCES `t_depart` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'zhangsan', '123', '张三', '1');
INSERT INTO `t_user` VALUES ('2', 'lisi', '666', '李四', '2');
INSERT INTO `t_user` VALUES ('3', 'wangwu', '444', '王五', '3');
INSERT INTO `t_user` VALUES ('4', 'zhaoliu', '555', '赵六', '4');
INSERT INTO `t_user` VALUES ('5', 'zhangqi', '134', null, '5');
INSERT INTO `t_user` VALUES ('6', 'wangba', '246', '王八', '6');
INSERT INTO `t_user` VALUES ('7', 'linjiu', '654', '林九', '1');
INSERT INTO `t_user` VALUES ('8', 'chenshi', '345', '陈十', '2');
INSERT INTO `t_user` VALUES ('9', 'zhaoyun', '888', '赵云', '2');
INSERT INTO `t_user` VALUES ('10', 'admin', '123', null, null);

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fk9` (`role_id`),
  KEY `FK_fk8` (`user_id`),
  CONSTRAINT `FK_fk8` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_fk9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
