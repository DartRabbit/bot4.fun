package fun.bot4.platform.repository.impl;

import fun.bot4.platform.model.bot.Bot;
import fun.bot4.platform.model.user.User;
import fun.bot4.platform.repository.BotRepository;
import fun.bot4.platform.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DataJpaBotRepositoryImpl {

  private BotRepository botRepository;

  private UserRepository userRepository;

  public DataJpaBotRepositoryImpl(BotRepository botRepository, UserRepository userRepository) {
    this.botRepository = botRepository;
    this.userRepository = userRepository;
  }

  // false if not found
  public boolean delete(int id, int userId) {
    return botRepository.delete(id, userId) != 0;
  }

  @Transactional
  public Bot save(Bot bot, int userId) {
    if (!bot.isNew() && get(bot.getId(), userId) == null) {
      return null;
    }
    bot.setUser(userRepository.getOne(userId));
    return botRepository.save(bot);
  }

  public Bot get(int id, int userId) {
    return botRepository.getByIdAndUserId(id, userId);
  }

  public int getCountByPeriod(LocalDateTime start, LocalDateTime end) {
    return botRepository.countAllByCreatedBetween(start, end);
  }

  public Bot getByName(String name, int userId) {
    return botRepository.getByNameAndUserId(name, userId);
  }

  public List<Bot> getAll() {
    return botRepository.findAll();
  }

  public int getCountByUser(User user) {
    return botRepository.countAllByUser(user);
  }
}
