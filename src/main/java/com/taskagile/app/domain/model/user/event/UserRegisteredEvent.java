package com.taskagile.app.domain.model.user.event;

import com.taskagile.app.domain.common.event.DomainEvent;
import com.taskagile.app.domain.model.user.User;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

@Setter
@ToString
@EqualsAndHashCode
public class UserRegisteredEvent extends DomainEvent {
  private static final long serialVersionUID = 2580061707540917880L;

  private User user;

  public UserRegisteredEvent(User user) {
    super(user);
    Assert.notNull(user, "Parameter `user` must not be null");
    this.user = user;
  }
}
