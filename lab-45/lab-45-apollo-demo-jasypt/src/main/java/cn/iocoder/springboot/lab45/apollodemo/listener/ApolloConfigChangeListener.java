package cn.iocoder.springboot.lab45.apollodemo.listener;

import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.enums.PropertyChangeType;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 自定义一个ApolloConfigChangeListener监听器，在onChange()方法中判断更新的属性值如果以“ENC(”开头则，调用第3步的类的刷新方法，刷新缓存。
 *
 * @author jaquez
 * @date 2021/09/17 17:00
 **/
// @Component
public class ApolloConfigChangeListener implements ConfigChangeListener {

    private JaspytRefreshScopeRefreshedEventListener listener;

    public ApolloConfigChangeListener() {
    }

    public ApolloConfigChangeListener(JaspytRefreshScopeRefreshedEventListener listener)
    {
        this.listener = listener;
    }
    @Override
    public void onChange(ConfigChangeEvent changeEvent) {

        changeEvent.changedKeys().forEach(k -> {
            ConfigChange configChange = changeEvent.getChange(k);

            String propertyName = configChange.getPropertyName();
            String newValue = configChange.getNewValue();
            PropertyChangeType changeType = configChange.getChangeType();

            if(newValue.startsWith("ENC(") && this.listener !=null)
            // 手动刷新 Jaspyt 本地缓存值
            {
                this.listener.refresh();
            }
        });
    }
}
