----Do it in /Program Files/mariadb/bin folder
--mysql -u root -p

----
--DROP DATABASE bhdb;
--DROP USER 'bhuser'@'localhost';
----

CREATE DATABASE bhdb;
--SHOW DATABASES;

CREATE USER 'bhuser'@'localhost' IDENTIFIED BY 'bhpw';
GRANT ALL PRIVILEGES ON bhdb.* TO 'bhuser'@'localhost';
--select user, host from mysql.user;

USE bhdb;


---- ---- ---- ----
-- TABLE
---- ---- ---- ----
CREATE TABLE behavior_category ( 
    behavior_category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content CHAR(144) NOT NULL,
    UNIQUE KEY category_unique (content)
);

--temp
CREATE TABLE user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email CHAR(144) NOT NULL, 
    nickname CHAR(64) NOT NULL,
    recently_visited DATETIME, 
    register_status CHAR(64)
);

CREATE TABLE behavior_records (
    behavior_records_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    behavior_category_id BIGINT , 
    user_id BIGINT, 
    start_time DATETIME, 
    end_time DATETIME, 
    detail CHAR(144), 
    FOREIGN KEY (behavior_category_id) REFERENCES behavior_category(behavior_category_id), 
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE behavior_priority_category (
    user_id BIGINT,
    priority_idx BIGINT,
    behavior_category_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (behavior_category_id) REFERENCES behavior_category(behavior_category_id)
);
--UNIQUE KEY priority_category_unique (user_id, priority_idx)


---- ---- ---- ----
-- DATA
---- ---- ---- ----
INSERT INTO behavior_category
(content)
VALUES
('일'), 
('휴식'), 
('공부'), 
('잠자기'), 
('식사'), 
('취미'), 
('운동'), 
('뒹굴뒹굴'), 
('함께'), 
('집안일'), 
('나머지'); 

INSERT INTO user
(email, nickname, recently_visited, register_status)
VALUES
('test@test.com', 'test-user', now(), 'normal'),
('devsshyuny@gmail.com', 'sshyuny', now(), 'normal');

INSERT INTO behavior_priority_category
(user_id, priority_idx, behavior_category_id)
VALUES
(1, 1, 3), 
(1, 2, 2), 
(1, 3, 4), 
(1, 4, 5);

--DELETE FROM behavior_priority_category WHERE user_id=1 AND priority_idx=12;