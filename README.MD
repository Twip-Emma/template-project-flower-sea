<!--
 * @Author: 七画一只妖
 * @Date: 2022-05-22 21:37:52
 * @LastEditors: 七画一只妖
 * @LastEditTime: 2022-05-22 22:17:42
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
>- **gateway模块：**
>- **cherry-blossoms：模块**
>- **higanbana-sorrow：模块**

### 已实现的功能
>- **数据库** 内置用户表和一条用户数据，可通过访问`http://localhost:10010/higanbana/index`查询到
>- **消息队列** 内置两个延时消息队列，各有一个监听器，准备了测试单元用于乱序发送不同延时时长的消息
>- **缓存** 内置缓存过期监听器一个，准备了测试单元用来发送有时间限制的缓存消息
>- **数据工厂** 使用抽象工厂设计模式，用于将后端数据整理成`{"message":xxx,"code":xxx,"data":xxx}`返回给前端，适配单个数据，多个简单类型数据，多个复杂类型数据

## 开始使用

### 1.配置数据库
### 2.配置Nacos
### 3.配置缓存Redis
