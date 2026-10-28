# MyBlog - 持久化留言板系统

## 📖 项目简介
本项目是 MyBlog 项目的 Week 06 迭代版本。在此之前，MyBlog 的数据存储依赖运行时的内存（``ArrayList``），一旦应用重启，留言数据就会彻底丢失。
本周的核心目标是**为 MyBlog 建造一座“记忆宫殿”** —— 引入 MySQL 数据库与 MyBatis Plus 持久层框架，真正实现数据穿越进程重启的持久化存储。

## 🛠️ 技术栈
- **后端框架**：Spring Boot
- **持久层框架**：MyBatis Plus
- **数据库**：MySQL (8.0+)

## 📡 API 接口说明

| 方法 | 路径 | 描述 | 请求体示例 |
| :--- | :--- | :--- | :--- |
| GET | ``/messages`` | 获取全部留言（支持关键词过滤） | 无 |
| GET | ``/messages?keyword=xxx`` | 按关键词搜索留言 | 无 |
| POST | ``/messages`` | 创建一条新留言 | ``{"content": "Hello World"}`` |

## 🚀 本地运行指南

### 1. 数据库准备
请先在本地 MySQL 中执行以下 SQL 脚本建库建表：
``````sql
CREATE DATABASE IF NOT EXISTS myblog DEFAULT CHARACTER SET utf8mb4;
USE myblog;

CREATE TABLE IF NOT EXISTS message (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(1000) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
