DROP TABLE IF EXISTS behavior_category, user, behavior_records, behavior_priority_category;

/* Create Test Table */

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


/* Insert Test Data */

INSERT INTO behavior_category
(content)
VALUES
('test1'), 
('테스트2'), 
('test3'), 
('테스트4');

INSERT INTO user
(email, nickname, recently_visited, register_status)
VALUES
('test@test.com', 'test-user', now(), 'normal');

INSERT INTO behavior_priority_category
(user_id, priority_idx, behavior_category_id)
VALUES
(1, 1, 2), 
(1, 2, 3), 
(1, 3, 4);