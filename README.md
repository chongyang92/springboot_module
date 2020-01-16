# springboot_model分为多个模块
## 0.uid-generator模块
    **_全局唯一ID生成器_**
    1. 介绍详见 https://github.com/baidu/uid-generator/blob/master/README.zh_cn.md
    2. 集成到springboot中详见 https://www.jianshu.com/p/947bff7be2da
    及 https://blog.csdn.net/m0_37367413/article/details/87341352
    https://blog.csdn.net/paincupid/article/details/89605554
    注：使用cached-uid-spring.xml注入bean
    3. 父工程和子模块创建详见 https://blog.csdn.net/wnf2018/article/details/80655153
    4. 同一工程不同模块之间的调用详见 https://blog.csdn.net/menggudaoke/article/details/90601008
## 1.spring boot模块  
**_不同分支是不同的集成环境_**
### 1.1 master分支
    很纯粹的spring boot,当前只有web环境，能启动8080端口，无代码
### 1.2 mybatis分支
    基于master，将mybatis集成到spring boot环境中
    1.mapper生成器：
        generatorConfig.xml:http://mybatis.org/generator/中xml找配置文件
        pom.xml:mybatis-generator-maven-plugin
    2.集成mybatis
        MybatisConfig.java:mapperScannerConfigurer、SqlSessionFactoryBean
        pom.xml:druid-spring-boot-starter、mysql-connector-java、spring-boot-starter-jdbc、mybatis、mybatis-spring
        application.properties:spring.datasource.url、spring.datasource.username、spring.datasource.password、spring.datasource.driver-class-name
    3.从postman发一次对org机构表请求的查询
        SET FOREIGN_KEY_CHECKS=0;
        
        -- ----------------------------
        -- Table structure for `sys_org`
        -- ----------------------------
        DROP TABLE IF EXISTS `sys_org`;
        CREATE TABLE `sys_org` (
          `ORG_ID` varchar(50) NOT NULL COMMENT '机构ID',
          `PARENT_ORG_ID` varchar(50) NOT NULL COMMENT '父机构ID',
          `ORG_NAME` varchar(200) NOT NULL COMMENT '机构名称',
          `ORG_NUMBER` varchar(20) NOT NULL COMMENT '机构号',
          `STATUS` varchar(10) NOT NULL COMMENT '状态',
          `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
          `UPDATE_TIME` datetime NOT NULL COMMENT '更新时间',
          PRIMARY KEY (`ORG_ID`)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
        
        -- ----------------------------
        -- Records of sys_org
        -- ----------------------------
        INSERT INTO `sys_org` VALUES ('001', 'Root', '中国银行', '100001', 'NORMAL', '2020-01-16 16:20:26', '2020-01-16 16:20:31');
        INSERT INTO `sys_org` VALUES ('002', '001０００', '中国银行北京分行', '100002', 'NORMAL', '2020-01-16 17:11:45', '2020-01-16 17:11:50');
