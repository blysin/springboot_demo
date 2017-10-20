package cn.blysin.springboot.radis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Blysin
 * @since 2017/8/24
 */
@Component
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


}
