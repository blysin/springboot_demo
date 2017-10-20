package cn.blysin.springboot.config.web;

import cn.blysin.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Blysin
 * @since 2017/8/23
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");//添加一个拦截器，并配置拦截uri
        super.addInterceptors(registry);
    }

}
