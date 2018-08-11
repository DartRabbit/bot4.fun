package fun.bot4.platform.repository;

import fun.bot4.platform.model.bot.Bot;
import fun.bot4.platform.model.user.User;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BotRepository extends JpaRepository<Bot, Integer> {

  @Modifying
  @Transactional
  @Query("DELETE FROM Bot b WHERE b.id=:id AND b.user.id=:userId")
  int delete(@Param("id") int id, @Param("userId") int userId);

  @Override
  @Transactional
  Bot save(Bot Bot);

  @Query("SELECT b FROM Bot b WHERE b.name=:name AND b.user.id=:userId")
  Bot getByNameAndUserId(@Param("name") String name, @Param("userId") int userId);

  @Query("SELECT b FROM Bot b WHERE b.id=:id AND b.user.id=:userId")
  Bot getByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

  int countAllByCreatedBetween(LocalDateTime start, LocalDateTime end);

  int countAllByUser(User user);
}
