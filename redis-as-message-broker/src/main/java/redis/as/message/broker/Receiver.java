package redis.as.message.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void onReceive(String message){
        logger.info("receive message:{}", message);
        latch.countDown();
    }
}
