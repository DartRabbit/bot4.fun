package fun.bot4.service.job;

import fun.bot4.model.bot.Bot;
import fun.bot4.repository.impl.DataJpaBotRepositoryImpl;
import fun.bot4.service.bot.TelegramBot;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.TelegramBotsApi;

@Service
@Slf4j
public class BotService {

  private static final Map<Bot, TelegramBot> botStartedMap = new HashMap<>();
  private DataJpaBotRepositoryImpl botRepository;
  private TelegramBotsApi telegramBotsApi;

  @Autowired
  BotService(DataJpaBotRepositoryImpl botRepository, TelegramBotsApi telegramBotsApi) {
    this.botRepository = botRepository;
    this.telegramBotsApi = telegramBotsApi;
  }

  public void updateBotsStatus() {
    for (Bot bot : botRepository.getAll()) {
      if (bot.isEnabled()) {
        if (!botStartedMap.containsKey(bot)) {
          TelegramBot telegramBot = new TelegramBot(bot, telegramBotsApi);
          log.info("Staring Telegram bot {}", telegramBot.getBotUsername());

          if (telegramBot.execute()) {
            botStartedMap.put(bot, telegramBot);
            log.info("Telegram bot {} started", telegramBot.getBotUsername());
          }
        }
      } else {
        if (botStartedMap.containsKey(bot)) {
          TelegramBot telegramBot = botStartedMap.get(bot);
          log.info("{} shutting down", telegramBot.getBotUsername());
          telegramBot.onClosing();
          botStartedMap.remove(bot);
        }
      }
    }
  }

}