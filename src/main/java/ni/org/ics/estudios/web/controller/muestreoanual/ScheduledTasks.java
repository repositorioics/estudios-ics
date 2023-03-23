package ni.org.ics.estudios.web.controller.muestreoanual;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class ScheduledTasks
{
	@Scheduled(cron="0 0/30 * * * ?")
    public void demoServiceMethod()
    {
        System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
    }
}