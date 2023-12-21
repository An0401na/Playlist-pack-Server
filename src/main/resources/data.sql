-- 데이터가 없을 때만 삽입
INSERT INTO `plypack`.`content` (`content_id`, `like_cnt`, `total_vote_cnt`, `view_cnt`)
SELECT '1', '201', '0', '254'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`content` WHERE `content_id` = '1');

INSERT INTO `plypack`.`content` (`content_id`, `like_cnt`, `total_vote_cnt`, `view_cnt`)
SELECT '2', '83', '0', '189'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`content` WHERE `content_id` = '2');

INSERT INTO `plypack`.`content` (`content_id`, `like_cnt`, `total_vote_cnt`, `view_cnt`)
SELECT '3', '56', '0', '91'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`content` WHERE `content_id` = '3');

INSERT INTO `plypack`.`content` (`content_id`, `like_cnt`, `total_vote_cnt`, `view_cnt`)
SELECT '4', '63', '0', '152'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`content` WHERE `content_id` = '4');

INSERT INTO `plypack`.`content` (`content_id`, `like_cnt`, `total_vote_cnt`, `view_cnt`)
SELECT '5', '24', '0', '84'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`content` WHERE `content_id` = '5');

INSERT INTO `plypack`.`content` (`content_id`, `like_cnt`, `total_vote_cnt`, `view_cnt`)
SELECT '6', '79', '0', '183'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`content` WHERE `content_id` = '6');

INSERT INTO `plypack`.`content` (`content_id`, `like_cnt`, `total_vote_cnt`, `view_cnt`)
SELECT '7', '88', '0', '161'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`content` WHERE `content_id` = '7');
-- 데이터가 없을 때만 삽입
INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '1', '1', '0', '2'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '1');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '2', '2', '0', '2'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '2');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '3', '3', '0', '2'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '3');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '4', '4', '0', '2'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '4');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '5', '5', '0', '2'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '5');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '6', '1', '0', '3'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '6');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '7', '2', '0', '3'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '7');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '8', '3', '0', '3'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '8');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '9', '2', '0', '3'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '9');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '10', '3', '0', '3'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '10');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '11', '1', '0', '4'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '11');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '12', '2', '0', '4'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '12');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '13', '3', '0', '4'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '13');

INSERT INTO `plypack`.`vote` (`vote_id`, `choice`, `vote_cnt`, `content_id`)
SELECT '14', '4', '0', '4'
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM `plypack`.`vote` WHERE `vote_id` = '14');
