CREATE TABLE question
(
    title VARCHAR(50),
    description TEXT,
    create_time BIGINT,
    modified_time BIGINT,
    creator INT,
    comment_count INT DEFAULT 0,
    view_count INT DEFAULT 0,
    like_count INT DEFAULT 0,
    tag VARCHAR(256)
);
