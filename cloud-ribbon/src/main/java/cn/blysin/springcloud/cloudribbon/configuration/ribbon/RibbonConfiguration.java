package cn.blysin.springcloud.cloudribbon.configuration.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author blysin
 * @since 2018/6/5
 */
public class RibbonConfiguration {
    public IPing ribbonPing(){
        return new PingUrl();
    }
}
