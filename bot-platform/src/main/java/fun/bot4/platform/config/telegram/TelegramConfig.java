package fun.bot4.platform.config.telegram;

import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.TelegramBotsApi;

@Configuration
@Slf4j
public class TelegramConfig {

  @Bean
  public @NotNull TelegramBotsApi telegramBotsApi() {
    log.info("Creating instance of TelegramBotApi");
    return new TelegramBotsApi();
  }

//  @Bean
//  public DefaultBotOptions defaultBotOptions(
//      @Value("${telegram.proxy}") boolean useProxy,
//      @Value("${telegram.proxy.host}") String proxyHost,
//      @Value("${telegram.proxy.port}") int proxyPort) {
//
//    if (useProxy) {
//      // Set up Http proxy
//      DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
//
//      HttpHost httpHost = new HttpHost(proxyHost, proxyPort);
//
//      RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost)
//          .setAuthenticationEnabled(false).build();
//      botOptions.setRequestConfig(requestConfig);
//      botOptions.setHttpProxy(httpHost);
//
//      return botOptions;
//    } else {
//      return null;
//    }
//  }
}