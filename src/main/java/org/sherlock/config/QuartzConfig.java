//package org.sherlock.config;
//
//import org.quartz.*;
//import org.sherlock.utils.QuartzDelFile;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class QuartzConfig {
//    @Value("${del.file.quartz.cron}")
//    private String delFile;
//
//    /**
//     * 创建定时任务
//     */
//    @Bean
//    public JobDetail quartzDelFile() {
//        JobDetail jobDetail = JobBuilder.newJob(QuartzDelFile.class)
//                .withIdentity("quartzDelFile", "QUARTZ_DEL")
//                .usingJobData("DelFile", "D:\\idea\\IDEAProject\\springBootDemo\\logs")
//                .storeDurably()
//                .build();
//        return jobDetail;
//    }
//
//    /**
//     * 创建触发器
//     */
//    @Bean
//    public Trigger quartzTestJobTrigger() {
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(delFile);
//        // 创建触发器
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .forJob(quartzDelFile())
//                .withIdentity("quartzDelFileTrigger", "QUARTZ_DEL_FILE_TRIGGER")
//                .withSchedule(cronScheduleBuilder)
//                .build();
//        return trigger;
//    }
//}
