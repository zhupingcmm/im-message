-- 用户表
CREATE TABLE user (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      user_id VARCHAR(255) NOT NULL,
                      username VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      last_updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 用户组表
CREATE TABLE user_group (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            group_name VARCHAR(255) NOT NULL,
                            create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            last_updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 用户-用户组关联表
CREATE TABLE user_user_group (
                                 id INT PRIMARY KEY AUTO_INCREMENT,
                                 user_id INT,
                                 group_id INT,
                                 create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 last_updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 FOREIGN KEY (user_id) REFERENCES user(id),
                                 FOREIGN KEY (group_id) REFERENCES user_group(id)
);
