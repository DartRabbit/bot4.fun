package fun.bot4.repository;

import static org.assertj.core.api.Assertions.assertThat;

import fun.bot4.model.user.User;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends AbstractRepositoryTest {

  @Autowired
  private UserRepository repository;

  @Test
  public void saveAndDelete() {
    //given
    User actual = new User();
    actual.setName("User");
    actual.setEmail("user@mail.ru");
    actual.setPassword("password");
    User forDelete = repository.save(actual);

    //when
    int deleted = repository.delete(forDelete.getId());

    //then
    assertThat(deleted).isNotZero();
  }

  @Test
  public void get() {
    //when
    User expected = repository.findById(100000).orElse(null);

    //then
    assertThat(expected).isNotNull();
    assertThat(expected.getEmail()).isEqualTo("admin@gmail.com");
  }

  @Test
  public void findByEmail() {
    //given
    User actual = new User();
    actual.setName("User");
    actual.setEmail("user@mail.ru");
    actual.setPassword("password");
    repository.save(actual);

    //when
    User expected = repository.findByEmail(actual.getEmail());

    //then
    assertThat(actual.getName()).isEqualTo(expected.getName());
  }

  @Test
  public void getAll() {
    //when
    List<User> expected = repository.findAll();

    //then
    assertThat(expected.size()).isEqualTo(2);
  }
}