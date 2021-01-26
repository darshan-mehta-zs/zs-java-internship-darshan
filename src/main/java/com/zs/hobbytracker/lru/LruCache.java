package main.java.com.zs.hobbytracker.lru;

import main.java.com.zs.hobbytracker.Hobby;
import main.java.com.zs.hobbytracker.models.HobbyAttributes;

import java.util.*;

public class LruCache {

    int capacity;
    LinkedHashMap<Integer, Map<Integer, HobbyAttributes>> lru;

    public LruCache(int capacity) {
        this.capacity = capacity;
        lru = new LinkedHashMap<>();
    }

    public Map<Integer, HobbyAttributes> get(Integer key) {
        if (lru.containsKey(key)) {
            Map<Integer, HobbyAttributes> hobbyAttributes = lru.get(key);
            lru.remove(key);
            lru.put(key, hobbyAttributes);
            return lru.get(key);
        }
        Hobby.logger.info("Key not present");
        return null;
    }

    public void put(Integer key, HobbyAttributes hobbyAttributes) {
        if (lru.containsKey(key)) {
            Map<Integer, HobbyAttributes> alreadyPresent = lru.get(key);
            lru.remove(key);
            alreadyPresent.put(hobbyAttributes.getHobbyId(), hobbyAttributes);
            lru.put(key, alreadyPresent);
        } else {
            if (lru.size() == capacity) {
                lru.remove(lru.keySet().iterator().next());
            }
            if (hobbyAttributes == null) {
                Hobby.logger.warning("Pass the object");
                return;
            }
            Map<Integer, HobbyAttributes> hobbyAttributesMap = new HashMap<>();
            hobbyAttributesMap.put(hobbyAttributes.getHobbyId(), hobbyAttributes);
            lru.put(key, hobbyAttributesMap);
        }

    }

}
