--Do it in /Program Files/pmariadb/bin folder
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

--temp
CREATE TABLE behavior_category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    content CHAR(144) NOT NULL, 
    recently_used DATETIME
);

--temp
INSERT INTO behavior_category
(user_id, content, recently_used)
VALUES
(1, 'study', '2022-09-14 20:33:00'), 
(1, 'hangout', '2022-09-14 21:33:00'), 
(1, 'work', '2022-09-14 22:33:00'), 
(1, 'rest', '2022-09-14 23:33:00');