package com.impv.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Description: 定时任务 Demo
 */
public class TestDemo extends QuartzJobBean {

    private Log logger = LogFactory.getLog(TestDemo.class);

    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("TestDemo 执行了............." + jobExecutionContext.getTrigger().getKey().getName());

        //Spring定时器配置文件中指定的 spring容器的key
        ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getJobDetail().getJobDataMap().get("applicationContext");

        logger.info("获取到的Spring容器是： " + applicationContext);
    }
}
