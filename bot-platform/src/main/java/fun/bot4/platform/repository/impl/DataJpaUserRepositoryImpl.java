package fun.bot4.platform.repository.impl;

import fun.bot4.platform.model.user.User;
import fun.bot4.platform.repository.UserRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class DataJpaUserRepositoryImpl {

  private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");
  private UserRepository repository;

  public DataJpaUserRepositoryImpl(UserRepository repository) {
    this.repository = repository;
  }

  public User save(User user) {
    return repository.save(user);
  }

//  public User save(UserTo userTo) {
//    User user = get(userTo.getId());
//    return repository.save(UserUtil.updateFromTo(user, userTo));
//  }

  // false if not found
  public boolean delete(int id) {
    return repository.delete(id) != 0;
  }

  // null if not found
  public User get(int id) {
    return repository.findById(id).orElse(null);
  }

  // null if not found
  public User getByEmail(String email) {
    return repository.findByEmail(email);
  }

  // null if not found
  public List<User> getAll() {
    return repository.findAll(SORT_NAME_EMAIL);
  }

}
