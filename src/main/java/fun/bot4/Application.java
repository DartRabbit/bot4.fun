package fun.bot4;

import fun.bot4.config.quartz.QuartzConfig;
import fun.bot4.config.telegram.TelegramConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication(scanBasePackages = {"fun.bot4.config","fun.bot4.service"})
@ImportResource(locations = {
    "classpath:spring/app-config.xml",
    "classpath:spring/db-config.xml"
})
public class Application {

  static {
    ApiContextInitializer.init();
  }

  public static void main(String[] args) {
    SpringApplication
        .run(new Class<?>[]{Application.class, QuartzConfig.class, TelegramConfig.class}, args);
  }
}
