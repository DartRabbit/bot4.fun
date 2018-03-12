package fun.bot4;

import fun.bot4.config.quartz.QuartzConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = {"fun.bot4.config"})
@ImportResource(locations = {"classpath:spring/app-config.xml", "classpath:spring/db-config.xml"})
public class Application {

  public static void main(String[] args) {
    SpringApplication
        .run(new Class<?>[]{Application.class, QuartzConfig.class}, args);
  }
}
