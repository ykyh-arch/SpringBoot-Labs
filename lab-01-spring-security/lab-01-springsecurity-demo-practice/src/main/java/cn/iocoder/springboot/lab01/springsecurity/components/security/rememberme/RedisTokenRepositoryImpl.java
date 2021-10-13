package cn.iocoder.springboot.lab01.springsecurity.components.security.rememberme;

import cn.iocoder.springboot.lab01.springsecurity.common.Constants;
import cn.iocoder.springboot.lab01.springsecurity.components.cache.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis 保存用户 token，参考自：https://www.jianshu.com/p/69b313bc4d70
 *
 * @author jaquez
 * @date 2021/10/12 11:56
 **/
public class RedisTokenRepositoryImpl implements PersistentTokenRepository {

    private static final Logger log = LoggerFactory.getLogger(RedisTokenRepositoryImpl.class);

    @Value("${remember-me.expireTime}")
    private Integer expireTime;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    public RedisTemplate redisTemplate;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        String key = generateKey(token.getSeries());
        HashMap<String, String> map = new HashMap();

        map.put("username", token.getUsername());
        map.put("tokenValue", token.getTokenValue());
        map.put("date", String.valueOf(token.getDate().getTime()));

        redisCache.setCacheObject(key,map,expireTime,TimeUnit.DAYS);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        String key = generateKey(series);
        HashMap<String, String> map = new HashMap();
        map.put("tokenValue", tokenValue);
        map.put("date", String.valueOf(lastUsed.getTime()));
        redisCache.setCacheObject(key,map,expireTime,TimeUnit.DAYS);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        String key = generateKey(seriesId);
        List<String> hashKeys = new ArrayList<>();
        hashKeys.add("username");
        hashKeys.add("tokenValue");
        hashKeys.add("date");
        List<String> hashValues = redisTemplate.opsForHash().multiGet(key, hashKeys);
        String username = hashValues.get(0);
        String tokenValue = hashValues.get(1);
        String date = hashValues.get(2);
        if (null == username || null == tokenValue || null == date) {
            return null;
        }
        Long timestamp = Long.valueOf(date);
        Date time = new Date(timestamp);
        PersistentRememberMeToken token = new PersistentRememberMeToken(username, seriesId, tokenValue, time);
        return token;
    }

    @Override
    public void removeUserTokens(String username) {
        byte[] hashKey = redisTemplate.getHashKeySerializer().serialize("username");
        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
        try (Cursor<byte[]> cursor = redisConnection.scan(ScanOptions.scanOptions().match(generateKey("*")).count(1024).build())) {
            while (cursor.hasNext()) {
                byte[] key = cursor.next();
                byte[] hashValue = redisConnection.hGet(key, hashKey);
                String storeName = (String) redisTemplate.getHashValueSerializer().deserialize(hashValue);
                if (username.equals(storeName)) {
                    redisConnection.expire(key, 0L);
                    return;
                }
            }
        } catch (IOException ex) {
            log.warn("token remove exception", ex);
        }

    }

    /**
     * 生成 key
     *
     * @param series
     * @return
     */
    private String generateKey(String series) {
        return Constants.REMEMBER_ME_KEY + series;
    }
}
