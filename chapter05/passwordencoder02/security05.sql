CREATE DATABASE IF NOT EXISTS security_encoder COLLATE utf8mb4_unicode_ci;

USE security_encoder;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`       int(11) unsigned NOT NULL AUTO_INCREMENT,
    `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

INSERT INTO `user` (`id`, `username`, `password`)
VALUES (1, '1', '{noop}1');
