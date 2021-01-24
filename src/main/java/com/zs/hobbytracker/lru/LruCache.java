package main.java.com.zs.hobbytracker.lru;

import main.java.com.zs.hobbytracker.models.HobbyAttributes;

import java.util.LinkedHashMap;

public class LruCache {

    int capacity;
    LinkedHashMap<String, Integer> lru;

    public LruCache(int capacity) {
        this.capacity = capacity;
        lru = new LinkedHashMap<>();
    }

    public Integer get(String key) {
        if (lru.containsKey(key)) {
            int numberOfTimes = lru.get(key);
            lru.remove(key);
            lru.put(key, numberOfTimes);
            return lru.get(key);
        }
        return null;
    }

    public void put(String key) {
        int number = 0;
        if (lru.containsKey(key)) {
            number = lru.get(key);
            lru.remove(key);
        } else {
            if (lru.size() == capacity) {
                lru.remove(lru.keySet().iterator().next());
                lru.put(key, 1);
            }
        }
        lru.put(key, number + 1);
    }

}
