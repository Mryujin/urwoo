/**
 * Date: 2017-04-17 18:31:17
 */
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for security_rights
-- ----------------------------
DROP TABLE IF EXISTS `security_rights`;
CREATE TABLE `security_rights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_bin NOT NULL,
  `type` int(1) NOT NULL,
  `uri` varchar(64) COLLATE utf8_bin NOT NULL,
  `right_code` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of security_rights
-- ----------------------------

-- ----------------------------
-- Table structure for security_roles
-- ----------------------------
DROP TABLE IF EXISTS `security_roles`;
CREATE TABLE `security_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_bin NOT NULL,
  `role_code` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of security_roles
-- ----------------------------

-- ----------------------------
-- Table structure for security_role_rights
-- ----------------------------
DROP TABLE IF EXISTS `security_role_rights`;
CREATE TABLE `security_role_rights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `right_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of security_role_rights
-- ----------------------------

-- ----------------------------
-- Table structure for security_users
-- ----------------------------
DROP TABLE IF EXISTS `security_users`;
CREATE TABLE `security_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_bin NOT NULL,
  `username` varchar(32) COLLATE utf8_bin NOT NULL,
  `password` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of security_users
-- ----------------------------

-- ----------------------------
-- Table structure for security_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `security_user_roles`;
CREATE TABLE `security_user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of security_user_roles
-- ----------------------------
