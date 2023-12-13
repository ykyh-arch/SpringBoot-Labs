package cn.iocoder.springboot.labs.statemachine.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.redis.RedisStateMachinePersister;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Persist 持久化状态
 *
 * @author fw001
 * @date 2023/12/12 17:22
 **/
@Configuration
@Slf4j
public class Persist<E, S> {

    /**
     * 持久化到内存 map 中，本地使用
     *
     * @return
     */
    @Bean(name = "stateMachineMemPersister")
    public static StateMachinePersister getPersister() {
        return new DefaultStateMachinePersister(new StateMachinePersist() {
            @Override
            public void write(StateMachineContext context, Object contextObj) throws Exception {
                log.info("持久化状态机，context:{}，contextObj:{}", JSON.toJSONString(context), JSON.toJSONString(contextObj));
                map.put(contextObj, context);
            }
            @Override
            public StateMachineContext read(Object contextObj) throws Exception {
                log.info("获取状态机，contextObj:{}", JSON.toJSONString(contextObj));
                StateMachineContext stateMachineContext = (StateMachineContext) map.get(contextObj);
                log.info("获取状态机结果，stateMachineContext:{}", JSON.toJSONString(stateMachineContext));
                return stateMachineContext;
            }
            private Map<Object,Object> map = new HashMap<>();
        });
    }

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 持久化到 redis 中，在分布式系统中使用
     *
     * @return
     */
    @Bean(name = "stateMachineRedisPersister")
    public RedisStateMachinePersister<E, S> getRedisPersister() {
        RedisStateMachineContextRepository<E, S> repository = new RedisStateMachineContextRepository<>(redisConnectionFactory);
        RepositoryStateMachinePersist p = new RepositoryStateMachinePersist<>(repository);
        return new RedisStateMachinePersister<>(p);
    }

    /**
     * Redis bean初始化设置
     *
     * @return
     */
    @Bean
    public BeanPostProcessor redisBeanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if ((bean instanceof RedisTemplate)) {
                    // 创建 RedisTemplate 对象
                    RedisTemplate<String, Object> template = new RedisTemplate<>();
                    // 设置 RedisConnection 工厂。它就是实现多种 Java Redis 客户端接入的秘密工厂。感兴趣的胖友，可以自己去撸下。
                    template.setConnectionFactory(new JedisConnectionFactory());
                    // 使用 String 序列化方式，序列化 KEY 。
                    template.setKeySerializer(RedisSerializer.string());
                    template.setHashKeySerializer(RedisSerializer.string());
                    // 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE 。
                    template.setValueSerializer(RedisSerializer.json());
                    template.setHashValueSerializer(RedisSerializer.json());
                    return template;
                }
                return bean;
            }

        };
    }

}
