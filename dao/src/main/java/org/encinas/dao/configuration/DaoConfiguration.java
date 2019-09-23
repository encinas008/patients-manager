package org.encinas.dao.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("org.encinas.dao")
@EntityScan(basePackages = {"org.encinas.dao.entity"})
@EnableJpaRepositories(basePackages = {"org.encinas.dao.repository"})
@EnableAutoConfiguration
@EnableTransactionManagement
public class DaoConfiguration {
}
