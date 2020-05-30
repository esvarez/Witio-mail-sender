DROP TABLE IF EXISTS `mailing_lists`;
CREATE TABLE `mailing_lists` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `modules`;
CREATE TABLE `modules` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `processes`;
CREATE TABLE `processes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `message` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `module_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrr8nucbava9r9wr8lgire1mmr` (`module_id`),
  CONSTRAINT `FKrr8nucbava9r9wr8lgire1mmr` FOREIGN KEY (`module_id`) REFERENCES `modules` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `processes_mailing_lists`;
CREATE TABLE `processes_mailing_lists` (
  `process_id` bigint NOT NULL,
  `mailing_list_id` int NOT NULL,
  PRIMARY KEY (`process_id`,`mailing_list_id`),
  KEY `FKs7a53lqkgueodn41iq32e52lh` (`mailing_list_id`),
  CONSTRAINT `FKe8t3tipmparen86mlk2kn7o3u` FOREIGN KEY (`process_id`) REFERENCES `processes` (`id`),
  CONSTRAINT `FKs7a53lqkgueodn41iq32e52lh` FOREIGN KEY (`mailing_list_id`) REFERENCES `mailing_lists` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `recipients`;
CREATE TABLE `recipients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mail_listing_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr5qpsm412ksaobg2r60ewqs2k` (`mail_listing_id`),
  CONSTRAINT `FKr5qpsm412ksaobg2r60ewqs2k` FOREIGN KEY (`mail_listing_id`) REFERENCES `mailing_lists` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `sellers`;
CREATE TABLE `sellers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `sellers_modules`;
CREATE TABLE `sellers_modules` (
  `seller_id` bigint NOT NULL,
  `module_id` int NOT NULL,
  PRIMARY KEY (`seller_id`,`module_id`),
  KEY `FKn59vdbt2ijgr1l2vs6rj4gxgs` (`module_id`),
  CONSTRAINT `FKn59vdbt2ijgr1l2vs6rj4gxgs` FOREIGN KEY (`module_id`) REFERENCES `modules` (`id`),
  CONSTRAINT `FKnqrlafa4prmcr9s6ejux6r74b` FOREIGN KEY (`seller_id`) REFERENCES `sellers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO sellers
    VALUES
        (1, 'Hugo'),
        (2, 'Alma'),
        (3, 'Paco');

INSERT INTO modules
    VALUES
        (1, 'Post-Venta'),
        (2, 'venta');

INSERT INTO sellers_modules
    VALUES
        (1,1),
        (2,1),
        (2,2),
        (3,2);

INSERT INTO processes
    VALUES
        (1, 'Hola Que tal %nombre_cliente%: Hemos recibido su cotizacion de %auto%, con %nombre_banco%, con plazo de %plazo%, con un enganche de %enganche%', 'Contacto', 2),
        (2, 'Mensaje del proceso Cita Servicio, a modo de ejemplo se uso el mismo cuerpo. Hola Que tal %nombre_cliente%: Hemos recibido su cotizacion de %auto%, con %nombre_banco%, con plazo de %plazo%, con un enganche de %enganche%', 'Cita Servicio', 1),
        (3, 'Mensaje del proceso Pintura, a modo de ejemplo se uso el mismo cuerpo. Hola Que tal %nombre_cliente%: Hemos recibido su cotizacion de %auto%, con %nombre_banco%, con plazo de %plazo%, con un enganche de %enganche%', 'Pintura', 1),
        (4, 'Mensaje del proceso Refacciones, a modo de ejemplo se uso el mismo cuerpo. Hola Que tal %nombre_cliente%: Hemos recibido su cotizacion de %auto%, con %nombre_banco%, con plazo de %plazo%, con un enganche de %enganche%', 'Refacciones', 1),
        (5, 'Mensaje del proceso Cotizaciones, a modo de ejemplo se uso el mismo cuerpo. Hola Que tal %nombre_cliente%: Hemos recibido su cotizacion de %auto%, con %nombre_banco%, con plazo de %plazo%, con un enganche de %enganche%', 'Contizaciones', 2);

INSERT INTO mailing_lists
    VALUES
        (1, 'Confimracion al cliente'),
        (2, 'Envio a agente de servicio'),
        (3, 'Confimracion cita a cliente');

INSERT INTO processes_mailing_lists
    VALUES
        (1, 1),
        (1, 2),
        (2, 2),
        (3, 3),
        (4, 1),
        (5, 2);

INSERT INTO recipients
    VALUES
        (1, '4.20rck@gmail.com', 'Sofware', 'Erick Suarez', 1),
        (2, '420rck@gmail.com', 'Lista 2', 'Erick Lista 2', 2),
        (3, 'serick03@gmail.com', 'Lista 3', 'Erick Lista 3', 2);
