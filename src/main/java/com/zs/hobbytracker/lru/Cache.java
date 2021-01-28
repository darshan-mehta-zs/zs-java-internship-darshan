package com.zs.hobbytracker.lru;

import java.util.LinkedHashMap;
import java.util.logging.Logger;

/**
 * This class can be used for caching and this class is implementation of LRU
 *
 * @param <K> key for the cache
 * @param <V> value for the cache
 */
public class Cache<K, V> {

    /**
     * logger for logging
     */
    private static Logger logger = com.zs.hobbytracker.logger.Logger.getLogger();

    /**
     * Capacity of cache
     */
    Integer capacity;

    /**
     * Data structure to store cache
     */
    LinkedHashMap<K, V> cache;

    /**
     * @param capacity accepts capacity for cache
     */
    public Cache(Integer capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<>();
    }

    /**
     * @param key accepts key to search in cache
     * @return value if key is found or null if key is not found
     */
    public V get(K key) {
        if (cache.containsKey(key)) {
            logger.info("Key found or cache hit");
            return cache.get(key);
        }
        logger.info("Key not found or cache miss");
        return null;
    }

    /**
     * @param key   accepts key to be stored
     * @param value accepts value to be stored for the key
     */
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        } else {
            if (capacity == cache.size()) {
                cache.remove(cache.keySet().iterator().next());
            }
        }
        logger.info("key value added to cache");
        cache.put(key, value);
    }


}
