package org.mary.pro.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//        excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
@Configuration
@EnableWebMvc
@Import(SecurityConfig.class)
@ComponentScan(basePackages = {"org.mary.pro.*"})

public class ApplicationContextConfig{
    public ApplicationContextConfig(){
    super();
}

}

