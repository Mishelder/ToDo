package com.miaskor.cache;

import com.miaskor.dto.FetchTaskDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TasksCacheLRU {
    private static final TasksCacheLRU INSTANCE = new TasksCacheLRU();
    private static final int MAX_CAPACITY = 10;
    private final Map<String, List<FetchTaskDto>> lruCache = new LinkedHashMap<>(MAX_CAPACITY, 1.1f, true){
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, List<FetchTaskDto>> eldest) {
            return size() > MAX_CAPACITY;
        }
    };

    public static TasksCacheLRU getInstance(){
        return INSTANCE;
    }

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
