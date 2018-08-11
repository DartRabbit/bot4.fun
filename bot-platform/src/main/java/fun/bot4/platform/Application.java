package fun.bot4.platform;

import fun.bot4.platform.config.quartz.QuartzConfig;
import fun.bot4.platform.config.telegram.TelegramConfig;
import fun.bot4.rules.config.DroolsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication(scanBasePackages = {"fun.bot4.platform.config","fun.bot4.platform.service"})
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
        .run(new Class<?>[]{Application.class, QuartzConfig.class, TelegramConfig.class, DroolsConfig.class}, args);
  }
}
