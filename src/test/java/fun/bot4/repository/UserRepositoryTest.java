package fun.bot4.repository;

import static org.assertj.core.api.Assertions.assertThat;

import fun.bot4.model.user.Role;
import fun.bot4.model.user.User;
import fun.bot4.repository.impl.DataJpaUserRepositoryImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends AbstractRepositoryTest {

  @Autowired
  private DataJpaUserRepositoryImpl repository;

  @Test
  public void saveWithRolesAndDelete() {
    //given
    User actual = new User();
    actual.setName("User");
    actual.setEmail("user@mail.ru");
    actual.setPassword("password");

    Set<Role> roles = new HashSet<>();
    roles.add(Role.ROLE_ADMIN);
    roles.add(Role.ROLE_USER);
    actual.setRoles(roles);

    assertThat(actual.isNew()).isTrue();

    User forDelete = repository.save(actual);

    //when
    boolean deleted = repository.delete(forDelete.getId());

    //then
    assertThat(deleted).isTrue();
    assertThat(forDelete.isNew()).isFalse();
    assertThat(forDelete.getRoles().size()).isEqualTo(2);
  }

  @Test
  public void get() {
    //when
    User expected = repository.get(100000);

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
    User expected = repository.getByEmail(actual.getEmail());

    //then
    assertThat(expected).isNotNull();
    assertThat(actual.getName()).isEqualTo(expected.getName());
  }

  @Test
  public void getAll() {
    //when
    List<User> expected = repository.getAll();

    //then
    assertThat(expected).isNotNull();
    assertThat(expected.size()).isEqualTo(2);
  }
}