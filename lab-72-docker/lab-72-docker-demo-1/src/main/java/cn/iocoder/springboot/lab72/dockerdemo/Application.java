package cn.iocoder.springboot.lab72.dockerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 模拟Java程序关闭是一个过程
     * @author Jaquez
     * @date 2021/05/28 16:14
     * @return Listener
     */
    @Component
    public class Listener implements ApplicationListener<ApplicationEvent> {

        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            if (event instanceof ContextClosedEvent) {
                this.sleep(10);
            }
        }

        private void sleep(int seconds) {
            try {
                Thread.sleep(seconds * 1000L);
            } catch (InterruptedException ignore) {
            }
        }

    }

}
