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
    重点：Spring:validate和messages消息加载机制及国际化解决方案 https://blog.csdn.net/GAOXINXINGgaoxinxing/article/details/92642470
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
### 2.5使用mybatis-generator时生成多余的类（***WithBLOBs）或发现生成的*Mapper类中缺少方法（byPrimaryKey）
    原因是在mybatis-generator看来mysql中没有catalog和schema的层级概念，全是平等的数据库，当自己的库中表名和mysql自带的库中表名重名了，
    可能会先找到mysql自带的表进行生成相关Java类，
    建议：对自动生成的相关Java类进行检查
    详细分析https://www.jianshu.com/p/dbeeac29ff27 看评论
    官方建议http://www.mybatis.org/generator/usage/mysql.html
### 2.6使用fastjson指定序列化方式重写toString方法
    使用fastjson的JSON.toJSONString来重写与其他系统交互时传递的core类里的toString方法
    引入fastjson
    使用注意事项https://segmentfault.com/a/1190000021287520 把常用序列化属性都写上了
    Idea快捷生成serialVersionUID https://www.cnblogs.com/huanshilang/p/11578694.html
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
### 2.13使用common-codec的加密工具类DigestUtils

### 2.14再谈mybatis-generator自动生成POJO、***Mapper及***mapper.xml
    对于多模块中某一模块想使用mybatis-generator：
        例，多模块springboot_module中某一模块springboot要使用mybatis-generator
        应该将模块springboot模块中pom.xml里相关引入另一模块的dependency注掉(如果groupId、artifactId、version在maven中央仓库找不到的话)
        不然会报错。
    对于要生成的数据库表中字段都是主键，会生成以Key为结尾的POJO，使用即可。
    对于生成***WithBLOBs.java文件，在generatorConfig.xml中jdbcConnection标签添加<property name="nullCatalogMeansCurrent" value="true" />即可
### 2.15 配置fastjson作为与前端的消息转换器
    WebMvcConfigurerAdapter过时的替换方法 https://blog.csdn.net/tyvbpq/article/details/83588508
    Fastjson的配置类WebMvcConfig,及fastjson不生效问题 https://segmentfault.com/a/1190000015975405
    为什么设置了SerializerFeature.WriteNullStringAsEmpty，fastjson字段为null时不输出空字符串？ https://blog.csdn.net/u012534163/article/details/88741884
    具体配置详见WebMvcConfig.java的configureMessageConverters及filter-process
    测试方法见/menu/list接口，分别用map和menu对象进行测试，是否将null处理成了""
### 2.16登陆拦截
    拦截机制中Aspect、ControllerAdvice、Interceptor、Fliter之间的区别详解 https://www.cnblogs.com/liushuchen/p/10899658.html
    Spring中的拦截器 —— HandlerInterceptor拦截器 https://www.cnblogs.com/onlymate/p/9563443.html
    Spring Boot拦截器配置拦截登陆 https://blog.csdn.net/qq_30745307/article/details/80974407
### 2.17关于时区、时间的显示
    时间戳：相对于1970年的时间毫秒差；
    时区：全球共用一个时间戳，因为时区不同，会在将时间戳转换为yyy-mm-dd日期时进行时区的加减，产生了不同的时间。
    现分为3块，前端，后端，数据库。
        前端和后端之间用fastjson进行时间转换，所以配置fastjson的日期默认格式和时区。
            fastjson会获取java.util中的TimeZone的默认时区作为返回给前端的时区
        后端和数据库使用jdbc做时间转换，在jdbc的url参数设置serverTimezone为本地时区Asia/Shanghai（也是新版jdbc要求配置的）
## 3.rbac模型实现登陆验证，权限管理(菜单、path)

## 4.rbac_shiro权限验证 （Shiro入门这篇就够了【Shiro的基础知识、回顾URL拦截】https://www.jianshu.com/p/b5a7312123d9）
    主要用到四大功能：：认证、授权、会话管理、加密。
### 4.1shiro功能模块理解
    认证：对用户名、密码的校验。
        用户是否存在；
        密码是否正确；
    授权：给身份认证通过的人授予它可以访问某些资源的权限。
        该用户是否有权访问XXX菜单；
        该用户是否有权访问XXX接口(uri)
    会话管理：使用shiro自带的session会话，不使用Java Servlet的HttpSession
        每个客户端(应该说浏览器，一台电脑两台浏览器，算是两个客户端)访问时创建一个session;
        可以对session设置超时时间，用户可以主动关闭session；
        session里存放用户信息，根据用户信息可以查询拥有的资源；
    加密：对密码提供多种加密方式，支持加盐(sault)(加密因子)
    shiro流程：浏览器发来uri请求，服务器通过shiro过滤器对一切uri进行拦截校验，对/login和logout应该直接放行。
    认证阶段：shiro首先判断当前浏览器传入的jsessionid是否存在，
        存在时，说明当前浏览器已有用户登陆(自己或其他用户)，
        不存在，当前浏览器无登陆用户，因为/login直接放行,进入到controller进行登陆判断
    授权阶段，shiro首先判断当前浏览器传入的jsessionid是否存在
    过滤器里有判断
### 4.2认证配置 （Springboot整合Shiro：简洁的身份认证： https://www.jianshu.com/p/a711961b07db）
        1)在pom.xml中添加依赖shiro-spring、shiro-core、shiro-web、shiro-ehcache、shiro-quartz
        2)在ShiroConfig中，shiro的web过滤器(filter)配置，相当于Spring中的拦截器 —— HandlerInterceptor拦截器
            2.1)配置SecurityManager安全管理器：所有具体的交互都通过SecurityManager进行控制；它管理着所有Subject、且负责进行认证和授权、及会话、缓存的管理
                2.1.1)配置ShiroRealm域：数据源，即用户名和密码获取方式，这里是通过userService连接数据库获取
                    2.1.1.1)继承AuthorizingRealm实现抽象方法doGetAuthenticationInfo，使用传入的userService
                    获取用户信息
                    2.1.1.2)配置凭证匹配器：凭证，即密码。就是对于前端传入的密码进行加密的凭证配置，加密完成后，被Authenticator获取后与从数据库获取的进行对比
            2.2)将SecurityManager设置到运行环境中(并与运行环境中的subject绑定)
        3)创建用户，存入数据库密码时，使用ShiroPwdEncryptUtil，原理是使用SimpleHash加密。
        4)认证逻辑
            4.1)先判断是否上次登陆5次仍失败时记录的时间距离现在是否超过5分钟
            4.2)将前端传来的用户名、密码传给shiro的UsernamePasswordToken，供置ShiroRealm域获取
            4.3)从环境中使用SecurityUtils获取subject,如果是被认证过的，则说明已有用户登陆
            4.4)subject调用login方法并传入token(UsernamePasswordToken产生的)，如果失败，可以捕获各种异常（实际上是让SecurityManager的login方法做的）
            4.5)用subject的isAuthenticated()方法验证是否登陆成功
        5)异常处理IncorrectCredentialsException密码不正确、UnknownAccountException账号不存在、AuthenticationException状态不正常
        注：处理逻辑放在了Controller，的登陆接口/login中
### 4.3授权配置
        整体流程：在资源未请求到controller之前，对资源进行过滤,一般自定义过滤器，对于过滤的是资源是菜单/path/按钮，需要自定义permission，然后通过realm获取数据库中正确的资源信息进行对比。
        自定义过滤器:判断当前浏览器是否有用户登陆，如果有，再通过realm获取正确的数据，进行比对，没有直接返回需要登陆。
        自定义permission：定义授权的对象是什么，比如授权path,那么属性就有method和uri。
        注：处理逻辑放在了过滤器中，因为需要在执行到controller之前进行处理
        在没有shiro时，使用的是拦截器，即在执行到controller之前，将资源和httpsession里的授权资源进行对比对比成功，才放行，继续执行Controller          
### 4.4Realm域
        ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
        认证：该处理是从前端拿到用户名，然后根据用户名从数据库拿到用户信息，放到规定的AuthenticationInfo，作为数据源，让Authenticator获取，然后与前端获取的用户名密码做对比。
        授权：该处理是从PrincipalCollection获取用户信息，然后根据用户ID到数据库读取授权信息，放到规定的AuthorizationInfo，作为数据源，让Authorizator获取，然后与前端获取的url做对比。
        注: 在没有shiro时，LoginController里将用户和权限信息在登陆的时候，就会保存到httpsession会话属性中，待拦截器获取。
### 4.5 ShiroConfigEntity这个配置类里的变量名是和shiro-config.properties对应的，当写入信息到shiro-config.properties，会在启动springboot的时候读取到ShiroConfigEntity类中
### 4.6缓存（ehcache/redis）
    用户登陆成功后，把用户信息和权限信息缓存起来，然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
    shiro-all默认集成了ehcache的配置文件,也可以添加配置.
    理解
        1)springboot集成shiro的ehcache的时候，要先集成ehcache然后再将ehcache设置为共享，共享给shiro,这样当shiro配置cacheManager的时候就可以直接使用了
        2)设置realm为开启缓存，并指定缓存名称(shiro-ehcache.xml)，
        3)缓存清空
          如果用户正常退出，缓存自动清空。
          如果用户非正常退出，缓存自动清空。（现在版本的shiro都是支持这个的，所以这个我们不用管了）
    springboot集成ehcache https://blog.csdn.net/hudchsdi/article/details/93770226
    springboot集成shiro-ehcache，配置权限缓存与系统本身集成的ehcache缓存冲突  https://blog.csdn.net/AI_jqy/article/details/90042021
    springboot整合shiro-ehcache缓存 https://blog.csdn.net/qq_34021712/article/details/80309246
### 4.3会话管理  
        2.1.2)配置SessionManager会话处理器
                2.1.2.1)sessionDao
                2.1.2.2)会话cookie模板  
### 4.4缓存会话管理
    2.1.3)配置cacheManager缓存管理器
    
## 5总结
    学完整个shiro配置之后关于配置的总结
### 5.1 Config配置类
    使用@Configuration注解一个类表明该类可以被Spring IoC容器用作bean定义的来源
    1)@Configuration和@bean注解要配合，缺一不可
    2)@Bean()重新起名
    3)IOC依赖注入的理解https://blog.csdn.net/javazejian/article/details/54561302
    
### 6.1activiti工作流
#### 6.1.1初识activiti
    
    https://blog.csdn.net/zxxz5201314/article/details/103166771?spm=a2c4e.10696291.0.0.577e19a4IjJWFd
    关于H2database https://blog.csdn.net/chenhao_c_h/article/details/80332260
    dependencies与dependencyManagement的是干嘛的 https://blog.csdn.net/liutengteng130/article/details/46991829
    关于activiti-spring-boot-starter 的说明https://www.jianshu.com/p/d2075b17bf38
    srpingboot +shiro + activiti model集成注意点 https://www.jianshu.com/p/70901f745927
    关于transactionManager 事务https://my.oschina.net/wjme/blog/1439364
    配置结果为ActivitiConfig，运行后数据库自动生成25张表
####6.1.2 初识25张表
    Activiti数据库表结构(表详细版) https://blog.csdn.net/hj7jay/article/details/51302829
    Java Activiti(3)--数据库说明 https://blog.csdn.net/lovoo/article/details/78187078
    【Activiti7】数据库表介绍 https://blog.csdn.net/zhouchenjun001/article/details/103629559
####6.1.3 bpm画图(流程定义)
    bpm的理解 https://blog.csdn.net/qq_35463719/article/details/82967287
    idea actiBPM插件生成png文件 (解决没有Diagrams或Designer选项问题) https://www.cnblogs.com/jpfss/p/11091376.html
####6.1.4 Activiti核心详解
    Activiti7的核心详解 https://segmentfault.com/a/1190000021129168
####6.1.5 activiti整体开发流程说明 https://blog.csdn.net/Marion158/article/details/87728172 
    1）activiti 部署流程定义的各种方式 
        https://blog.csdn.net/ctwy291314/article/details/81285408
        https://blog.csdn.net/qq_32541407/article/details/76768651
        Activiti流程文件部署过程解析 https://www.jb51.net/article/182240.htm
        流程管理定义（查看流程图附件） https://blog.csdn.net/qq_20042935/article/details/97899062
    2）启动流程
        Activiti流程实例启动 开启流程实例 https://blog.csdn.net/zlt995768025/article/details/78350296
        流程启动的相关操作 https://blog.csdn.net/changqi008/article/details/83033956
    3）流程变量
        流程变量的设置与应用 https://blog.csdn.net/changqi008/article/details/83040888
        Activiti工作流之流程变量 https://blog.csdn.net/ip_JL/article/details/83684023