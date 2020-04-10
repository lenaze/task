package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever{
    OriginalRetriever retriever;
    LRUCache cache = new LRUCache(0);

    public CachingProxyRetriever(Storage storage) {
        retriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object o = null;
        if(cache.find(id) != null) o = cache.find(id);
        else {
            o = retriever.retrieve(id);
            cache.set(id, o);
        }
        return o;
    }
}
