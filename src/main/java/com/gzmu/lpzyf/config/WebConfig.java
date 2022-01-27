package com.gzmu.lpzyf.config;

        import com.gzmu.lpzyf.interceptor.LoginInterceptor;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login","/login/validate",
                        "/register","/css/**",
                        "/img/**","/js/**",
                        "/fonts/**","/to_register","/sendCode","/loginByCode");
    }

}
