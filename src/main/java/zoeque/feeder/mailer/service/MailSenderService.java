package zoeque.feeder.mailer.service;

import io.vavr.control.Try;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import zoeque.feeder.mailer.configuration.MailServiceCollector;
import zoeque.feeder.mailer.model.MailService;
import zoeque.feeder.mailer.model.MailServiceProviderModel;

/**
 * The class to send the mail via {@link JavaMailSender}.
 */
@Service
@MailService(MailServiceProviderModel.OTHERS)
public class MailSenderService extends AbstractMailSenderService {
  JavaMailSender javaMailSender;
  public MailSenderService(@Value("${zoeque.limitchecker.mail.address.to:null}")
                           String toMailAddress,
                           @Value("${zoeque.limitchecker.mail.address.from:null}")
                           String fromMailAddress,
                           @Value("${zoeque.limitchecker.mail.provider:OTHERS}")
                           MailServiceProviderModel model,
                           MailServiceCollector collector,
                           JavaMailSender javaMailSender) {
    super(toMailAddress, fromMailAddress, model, collector);
    this.javaMailSender = javaMailSender;
  }

  /**
   * Send e-mail with full parameter in application.properties
   */
  public Try<String> sendMailToUser(String subject,
                                    String messageContent) {
    MimeMessage message = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(fromMailAddress);
      helper.setTo(toMailAddress);
      helper.setSubject(subject);
      helper.setText(messageContent);
      javaMailSender.send(message);
      return Try.success(messageContent);
    } catch (Exception e) {
      return Try.failure(e);
    }
  }
}
