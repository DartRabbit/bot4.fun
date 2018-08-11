package fun.bot4.platform.service.bot;

import static fun.bot4.platform.util.telegram.TextFormatUtils.bold;
import static fun.bot4.platform.util.telegram.TextFormatUtils.code;
import static fun.bot4.platform.util.telegram.TextFormatUtils.italic;
import static fun.bot4.platform.util.telegram.TextFormatUtils.userLink;

import com.vdurmont.emoji.EmojiParser;
import fun.bot4.platform.model.bot.Bot;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

  private Bot bot;

  @Override
  public String getBotToken() {
    return bot.getToken();
  }

  @Override
  public String getBotUsername() {
    return bot.getName();
  }

  private TelegramBotsApi telegramBotsApi;

  public TelegramBot(Bot bot, TelegramBotsApi telegramBotsApi) {
    this.bot = bot;
    this.telegramBotsApi = telegramBotsApi;
  }

  public boolean execute() {
    try {
      telegramBotsApi.registerBot(this);
      return true;
    } catch (TelegramApiException e) {
      log.error("{} {}", e.getLocalizedMessage(), e.getCause());
      return false;
    }
  }

  @Override
  public void onUpdateReceived(Update update) {

    if (!update.hasMessage() || !update.getMessage().hasText()) {
      return;
    }

    //String text = update.getMessage().getText();
    sendMessage(update.getMessage(), getKeyboardMarkUp(true));
  }

  private void sendMessage(Message message, ReplyKeyboard keyboard) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(message.getChatId());
    sendMessage.enableMarkdown(true);
    sendMessage.setText(bold("Привет, я - Шрэк Второй") + "\n"
        + italic("Привет, я - Шрэк Второй") + "\n"
        + code("Привет, я - Шрэк Второй") + "\n"
        + userLink(message.getFrom().getId(), message.getFrom().getUserName())
    );

    sendMessage.setReplyMarkup(keyboard);
    try {
      execute(sendMessage);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

  private ReplyKeyboard getKeyboardMarkUp(boolean getKeyboard) {

    if (!getKeyboard) {
      return new ReplyKeyboardRemove();
    }

    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
    keyboardMarkup.setResizeKeyboard(true);
    List<KeyboardRow> keyboard = new ArrayList<>();
    KeyboardRow row = new KeyboardRow();
    row.add(EmojiParser.parseToUnicode(":scream_cat: Help"));
    keyboard.add(row);
    keyboardMarkup.setKeyboard(keyboard);
    return keyboardMarkup;
  }

//  public InlineKeyboardMarkup getInlineKeyboardMarkUp() {
//    SendMessage message = new SendMessage() // Create a message object object
//        .setChatId(chat_id)
//        .setText("You send /start");
//    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//    List<InlineKeyboardButton> rowInline = new ArrayList<>();
//    rowInline.add(new InlineKeyboardButton().setText("Update message text")
//        .setCallbackData("update_msg_text"));
//    // Set the keyboard to the markup
//    rowsInline.add(rowInline);
//    // Add it to the message
//    markupInline.setKeyboard(rowsInline);
//    message.setReplyMarkup(markupInline);
//    try {
//      execute(message); // Sending our message object to user
//    } catch (TelegramApiException e) {
//      e.printStackTrace();
//    }
//  }
//
//  @Override
//  public int creatorId() {
//    return 0;
//  }


//  public Ability listener() {
//
//    //URLEncoder.encode("%F0%9F%90%BC","UTF-8");
//
//    return Ability
//        .builder()
//        .flag(NONE)
//        .name(DEFAULT)
//        .info("gets a keyboard markup")
//        .input(0)
//        .locality(ALL)
//        .privacy(PUBLIC)
//        .action(ctx -> silent.send(EmojiParser.parseToUnicode("Here is a smile emoji: :smile:\n\n Here is alien emoji: :alien:"),ctx.chatId()))
//        .post(getKeyboardMarkUp(true))
//        .build();
//  }

  //  public Ability hello() {
//    return Ability
//        .builder()
//        .name("hello")
//        .info("says hello world!")
//        .input(0)
//        .locality(USER)
//        .privacy(PUBLIC)
//        .action(ctx -> silent.send("Hello world!", ctx.chatId()))
//        .post(ctx -> silent.send("Bye world!", ctx.chatId()))
//        .build();
//  }

  //  @Override
//  public void onUpdateReceived(Update update) {
//    Message message = update.getMessage();
//
//    if (message != null && message.hasText()) {
//      if (message.getText().equals("/help")) {
//        sendMsg(message, "Привет, я " + this.getBotUsername() + "\n" +
//            "/help - это сообщение\n" +
//            "/скажи - цитата\n" +
//            "/скажи N - цитата с номером N\n" +
//            "/запомни S - запомнить сообщение S");
//        //sendMsg(message, "Я не знаю, что ответить на это. Набери-ка ты '/help'");
//      }
//    }
//  }
//

//
//  private void sendMsg(Message message, String text) {
//    SendMessage sendMessage = new SendMessage();
//
//    // Add keyboard to the message
//    //sendMessage.setReplyMarkup(getKeyboardMarkUp());
//    sendMessage.setReplyMarkup(removeReplyKeyboard());
//    sendMessage.enableMarkdown(true);
//    sendMessage.setChatId(message.getChatId().toString());
//    sendMessage.setReplyToMessageId(message.getMessageId());
//    sendMessage.setText(text);
//
//    try {
//      execute(sendMessage);
//    } catch (TelegramApiException e) {
//      e.printStackTrace();
//    }
//  }
}