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

CREATE TABLE behavior_category (
    behavior_category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content CHAR(144) NOT NULL
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
('devsshyuny@gmail.com', 'sshyuny', now(), 'normal');