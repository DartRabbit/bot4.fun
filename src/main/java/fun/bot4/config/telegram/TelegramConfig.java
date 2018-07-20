package fun.bot4.config.telegram;

import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Configuration
@Slf4j
public class TelegramConfig {

  @Bean
  public @NotNull TelegramBotsApi telegramBotsApi() {
    log.info("Creating instance of TelegramBotApi");
    return new TelegramBotsApi();
  }

  @Bean
  public DefaultBotOptions defaultBotOptions(
      @Value("${telegram.proxy}") boolean useProxy,
      @Value("${telegram.proxy.host}") String proxyHost,
      @Value("${telegram.proxy.port}") int proxyPort) {

    if (useProxy) {
      // Set up Http proxy
      DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

      HttpHost httpHost = new HttpHost(proxyHost, proxyPort);

      RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost)
          .setAuthenticationEnabled(false).build();
      botOptions.setRequestConfig(requestConfig);
      botOptions.setHttpProxy(httpHost);

      return botOptions;
    } else {
      return null;
    }

  }
}