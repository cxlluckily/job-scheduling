package com.shankephone.elasticjob.model;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 事件追踪数据源工厂.
 *
 * @author zhangxinguo
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EventTraceDataSourceFactory {
    
    private static final ConcurrentHashMap<HashCode, EventTraceDataSourceInit> DATA_SOURCE_REGISTRY = new ConcurrentHashMap<>(); 
    
    /**
     * 创建事件追踪数据源.
     * 
     * @param driver 数据库驱动类名称
     * @param url 数据库URL
     * @param username 数据库用户名
     * @param password 数据库密码
     * @return 事件追踪数据源
     */
    public static EventTraceDataSourceInit createEventTraceDataSource(final String driver, final String url, final String username, final Optional<String> password) {
        Hasher hasher =  Hashing.md5().newHasher().putString(driver, Charsets.UTF_8).putString(url, Charsets.UTF_8);
        if (!Strings.isNullOrEmpty(username)) {
            hasher.putString(username, Charsets.UTF_8);
        }
        if (password.isPresent()) {
            hasher.putString(password.get(), Charsets.UTF_8);
        }
        HashCode hashCode = hasher.hash();
        EventTraceDataSourceInit result = DATA_SOURCE_REGISTRY.get(hashCode);
        if (null != result) {
            return result;
        }
        EventTraceDataSource eventTraceDataSourceConfiguration = new EventTraceDataSource(driver, url, username);
        if (password.isPresent()) {
            eventTraceDataSourceConfiguration.setPassword(password.get());
        }
        result = new EventTraceDataSourceInit(eventTraceDataSourceConfiguration);
        result.init();
        DATA_SOURCE_REGISTRY.put(hashCode, result);
        return result;
    }
}
