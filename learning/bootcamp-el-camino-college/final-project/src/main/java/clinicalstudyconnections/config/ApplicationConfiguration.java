package clinicalstudyconnections.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import clinicalstudyconnections.repository.OwnerMockRepository;
import clinicalstudyconnections.repository.OwnerRepository;

// https://www.digitalocean.com/community/tutorials/spring-dependency-injection

@Configuration
//@ComponentScan(value={"com.journaldev.spring.di.consumer"})
public class ApplicationConfiguration {
	@Bean 
	@Primary
	public OwnerRepository getOwnerRepository() {
		return new OwnerMockRepository();
	}
}

/*
 * Some important points related to above class are:
 * 
 * @Configuration annotation is used to let Spring know that it’s a Configuration class.
 * @ComponentScan annotation is used with @Configuration annotation to specify the packages to look for Component classes.
 * @Bean annotation is used to let Spring framework know that this method should be used to get the bean implementation to inject in Component classes.
 */
