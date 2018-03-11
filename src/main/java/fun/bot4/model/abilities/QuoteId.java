package fun.bot4.model.abilities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Composite Primary Key
//http://javasampleapproach.com/spring-framework/spring-data/create-composite-primary-key-spring-jpa-mysql
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class QuoteId implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "bot_id")
  private Integer botId;

  @Column(name = "created")
  private LocalDateTime created;
}
