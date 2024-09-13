package com.shiguang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                // 配置API的基本信息
                .apiInfo(apiInfo())
                // 是否启用Swagger
                .enable(true)
                // 选择哪些路径需要生成API文档
                .select()
                // 指定哪些类或方法应该被Swagger扫描和文档化
                .apis(RequestHandlerSelectors.basePackage("com.shiguang.controller")) //你自己的package
                // 指定哪些路径应该被Swagger扫描和文档化 any() 所有路径
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 配置API的基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API文档") // 设置API的标题
                .description("这是一个API文档的示例") // 设置API的描述
                .version("1.0.0") // 设置API的版本
                .contact(new Contact("作者", "http://www.example.com", "author@example.com")) // 设置API的联系人信息
                .license("Apache 2.0") // 设置API的许可证名称
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html") // 设置API的许可证URL
                .termsOfServiceUrl("http://www.example.com/terms") // 设置API的服务条款URL
                .build();
    }
}