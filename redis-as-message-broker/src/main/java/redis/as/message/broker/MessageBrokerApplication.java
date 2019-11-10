package redis.as.message.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class MessageBrokerApplication {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    RedisMessageListenerContainer genMessageListenerContainer(RedisConnectionFactory factory, MessageListenerAdapter adapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(adapter, new PatternTopic("chat"));

        return container;
    }

    @Bean
    MessageListenerAdapter genMessageListenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "onReceive");
    }

    @Bean
    Receiver genReceiver(CountDownLatch latch) {
        return new Receiver(latch);
    }

    @Bean
    CountDownLatch genLatch() {
        return new CountDownLatch(1);
    }

    @Bean
    StringRedisTemplate genRedisTemplate(RedisConnectionFactory factory) {
        return new StringRedisTemplate(factory);
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(MessageBrokerApplication.class, args);

        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
        CountDownLatch latch = ctx.getBean(CountDownLatch.class);

        template.convertAndSend("chat", "hello from redis");

        latch.await();
        System.exit(0);
    }
}
