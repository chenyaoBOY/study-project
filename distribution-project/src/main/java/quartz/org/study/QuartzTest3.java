package quartz.org.study;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;

/**
 * Author: chenyao
 * Date: 2019/4/3 eight:00
 * Description:
 */
public class QuartzTest3 {

    public static void main(String[] args) throws Exception {

        addJob("simple", "printGroup", "0/1 * * * * ? ","msg");

    }


    public static void addJob(String triggerName, String triggerGroup, String cron,String msg) throws Exception {
        //此处可以添加一个参数 为定时任务的配置地址
        // new StdSchedulerFactory(envConfig+"/split_quartz.properties");
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();


        JobDetail detail = JobBuilder.newJob(MyJob.class)
                .withIdentity(triggerName, triggerGroup)
                .build();
        JobDataMap jobDataMap = detail.getJobDataMap();
        jobDataMap.put("key",msg);
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
        scheduler.scheduleJob(detail,trigger);
//        scheduler.resumeJob(new JobKey(triggerName,triggerGroup));
//        scheduler.rescheduleJob(new TriggerKey(triggerName,triggerGroup),trigger);
        Trigger key = scheduler.getTrigger(new TriggerKey(triggerName,triggerGroup));
//        System.out.println(key.getKey().getName());
    }
}
