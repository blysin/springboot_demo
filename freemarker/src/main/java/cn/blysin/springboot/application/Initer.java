package cn.blysin.springboot.application;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Blysin
 * @since 2017/8/23
 */
@Component
public class Initer implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("------------------项目启动了------------------");
    }
}
