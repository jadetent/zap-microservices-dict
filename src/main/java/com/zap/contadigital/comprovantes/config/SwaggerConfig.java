package com.zap.contadigital.comprovantes.config;

import com.zap.contadigital.doc.BaseSwaggerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile("!prod")
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    public SwaggerConfig() {
        super("com.zap.contadigital.comprovantes.controller");
    }

}
