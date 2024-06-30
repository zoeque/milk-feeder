package zoeque.feeder.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zoeque.feeder.mailer.event.MailNotificationEvent;

/**
 * The main service class to nortify the time to feed the milk
 */
@Slf4j
@Service
public class MilkFeederService {
  ApplicationEventPublisher publisher;

  public MilkFeederService(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  /**
   * The main service method to notify the time to feed milk
   */
  @Scheduled(cron = "0 */3 * * *")
  public void notifyTimeToFeedMilk() {
    log.info("Time to feed milk");
    MailNotificationEvent mailEvent
            = new MailNotificationEvent("Milk Feederよりお知らせ", createMessage());
    publisher.publishEvent(mailEvent);
  }

  private String createMessage() {
    String message = """
              ミルクを与える時間になりました。
              ミルクをあげるなどの対応してください。
            """;
    return message;
  }
}
