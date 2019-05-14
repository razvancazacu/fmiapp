CREATE TABLE `users` (
                         `username` varchar(16) NOT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `password` varchar(32) NOT NULL,
                         `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`username`),
                         UNIQUE KEY `username_UNIQUE` (`username`)
);

INSERT INTO `accounts`.`users`
(
 `username`,
 `email`,
 `password`,
 `create_time`
)
VALUES
(
 'admin',
 'admin@admin.admin',
 'admin',
 DEFAULT
);
