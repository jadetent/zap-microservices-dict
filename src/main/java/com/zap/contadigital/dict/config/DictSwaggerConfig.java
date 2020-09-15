package com.zap.contadigital.dict.config;

import com.zap.contadigital.doc.BaseSwaggerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile("!prod")
@Configuration
@EnableSwagger2
public class DictSwaggerConfig extends BaseSwaggerConfig {

    public DictSwaggerConfig() {
        super("com.zap.contadigital.dict.controller");
    }

}
