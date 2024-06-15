package zoeque.feeder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"zoeque.feeder"})
@ComponentScan(basePackages = {"zoeque.feeder"})
public class MilkFeederApplication {

	public static void main(String[] args) {
		SpringApplication.run(MilkFeederApplication.class, args);
	}

}
