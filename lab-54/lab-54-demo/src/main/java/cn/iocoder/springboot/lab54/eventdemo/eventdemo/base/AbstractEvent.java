package cn.iocoder.springboot.lab54.eventdemo.eventdemo.base;

/**
 * AbstractEvent 事件对象，包含事件源信息
 *
 * @author jaquez
 * @date 2023/05/17 17:39
 **/
public abstract class AbstractEvent {

    // 事件源
    protected Object source;

    public AbstractEvent(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
