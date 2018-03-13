package fun.bot4.repository;

import static org.assertj.core.api.Assertions.assertThat;

import fun.bot4.model.bot.Bot;
import fun.bot4.model.bot.BotType;
import fun.bot4.model.user.User;
import fun.bot4.repository.impl.DataJpaBotRepositoryImpl;
import fun.bot4.repository.impl.DataJpaUserRepositoryImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DataJpaBotRepositoryImplTest extends AbstractRepositoryTest {

  @Autowired
  DataJpaUserRepositoryImpl userRepository;
  @Autowired
  DataJpaBotRepositoryImpl botRepository;

  @Test
  public void saveAndDelete() throws Exception {
    //given
    User user = userRepository.get(100000);
    Bot botForSave = new Bot();
    botForSave.setName("New Bot");
    botForSave.setToken("ksdjgvbkljsdbnlfjspeoi04");
    botForSave.setType(BotType.TELEGRAM_BOT);

    //when
    Bot botForDelete = botRepository.save(botForSave, user.getId());
    boolean deleted = botRepository.delete(botForDelete.getId(), user.getId());

    //then
    assertThat(deleted).isTrue();
  }

  @Test
  public void get() throws Exception {
    //given
    User user = userRepository.get(100000);

    //when
    Bot bot = botRepository.get(100002, user.getId());

    //then
    assertThat(bot.getName()).isEqualTo("Шрэк");
  }

  @Test
  public void getCountByPeriod() throws Exception {
    //given
    LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN);
    LocalDateTime end = start.plusDays(2);

    //when
    int count = botRepository.getCountByPeriod(start, end);

    //then
    assertThat(count).isEqualTo(2);
  }

  @Test
  public void getByName() throws Exception {
    //given
    User user = userRepository.get(100000);

    //when
    Bot bot = botRepository.getByName("Шрэк", user.getId());

    //then
    assertThat(bot.getId()).isEqualTo(100002);
  }

}