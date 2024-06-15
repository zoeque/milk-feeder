package zoeque.feeder.mailer.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

/**
 * The event to send the information of e-mail
 */
@Getter
@AllArgsConstructor
public class MailNotificationEvent {
  @NonNull
  String subject;
  @NonNull
  String messageBody;
}
