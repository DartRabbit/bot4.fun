package fun.bot4.config;

import fun.bot4.config.quartz.QuartzConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("fun.bot4.**")
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication
        .run(new Class<?>[]{Application.class, JpaConfig.class, QuartzConfig.class}, args);
  }
}
