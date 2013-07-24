-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.43-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema easyorder
--

CREATE DATABASE IF NOT EXISTS easyorder;
USE easyorder;

--
-- Temporary table structure for view `v_menu`
--
DROP TABLE IF EXISTS `v_menu`;
DROP VIEW IF EXISTS `v_menu`;
CREATE TABLE `v_menu` (
  `tb_menu_id` bigint(20) unsigned,
  `tb_menu_name` varchar(128),
  `tb_menu_price` float,
  `tb_menu_menuclassify_id` bigint(20) unsigned,
  `tb_menu_merchant_id` bigint(20) unsigned,
  `tb_menu_description` text,
  `tb_menu_stars` float,
  `tb_menu_vote` bigint(20) unsigned,
  `tb_menu_picdir` varchar(128),
  `tb_menuclassify_id` bigint(20) unsigned,
  `tb_menuclassify_name` varchar(128),
  `tb_merchant_id` bigint(20) unsigned,
  `tb_merchant_name` varchar(128),
  `tb_merchant_address` varchar(256),
  `tb_merchant_telephone` varchar(128),
  `tb_merchant_stars` float,
  `tb_merchant_vote` bigint(20) unsigned
);

--
-- Temporary table structure for view `v_order`
--
DROP TABLE IF EXISTS `v_order`;
DROP VIEW IF EXISTS `v_order`;
CREATE TABLE `v_order` (
  `tb_user_id` bigint(20) unsigned,
  `tb_user_username` varchar(45),
  `tb_user_password` varchar(45),
  `tb_user_telephone` varchar(20),
  `tb_user_name` varchar(45),
  `tb_user_sex` tinyint(3) unsigned,
  `tb_user_level` tinyint(3) unsigned,
  `tb_order_id` bigint(20) unsigned,
  `tb_order_user_id` bigint(20) unsigned,
  `tb_order_menu_id` bigint(20) unsigned,
  `tb_order_time` varchar(128),
  `tb_order_method` tinyint(3) unsigned,
  `tb_order_count` int(10) unsigned,
  `tb_menu_id` bigint(20) unsigned,
  `tb_menu_name` varchar(128),
  `tb_menu_price` float,
  `tb_menu_menuclassify_id` bigint(20) unsigned,
  `tb_menu_merchant_id` bigint(20) unsigned,
  `tb_menu_description` text,
  `tb_menu_stars` float,
  `tb_menu_vote` bigint(20) unsigned,
  `tb_menu_picdir` varchar(128),
  `tb_menuclassify_id` bigint(20) unsigned,
  `tb_menuclassify_name` varchar(128),
  `tb_merchant_id` bigint(20) unsigned,
  `tb_merchant_name` varchar(128),
  `tb_merchant_address` varchar(256),
  `tb_merchant_telephone` varchar(128),
  `tb_merchant_stars` float,
  `tb_merchant_vote` bigint(20) unsigned
);

--
-- Temporary table structure for view `v_recommendation`
--
DROP TABLE IF EXISTS `v_recommendation`;
DROP VIEW IF EXISTS `v_recommendation`;
CREATE TABLE `v_recommendation` (
  `tb_menu_id` bigint(20) unsigned,
  `tb_menu_name` varchar(128),
  `tb_menu_price` float,
  `tb_menu_menuclassify_id` bigint(20) unsigned,
  `tb_menu_merchant_id` bigint(20) unsigned,
  `tb_menu_description` text,
  `tb_menu_stars` float,
  `tb_menu_vote` bigint(20) unsigned,
  `tb_menu_picdir` varchar(128),
  `tb_menuclassify_id` bigint(20) unsigned,
  `tb_menuclassify_name` varchar(128),
  `tb_merchant_id` bigint(20) unsigned,
  `tb_merchant_name` varchar(128),
  `tb_merchant_address` varchar(256),
  `tb_merchant_telephone` varchar(128),
  `tb_merchant_stars` float,
  `tb_merchant_vote` bigint(20) unsigned,
  `tb_recommendation_id` bigint(20) unsigned,
  `tb_recommendation_menu_id` bigint(20) unsigned
);

--
-- Definition of table `tb_menu`
--

DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `tb_menu_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tb_menu_name` varchar(128) NOT NULL,
  `tb_menu_price` float NOT NULL,
  `tb_menu_menuclassify_id` bigint(20) unsigned NOT NULL,
  `tb_menu_merchant_id` bigint(20) unsigned NOT NULL,
  `tb_menu_description` text NOT NULL,
  `tb_menu_stars` float NOT NULL,
  `tb_menu_vote` bigint(20) unsigned NOT NULL,
  `tb_menu_picdir` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`tb_menu_id`),
  KEY `FK_MENU_MERCHANT` (`tb_menu_merchant_id`),
  KEY `FK_MENU_MENUCLASSIFY` (`tb_menu_menuclassify_id`),
  CONSTRAINT `FK_MENU_MENUCLASSIFY` FOREIGN KEY (`tb_menu_menuclassify_id`) REFERENCES `tb_menuclassify` (`tb_menuclassify_id`),
  CONSTRAINT `FK_MENU_MERCHANT` FOREIGN KEY (`tb_menu_merchant_id`) REFERENCES `tb_merchant` (`tb_merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_menu`
--

/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
INSERT INTO `tb_menu` (`tb_menu_id`,`tb_menu_name`,`tb_menu_price`,`tb_menu_menuclassify_id`,`tb_menu_merchant_id`,`tb_menu_description`,`tb_menu_stars`,`tb_menu_vote`,`tb_menu_picdir`) VALUES 
 (1,'水煮肉片',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (2,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (3,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (4,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (5,'水煮肉片',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (6,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (7,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (8,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (9,'水煮肉片11',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (10,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (11,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (12,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (13,'水煮肉片222',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (14,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (15,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (16,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (17,'水煮肉片33',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (18,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (19,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (20,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (21,'水煮肉片44',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (22,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (23,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (24,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (25,'水煮肉片55',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (26,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (27,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (28,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (29,'水煮肉片66',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (30,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (31,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (32,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (33,'水煮肉片77',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (34,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (35,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (36,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (37,'水煮肉片88',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (38,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (39,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (40,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (41,'水煮肉片99',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (42,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (43,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (44,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (45,'水煮肉片22222',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (46,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (47,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (48,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (49,'水煮肉片',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (50,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (51,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (52,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (53,'水煮肉片',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (54,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (55,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (56,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (57,'水煮肉片',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (58,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (59,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (60,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (61,'水煮肉片',13.5,1,1,'好吃的水煮肉片呀.',5,0,'1'),
 (62,'水煮焦明鑫',0.5,2,1,'焦明鑫乃千年唐僧肉 吃了可以长高哦!!!',5,0,'2'),
 (63,'葱蒜拌鸡蛋',5,3,3,'一道下饭凉拌菜,嫩葱香,鲜蒜香,鸡蛋香还有陈醋那醇厚的口感',5,0,'11'),
 (64,'蜜汁苦瓜',7,3,3,'入口微甜,',5,0,'12'),
 (65,'adf',13.4,1,1,'ad',2,0,NULL),
 (66,'阿道夫',1,1,1,'按时地方',2,0,NULL),
 (67,'焦明鑫',12,3,1,'萨法是否',2,0,NULL),
 (68,'水煮焦明鑫2',12.4,4,1,',,,,,',2,0,NULL),
 (69,'adf',12,1,3,'sdf',2,0,NULL);
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;


--
-- Definition of table `tb_menuclassify`
--

DROP TABLE IF EXISTS `tb_menuclassify`;
CREATE TABLE `tb_menuclassify` (
  `tb_menuclassify_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tb_menuclassify_name` varchar(128) NOT NULL,
  PRIMARY KEY (`tb_menuclassify_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_menuclassify`
--

/*!40000 ALTER TABLE `tb_menuclassify` DISABLE KEYS */;
INSERT INTO `tb_menuclassify` (`tb_menuclassify_id`,`tb_menuclassify_name`) VALUES 
 (1,'川菜'),
 (2,'粤菜'),
 (3,'凉菜'),
 (4,'热菜'),
 (5,'粥汤');
/*!40000 ALTER TABLE `tb_menuclassify` ENABLE KEYS */;


--
-- Definition of table `tb_merchant`
--

DROP TABLE IF EXISTS `tb_merchant`;
CREATE TABLE `tb_merchant` (
  `tb_merchant_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tb_merchant_name` varchar(128) NOT NULL,
  `tb_merchant_address` varchar(256) NOT NULL,
  `tb_merchant_telephone` varchar(128) NOT NULL,
  `tb_merchant_stars` float NOT NULL DEFAULT '5',
  `tb_merchant_vote` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`tb_merchant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_merchant`
--

/*!40000 ALTER TABLE `tb_merchant` DISABLE KEYS */;
INSERT INTO `tb_merchant` (`tb_merchant_id`,`tb_merchant_name`,`tb_merchant_address`,`tb_merchant_telephone`,`tb_merchant_stars`,`tb_merchant_vote`) VALUES 
 (1,'焦明鑫饭店','成都信息工程学院16栋','66666666',5,0),
 (2,'Fight','unkown','15208003000',5,0),
 (3,'简餐','西北街46号','65595666',5,0),
 (4,'吃湘喝辣','西二街78号','478994561',5,0);
/*!40000 ALTER TABLE `tb_merchant` ENABLE KEYS */;


--
-- Definition of table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `tb_order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tb_order_user_id` bigint(20) unsigned NOT NULL,
  `tb_order_menu_id` bigint(20) unsigned NOT NULL,
  `tb_order_time` varchar(128) NOT NULL,
  `tb_order_method` tinyint(3) unsigned NOT NULL,
  `tb_order_count` int(10) unsigned NOT NULL,
  PRIMARY KEY (`tb_order_id`),
  KEY `FK_ORDER_MENU` (`tb_order_menu_id`),
  KEY `FK_ORDER_USER` (`tb_order_user_id`),
  CONSTRAINT `FK_ORDER_MENU` FOREIGN KEY (`tb_order_menu_id`) REFERENCES `tb_menu` (`tb_menu_id`),
  CONSTRAINT `FK_ORDER_USER` FOREIGN KEY (`tb_order_user_id`) REFERENCES `tb_user` (`tb_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_order`
--

/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` (`tb_order_id`,`tb_order_user_id`,`tb_order_menu_id`,`tb_order_time`,`tb_order_method`,`tb_order_count`) VALUES 
 (1,1,1,'2012-06-02 3:22:43',1,5),
 (2,1,1,'2012-06-02 3:26:24',1,5),
 (3,1,1,'2012-06-04 11:07:44',1,5),
 (4,1,1,'2012-06-06 14:57:29',1,5),
 (5,1,1,'2012-06-07 6:08:52',1,5),
 (6,1,1,'2012-06-07 6:35:12',1,5),
 (7,1,1,'2012-06-10 16:09:47',1,5),
 (8,1,1,'2012-06-12 17:14:56',1,5),
 (9,10,1,'2012-06-14 8:10:28',1,2),
 (10,12,1,'2012-06-14 8:27:21',1,1),
 (11,12,2,'2012-06-14 8:27:21',1,3),
 (12,1,1,'2012-06-14 17:33:57',1,5),
 (13,1,1,'2012-06-17 2:29:24',1,5),
 (14,13,1,'2012-06-17 3:21:15',1,2),
 (15,13,2,'2012-06-17 3:21:40',1,5),
 (16,13,3,'2012-06-17 3:36:23',1,2),
 (17,13,4,'2012-06-17 3:36:23',1,1),
 (18,13,1,'2012-06-17 3:57:32',1,2),
 (19,13,2,'2012-06-17 3:57:32',1,2),
 (20,13,1,'2012-06-17 4:03:09',1,1),
 (21,13,2,'2012-06-17 4:03:09',1,1),
 (22,13,1,'2012-06-17 4:04:38',1,1),
 (23,13,2,'2012-06-17 4:04:39',1,1),
 (24,13,1,'2012-06-17 4:06:16',1,1),
 (25,13,2,'2012-06-17 4:06:16',1,3),
 (26,13,2,'2012-06-17 4:08:50',1,2),
 (27,14,1,'2012-06-17 6:27:12',1,1),
 (28,14,2,'2012-06-17 6:27:12',1,1),
 (29,14,8,'2012-06-17 6:27:34',1,1),
 (30,14,11,'2012-06-17 6:27:34',1,2),
 (31,14,5,'2012-06-17 6:52:55',1,1),
 (32,14,9,'2012-06-17 6:52:57',1,1),
 (33,14,1,'2012-06-17 7:41:22',1,2),
 (34,14,2,'2012-06-17 7:41:22',1,2),
 (35,14,1,'2012-06-17 7:55:59',1,1),
 (36,14,2,'2012-06-17 7:56:00',1,2),
 (37,14,2,'2012-06-17 8:38:55',1,1),
 (38,14,6,'2012-06-17 8:38:56',1,1),
 (39,14,1,'2012-06-17 9:04:28',1,3),
 (40,14,2,'2012-06-17 9:04:28',1,2),
 (41,14,2,'2012-06-17 9:14:16',1,1),
 (42,15,1,'2012-06-17 9:45:46',1,1),
 (43,15,2,'2012-06-17 9:45:46',1,2),
 (44,15,3,'2012-06-17 9:48:50',1,1),
 (45,15,4,'2012-06-17 9:48:50',1,1),
 (46,15,7,'2012-06-17 9:48:51',1,1),
 (47,15,8,'2012-06-17 9:48:51',1,4),
 (48,15,1,'2012-06-17 10:06:27',1,1),
 (49,15,2,'2012-06-17 10:06:27',1,1),
 (50,15,5,'2012-06-17 10:23:15',1,1),
 (51,15,9,'2012-06-17 10:23:15',1,1),
 (52,15,2,'2012-06-17 10:28:12',1,0),
 (53,16,2,'2012-06-18 1:27:50',1,2),
 (54,16,26,'2012-06-18 2:06:00',1,2),
 (55,16,34,'2012-06-18 2:06:02',1,1),
 (56,16,67,'2012-06-18 2:23:18',1,2),
 (57,17,67,'2012-06-18 2:25:52',1,1),
 (58,18,1,'2012-06-18 2:31:58',1,1),
 (59,18,2,'2012-06-18 2:31:58',1,1),
 (60,19,1,'2012-06-18 2:44:30',1,3),
 (61,19,2,'2012-06-18 2:44:32',1,2),
 (62,19,3,'2012-06-18 2:46:30',1,0),
 (63,19,4,'2012-06-18 2:46:32',1,0),
 (64,19,7,'2012-06-18 2:46:34',1,2),
 (65,19,8,'2012-06-18 2:46:35',0,2),
 (66,19,2,'2012-06-18 2:49:16',1,7);
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;


--
-- Definition of table `tb_recommendation`
--

DROP TABLE IF EXISTS `tb_recommendation`;
CREATE TABLE `tb_recommendation` (
  `tb_recommendation_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tb_recommendation_menu_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`tb_recommendation_id`),
  KEY `FK_RECOMMENDATION_MENU` (`tb_recommendation_menu_id`),
  CONSTRAINT `FK_RECOMMENDATION_MENU` FOREIGN KEY (`tb_recommendation_menu_id`) REFERENCES `tb_menu` (`tb_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_recommendation`
--

/*!40000 ALTER TABLE `tb_recommendation` DISABLE KEYS */;
INSERT INTO `tb_recommendation` (`tb_recommendation_id`,`tb_recommendation_menu_id`) VALUES 
 (1,1),
 (2,2);
/*!40000 ALTER TABLE `tb_recommendation` ENABLE KEYS */;


--
-- Definition of table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `tb_user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tb_user_username` varchar(45) NOT NULL,
  `tb_user_password` varchar(45) NOT NULL,
  `tb_user_telephone` varchar(20) NOT NULL,
  `tb_user_name` varchar(45) NOT NULL,
  `tb_user_sex` tinyint(3) unsigned NOT NULL,
  `tb_user_level` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`tb_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tb_user`
--

/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` (`tb_user_id`,`tb_user_username`,`tb_user_password`,`tb_user_telephone`,`tb_user_name`,`tb_user_sex`,`tb_user_level`) VALUES 
 (1,'xxxziheng','rjgc2009','15208205203','ZANE',1,10),
 (2,'jiao','jjj','15000000000','焦明鑫',0,0),
 (3,'rrr','11','2222222222','undefined',1,0),
 (4,'ddd','22','2222','undefined',0,0),
 (5,'eee','2','11','undefined',0,0),
 (6,'eee1','1','66','undefined',0,0),
 (7,'eee14','11','661111','undefined',0,0),
 (8,'44','1','11','undefined',0,0),
 (9,'441','1','11','undefined',0,0),
 (10,'ee','1','11','undefined',0,0),
 (11,'123','1','11','undefined',0,0),
 (12,'121','11','11223344','undefined',0,0),
 (13,'121may','1','123','undefined',0,0),
 (14,'332','1','112233','undefined',0,0),
 (15,'dminter','11','15196615493','undefined',0,0),
 (16,'qq','1','111','undefined',0,0),
 (17,'1213','1','1111','undefined',0,0),
 (18,'ww','qq','22','undefined',0,0),
 (19,'er','1','555','undefined',0,0);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;


--
-- Definition of view `v_menu`
--

DROP TABLE IF EXISTS `v_menu`;
DROP VIEW IF EXISTS `v_menu`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_menu` AS select `tb_menu`.`tb_menu_id` AS `tb_menu_id`,`tb_menu`.`tb_menu_name` AS `tb_menu_name`,`tb_menu`.`tb_menu_price` AS `tb_menu_price`,`tb_menu`.`tb_menu_menuclassify_id` AS `tb_menu_menuclassify_id`,`tb_menu`.`tb_menu_merchant_id` AS `tb_menu_merchant_id`,`tb_menu`.`tb_menu_description` AS `tb_menu_description`,`tb_menu`.`tb_menu_stars` AS `tb_menu_stars`,`tb_menu`.`tb_menu_vote` AS `tb_menu_vote`,`tb_menu`.`tb_menu_picdir` AS `tb_menu_picdir`,`tb_menuclassify`.`tb_menuclassify_id` AS `tb_menuclassify_id`,`tb_menuclassify`.`tb_menuclassify_name` AS `tb_menuclassify_name`,`tb_merchant`.`tb_merchant_id` AS `tb_merchant_id`,`tb_merchant`.`tb_merchant_name` AS `tb_merchant_name`,`tb_merchant`.`tb_merchant_address` AS `tb_merchant_address`,`tb_merchant`.`tb_merchant_telephone` AS `tb_merchant_telephone`,`tb_merchant`.`tb_merchant_stars` AS `tb_merchant_stars`,`tb_merchant`.`tb_merchant_vote` AS `tb_merchant_vote` from ((`tb_menu` join `tb_menuclassify`) join `tb_merchant`) where ((`tb_menu`.`tb_menu_menuclassify_id` = `tb_menuclassify`.`tb_menuclassify_id`) and (`tb_menu`.`tb_menu_merchant_id` = `tb_merchant`.`tb_merchant_id`));

--
-- Definition of view `v_order`
--

DROP TABLE IF EXISTS `v_order`;
DROP VIEW IF EXISTS `v_order`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_order` AS select `tb_user`.`tb_user_id` AS `tb_user_id`,`tb_user`.`tb_user_username` AS `tb_user_username`,`tb_user`.`tb_user_password` AS `tb_user_password`,`tb_user`.`tb_user_telephone` AS `tb_user_telephone`,`tb_user`.`tb_user_name` AS `tb_user_name`,`tb_user`.`tb_user_sex` AS `tb_user_sex`,`tb_user`.`tb_user_level` AS `tb_user_level`,`tb_order`.`tb_order_id` AS `tb_order_id`,`tb_order`.`tb_order_user_id` AS `tb_order_user_id`,`tb_order`.`tb_order_menu_id` AS `tb_order_menu_id`,`tb_order`.`tb_order_time` AS `tb_order_time`,`tb_order`.`tb_order_method` AS `tb_order_method`,`tb_order`.`tb_order_count` AS `tb_order_count`,`v_menu`.`tb_menu_id` AS `tb_menu_id`,`v_menu`.`tb_menu_name` AS `tb_menu_name`,`v_menu`.`tb_menu_price` AS `tb_menu_price`,`v_menu`.`tb_menu_menuclassify_id` AS `tb_menu_menuclassify_id`,`v_menu`.`tb_menu_merchant_id` AS `tb_menu_merchant_id`,`v_menu`.`tb_menu_description` AS `tb_menu_description`,`v_menu`.`tb_menu_stars` AS `tb_menu_stars`,`v_menu`.`tb_menu_vote` AS `tb_menu_vote`,`v_menu`.`tb_menu_picdir` AS `tb_menu_picdir`,`v_menu`.`tb_menuclassify_id` AS `tb_menuclassify_id`,`v_menu`.`tb_menuclassify_name` AS `tb_menuclassify_name`,`v_menu`.`tb_merchant_id` AS `tb_merchant_id`,`v_menu`.`tb_merchant_name` AS `tb_merchant_name`,`v_menu`.`tb_merchant_address` AS `tb_merchant_address`,`v_menu`.`tb_merchant_telephone` AS `tb_merchant_telephone`,`v_menu`.`tb_merchant_stars` AS `tb_merchant_stars`,`v_menu`.`tb_merchant_vote` AS `tb_merchant_vote` from ((`tb_user` join `tb_order`) join `v_menu`) where ((`tb_order`.`tb_order_user_id` = `tb_user`.`tb_user_id`) and (`tb_order`.`tb_order_menu_id` = `v_menu`.`tb_menu_id`));

--
-- Definition of view `v_recommendation`
--

DROP TABLE IF EXISTS `v_recommendation`;
DROP VIEW IF EXISTS `v_recommendation`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_recommendation` AS select `tb_menu`.`tb_menu_id` AS `tb_menu_id`,`tb_menu`.`tb_menu_name` AS `tb_menu_name`,`tb_menu`.`tb_menu_price` AS `tb_menu_price`,`tb_menu`.`tb_menu_menuclassify_id` AS `tb_menu_menuclassify_id`,`tb_menu`.`tb_menu_merchant_id` AS `tb_menu_merchant_id`,`tb_menu`.`tb_menu_description` AS `tb_menu_description`,`tb_menu`.`tb_menu_stars` AS `tb_menu_stars`,`tb_menu`.`tb_menu_vote` AS `tb_menu_vote`,`tb_menu`.`tb_menu_picdir` AS `tb_menu_picdir`,`tb_menuclassify`.`tb_menuclassify_id` AS `tb_menuclassify_id`,`tb_menuclassify`.`tb_menuclassify_name` AS `tb_menuclassify_name`,`tb_merchant`.`tb_merchant_id` AS `tb_merchant_id`,`tb_merchant`.`tb_merchant_name` AS `tb_merchant_name`,`tb_merchant`.`tb_merchant_address` AS `tb_merchant_address`,`tb_merchant`.`tb_merchant_telephone` AS `tb_merchant_telephone`,`tb_merchant`.`tb_merchant_stars` AS `tb_merchant_stars`,`tb_merchant`.`tb_merchant_vote` AS `tb_merchant_vote`,`tb_recommendation`.`tb_recommendation_id` AS `tb_recommendation_id`,`tb_recommendation`.`tb_recommendation_menu_id` AS `tb_recommendation_menu_id` from (((`tb_menu` join `tb_menuclassify`) join `tb_merchant`) join `tb_recommendation`) where ((`tb_recommendation`.`tb_recommendation_menu_id` = `tb_menu`.`tb_menu_id`) and (`tb_menu`.`tb_menu_menuclassify_id` = `tb_menuclassify`.`tb_menuclassify_id`) and (`tb_menu`.`tb_menu_merchant_id` = `tb_merchant`.`tb_merchant_id`));



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
