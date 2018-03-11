package fun.bot4.model;

import fun.bot4.HasId;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
// http://stackoverflow.com/questions/594597/hibernate-annotations-which-is-better-field-or-property-access
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity implements Persistable<Integer>, HasId {

  public static final int START_SEQ = 100000;

  @Id
  @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")

//    PROPERTY access for id due to bug: https://hibernate.atlassian.net/browse/HHH-3718
//    Fixed at last?
//    @Access(value = AccessType.PROPERTY)
  protected Integer id;

  protected AbstractBaseEntity() {
  }

  protected AbstractBaseEntity(Integer id) {
    this.id = id;
  }

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public boolean isNew() {
    return this.id == null;
  }

  @Override
  public String toString() {
    return String.format("Entity %s (%s)", getClass().getName(), id);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || !getClass().equals(Hibernate.getClass(o))) {
      return false;
    }
    AbstractBaseEntity that = (AbstractBaseEntity) o;
    return id != null && id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id == null ? 0 : id;
  }
}