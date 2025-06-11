-- 创建数据库 (如果不存在)
CREATE DATABASE IF NOT EXISTS `hospital_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用该数据库
USE `hospital_db`;

-- ----------------------------
-- 按安全顺序删除所有旧表 (如果需要重置)
-- ----------------------------
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `prescription_details`;
DROP TABLE IF EXISTS `patient_lab_tests`;
DROP TABLE IF EXISTS `prescriptions`;
DROP TABLE IF EXISTS `patients`;
DROP TABLE IF EXISTS `doctors`;
DROP TABLE IF EXISTS `departments`;
DROP TABLE IF EXISTS `wards`;
DROP TABLE IF EXISTS `lab_items`;
DROP TABLE IF EXISTS `medicines`;
SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- 按正确顺序重新创建所有新表
-- ----------------------------
-- 1. 基础数据表 (不依赖其他表)
CREATE TABLE `departments` ( `id` INT PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(50) NOT NULL, `location` VARCHAR(100), `tel` VARCHAR(20) ) COMMENT='科室信息表';
CREATE TABLE `wards` ( `id` INT PRIMARY KEY AUTO_INCREMENT, `ward_no` VARCHAR(20) NOT NULL UNIQUE, `total_beds` INT NOT NULL, `used_beds` INT NOT NULL DEFAULT 0 ) COMMENT='病房信息表';
CREATE TABLE `medicines` ( `id` INT PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(100) NOT NULL, `specification` VARCHAR(100), `manufacturer` VARCHAR(100), `unit_price` DECIMAL(10, 2) NOT NULL ) COMMENT='药品信息表';
CREATE TABLE `lab_items` ( `id` INT PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(100) NOT NULL, `department_name` VARCHAR(100), `price` DECIMAL(10, 2) NOT NULL ) COMMENT='检验项目字典表';

-- 2. 依赖基础数据的表
CREATE TABLE `doctors` ( `id` INT PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(50) NOT NULL, `department_id` INT NOT NULL, `title` VARCHAR(50), FOREIGN KEY (`department_id`) REFERENCES `departments`(`id`) ) COMMENT='医生信息表';
CREATE TABLE `patients` ( `id` INT PRIMARY KEY AUTO_INCREMENT, `name` VARCHAR(50) NOT NULL, `gender` VARCHAR(5), `age` INT, `id_card` VARCHAR(18) NOT NULL UNIQUE, `phone` VARCHAR(15), `address` TEXT, `allergy_history` TEXT, `admission_date` DATETIME, `discharge_date` DATETIME, `status` VARCHAR(20), `department_id` INT, `doctor_id` INT, `ward_id` INT, FOREIGN KEY (`department_id`) REFERENCES `departments`(`id`), FOREIGN KEY (`doctor_id`) REFERENCES `doctors`(`id`), FOREIGN KEY (`ward_id`) REFERENCES `wards`(`id`) ) COMMENT='病人信息表';

-- 3. 依赖病人医生等的业务表
CREATE TABLE `prescriptions` ( `id` INT PRIMARY KEY AUTO_INCREMENT, `patient_id` INT NOT NULL, `doctor_id` INT, `diagnosis` TEXT, `created_at` DATETIME NOT NULL, `total_fee` DECIMAL(10, 2), FOREIGN KEY (`patient_id`) REFERENCES `patients`(`id`), FOREIGN KEY (`doctor_id`) REFERENCES `doctors`(`id`) ) COMMENT='处方主表';
CREATE TABLE `patient_lab_tests` ( `id` INT PRIMARY KEY AUTO_INCREMENT, `patient_id` INT NOT NULL, `doctor_id` INT, `lab_item_id` INT NOT NULL, `test_time` DATETIME NOT NULL, `result` TEXT, `fee` DECIMAL(10, 2) NOT NULL, FOREIGN KEY (`patient_id`) REFERENCES `patients`(`id`), FOREIGN KEY (`doctor_id`) REFERENCES `doctors`(`id`), FOREIGN KEY (`lab_item_id`) REFERENCES `lab_items`(`id`) ) COMMENT='病人检验记录表';
CREATE TABLE `prescription_details` ( `id` INT PRIMARY KEY AUTO_INCREMENT, `prescription_id` INT NOT NULL, `medicine_id` INT NOT NULL, `quantity` INT NOT NULL, `notes` VARCHAR(255), `subtotal` DECIMAL(10, 2) NOT NULL, FOREIGN KEY (`prescription_id`) REFERENCES `prescriptions`(`id`), FOREIGN KEY (`medicine_id`) REFERENCES `medicines`(`id`) ) COMMENT='处方详情表';