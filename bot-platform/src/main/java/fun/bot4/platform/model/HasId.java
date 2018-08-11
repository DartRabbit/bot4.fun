package fun.bot4.platform.model;

public interface HasId {

  Integer getId();

  void setId(Integer id);

  default boolean isNew() {
    return getId() == null;
  }
}
