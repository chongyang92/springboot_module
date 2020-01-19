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
### 1.1 master分支（历史）
    很纯粹的spring boot,当前只有web环境，能启动8080端口，无代码
### 1.2 mybatis分支（历史）
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
## 2.非rbac模型的登录、权限（菜单、path）
    数据库脚本在sqlScripts下
    数据库表设计时，REAL_ORG_ID字段通过mybatis-generator插件会生成realOrgId属性
    如果是REAL_ORGID,会生成realOrgid，推荐使用REAL_ORG_ID
### 2.1validation国际化
    https://blog.csdn.net/qq78442761/article/details/88388787
    设置IDEA中properties文件显示中文https://www.cnblogs.com/shaohsiung/p/9581077.html
    https://blog.csdn.net/GAOXINXINGgaoxinxing/article/details/92642470
    http://miao.blog/article/spring-boot-validator-i18n
### 2.2create_time和update_time很多表都有，统一管理
    在建表时设置
    `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    注意：在使用更新数据时，一定要使用updateByPrimaryKeySelective更新有变化的字段，而不能用updateByPrimaryKey，会导致create_time也更新为当前时间
    在navicat设置参考https://www.jb51.net/article/170703.htm
### 2.3hibernate validation校验
    https://blog.csdn.net/f641385712/article/details/97621783
    校验出为空,会通过ConstraintViolationException异常，里面存了异常的key，通过捕获这个全局异常取出key，然后到validationmessages.properaties获取对应的值
    GlobalExceptionHandler异常类，spring boot不会捕获ConstraintViolationException异常
### 2.4使用BeanCopier做高效浅拷贝
    深拷贝与浅拷贝https://blog.csdn.net/weixin_33889245/article/details/92531051
    bean拷贝，各工具对比https://blog.csdn.net/Nirrvana0722/article/details/90600690
    beanCopier的使用https://blog.csdn.net/qq_32317661/article/details/84393465
### 2.5使用mybatis-generator时生成多余的类（***WithBLOBs和***Key）或发现生成的*Mapper类中缺少方法（byPrimaryKey）
    原因是在mybatis-generator看来mysql中没有catalog和schema的层级概念，全是平等的数据库，当自己的库中表名和mysql自带的库中表名重名了，
    可能会先找到mysql自带的表进行生成相关Java类，
    建议：对自动生成的相关Java类进行检查
    详细分析https://www.jianshu.com/p/dbeeac29ff27 看评论
    官方建议http://www.mybatis.org/generator/usage/mysql.html
### 2.6使用fastjson指定序列化方式重写toString方法
    使用fastjson的JSON.toJSONString来重写于其他系统交互时传递的core类里的toString方法
    引入fastjson
    使用注意事项https://segmentfault.com/a/1190000021287520 把常用序列化属性都写上了
    