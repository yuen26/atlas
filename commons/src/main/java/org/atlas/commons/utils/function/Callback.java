package org.atlas.commons.utils.function;

public interface Callback<T> {

    void onSuccess(T result);
    
    void onError(Exception e);
}
