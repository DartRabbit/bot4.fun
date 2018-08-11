package fun.bot4.platform.model.bot;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;

public enum BotType {
  TELEGRAM_BOT("Telegram", 0),
  VIBER_BOT("Viber", 1);

  private static ImmutableMap<Integer, BotType> CODES_MAP;

  static {
    Map<Integer, BotType> mapCodes = new HashMap<>();
    for (BotType item : values()) {
      mapCodes.put(item.code(), item);
    }
    CODES_MAP = ImmutableMap.copyOf(mapCodes);
  }

  private final String value;
  private final int code;

  BotType(String value, int code) {
    this.value = value;
    this.code = code;
  }

  public String value() {
    return value;
  }

  public int code() {
    return code;
  }

  public BotType getByCode(int code) {
    return CODES_MAP.get(code);
  }

  @Override
  public String toString() {
    return this.name() + " " + value;
  }
}
