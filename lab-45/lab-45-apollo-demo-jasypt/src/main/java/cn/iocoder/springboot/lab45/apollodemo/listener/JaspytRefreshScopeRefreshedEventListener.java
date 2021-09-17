package cn.iocoder.springboot.lab45.apollodemo.listener;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertySource;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertySourceConverter;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;

/**
 * JaspytRefreshScopeRefreshedEventListener
 * 刷新本地的 PropertySource 相关信息，清理缓存并赋予新值
 * @author jaquez
 * @date 2021/09/17 16:44
 **/
// @Component
public class JaspytRefreshScopeRefreshedEventListener {

    private final ConfigurableEnvironment environment;
    private final EncryptablePropertySourceConverter converter;

    // 构造器注入
    public JaspytRefreshScopeRefreshedEventListener(ConfigurableEnvironment environment, EncryptablePropertySourceConverter converter) {
        this.environment = environment;
        this.converter = converter;
    }

    public void refresh() {
        refreshCachedProperties();
        decorateNewSources();
    }

    private void decorateNewSources() {
        MutablePropertySources propSources = environment.getPropertySources();
        converter.convertPropertySources(propSources);
    }

    private void refreshCachedProperties() {
        PropertySources propertySources = environment.getPropertySources();
        propertySources.forEach(this::refreshPropertySource);
    }

    @SuppressWarnings("rawtypes")
    private void refreshPropertySource(PropertySource<?> propertySource) {
        if (propertySource instanceof CompositePropertySource) {
            CompositePropertySource cps = (CompositePropertySource) propertySource;
            cps.getPropertySources().forEach(this::refreshPropertySource);
        } else if (propertySource instanceof EncryptablePropertySource) {
            EncryptablePropertySource eps = (EncryptablePropertySource) propertySource;
            eps.refresh();
        }
    }

}
