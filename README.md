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
### 2.7通过jad反编译工具理解枚举
    深入理解枚举https://blog.csdn.net/javazejian/article/details/71333103
    安装jad 步骤文档：jad配置到idea的external tools中.note
            链接：http://note.youdao.com/noteshare?id=f5d5406f86510343f233d6afd8e99f14&sub=B6961BE8556B43CD9E9672B418FBC369
### 2.8自定义异常及异常全局处理
    自定义异常及捕获：
        1)自定义一个异常类ServiceException，需要继承RuntimeException
        2)使用全局异常进行捕获GlobalExceptionHandler
        3)在impl实现类的方法中抛出异常(最好携带异常信息)
    在2.5mybatis-generator中提到全局异常处理，使用@ControllerAdvice声明本类为全局异常处理类(全局异常捕捉处理器)， @ExceptionHandler指定异常类的类对象，@ResponseStatus指定异常返回状态(通常为OK，为了正确显示异常提示信息)
    异常全局处理https://sq.163yun.com/blog/article/187288338344722432
    完整的自定义异常设计https://blog.csdn.net/winy_lm/article/details/51064320     
### 2.9引入java第2库 commons-lang3 (commons-long已经过时，springframework是提供给spring用的)
    主要使用该库的StringUtils工具类https://blog.csdn.net/f641385712/article/details/82469877
    其他工具类介绍https://blog.csdn.net/f641385712/article/details/82468927
### 2.10自定义业务异常ServiceException

### 2.11jackson和fastjson,都是json工具包，能实现Java对象和json串相互转换

    调用方便性而言：
    。FastJSON提供了大量静态方法，调用简洁方便
    。Jackson须实例化类，调用相对繁琐，可通过封装成JSON工具类简化调用
 
    性能而言：
    。FastJSON反序列化的性能略差，对于256k的json字符串，平均700ms
    。Jackson 的 data binding反序列化的性能稍好，对于256k的json字符串，平均600ms
    。两者的序列化性能基本相同，对于256k的json字符串，平均140ms
    。相对data binding方式（ObjectMapper.writeValueAsString()），Jackson的流输出方式（JsonGenerator.writeObject()）性能稍好，平均130ms
    jackson是spring boot start web会自动引入https://www.jianshu.com/p/b804874b7a69
### 2.12序列化和反序列化
    含义：序列化——对内存中对象进行freeze冻结，
        1).存储到本地磁盘
        2).远程传输到另一台机器
    反序列化——对读/收到的已被序列化的对象
        1).解析到对应数据结构的内存中
        2).解析成对应结构的对象

    