package zoeque.feeder.mailer.service;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import zoeque.feeder.mailer.configuration.MailServiceCollector;
import zoeque.feeder.mailer.event.MailNotificationEvent;
import zoeque.feeder.mailer.model.MailServiceProviderModel;


/**
 * Class to send e-mail
 */
@Slf4j
public abstract class AbstractMailSenderService implements IMailService {
  protected String toMailAddress;
  protected String fromMailAddress;
  protected MailServiceProviderModel model;
  MailServiceCollector collector;

  public AbstractMailSenderService(@Value("${zoeque.mail.address.to:null}")
                                   String toMailAddress,
                                   @Value("${zoeque.mail.address.from:null}")
                                   String fromMailAddress,
                                   @Value("${zoeque.mail.provider:GMAIL}")
                                   MailServiceProviderModel model,
                                   MailServiceCollector collector) {
    this.toMailAddress = toMailAddress;
    this.fromMailAddress = fromMailAddress;
    this.model = model;
    this.collector = collector;
  }

  /**
   * The event listener for {@link MailNotificationEvent}.
   *
   * @param event with a subject and the message body.
   */
  @EventListener
  public void sendMail(MailNotificationEvent event) {
    if (StringUtils.isEmpty(toMailAddress) || StringUtils.isEmpty(fromMailAddress)) {
      log.error("The mail address must not be null!! Failed to send email!!");
      return;
    }
    collector.findMailService(model)
            .get()
            .sendMailToUser(event.getSubject(), event.getMessageBody());
  }
}
