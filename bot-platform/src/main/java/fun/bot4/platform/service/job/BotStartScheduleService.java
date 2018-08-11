package fun.bot4.platform.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BotStartScheduleService {

  private static final Logger LOG = LoggerFactory.getLogger(BotStartScheduleService.class);

  public void startBots() {
    LOG.info("Hello World!");
  }
}