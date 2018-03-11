package fun.bot4.model.abilities;

import fun.bot4.model.bot.Bot;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "quotes", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bot_id", "created"}, name = "vote_unique_idx")})
@EqualsAndHashCode(of = {"quoteId"})
public class Quote {

  @EmbeddedId
  private QuoteId quoteId;

  @Column(name = "author")
  private String author;

  @Column(name = "text")
  @NotNull
  private String text;

  @Column(name = "created", nullable = false, updatable = false, insertable = false)
  @NotNull
  private LocalDateTime created;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bot_id", nullable = false, updatable = false, insertable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @NotNull
  private Bot bot;
}