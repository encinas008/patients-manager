package org.encinas.api.configuration;

import org.encinas.business.configuration.BusinessConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BusinessConfiguration.class)
public class ApiConfiguration {
}
