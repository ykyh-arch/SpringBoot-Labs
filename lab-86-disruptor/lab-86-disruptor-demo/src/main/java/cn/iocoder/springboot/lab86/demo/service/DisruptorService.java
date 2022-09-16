package cn.iocoder.springboot.lab86.demo.service;

/**
 * DisruptorService
 *
 * @author jaquez
 * @date 2022/09/16 14:20
 **/
public interface DisruptorService {

    /**
     * 消息
     * @param message
     */
    void send(String message);
}
