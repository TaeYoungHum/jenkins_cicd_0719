package com.cook.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class S3Config implements WebMvcConfigurer {

	@Value("${uploadPath}")
    String uploadPath;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		       
		// addResourceHandler("매핑 경로")를 적어둔다. localhost:8080/upload/ 로 들어오는 모든 정적 리소스 요청을 static폴더가 아닌 .addResourceLoactions에 적어둔 경로로 부터 찾아준다.
		registry.addResourceHandler("/upload/**").addResourceLocations(uploadPath);
		
		 
//		// Swagger UI 경로를 제외한 모든 정적 리소스 처리
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/")
//                .resourceChain(true)
//                .addResolver(new PathResourceResolver() {
//                    @Override
//                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
//                        Resource requestedResource = location.createRelative(resourcePath);
//                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource : new ClassPathResource("/static/index.html");
//                    }
//                });
    }
    
    
}
