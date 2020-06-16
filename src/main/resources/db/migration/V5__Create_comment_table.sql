CREATE TABLE comment
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    parent_id INTEGER NOT NULL,
    type INT NOT NULL,
    commenter INT NOT NULL,
    create_date BIGINT NOT NULL,
    modify_date BIGINT NOT NULL,
    like_count BIGINT DEFAULT 0
);