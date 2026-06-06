USE icampus;

-- 用户表
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    avatar VARCHAR(255),
    signature VARCHAR(255),
    phone VARCHAR(20),
    student_id VARCHAR(20),
    college VARCHAR(100),
    major VARCHAR(100),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 公告表
CREATE TABLE IF NOT EXISTS notices (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    category VARCHAR(50),
    publisher VARCHAR(100),
    publish_time DATETIME,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 二手商品表
CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    price DECIMAL(10,2),
    category VARCHAR(50),
    item_condition VARCHAR(50),
    images VARCHAR(1000),
    contact VARCHAR(100),
    user_id BIGINT,
    status VARCHAR(20),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 预约服务表
CREATE TABLE IF NOT EXISTS reservations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    service_type VARCHAR(50),
    service_name VARCHAR(100),
    reserve_time DATETIME,
    location VARCHAR(200),
    description TEXT,
    images VARCHAR(1000),
    contact VARCHAR(100),
    user_id BIGINT,
    status VARCHAR(20),
    remark TEXT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 消息表
CREATE TABLE IF NOT EXISTS messages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    type VARCHAR(50),
    user_id BIGINT,
    is_read INT DEFAULT 0,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入测试用户 (密码: 123456 的MD5)
INSERT INTO users (username, password, nickname, phone, student_id, college, major) VALUES
('admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '13800138000', '2021001', '计算机学院', '软件工程'),
('test', 'e10adc3949ba59abbe56e057f20f883e', '测试用户', '13800138001', '2021002', '信息学院', '计算机科学');

-- 插入公告数据
INSERT INTO notices (title, content, category, publisher, publish_time) VALUES
('关于2025年春季学期选课的通知', '各位同学，2025年春季学期选课将于3月1日开始，请同学们提前做好选课准备。', '教务通知', '教务处', '2025-02-20 10:00:00'),
('图书馆开放时间调整', '为方便同学们学习，图书馆自3月1日起调整开放时间为7:00-22:00。', '其他', '图书馆', '2025-02-18 14:30:00'),
('校园歌手大赛报名', '第十届校园歌手大赛现在开始报名，欢迎同学们积极参与。', '活动讲座', '学生会', '2025-02-15 09:00:00'),
('2024-2025学年奖学金评定', '2024-2025学年奖学金评定工作即将开始，请关注具体通知。', '奖学金', '学生处', '2025-02-10 16:00:00'),
('心理健康讲座', '本周五下午3点，在报告厅举办心理健康讲座，欢迎参加。', '活动讲座', '心理健康中心', '2025-02-08 11:00:00');

-- 插入二手商品数据
INSERT INTO products (title, description, price, category, item_condition, contact, user_id, status) VALUES
('高等数学教材', '上下册，九成新，笔记较少', 25.00, '书籍教材', '九成新', 'wx: math2025', 2, '在售'),
('iPad Air 4', '64GB，无划痕，配件齐全', 2800.00, '电子产品', '九成新', '电话:13800138001', 2, '在售'),
('电动滑板车', '续航30公里，九成新', 800.00, '生活用品', '九成新', 'wx: scooter2025', 2, '在售'),
('考研英语词汇书', '2025版，全新未使用', 30.00, '书籍教材', '全新', 'wx: kaoyan2025', 2, '在售'),
('无线蓝牙耳机', '小米Air2 SE，功能正常', 120.00, '电子产品', '八成新', 'wx: ear2025', 2, '在售');

-- 插入服务数据
INSERT INTO reservations (service_type, service_name, reserve_time, location, description, contact, user_id, status) VALUES
('selfroom', '自习室预约', '2025-03-05 14:00:00', '图书馆302', '需要安静的学习环境', '13800138001', 2, '待处理'),
('repair', '宿舍报修', '2025-03-03 10:00:00', '3号楼301', '水龙头漏水', '13800138001', 2, '处理中'),
('errands', '快递代取', '2025-03-02 16:00:00', '菜鸟驿站', '取件码: 123456', '13800138001', 2, '已完成');

-- 图片表
CREATE TABLE IF NOT EXISTS images (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(500),
    type VARCHAR(50),
    related_id BIGINT,
    user_id BIGINT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入消息数据
INSERT INTO messages (title, content, type, user_id, is_read) VALUES
('预约已受理', '您的自习室预约已通过审核', '预约通知', 2, 0),
('商品已下架', '您发布的iPad Air 4已被人预订', '二手交易', 2, 1),
('系统通知', '欢迎使用iCampus智慧校园App', '系统通知', 2, 0);
