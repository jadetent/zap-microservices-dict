package com.zap.contadigital.doc;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    public SwaggerConfig() {
        super("com.zap.contadigital.comprovantes.controller");
    }

}
