CREATE TABLE `account` (
  	`id` bigint(20) NOT NULL AUTO_INCREMENT,
  	`employee_Id` bigint(20) NOT NULL,
  	`username` varchar(15) NOT NULL,
  	`email` varchar(40) NOT NULL,
  	`password` varchar(100) NOT NULL,
  	`created` DATETIME NOT NULL,
	`modified` TIMESTAMP,
  	PRIMARY KEY (`id`),
  	UNIQUE KEY `uk_users_username` (`username`),
  	UNIQUE KEY `uk_users_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `employee` (
    `id` BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `phone` varchar(45) NOT NULL,
    `salary` DECIMAL(15,3) NOT NULL,
    `created` DATETIME NOT NULL,
	`modified` TIMESTAMP
)ENGINE=InnoDB CHARACTER SET=UTF8 COLLATE=utf8_unicode_ci;

CREATE TABLE `user_roles` (
  	`user_id` bigint(20) NOT NULL,
  	`role_id` bigint(20) NOT NULL,
  	PRIMARY KEY (`user_id`,`role_id`),
  	KEY `fk_user_roles_role_id` (`role_id`),
  	CONSTRAINT `fk_user_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  	CONSTRAINT `fk_user_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
