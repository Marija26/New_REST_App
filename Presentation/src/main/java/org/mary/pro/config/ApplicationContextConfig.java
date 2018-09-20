package org.mary.pro.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import(SecurityConfig.class)
@ComponentScan("org.mary.pro.*")
public class ApplicationContextConfig{
    public ApplicationContextConfig(){
    super();
}

}
