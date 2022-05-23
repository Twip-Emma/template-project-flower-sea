<!--
 * @Author: 七画一只妖
 * @Date: 2022-05-22 21:37:52
 * @LastEditors: 七画一只妖
 * @LastEditTime: 2022-05-23 11:23:20
 * @Description: file content
-->
# 花海-模板工程

## 介绍以及Q/A

### 这是什么？
>- 这是一个 **SpringCloud** 工程，搭建了基本的环境以及配置好了几大常用服务的设置和环境。

### 为什么用这个？谁可以用？
>- 主要是我觉得每次新建一个SpringCloud挺麻烦的，而且搞来搞去都是那几个组件，干脆就做一个模板，想用的时候直接拉下来进行二次加工
>- 本工程没有太多牛逼的、新鲜的技术，且使用的是较老版本的SpringBoot，稳定，基本没有环境问题，适合SpringCloud初学者使用，或者想快速搭建SpringCloud环境的人使用。

### 用到了哪些技术/依赖/服务？
>- SpringCloud、SpringBoot、MyBatis-plus、MySQL、Nacos、Fastjson、RabbitMQ、SpringAMQP、Seata、Redis、Lombok

## 项目基本信息
### 依赖版本
#### 项目内依赖
>- Java（11）
>- Maven（15）
>- SpringBoot（2.3.9.RELEASE）
>- SpringCloud（Hoxton.SR10）
>- MySQL（5.1.47）
>- MyBatis-plus（3.4.2）
>- Nacos（2.2.5.RELEASE）
>- Fastjson（1.2.71）
>- SpringAMQP（2.3.9.RELEASE）
>- Seata（1.4.2）
#### 项目外服务
>- Nacos（1.4.1）
>- RabbitMQ（3.9.12）
>- Redis（5.0.10）
>- Seata（1.4.2）

### 项目内模块
>- **api-common模块：**
   > 代表公共模块内含：数据工厂、公共变量、实体类、远程调用client、枚举等

>- **gateway模块：**
   > 代表网关：已实现：服务映射、跨域

>- **cherry-blossoms：模块**
   > 消费者/生产者A

>- **higanbana-sorrow：模块**
   > 消费者/生产者B

### 已实现的功能
>- **数据库** 内置用户表和一条用户数据，可通过访问`http://localhost:10010/higanbana/index`查询到
>- **消息队列** 内置两个延时消息队列，各有一个监听器，准备了测试单元用于乱序发送不同延时时长的消息
>- **缓存** 内置缓存过期监听器一个，准备了测试单元用来发送有时间限制的缓存消息
>- **数据工厂** 使用抽象工厂设计模式，用于将后端数据整理成`{"message":xxx,"code":xxx,"data":xxx}`返回给前端，适配单个数据，多个简单类型数据，多个复杂类型数据

## 开始使用

### 1.配置数据库
**第一步、创建数据库，命名为flower_sea**

**第二步、在这个数据库下执行以下SQL语句**
~~~sql
/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : flower_sea

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 23/05/2022 10:43:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_sex` int NULL DEFAULT NULL,
  `user_age` int NULL DEFAULT NULL,
  `user_mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('123', '张三', 1, 19, 'zhangsan@163.com');

SET FOREIGN_KEY_CHECKS = 1;
~~~
### 2.配置Nacos
**第一步、设置Nacos端口号为8848**

**第二步、创建三个配置文分别命名**
>- higanbanaservice
>- cherryservice
>- flowerseagateway

**第三步、为higanbanaservice配置文件编写内容**
~~~yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/flower_sea?useSSL=false&allowPublicKeyRetrieval=true
    username: # 数据库账户
    password: # 数据库密码
    driver-class-name: com.mysql.jdbc.Driver
  rabbitmq:
    host:  # 主机名
    port:  # 端口
    virtual-host:  # 虚拟主机
    username:  # 用户名
    password:  # 密码
    cache:
      channel:
        checkout-timeout: 3000000
    template:
      receive-timeout: 3000000
    listener:
      simple:
        prefetch: 1
~~~

**第四步、为cherryservice配置文件编写内容**
~~~yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/flower_sea?useSSL=false&allowPublicKeyRetrieval=true
    username: # 数据库账户
    password: # 数据库密码
    driver-class-name: com.mysql.jdbc.Driver
~~~

**第五步、为flowerseagateway配置文件编写内容**
~~~yaml
logging:
  level:
    com.seckill: debug
  pattern:
    dateformat: MM-dd HH:mm:ss:SSS
spring:
  cloud:
    gateway:
      routes:
        - id: higanbanaservice
          uri: lb://higanbanaservice
          predicates:
            - Path=/higanbana/**
        - id: cherryservice
          uri: lb://cherryservice
          predicates:
            - Path=/cherry/**
      globalcors:
        add-to-simple-url-handler-mapping: true
        cors-configurations:
          '[/**]':
            allowedOrigins: "*"
            allowedHeaders: "*"
            allowedMethods: "*"
            allowCredentials: true
            maxAge: 360000
~~~
### 3.配置缓存Redis
**方法一、进入配置文件**
> notify-keyspace-events ""

修改为

> notify-keyspace-events Ex

**方法二、打开redis-cli**

输入

> config set notify-keyspace-events Ex

### 4.配置模块内配置文件
**第一步、为higanbana、cherry、gateway模块的resources目录下创建bootstrap.yml文件并填写一下内容**
~~~yml
spring:
  application:
    name: higanbanaservice、cherryservice、flowerseagateway、 # 服务名称，不同模块用对应的服务名称（三选一）
  cloud:
    nacos:
      server-addr: localhost:8848 # Nacos地址
      config:
        file-extension: yaml # 文件后缀名
~~~

**第二步、为higanbana、cherry模块的resources目录下创建application.yml文件并填写一下内容**
~~~yml
server:
  port: 0
~~~

**第三步、为gateway模块的resources目录下创建application.yml文件并填写一下内容**
~~~yml
server:
  port: 10010
~~~