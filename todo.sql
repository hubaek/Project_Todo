CREATE TABLE `todo` (
                        `id`	BIGINT	NOT NULL	COMMENT '일정ID',
                        `title`	VARCHAR(400)	NOT NULL	COMMENT '할일제목',
                        `content`	VARCHAR(400)	NOT NULL	COMMENT '할일내용',
                        `name`	VARCHAR(50)	NOT NULL	COMMENT '작성자명',
                        `created_at`	DATETIME	NOT NULL	DEFAULT now()	COMMENT '작성일',
                        `updated_at`	DATETIME	NOT NULL	DEFAULT now()	COMMENT '수정일'
);

ALTER TABLE `todo` ADD CONSTRAINT `PK_TODO` PRIMARY KEY (
                                                         `id`
    );

CREATE TABLE `comment` (
                         `id`	BIGINT	NOT NULL,
                         `reply`	VARCHAR(400)	NULL,
                         `created_at`	DATETIME	NULL,
                         `updated_at`	DATETIME	NULL,
                         `name`	VARCHAR(255)	NULL
);

ALTER TABLE `comment` ADD CONSTRAINT `PK_REPLY` PRIMARY KEY (
                                                           `id`
    );



CREATE TABLE `member` (
                          `id`	BIGINT	NOT NULL,
                          `name`	VARCHAR(50)	NULL,
                          `email`	VARCHAR(50)	NULL,
                          `created_at`	DATETIME	NULL,
                          `updated_at`	DATETIME	NULL
);

ALTER TABLE `member` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
                                                             `id`
    );


