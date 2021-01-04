package com.buko.db.designticketingsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 吴泽欣
 * @since 2020/11/29 11:23
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

//  可视化1  http://{$host}:${port}/swagger-ui.html
//  可视化2  http://{$host}:${port}/doc.html
//  json文档：  http://{$host}:${port}/v2/api-docs

    /**
     * 配置swagger2核心配置 docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.buko.db.designticketingsystem"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档页标题
                .title("buko票务系统")
                // 联系人信息
                .contact(new Contact("buko",
                        "https://bukotop.site",
                        "1727346009@qq.com"))
                .description("buko票务系统")
                .version("1.0.0")
                .termsOfServiceUrl("https://bukotop.site")
                .build();
    }
}
