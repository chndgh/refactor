/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-06-23 14:25:27
*/

DROP TABLE IF EXISTS `t_proxy`;
CREATE TABLE `t_proxy` (
  `Id` int(5) NOT NULL AUTO_INCREMENT ,
  `ip` varchar(20) DEFAULT NULL ,
  `port` int(6) DEFAULT 80 ,
  `type` varchar(255) DEFAULT 'http' ,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
alter table t_proxy AUTO_INCREMENT = 1 ;



DROP TABLE IF EXISTS `t_proxy_verify`;
CREATE TABLE `t_proxy_verify` (
  `Id` int(6) NOT NULL AUTO_INCREMENT ,
  `ip` varchar(20) DEFAULT NULL ,
  `port` int(6) DEFAULT 80 ,
  `type` varchar(10) DEFAULT 'http' ,
  `pass` int(2) DEFAULT 0 ,
  `verify_time` varchar(20) DEFAULT NULL ,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
alter table t_proxy_verify AUTO_INCREMENT = 1 ;


DROP TABLE IF EXISTS `t_front_category`;
CREATE TABLE `t_front_category` (
  `Id` int(11) NOT NULL AUTO_INCREMENT ,
  `cateid` varchar(20) DEFAULT NULL ,
  `catename` varchar(40) DEFAULT NULL ,
  `level` int(2) DEFAULT 0 ,
  `parentcateid` varchar(20) DEFAULT NULL ,
  `updatetime` int(11) DEFAULT 0 ,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
alter table t_front_category AUTO_INCREMENT = 1 ;

CREATE INDEX index_t_front_category_cateid ON t_front_category (cateid);


DROP TABLE IF EXISTS `t_back_category`;
CREATE TABLE `t_back_category` (
  `Id` int(11) NOT NULL AUTO_INCREMENT ,
  `cateid` varchar(20) DEFAULT NULL ,
  `hangye` varchar(40) DEFAULT NULL ,
  `zihangye` varchar(40) DEFAULT NULL ,
  `level1` varchar(40) DEFAULT NULL ,
  `level2` varchar(40) DEFAULT NULL ,
  `level3` varchar(40) DEFAULT NULL ,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
alter table t_back_category AUTO_INCREMENT = 1 ;

CREATE INDEX index_t_back_category_cateid ON t_back_category (cateid);


drop table if EXISTS `t_search_category`;
create table `t_search_category`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `page` INT(6) DEFAULT NULL,
  `categoryID`  varchar(20) DEFAULT NULL,
  `totalCount` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
alter table t_search_category AUTO_INCREMENT = 1 ;

CREATE INDEX index_t_search_category_url ON t_search_category (url);
CREATE INDEX index_t_search_category_categoryID ON t_search_category (categoryID);
CREATE INDEX index_t_search_category_page ON t_search_category (page);
CREATE INDEX index_t_search_category_totalCount ON t_search_category (totalCount);


DROP TABLE IF EXISTS `t_search`;
CREATE TABLE `t_search` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `portraitURL` varchar(255)   DEFAULT NULL,
  `title` varchar(255)  DEFAULT NULL,
  `price` varchar(255)  DEFAULT NULL,
  `owner` varchar(255)  DEFAULT NULL,
  `deliverFromProvince` varchar(20)  DEFAULT NULL,
  `deliverFromCity` varchar(20)  DEFAULT NULL,
  `customerCount` INTEGER(10)  DEFAULT 0,
  `commentCount` INTEGER(10)  DEFAULT 0,
  `ownerType` INTEGER(10)  DEFAULT NULL,
  `duliang` varchar(20)  DEFAULT NULL,
  `goodsLink` varchar(255)  DEFAULT NULL,
  `goodsId` varchar(20)  DEFAULT NULL,
  `categoryID`  varchar(20) DEFAULT NULL,
  `sellerCredit` varchar(20) DEFAULT NULL ,
  `totalRate`  varchar(20) DEFAULT NULL ,
  `baseSearch` INT(2) DEFAULT NULL,
  `BackCategoryId` varchar(255) DEFAULT NULL ,
  --  alter table t_search add `BackCategoryId` varchar(255) DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  ;
alter table t_search AUTO_INCREMENT = 1 ;

CREATE INDEX index_t_search_categoryID ON t_search (categoryID);
CREATE INDEX index_t_search_goodsId ON t_search (goodsId);
CREATE INDEX index_t_search_baseSearch ON t_search (baseSearch);
CREATE INDEX index_t_search_commentCount ON t_search (commentCount);
CREATE INDEX index_t_search_customerCount ON t_search (customerCount);
CREATE INDEX index_t_search_ownerType ON t_search (ownerType);
CREATE INDEX index_t_search_BackCategoryId ON t_search (BackCategoryId);

DROP TABLE IF EXISTS `t_search_shop`;
CREATE TABLE `t_search_shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shopid` bigint(20)  DEFAULT NULL,
  `shopuserid` bigint(20)  DEFAULT NULL,
  `shopurl` varchar(255)  DEFAULT NULL,
  `zhangguiid` bigint(20)  DEFAULT NULL,
  `zhangguiname` varchar(255)  DEFAULT NULL,
  `zhangguiurl` varchar(255)  DEFAULT NULL,
  `location`  varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  ;
alter table t_search_shop AUTO_INCREMENT = 1 ;
CREATE INDEX index_t_search_shop_shopid ON t_search_shop (shopid);

DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `zhanggui` varchar(255) DEFAULT NULL ,
  `categoryId` varchar(255) DEFAULT NULL ,
  `brand` varchar(30) DEFAULT NULL ,
  `goodsTitle` varchar(100) DEFAULT NULL ,
  `stock` varchar(20) DEFAULT NULL ,
  `price` varchar(20) DEFAULT NULL ,
  `promotionPrice` varchar(20) DEFAULT NULL ,
  `salesVolume` varchar(20) DEFAULT NULL ,
  `confirmGoodsCount` varchar(20) DEFAULT NULL ,
  `comentCount` varchar(20) DEFAULT NULL ,
  `deliveryFromProvince` varchar(20) DEFAULT NULL ,
  `deliveryFromCity` varchar(20) DEFAULT NULL ,
  `bookmarks` varchar(20) DEFAULT NULL ,
  `shopType` INTEGER(10) DEFAULT NULL ,
  `shopLength` varchar(20) DEFAULT NULL ,
  `shopName` varchar(50) DEFAULT NULL ,
  `goodsId` varchar(20) DEFAULT NULL ,
  `spuId` varchar(20) DEFAULT NULL ,
  `sellerId` varchar(20) DEFAULT NULL ,
  `shopId` varchar(20) DEFAULT NULL ,
  `encryptSellerId` VARCHAR(20) DEFAULT NULL ,
  `apiBeans` varchar(255) DEFAULT NULL ,
  `initApi` varchar(500) DEFAULT NULL ,
  `shopRate` varchar(500) DEFAULT NULL ,
  `shuxing` varchar(2000) DEFAULT NULL ,
  `zizhi` varchar(255) DEFAULT NULL ,
  `sellerCredit` varchar(20) DEFAULT NULL ,
  `totalRate`  varchar(20) DEFAULT NULL ,
  `dataRateUrl` VARCHAR(500) DEFAULT NULL ,
  `rateCounterApi` varchar(500) DEFAULT NULL ,
  `yinxiang` varchar(200) DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
alter table t_product AUTO_INCREMENT = 1 ;

CREATE INDEX index_t_product_goodsId ON t_product (goodsId);
CREATE INDEX index_t_product_categoryId ON t_product (categoryId);
CREATE INDEX index_t_product_bookmarks on t_product (bookmarks) ;
CREATE INDEX index_t_product_apiBeans on t_product (apiBeans) ;
CREATE INDEX index_t_product_confirmGoodsCount on t_product (confirmGoodsCount) ;
CREATE INDEX index_t_product_stock on t_product (stock) ;
CREATE INDEX index_t_product_initApi on t_product (initApi) ;


DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL ,
  `zhanggui` varchar(255) DEFAULT NULL ,
  `xingyu` varchar(255) DEFAULT NULL ,
  `shoptype` varchar(40) DEFAULT NULL ,
  `shopcreatetime` varchar(40) DEFAULT NULL ,
  `dsr` varchar(255) DEFAULT NULL ,
  `goodcount` varchar(40) DEFAULT NULL ,
  `pingrate` varchar(20) DEFAULT NULL ,
  `guanzhu` varchar(40) DEFAULT NULL ,
  `shopid` bigint(20) DEFAULT NULL ,
  `location` varchar(20) DEFAULT NULL ,
  `fuwuchengnuo` varchar(255) DEFAULT NULL ,
  `payway` varchar(255) DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
alter table t_shop AUTO_INCREMENT = 1 ;

CREATE INDEX index_t_shop_shopid ON t_shop (shopid);



DROP TABLE IF EXISTS `t_systemlog`;
CREATE TABLE `t_systemlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taskerName` varchar(255)   DEFAULT NULL,
  `taskerSeries` varchar(30)  DEFAULT NULL,
  `taskerOwner` varchar(30)  DEFAULT NULL,
  `runHost` varchar(10)  DEFAULT NULL,
  `url` varchar(500)  DEFAULT NULL,
  `result` int(1) DEFAULT 0 ,
  `errMessage` varchar(20000)  DEFAULT NULL,
  `runtime` varchar(255)  DEFAULT NULL,
  `proxy` varchar(30)  DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  ;
alter table t_systemlog AUTO_INCREMENT = 1 ;

CREATE INDEX index_systemlog_taskerName ON t_systemlog (taskerName);
CREATE INDEX index_systemlog_taskerSeries ON t_systemlog (taskerSeries);
CREATE INDEX index_systemlog_taskerOwner ON t_systemlog (taskerOwner);
CREATE INDEX index_systemlog_result ON t_systemlog (result);
