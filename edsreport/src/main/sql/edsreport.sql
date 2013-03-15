/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50516
Source Host           : localhost:3306
Source Database       : edsreport

Target Server Type    : MYSQL
Target Server Version : 50516
File Encoding         : 65001

Date: 2012-11-18 23:13:25
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `soil_monitor_constant`
-- ----------------------------
DROP TABLE IF EXISTS `soil_monitor_constant`;
CREATE TABLE `soil_monitor_constant` (
  `stationId` varchar(10) NOT NULL,
  `stationName` varchar(100) DEFAULT NULL,
  `operationTime` date DEFAULT NULL,
  `boxWeight1_10` float(10,2) DEFAULT NULL,
  `boxWeight1_20` float(10,2) DEFAULT NULL,
  `boxWeight1_30` float(10,2) DEFAULT NULL,
  `boxWeight1_40` float(10,2) DEFAULT NULL,
  `boxWeight1_50` float(10,2) DEFAULT NULL,
  `boxWeight2_10` float(10,2) DEFAULT NULL,
  `boxWeight2_20` float(10,2) DEFAULT NULL,
  `boxWeight2_30` float(10,2) DEFAULT NULL,
  `boxWeight2_40` float(10,2) DEFAULT NULL,
  `boxWeight2_50` float(10,2) DEFAULT NULL,
  `boxWeight3_10` float(10,2) DEFAULT NULL,
  `boxWeight3_20` float(10,2) DEFAULT NULL,
  `boxWeight3_30` float(10,2) DEFAULT NULL,
  `boxWeight3_40` float(10,2) DEFAULT NULL,
  `boxWeight3_50` float(10,2) DEFAULT NULL,
  `boxWeight4_10` float(10,2) DEFAULT NULL,
  `boxWeight4_20` float(10,2) DEFAULT NULL,
  `boxWeight4_30` float(10,2) DEFAULT NULL,
  `boxWeight4_40` float(10,2) DEFAULT NULL,
  `boxWeight4_50` float(10,2) DEFAULT NULL,
  `fieldCapacity10` float(10,2) DEFAULT NULL,
  `fieldCapacity20` float(10,2) DEFAULT NULL,
  `fieldCapacity30` float(10,2) DEFAULT NULL,
  `fieldCapacity40` float(10,2) DEFAULT NULL,
  `fieldCapacity50` float(10,2) DEFAULT NULL,
  `wiltingMoisture10` float(10,2) DEFAULT NULL,
  `wiltingMoisture20` float(10,2) DEFAULT NULL,
  `wiltingMoisture30` float(10,2) DEFAULT NULL,
  `wiltingMoisture40` float(10,2) DEFAULT NULL,
  `wiltingMoisture50` float(10,2) DEFAULT NULL,
  `soilDensity10` float(10,2) DEFAULT NULL,
  `soilDensity20` float(10,2) DEFAULT NULL,
  `soilDensity30` float(10,2) DEFAULT NULL,
  `soilDensity40` float(10,2) DEFAULT NULL,
  `soilDensity50` float(10,2) DEFAULT NULL,
  `reportSuffix` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`stationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of soil_monitor_constant
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_crop_growth_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_crop_growth_monitor`;
CREATE TABLE `tb_crop_growth_monitor` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userId` int(20) DEFAULT NULL,
  `operationTime` datetime DEFAULT NULL,
  `stationId` varchar(10) DEFAULT NULL,
  `stationName` varchar(100) DEFAULT NULL,
  `cropName` varchar(100) DEFAULT NULL,
  `sowingDate` date DEFAULT NULL,
  `seedlingDate` date DEFAULT NULL,
  `trefoilDate` date DEFAULT NULL,
  `jointingDate` date DEFAULT NULL,
  `headingDate` date DEFAULT NULL,
  `tasselingDate` date DEFAULT NULL,
  `floweringDate` date DEFAULT NULL,
  `silkingDate` date DEFAULT NULL,
  `milkyDate` date DEFAULT NULL,
  `maturityDate` date DEFAULT NULL,
  `growthHeight` float(10,2) DEFAULT NULL,
  `growthCondition` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_crop_growth_monitor
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_crop_yield_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_crop_yield_monitor`;
CREATE TABLE `tb_crop_yield_monitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `operationTime` datetime DEFAULT NULL,
  `stationId` varchar(10) DEFAULT NULL,
  `stationName` varchar(100) DEFAULT NULL,
  `cropName` varchar(100) DEFAULT NULL,
  `wheatLength` float(10,2) DEFAULT NULL,
  `wheatCount` float(10,2) DEFAULT NULL,
  `infertilityWheatCount` float(10,2) DEFAULT NULL,
  `solidCount` float(10,2) DEFAULT NULL,
  `stemWidth` float(10,2) DEFAULT NULL,
  `cropEarLength` float(10,2) DEFAULT NULL,
  `cropEarWidth` float(10,2) DEFAULT NULL,
  `doubleEarCount` varchar(100) DEFAULT NULL,
  `cropYield` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_crop_yield_monitor
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_dune_move_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_dune_move_monitor`;
CREATE TABLE `tb_dune_move_monitor` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userId` int(20) DEFAULT NULL,
  `operationTime` datetime DEFAULT NULL,
  `stationId` varchar(10) DEFAULT NULL,
  `stationName` varchar(100) DEFAULT NULL,
  `duneMoveMonitorDate` date DEFAULT NULL,
  `duneMoveLongtitude` varchar(20) DEFAULT NULL,
  `duneMoveLatitude` varchar(20) DEFAULT NULL,
  `duneAltitude` float(10,2) DEFAULT NULL,
  `duneHeight` float(10,2) DEFAULT NULL,
  `duneWindwardSlope` float(10,2) DEFAULT NULL,
  `duneHilltop` float(10,2) DEFAULT NULL,
  `duneLeewardSlope` float(10,2) DEFAULT NULL,
  `duneWindPosition` float(10,2) DEFAULT NULL,
  `hilltopPosition` float(10,2) DEFAULT NULL,
  `leewardPosition` float(10,2) DEFAULT NULL,
  `windDirection` varchar(10) DEFAULT NULL,
  `windSpeed` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dune_move_monitor
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_dune_move_report`
-- ----------------------------
DROP TABLE IF EXISTS `tb_dune_move_report`;
CREATE TABLE `tb_dune_move_report` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userId` int(20) DEFAULT NULL,
  `operationTime` datetime DEFAULT NULL,
  `stationId` varchar(10) DEFAULT NULL,
  `stationName` varchar(100) DEFAULT NULL,
  `duneMoveMonitorDate` date DEFAULT NULL,
  `duneMoveLongtitude` varchar(20) DEFAULT NULL,
  `duneMoveLatitude` varchar(20) DEFAULT NULL,
  `duneAltitude` float(10,2) DEFAULT NULL,
  `duneHeight` float(10,2) DEFAULT NULL,
  `duneWindwardSlope` float(10,2) DEFAULT NULL,
  `duneHilltop` float(10,2) DEFAULT NULL,
  `duneLeewardSlope` float(10,2) DEFAULT NULL,
  `duneWindPosition` float(10,2) DEFAULT NULL,
  `hilltopPosition` float(10,2) DEFAULT NULL,
  `leewardPosition` float(10,2) DEFAULT NULL,
  `windDirection` varchar(10) DEFAULT NULL,
  `windSpeed` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dune_move_report
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_dust_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_dust_monitor`;
CREATE TABLE `tb_dust_monitor` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userId` int(20) DEFAULT NULL,
  `stationId` varchar(10) DEFAULT NULL,
  `operationTime` datetime DEFAULT NULL,
  `stationName` varchar(100) DEFAULT NULL,
  `dustDryWeight` float(20,2) DEFAULT NULL,
  `dustAvgDryWeight` float(20,2) DEFAULT NULL,
  `dustType` varchar(100) DEFAULT NULL,
  `dustStartTime` datetime DEFAULT NULL,
  `dustEndTime` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dust_monitor
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_gb_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `tb_gb_login_log`;
CREATE TABLE `tb_gb_login_log` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `userId` int(20) DEFAULT NULL COMMENT '用户ID',
  `loginName` varchar(20) DEFAULT NULL COMMENT '登录名',
  `nickname` varchar(100) DEFAULT NULL COMMENT '用户名',
  `loginTime` datetime DEFAULT NULL COMMENT '登录时间',
  `loginIp` varchar(40) DEFAULT NULL COMMENT 'IP地址',
  `isSuccess` bit(1) DEFAULT NULL COMMENT '是否成功',
  `comments` varchar(255) DEFAULT NULL COMMENT '登录描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='登录日志';

-- ----------------------------
-- Records of tb_gb_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_gb_operating_log`
-- ----------------------------
DROP TABLE IF EXISTS `tb_gb_operating_log`;
CREATE TABLE `tb_gb_operating_log` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `userId` int(20) DEFAULT NULL COMMENT '用户ID',
  `loginName` varchar(100) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL COMMENT '用户名',
  `happenTime` datetime DEFAULT NULL COMMENT '操作时间',
  `module` varchar(255) DEFAULT NULL COMMENT '操作模块',
  `comments` varchar(255) DEFAULT NULL COMMENT '操作描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
-- Records of tb_gb_operating_log
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_grass_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_grass_monitor`;
CREATE TABLE `tb_grass_monitor` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userId` int(20) DEFAULT NULL,
  `operationTime` datetime DEFAULT NULL,
  `stationId` varchar(10) DEFAULT NULL,
  `stationName` varchar(100) DEFAULT NULL,
  `monitorArea` varchar(100) DEFAULT NULL,
  `grassName` varchar(100) DEFAULT NULL,
  `grassType` varchar(100) DEFAULT NULL,
  `backDate` varchar(20) DEFAULT NULL,
  `flowerDate` varchar(20) DEFAULT NULL,
  `yellowDate` varchar(20) DEFAULT NULL,
  `absoluteHeigth` varchar(20) DEFAULT NULL,
  `grassHeigth` varchar(20) DEFAULT NULL,
  `coverDegree` varchar(20) DEFAULT NULL,
  `earthBiomass` varchar(20) DEFAULT NULL,
  `betterGrassRate` varchar(20) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_grass_monitor
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_plant_details`
-- ----------------------------
DROP TABLE IF EXISTS `tb_plant_details`;
CREATE TABLE `tb_plant_details` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `plantSpeciesId` int(20) DEFAULT NULL,
  `plantMonitorArea` varchar(10) DEFAULT NULL,
  `plantName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_plant_details
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_plant_species_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_plant_species_monitor`;
CREATE TABLE `tb_plant_species_monitor` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userId` int(20) DEFAULT NULL,
  `operationTime` datetime DEFAULT NULL,
  `stationId` varchar(10) DEFAULT NULL,
  `stationName` varchar(100) DEFAULT NULL,
  `plantMonitorDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_plant_species_monitor
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_report_header`
-- ----------------------------
DROP TABLE IF EXISTS `tb_report_header`;
CREATE TABLE `tb_report_header` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `operationTime` datetime DEFAULT NULL,
  `currentUserId` int(20) DEFAULT NULL,
  `reporterName` varchar(255) DEFAULT NULL,
  `reportDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_report_header
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_resource_authority`
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource_authority`;
CREATE TABLE `tb_resource_authority` (
  `resource_id` int(20) NOT NULL,
  `authority_id` int(20) NOT NULL,
  PRIMARY KEY (`resource_id`,`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_resource_authority
-- ----------------------------
INSERT INTO `tb_resource_authority` VALUES ('10100', '100');
INSERT INTO `tb_resource_authority` VALUES ('10200', '220');
INSERT INTO `tb_resource_authority` VALUES ('10300', '300');
INSERT INTO `tb_resource_authority` VALUES ('10400', '400');
INSERT INTO `tb_resource_authority` VALUES ('10500', '500');
INSERT INTO `tb_resource_authority` VALUES ('10600', '600');
INSERT INTO `tb_resource_authority` VALUES ('10700', '700');
INSERT INTO `tb_resource_authority` VALUES ('10800', '800');
INSERT INTO `tb_resource_authority` VALUES ('10900', '900');
INSERT INTO `tb_resource_authority` VALUES ('10910', '910');
INSERT INTO `tb_resource_authority` VALUES ('11000', '200');
INSERT INTO `tb_resource_authority` VALUES ('11100', '210');
INSERT INTO `tb_resource_authority` VALUES ('11300', '230');
INSERT INTO `tb_resource_authority` VALUES ('11400', '240');
INSERT INTO `tb_resource_authority` VALUES ('11800', '280');
INSERT INTO `tb_resource_authority` VALUES ('20100', '100');
INSERT INTO `tb_resource_authority` VALUES ('20300', '300');
INSERT INTO `tb_resource_authority` VALUES ('20400', '400');
INSERT INTO `tb_resource_authority` VALUES ('20500', '500');
INSERT INTO `tb_resource_authority` VALUES ('20600', '600');
INSERT INTO `tb_resource_authority` VALUES ('20700', '700');
INSERT INTO `tb_resource_authority` VALUES ('20800', '800');
INSERT INTO `tb_resource_authority` VALUES ('20900', '900');
INSERT INTO `tb_resource_authority` VALUES ('20910', '910');
INSERT INTO `tb_resource_authority` VALUES ('21000', '200');
INSERT INTO `tb_resource_authority` VALUES ('21100', '210');
INSERT INTO `tb_resource_authority` VALUES ('21200', '220');
INSERT INTO `tb_resource_authority` VALUES ('21300', '230');
INSERT INTO `tb_resource_authority` VALUES ('21400', '240');
INSERT INTO `tb_resource_authority` VALUES ('21800', '280');

-- ----------------------------
-- Table structure for `tb_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_authority`;
CREATE TABLE `tb_role_authority` (
  `role_id` int(20) NOT NULL,
  `authority_id` int(20) NOT NULL,
  PRIMARY KEY (`role_id`,`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_authority
-- ----------------------------
INSERT INTO `tb_role_authority` VALUES ('100', '100');
INSERT INTO `tb_role_authority` VALUES ('110', '110');
INSERT INTO `tb_role_authority` VALUES ('300', '300');
INSERT INTO `tb_role_authority` VALUES ('400', '400');
INSERT INTO `tb_role_authority` VALUES ('500', '500');
INSERT INTO `tb_role_authority` VALUES ('600', '600');
INSERT INTO `tb_role_authority` VALUES ('700', '700');
INSERT INTO `tb_role_authority` VALUES ('800', '800');
INSERT INTO `tb_role_authority` VALUES ('900', '900');
INSERT INTO `tb_role_authority` VALUES ('910', '910');
INSERT INTO `tb_role_authority` VALUES ('2000', '200');
INSERT INTO `tb_role_authority` VALUES ('2109', '210');
INSERT INTO `tb_role_authority` VALUES ('2209', '220');
INSERT INTO `tb_role_authority` VALUES ('2309', '230');
INSERT INTO `tb_role_authority` VALUES ('2409', '240');
INSERT INTO `tb_role_authority` VALUES ('2809', '280');

-- ----------------------------
-- Table structure for `tb_soil_analysis_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_soil_analysis_monitor`;
CREATE TABLE `tb_soil_analysis_monitor` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userId` int(20) DEFAULT NULL,
  `operationTime` datetime DEFAULT NULL,
  `stationId` varchar(10) DEFAULT NULL,
  `stationName` varchar(100) DEFAULT NULL,
  `soilAnalysisDate` date DEFAULT NULL,
  `drySoilHeight` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight1_10` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight1_20` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight1_30` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight1_40` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight1_50` float(10,2) DEFAULT NULL,
  `drySoilboxWeight1_10` float(10,2) DEFAULT NULL,
  `drySoilboxWeight1_20` float(10,2) DEFAULT NULL,
  `drySoilboxWeight1_30` float(10,2) DEFAULT NULL,
  `drySoilboxWeight1_40` float(10,2) DEFAULT NULL,
  `drySoilboxWeight1_50` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight2_10` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight2_20` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight2_30` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight2_40` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight2_50` float(10,2) DEFAULT NULL,
  `drySoilboxWeight2_10` float(10,2) DEFAULT NULL,
  `drySoilboxWeight2_20` float(10,2) DEFAULT NULL,
  `drySoilboxWeight2_30` float(10,2) DEFAULT NULL,
  `drySoilboxWeight2_40` float(10,2) DEFAULT NULL,
  `drySoilboxWeight2_50` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight3_10` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight3_20` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight3_30` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight3_40` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight3_50` float(10,2) DEFAULT NULL,
  `drySoilboxWeight3_10` float(10,2) DEFAULT NULL,
  `drySoilboxWeight3_20` float(10,2) DEFAULT NULL,
  `drySoilboxWeight3_30` float(10,2) DEFAULT NULL,
  `drySoilboxWeight3_40` float(10,2) DEFAULT NULL,
  `drySoilboxWeight3_50` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight4_10` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight4_20` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight4_30` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight4_40` float(10,2) DEFAULT NULL,
  `wetSoilboxWeight4_50` float(10,2) DEFAULT NULL,
  `drySoilboxWeight4_10` float(10,2) DEFAULT NULL,
  `drySoilboxWeight4_20` float(10,2) DEFAULT NULL,
  `drySoilboxWeight4_30` float(10,2) DEFAULT NULL,
  `drySoilboxWeight4_40` float(10,2) DEFAULT NULL,
  `drySoilboxWeight4_50` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_soil_analysis_monitor
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_soil_erosion_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_soil_erosion_monitor`;
CREATE TABLE `tb_soil_erosion_monitor` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userId` int(20) DEFAULT NULL,
  `operationTime` datetime DEFAULT NULL,
  `stationId` varchar(10) DEFAULT NULL,
  `stationName` float(10,2) DEFAULT NULL,
  `soilMonitorDate` date DEFAULT NULL,
  `innerSoilErosion1` float(10,2) DEFAULT '0.00',
  `innerSoilErosion2` float(10,2) DEFAULT '0.00',
  `innerSoilErosion3` float(10,2) DEFAULT '0.00',
  `innerSoilErosion4` float(10,2) DEFAULT '0.00',
  `innerSoilErosion5` float(10,2) DEFAULT '0.00',
  `innerSoilErosion6` float(10,2) DEFAULT '0.00',
  `innerSoilErosion7` float(10,2) DEFAULT '0.00',
  `innerSoilErosion8` float(10,2) DEFAULT '0.00',
  `innerSoilErosion9` float(10,2) DEFAULT '0.00',
  `innerSoilErosion10` float(10,2) DEFAULT '0.00',
  `innerSoilErosion11` float(10,2) DEFAULT '0.00',
  `innerSoilErosion12` float(10,2) DEFAULT '0.00',
  `innerSoilErosion13` float(10,2) DEFAULT '0.00',
  `innerSoilErosion14` float(10,2) DEFAULT '0.00',
  `innerSoilErosion15` float(10,2) DEFAULT '0.00',
  `innerSoilErosion16` float(10,2) DEFAULT '0.00',
  `innerSoilErosion17` float(10,2) DEFAULT '0.00',
  `innerSoilErosion18` float(10,2) DEFAULT '0.00',
  `innerSoilErosion19` float(10,2) DEFAULT '0.00',
  `innerSoilErosion20` float(10,2) DEFAULT '0.00',
  `outSoilErosion1` float(10,2) DEFAULT '0.00',
  `outSoilErosion2` float(10,2) DEFAULT '0.00',
  `outSoilErosion3` float(10,2) DEFAULT '0.00',
  `outSoilErosion4` float(10,2) DEFAULT '0.00',
  `outSoilErosion5` float(10,2) DEFAULT '0.00',
  `outSoilErosion6` float(10,2) DEFAULT '0.00',
  `outSoilErosion7` float(10,2) DEFAULT '0.00',
  `outSoilErosion8` float(10,2) DEFAULT '0.00',
  `outSoilErosion9` float(10,2) DEFAULT '0.00',
  `outSoilErosion10` float(10,2) DEFAULT '0.00',
  `outSoilErosion11` float(10,2) DEFAULT '0.00',
  `outSoilErosion12` float(10,2) DEFAULT '0.00',
  `outSoilErosion13` float(10,2) DEFAULT '0.00',
  `outSoilErosion14` float(10,2) DEFAULT '0.00',
  `outSoilErosion15` float(10,2) DEFAULT '0.00',
  `outSoilErosion16` float(10,2) DEFAULT '0.00',
  `outSoilErosion17` float(10,2) DEFAULT '0.00',
  `outSoilErosion18` float(10,2) DEFAULT '0.00',
  `outSoilErosion19` float(10,2) DEFAULT '0.00',
  `outSoilErosion20` float(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_soil_erosion_monitor
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_soil_erosion_report`
-- ----------------------------
DROP TABLE IF EXISTS `tb_soil_erosion_report`;
CREATE TABLE `tb_soil_erosion_report` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userId` int(20) DEFAULT NULL,
  `operationTime` datetime DEFAULT NULL,
  `stationId` varchar(10) DEFAULT NULL,
  `stationName` varchar(100) DEFAULT NULL,
  `soilMonitorDate` date DEFAULT NULL,
  `innerSoilErosion` float(10,2) DEFAULT NULL,
  `outSoilErosion` float(10,2) DEFAULT NULL,
  `avgInnerSoilProduct` float(10,2) DEFAULT NULL,
  `avgInnerSoilLose` float(10,2) DEFAULT NULL,
  `avgOutSoilProduct` float(10,2) DEFAULT NULL,
  `avgOutSoilLose` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_soil_erosion_report
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_system_authority`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_authority`;
CREATE TABLE `tb_system_authority` (
  `id` int(20) NOT NULL COMMENT '限权值',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `authority` varchar(36) NOT NULL COMMENT '权限值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system_authority
-- ----------------------------
INSERT INTO `tb_system_authority` VALUES ('100', '水体监测数据报表', 'A_WATER_MONITOR');
INSERT INTO `tb_system_authority` VALUES ('110', '初始化权限', 'A_INIT');
INSERT INTO `tb_system_authority` VALUES ('200', '后台管理', 'A_MANAGEMENT');
INSERT INTO `tb_system_authority` VALUES ('210', '用户管理', 'A_USER');
INSERT INTO `tb_system_authority` VALUES ('220', '气象站点管理', 'A_STATION');
INSERT INTO `tb_system_authority` VALUES ('230', '登录日志管理', 'A_LOGINLOG');
INSERT INTO `tb_system_authority` VALUES ('240', '操作日志管理', 'A_OPERATIONLOG');
INSERT INTO `tb_system_authority` VALUES ('280', '修改密码', 'A_PASSWORD');
INSERT INTO `tb_system_authority` VALUES ('300', '沙尘监测数据报表', 'A_DUST_MONITOR');
INSERT INTO `tb_system_authority` VALUES ('400', '农作物生长监测数据报表', 'A_CROP_GROWTH');
INSERT INTO `tb_system_authority` VALUES ('500', '农作物产量监测数据报表', 'A_CROP_YIELD');
INSERT INTO `tb_system_authority` VALUES ('600', '天然草场监测数据报表', 'A_GRASS');
INSERT INTO `tb_system_authority` VALUES ('700', '土壤风蚀监测数据报表', 'A_SOIL');
INSERT INTO `tb_system_authority` VALUES ('800', '沙丘移动监测数据报表', 'A_DUNE_MOVE');
INSERT INTO `tb_system_authority` VALUES ('900', '植物物种多样性监测数据报表', 'A_PLANT_SPECIES');
INSERT INTO `tb_system_authority` VALUES ('910', '土壤水分观测数据报表', 'A_SOIL_ANALYSIS');

-- ----------------------------
-- Table structure for `tb_system_datadict`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_datadict`;
CREATE TABLE `tb_system_datadict` (
  `code` varchar(10) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system_datadict
-- ----------------------------
INSERT INTO `tb_system_datadict` VALUES ('SC001', null, '初始密码', '21218cca77804d2ba1922c33e0151105', null);

-- ----------------------------
-- Table structure for `tb_system_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_resource`;
CREATE TABLE `tb_system_resource` (
  `id` int(20) NOT NULL,
  `url` varchar(255) NOT NULL COMMENT '资源URL',
  `name` varchar(36) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `parentId` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system_resource
-- ----------------------------
INSERT INTO `tb_system_resource` VALUES ('10100', '/security/water/*.*', '水体监测数据报表', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('10300', '/security/dust/*.*', '沙尘监测数据报表', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('10400', '/security/cropgrowth/*.*', '农作物生长监测数据报表', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('10500', '/security/cropyield/*.*', '农作物产量监测数据报表', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('10600', '/security/grass/*.*', '天然草场监测数据报表', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('10700', '/security/soil/*.*', '土壤风蚀监测数据报表', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('10800', '/security/dunemove/*.*', '沙丘移动监测数据报表', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('10900', '/security/plant/*.*', '植物物种多样性监测数据报表', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('10910', '/security/soilanalysis/*.*', '土壤水分观测数据报表', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('11000', '/security/management/*.*', '后台管理', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('11100', '/security/management/user/*.*', '用户管理', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('11200', '/security/management/station/*.*', '气象站点管理', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('11300', '/security/management/loginlog/*.*', '登录日志管理', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('11400', '/security/management/operatinglog/*.*', '操作日志管理', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('11800', '/security/management/password/*.*', '修改密码', 'url', null);
INSERT INTO `tb_system_resource` VALUES ('20100', '/security/water/water.action', '水体监测数据报表', 'menu', null);
INSERT INTO `tb_system_resource` VALUES ('20300', '/security/dust/dust.action', '沙尘监测数据报表', 'menu', null);
INSERT INTO `tb_system_resource` VALUES ('20400', '/security/cropgrowth/growth.action', '农作物生长监测数据报表', 'menu', null);
INSERT INTO `tb_system_resource` VALUES ('20500', '/security/cropyield/yield.action', '农作物产量监测数据报表', 'menu', null);
INSERT INTO `tb_system_resource` VALUES ('20600', '/security/grass/grass.action', '天然草场监测数据报表', 'menu', null);
INSERT INTO `tb_system_resource` VALUES ('20700', '/security/soil/soil.action', '土壤风蚀监测数据报表', 'menu', null);
INSERT INTO `tb_system_resource` VALUES ('20800', '/security/dunemove/dune.action', '沙丘移动监测数据报表', 'menu', null);
INSERT INTO `tb_system_resource` VALUES ('20900', '/security/plant/plant.action', '植物物种多样性监测数据报表', 'menu', null);
INSERT INTO `tb_system_resource` VALUES ('20910', '/security/soilanalysis/analysis.action', '土壤水分观测数据报表', 'menu', null);
INSERT INTO `tb_system_resource` VALUES ('21000', '/security/management/management.action', '后台管理', 'menu', null);
INSERT INTO `tb_system_resource` VALUES ('21100', '/security/management/user/user.action', '用户管理', 'menu1', '21000');
INSERT INTO `tb_system_resource` VALUES ('21200', '/security/management/station/station.action', '气象站点管理', 'menu1', '21000');
INSERT INTO `tb_system_resource` VALUES ('21300', '/security/management/loginlog/log.action', '登录日志管理', 'menu1', '21000');
INSERT INTO `tb_system_resource` VALUES ('21400', '/security/management/operatinglog/log.action', '操作日志管理', 'menu1', '21000');
INSERT INTO `tb_system_resource` VALUES ('21800', '/security/management/password/password.action', '修改密码', 'menu1', '21000');

-- ----------------------------
-- Table structure for `tb_system_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_role`;
CREATE TABLE `tb_system_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(36) NOT NULL,
  `type` varchar(20) NOT NULL,
  `parentId` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2810 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system_role
-- ----------------------------
INSERT INTO `tb_system_role` VALUES ('100', '水体监测数据报表', 'option', null);
INSERT INTO `tb_system_role` VALUES ('110', '初始化角色', 'required', null);
INSERT INTO `tb_system_role` VALUES ('300', '沙尘监测数据报表', 'option', null);
INSERT INTO `tb_system_role` VALUES ('400', '农作物生长监测数据报表', 'option', null);
INSERT INTO `tb_system_role` VALUES ('500', '农作物产量监测数据报表', 'option', null);
INSERT INTO `tb_system_role` VALUES ('600', '天然草场监测数据报表', 'option', null);
INSERT INTO `tb_system_role` VALUES ('700', '土壤风蚀监测数据报表', 'option', null);
INSERT INTO `tb_system_role` VALUES ('800', '沙丘移动监测数据报表', 'option', null);
INSERT INTO `tb_system_role` VALUES ('900', '植物物种多样性监测数据报表', 'option', null);
INSERT INTO `tb_system_role` VALUES ('910', '土壤水分观测数据报表', 'option', null);
INSERT INTO `tb_system_role` VALUES ('2000', '后台管理', 'option', null);
INSERT INTO `tb_system_role` VALUES ('2109', '用户管理', 'option', '2000');
INSERT INTO `tb_system_role` VALUES ('2209', '气象站点管理', 'option', '2000');
INSERT INTO `tb_system_role` VALUES ('2309', '登录日志管理', 'option', '2000');
INSERT INTO `tb_system_role` VALUES ('2409', '操作日志管理', 'option', '2000');
INSERT INTO `tb_system_role` VALUES ('2809', '修改密码', 'option', '2000');

-- ----------------------------
-- Table structure for `tb_system_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user`;
CREATE TABLE `tb_system_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(36) NOT NULL COMMENT '密码',
  `nickname` varchar(255) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `userRole` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system_user
-- ----------------------------
INSERT INTO `tb_system_user` VALUES ('1', 'admin', '670b14728ad9902aecba32e22fa4f6bd', '系统管理员', '88888888', 'admin@admin.com', 'admin', 'super');

-- ----------------------------
-- Table structure for `tb_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `user_id` int(20) NOT NULL,
  `role_id` int(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '100');
INSERT INTO `tb_user_role` VALUES ('1', '110');
INSERT INTO `tb_user_role` VALUES ('1', '300');
INSERT INTO `tb_user_role` VALUES ('1', '400');
INSERT INTO `tb_user_role` VALUES ('1', '500');
INSERT INTO `tb_user_role` VALUES ('1', '600');
INSERT INTO `tb_user_role` VALUES ('1', '700');
INSERT INTO `tb_user_role` VALUES ('1', '800');
INSERT INTO `tb_user_role` VALUES ('1', '900');
INSERT INTO `tb_user_role` VALUES ('1', '910');
INSERT INTO `tb_user_role` VALUES ('1', '2000');
INSERT INTO `tb_user_role` VALUES ('1', '2109');
INSERT INTO `tb_user_role` VALUES ('1', '2209');
INSERT INTO `tb_user_role` VALUES ('1', '2309');
INSERT INTO `tb_user_role` VALUES ('1', '2409');
INSERT INTO `tb_user_role` VALUES ('1', '2809');

-- ----------------------------
-- Table structure for `tb_user_station`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_station`;
CREATE TABLE `tb_user_station` (
  `stationId` varchar(10) NOT NULL,
  `userId` int(20) NOT NULL,
  PRIMARY KEY (`stationId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_station
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_water_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `tb_water_monitor`;
CREATE TABLE `tb_water_monitor` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `operationTime` datetime DEFAULT NULL,
  `stationId` varchar(10) DEFAULT NULL,
  `userId` int(20) DEFAULT NULL,
  `waterName` varchar(20) DEFAULT NULL,
  `fetchLongitude` varchar(20) DEFAULT NULL,
  `fetchLatitude` varchar(20) DEFAULT NULL,
  `turnLongitude` varchar(20) DEFAULT NULL,
  `turnLatitude` varchar(20) DEFAULT NULL,
  `waterArea` varchar(50) DEFAULT NULL,
  `waterLevel` varchar(20) DEFAULT NULL,
  `waterOpacity` varchar(20) DEFAULT NULL,
  `waterColor` varchar(20) DEFAULT NULL,
  `waterTemperature` varchar(20) DEFAULT NULL,
  `waterPh` varchar(20) DEFAULT NULL,
  `waterTotalSalt` varchar(20) DEFAULT NULL,
  `waterChlorine` varchar(20) DEFAULT NULL,
  `waterSulfide` varchar(20) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_water_monitor
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_weather_station`
-- ----------------------------
DROP TABLE IF EXISTS `tb_weather_station`;
CREATE TABLE `tb_weather_station` (
  `stationId` varchar(10) NOT NULL,
  `stationName` varchar(100) DEFAULT NULL,
  `stationTele` varchar(20) DEFAULT NULL,
  `stationArea` varchar(100) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`stationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_weather_station
-- ----------------------------
