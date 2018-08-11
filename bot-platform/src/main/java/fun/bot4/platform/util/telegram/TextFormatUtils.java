package fun.bot4.platform.util.telegram;

public class TextFormatUtils {

  private static final String USER_LINK = "[%s](tg://user?id=%d)";
  private static final String CODE_TEXT = "`%s`";
  private static final String BOLD_TEXT = "*%s*";
  private static final String ITALIC_TEXT = "_%s_";

  public static String userLink(Integer userId, String userName) {

    return String.format(USER_LINK, userName, userId);
  }

  public static String code(String text){
    return String.format(CODE_TEXT, text);
  }

  public static String bold(String text){
    return String.format(BOLD_TEXT, text);
  }

  public static String italic(String text){
    return String.format(ITALIC_TEXT, text);
  }
}
