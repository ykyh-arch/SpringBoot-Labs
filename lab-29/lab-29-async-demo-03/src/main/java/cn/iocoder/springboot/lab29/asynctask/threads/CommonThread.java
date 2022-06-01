package cn.iocoder.springboot.lab29.asynctask.threads;

import org.springframework.stereotype.Component;

/**
 * 公用线程类
 *
 * @author jaquez
 * @date 2022/05/31 16:10
 **/
@Component
public abstract class CommonThread implements Runnable {

    public String getTaskName() {
        return this.getClass().getTypeName()+"@"+this.hashCode();
    }

    @Override
    public void run() {
    }

    public abstract String getTaskParam();
}
