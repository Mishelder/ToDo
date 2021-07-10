package com.miaskor.cache;

import com.miaskor.dto.FetchTaskDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Deprecated(since = "Since switching to javascript")
public class TasksCacheLRU {
    private static final int MAX_CAPACITY = 20;
    private final Map<String, List<FetchTaskDto>> lruCache = new LinkedHashMap<>(MAX_CAPACITY, 1.1f, true){
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, List<FetchTaskDto>> eldest) {
            return size() > MAX_CAPACITY;
        }
    };

    public void put(String key,List<FetchTaskDto> tasks){
        this.lruCache.put(key,tasks);
    }

    public List<FetchTaskDto> getByKey(String key){
        return lruCache.get(key);
    }

    public Map<String, List<FetchTaskDto>> getMap(){
        return lruCache;
    }
}
