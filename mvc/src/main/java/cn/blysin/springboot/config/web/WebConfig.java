package cn.blysin.springboot.config.web;

import cn.blysin.springboot.interceptor.LoginInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author Blysin
 * @since 2017/8/23
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");//添加一个拦截器，并配置拦截uri
        super.addInterceptors(registry);
    }

    /**
     * 消息转换器
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(fastConverter);
        super.configureMessageConverters(converters);
    }

    /**
     * 配置跨域请求，在对应的请求方法上添加@CrossOrigin注解即可
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**");
        super.addCorsMappings(registry);
    }
}
