//package org.sherlock.utils;
//
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//import java.io.File;
//import java.util.Date;
//
//@Slf4j
//public class QuartzDelFile extends QuartzJobBean {
//    @Override
//    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//        String delFilePath = (String) context.getJobDetail().getJobDataMap().get("DelFile");
//        System.out.println("文件路径：" + delFilePath);
//        File file = new File(delFilePath);
//        if (file.exists()) {
//            // 获取文件夹下文件
//            File[] logFiles = file.listFiles();
//            for (File logFile : logFiles) {
//                if (logFile.isFile() && logFile.getName().endsWith(".log")) {
//                    logFile.delete();
//                    System.out.println("删除文件：" + logFile.getName() + "  删除时间：" + new Date());
//                }
//            }
//        }
//    }
//}
