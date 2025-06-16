# 住院管理信息系统

这是一个基于 Spring Boot 和 JSP 技术栈实现的简易住院管理信息系统，是为课程设计而开发的全功能Web应用。系统涵盖了从病人入院登记、医嘱开立、到最终出院结算的全流程，并实现了基于角色的权限管理和数据隔离。

---

## ✨ 功能列表 (Features)

本系统包含两大角色：管理员和医生。

#### 👨‍⚕️ 管理员 (Admin)
- **登录系统**：拥有独立的后台管理仪表盘。
- **基础数据维护**：对系统的核心数据进行增、删、改、查操作。
  - 科室管理
  - 医生管理
  - 病房管理
  - 药品库管理
  - 检验项目管理
- **用户账户管理**：在创建医生档案的同时，为其创建并关联可登录的系统账户。
- **病人入院登记**：负责录入新病人信息，并将其分配给指定的主治医生和病房。

#### 👩‍⚕️ 医生 (Doctor)
- **登录系统**：拥有专属的工作台界面。
- **数据隔离**：登录后，只能看到由自己主管的病人列表，保证了数据的私密性和安全性。
- **病人信息总览**：查看名下所有病人的核心信息。
- **查看病人详情**：点击病人可进入详情页，全面了解病人的基本信息、历史处方和历史检验单。
- **开立处方**：为住院病人开具电子处方，支持动态添加多种药品。
- **新增检验项目**：为住院病人开具检验申请单。
- **办理出院**：在病人出院前，可进入结算页面，系统会自动汇总该病人的所有处方和检验费用，完成出院流程。

---

## 🚀 技术栈 (Tech Stack)

* **后端**: Spring Boot 3.x, Spring Data JPA (Hibernate), 自定义会话过滤器 (Servlet Filter)
* **前端**: JSP, JSTL, JavaScript (ES6+), CSS3
* **数据库**: MySQL 8.x
* **构建工具**: Maven
* **版本控制**: Git & GitHub

---

## 🏁 快速开始 (Quick Start)

请遵循以下步骤在本地运行本项目。

### 1. 环境准备
确保本地环境已安装以下软件：
- JDK 17 或更高版本
- Maven 3.6 或更高版本
- MySQL 8.x

### 2. 克隆项目
```bash
git clone [https://github.com/Leefy26/hospital-management-system.git](https://github.com/Leefy26/hospital-management-system.git)
```

### 3. 数据库设置
本项目支持两种数据库初始化方式：

#### 方式A (自动建表 - 推荐)
1.  在本地 MySQL 中手动创建一个空的数据库：`CREATE DATABASE hospital_db;`
2.  打开 `src/main/resources/application.properties` 文件。
3.  确保 `spring.jpa.hibernate.ddl-auto` 的值被设置为 `create`。
4.  修改 `spring.datasource.username` 和 `spring.datasource.password` 为自己的数据库用户名和密码。
5.  启动应用，JPA/Hibernate 会自动为您创建所有的表结构。

#### 方式B (手动执行脚本)
可以执行项目根目录下的 `schema.sql` 脚本来一次性创建所有表。

### 4. 运行项目
1.  在 IntelliJ IDEA 中打开项目，等待 Maven 加载完所有依赖。
2.  直接运行主启动类 `SystemApplication.java`。
3.  程序启动时，`DataInitializer` 会自动在空的`users`表中创建一个默认管理员账户。

### 5. 初始账户
- **登录地址**: `http://localhost:8080/login`
- **管理员用户名**: `admin`
- **管理员密码**: `123456`

---


## 💾 数据备份与恢复 (Backup & Recovery)

项目根目录提供了一个 `backup.bat` (Windows) 脚本用于数据库的全量冷备份。

首次使用前，需根据模板在本地创建 `backup_config.bat` 并填入您的私密配置（数据库密码和`mysqldump.exe`路径）。此文件已被 `.gitignore` 忽略，不会上传至代码仓库。

配置完成后，直接双击 `backup.bat` 即可在指定路径下生成带时间戳的 `.sql` 备份文件。

---

## 👨‍💻 作者

- [LeeFY]

*(LeeFY)*
