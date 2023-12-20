package com.example.Playlist_pack.Config.cache;


import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class CacheEventLogger implements CacheEventListener<Object, Object> {

    public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        log.info(
                "cache event logger message. getKey: {} / getOldValue: {} / getNewValue:{}",
                cacheEvent.getKey(),
                cacheEvent.getOldValue() == null ? "없음" : cacheEvent.getOldValue().hashCode(),
                cacheEvent.getNewValue() == null ? "없음" : cacheEvent.getNewValue().hashCode());
    }
}
