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

CREATE TABLE behavior_priorityofcategory (
    user_id BIGINT, 
    n1 BIGINT, n2 BIGINT, n3 BIGINT, n4 BIGINT, n5 BIGINT,
    n6 BIGINT, n7 BIGINT, n8 BIGINT, n9 BIGINT, n10 BIGINT,
    n11 BIGINT, n12 BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (n1) REFERENCES behavior_category(behavior_category_id), 
    FOREIGN KEY (n2) REFERENCES behavior_category(behavior_category_id), 
    FOREIGN KEY (n3) REFERENCES behavior_category(behavior_category_id), 
    FOREIGN KEY (n4) REFERENCES behavior_category(behavior_category_id), 
    FOREIGN KEY (n5) REFERENCES behavior_category(behavior_category_id), 
    FOREIGN KEY (n6) REFERENCES behavior_category(behavior_category_id), 
    FOREIGN KEY (n7) REFERENCES behavior_category(behavior_category_id), 
    FOREIGN KEY (n8) REFERENCES behavior_category(behavior_category_id), 
    FOREIGN KEY (n9) REFERENCES behavior_category(behavior_category_id), 
    FOREIGN KEY (n10) REFERENCES behavior_category(behavior_category_id), 
    FOREIGN KEY (n11) REFERENCES behavior_category(behavior_category_id), 
    FOREIGN KEY (n12) REFERENCES behavior_category(behavior_category_id)
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

insert into behavior_priorityofcategory (user_id, n1, n2, n3) values ('1', '1', '2', '3');