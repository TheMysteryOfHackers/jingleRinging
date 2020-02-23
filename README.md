一个平时用来练习新知识的前后端分离项目

演示地址：http://dldl.vipgz1.idcfengye.com/LayuiTest

## 相关技术

- 核心框架：Spring Boot

- 权限框架：Apache Shiro

- 持久层框架：MyBatis 和 MyBatis-plus

- 数据库连接池：Alibaba Druid

- 缓存框架：Redis

- 身份验证：JWT

- 日志管理：log4j2

- 视图框架：Spring MVC

- 数据格式：json

- 工具类：Apache Commons、joda-time、fastjson、easypoi

- 支付宝支付接口

  

## 项目结构

 **config 配置包**

 --alipay 支付宝配置

 --mvc MVC

 --mybatis 数据库、mybatisplus自定义公共字段、事务

 --shiro 验证、权限

 **core 基础代码**

 --base 基本controller、entity

 --util 一些工具类

 --vo 目前有基本响应格式和Ztree的entity类

 **oa controller模块**

 --device 设备模块

 --system 系统模块

## 软件需求

- IDEA
- JDK1.8
- MySQL5.5+
- Maven3.5+
- redis

## 本地部署

clone或下载项目，用maven的方式导入到IDEA中，

用mysql执行db文件的sql语句

更改application-dev.yml中的MySQL地址、账号和密码，

有需求可以填写支付宝的商户私钥、支付宝公钥、异步通知的公网ip或网址，同步通知根据前端部署情况填写，

更改redis..properties中的redis地址、端口、密码

用SpringBoot的方式启动项目

