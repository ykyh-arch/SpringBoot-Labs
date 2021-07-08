package cn.iocoder.springboot.lab28.task.factory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * JobFactory 类
 *
 * @author jaquez
 * @date 2021/07/08 16:16
 **/
@Component
public class JobFactory extends AdaptableJobFactory {
    //大家可以看下我们的任务类，大家可以看到Job对象的实例化过程是在Quartz中进行的，这时候我们将spring的东西注入进来，肯定是行不通的，所以需要这个类
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        //进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }

}
