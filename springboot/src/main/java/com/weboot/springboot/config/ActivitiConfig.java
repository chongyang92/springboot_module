package com.weboot.springboot.config;

import org.activiti.engine.*;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class ActivitiConfig {

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(DataSource dataSource, DataSourceTransactionManager transactionManager){
        SpringProcessEngineConfiguration spec = new SpringProcessEngineConfiguration();
        //和springboot的使用同一个数据源，即application.properties中spring.datasource
        spec.setDataSource(dataSource);
        /**
        false（默认）：检查数据库表的版本和依赖库的版本， 如果版本不匹配就抛出异常。
        true: 构建流程引擎时，执行检查，如果需要就执行更新。 如果表不存在，就创建。
        create-drop: 构建流程引擎时创建数据库表， 关闭流程引擎时删除这些表。
        drop-create：先删除表再创建表。
        create: 构建流程引擎时创建数据库表， 关闭流程引擎时不删除这些表。
        */
        spec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //Activiti与程序中的其他操作共用事务，即activiti与spring整合 https://my.oschina.net/wjme/blog/1439364
        spec.setTransactionManager(transactionManager);
        /**
         *  ACT_HI_* : “HI”代表“History”（历史），这些表中保存的都是历史数据，比如执行过的流程实例、变量、任务，等等。Activit默认提供了4种历史级别：
         *      none: 不保存任何历史记录，可以提高系统性能；
         *      activity：保存所有的流程实例、任务、活动信息；
         *      audit：也是Activiti的默认级别，保存所有的流程实例、任务、活动、表单属性；
         *      full：最完整的历史记录，除了包含audit级别的信息之外还能保存详细，例如：流程变量。
         * 对于几种级别根据对功能的要求选择，如果需要日后跟踪详细可以开启full。
         */
        //保存历史数据级别设置为full最高级别，便于历史数据的追溯
        spec.setHistoryLevel(HistoryLevel.FULL);
        //JobExecutor是管理几个线程计时器的组成部分,JobExecutor对多线程的处理较为笨重缓慢
        spec.setAsyncExecutorActivate(true);////定义为true，使用AsyncExecutor代替默认的JobExecutor;
        return spec;
    }

    //工作流流程引擎
    @Bean
    public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration processEngineConfiguration) {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration);
        return processEngineFactoryBean;
    }

    //工作流仓储服务/管理流程定义,可以用来部署流程
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {//这里的参数就是上面processEngine方法名
        return processEngine.getRepositoryService();
    }

    //工作流历史数据服务
    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    //工作流运行服务
    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    //工作流任务服务
    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    //新的activiti7删除了identityService工作流唯一服务（用户服务） 和formService表单服务

}
