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
    content VARCHAR(144) NOT NULL,
    UNIQUE KEY category_unique (content)
);

CREATE TABLE user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    kakao_id BIGINT,
    kakao_connected_at DATETIME,
    email VARCHAR(144), 
    nickname VARCHAR(64) NOT NULL,
    recently_visited DATETIME, 
    register_status VARCHAR(64)
);

CREATE TABLE behavior_records (
    behavior_records_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    behavior_category_id BIGINT , 
    user_id BIGINT, 
    start_time DATETIME, 
    end_time DATETIME, 
    detail VARCHAR(144), 
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


---- ---- ---- ----
-- ALTER TABLE
---- ---- ---- ----
--UNIQUE KEY priority_category_unique (user_id, priority_idx)

--DESC behavior_category;
--ALTER TABLE behavior_category MODIFY content VARCHAR(144);
--DESC user;
--ALTER TABLE user MODIFY email VARCHAR(144);
--ALTER TABLE user MODIFY nickname VARCHAR(64);
--ALTER TABLE user MODIFY register_status VARCHAR(64);
--DESC behavior_records;
--ALTER TABLE behavior_records MODIFY detail VARCHAR(144);

--DESC user;
--ALTER TABLE user ADD kakao_user_id BIGINT AFTER user_id;
--ALTER TABLE user CHANGE kakao_user_id kakao_id BIGINT;
--ALTER TABLE user MODIFY COLUMN email VARCHAR(144);
--ALTER TABLE user ADD kakao_connected_at DATETIME AFTER kakao_id;