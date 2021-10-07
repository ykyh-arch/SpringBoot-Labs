
package cn.iocoder.springboot.lab33.shirodemo.utils;

import java.util.HashMap;

public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
