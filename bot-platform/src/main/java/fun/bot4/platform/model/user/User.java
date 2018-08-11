package fun.bot4.platform.model.user;

import fun.bot4.platform.model.AbstractNamedEntity;
import fun.bot4.platform.model.bot.Bot;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractNamedEntity {

  @Column
  @Email
  @NotBlank
  @Size(max = 100)
  private String email;

  @Column(name = "password")
  @NotBlank
  @Size(min = 8, max = 64)
  private String password;

  @Column(name = "registered", columnDefinition = "timestamp default now()",
      nullable = false, updatable = false, insertable = false)
  @NotNull
  private LocalDateTime registered = LocalDateTime.now();

  @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
  private boolean enabled = true;

  //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "role")
  @ElementCollection(fetch = FetchType.EAGER)
  @BatchSize(size = 200)
  private Set<Role> roles;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
  protected List<Bot> bots;
}
