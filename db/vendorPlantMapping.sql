CREATE TABLE `vendor_plant_mapping` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `vendor_code` varchar(45) DEFAULT NULL,
  `plant_code` varchar(45) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 |