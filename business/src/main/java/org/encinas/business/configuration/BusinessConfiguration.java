package org.encinas.business.configuration;

import org.encinas.dao.configuration.DaoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"org.encinas.business"})
@Import({DaoConfiguration.class})
public class BusinessConfiguration {
}
