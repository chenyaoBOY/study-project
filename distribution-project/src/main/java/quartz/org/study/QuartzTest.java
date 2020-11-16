package quartz.org.study;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Author: chenyao
 * Date: 2019/4/3 eight:00
 * Description:
 */
public class QuartzTest {

    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        CronTrigger trigger = newTrigger().withIdentity("job5", "group3").startNow()
                .withSchedule(cronSchedule("0/1 * * * * ? *")).build();
        JobDetail job = newJob(MyJob.class).withIdentity("job5", "group3").build();
        scheduler.scheduleJob(job, trigger);
        scheduler.shutdown();
    }
}
