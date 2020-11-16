package quartz.org.study;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Author: chenyao
 * Date: 2019/4/4 15:51
 * Description:
 */
public class QuartzTest2 {
    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobKey jobKey = new JobKey("job1", "group1");
        boolean exists = scheduler.checkExists(jobKey);
        if(!exists){
            CronTrigger trigger = newTrigger().withIdentity("job1", "group1").startNow()
                    .withSchedule(cronSchedule("0/1 * * * * ? *")).build();
            JobDetail job = newJob(MyJob.class).withIdentity("job1", "group1").build();
            scheduler.scheduleJob(job,trigger);
        }else{
            CronTrigger trigger = newTrigger().withIdentity("job1", "group1").startNow()
                    .withSchedule(cronSchedule("0/10 * * * * ? *")).build();
            scheduler.rescheduleJob(new TriggerKey("job1", "group1"),trigger);
//            scheduler.deleteJob(jobKey);
        }
        scheduler.start();
    }
}
