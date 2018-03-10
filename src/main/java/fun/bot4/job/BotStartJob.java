package fun.bot4.job;

import fun.bot4.service.job.BotStartScheduleService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class BotStartJob implements Job {

  @Autowired
  private BotStartScheduleService service;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    service.startBots();
  }
}
