INSERT INTO `player` (`id`,`user_name`,`email`,`password`) VALUES (1,'1','1','1');

INSERT INTO `game` (`id`,`player_id`,`game_status`,`created`) VALUES (1,1,'FINISHED','2017-01-08');
INSERT INTO `game` (`id`,`player_id`,`game_status`,`created`) VALUES (2,1,'IN_PROGRESS','2017-01-08');
INSERT INTO `game` (`id`,`player_id`,`game_status`,`created`) VALUES (3,1,'CREATED','2017-01-08');


INSERT INTO `frame` (`id`,`pins_hit_count_first_take`,`pins_hit_count_second_take`,`game_id`) VALUES (1,'0','2',1);
INSERT INTO `frame` (`id`,`pins_hit_count_first_take`,`pins_hit_count_second_take`,`game_id`) VALUES (2,'8','1',1);
INSERT INTO `frame` (`id`,`pins_hit_count_first_take`,`pins_hit_count_second_take`,`game_id`) VALUES (3,'6','2',1);
INSERT INTO `frame` (`id`,`pins_hit_count_first_take`,`pins_hit_count_second_take`,`game_id`) VALUES (4,'6','3',1);
INSERT INTO `frame` (`id`,`pins_hit_count_first_take`,`pins_hit_count_second_take`,`game_id`) VALUES (5,'4','5',1);
INSERT INTO `frame` (`id`,`pins_hit_count_first_take`,`pins_hit_count_second_take`,`game_id`) VALUES (6,'7','1',1);
INSERT INTO `frame` (`id`,`pins_hit_count_first_take`,`pins_hit_count_second_take`,`game_id`) VALUES (7,'4','6',1);
INSERT INTO `frame` (`id`,`pins_hit_count_first_take`,`pins_hit_count_second_take`,`game_id`) VALUES (8,'6','4',1);
INSERT INTO `frame` (`id`,`pins_hit_count_first_take`,`pins_hit_count_second_take`,`game_id`) VALUES (9,'6','2',1);
INSERT INTO `frame` (`id`,`pins_hit_count_first_take`,`pins_hit_count_second_take`,`game_id`) VALUES (10,'7','1',1);
INSERT INTO `frame` (`id`,`pins_hit_count_first_take`,`pins_hit_count_second_take`,`game_id`) VALUES (11,'3','3',2);
INSERT INTO `frame` (`id`,`pins_hit_count_first_take`,`pins_hit_count_second_take`,`game_id`) VALUES (12,'10','0',2);
