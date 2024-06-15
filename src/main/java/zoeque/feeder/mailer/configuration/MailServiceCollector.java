package zoeque.feeder.mailer.configuration;

import io.vavr.control.Try;
import java.util.Map;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;
import zoeque.feeder.mailer.model.MailServiceProviderModel;
import zoeque.feeder.mailer.service.IMailService;

/**
 * The class to find the mail service to use
 */
@Component
public class MailServiceCollector {
  MailServiceBeanConfig beanConfig;
  BeanFactory beanFactory;

  public MailServiceCollector(MailServiceBeanConfig beanConfig,
                              BeanFactory beanFactory) {
    this.beanConfig = beanConfig;
    this.beanFactory = beanFactory;
  }

  public Try<IMailService> findMailService(MailServiceProviderModel model) {
    return Try.success(
            (IMailService) beanFactory.getBean(beanConfig.getServiceMap().get(model)));
  }
}
