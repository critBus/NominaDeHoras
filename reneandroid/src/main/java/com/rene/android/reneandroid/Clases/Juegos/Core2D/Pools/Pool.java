package com.rene.android.reneandroid.Clases.Juegos.Core2D.Pools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rene on 24/6/2020.
 */

public class Pool<T> {
    public interface PoolObjectFactory<T>{
        public T createObject();
    }

    private final List<T> freeObjects;
    private final PoolObjectFactory<T> factory;
    private final int maxSize;

    public Pool(PoolObjectFactory<T> factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        freeObjects=new ArrayList<>(maxSize);
    }

    public T newObject(){
        T object;
        if(freeObjects.size()==0){
            object=factory.createObject();
        }else{
            object=freeObjects.remove(freeObjects.size()-1);
        }
        return object;
    }

    public void free(T object){
        if(freeObjects.size()<maxSize){
            freeObjects.add(object);
        }
    }
}
