CREATE TABLE IF NOT EXISTS `player` (
  `id` int(11) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL,
  `game_status` varchar(45) NOT NULL,
  `created` date NOT NULL,
  `frame_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `player_id_fk_idx` (`player_id`),
  CONSTRAINT `player_id_fk` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `frame` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pins_hit_count_first_take` varchar(45) NOT NULL,
  `pins_hit_count_second_take` varchar(45) DEFAULT NULL,
  `game_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `game_fk_idx` (`game_id`),
  CONSTRAINT `game_fk` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=351 DEFAULT CHARSET=utf8;

