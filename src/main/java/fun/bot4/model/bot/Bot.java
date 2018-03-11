package fun.bot4.model.bot;

import fun.bot4.model.AbstractNamedEntity;
import fun.bot4.model.abilities.Quote;
import fun.bot4.model.user.User;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bots")
public class Bot extends AbstractNamedEntity {

  @Column(name = "token")
  @NotBlank
  private String token;

  @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
  private boolean enabled;

  @Column(name = "created", columnDefinition = "timestamp default now()",
      nullable = false, updatable = false, insertable = false)
  private LocalDateTime created;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @NotNull
  private User user;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "bot")
  private List<Quote> quotes;

//  public static void main(String[] args) {
//    ApiContextInitializer.init();
//
//    TelegramBotsApi botsApi = new TelegramBotsApi();
//
//    Bot telegramBot1 = new Bot();
//    telegramBot1.setActive(true);
//    telegramBot1.setBotToken("347037495:AAGtX8QqLKNIdi8hqPTDj40nCdHDNZ_Le7w");
//    telegramBot1.setName("ShrekTheSecondBot");
//
//    Bot telegramBot2 = new Bot();
//    telegramBot2.setActive(true);
//    telegramBot2.setBotToken("569944003:AAFjdvqOX11T4TfB47cO4jU6Mr4My32uZos");
//    telegramBot2.setName("Shrek");
//
//    try {
//      botsApi.registerBot(telegramBot1);
//      botsApi.registerBot(telegramBot2);
//    } catch (TelegramApiException e) {
//      e.printStackTrace();
//    }
//  }
}