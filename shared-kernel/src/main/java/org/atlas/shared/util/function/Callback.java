package org.atlas.shared.util.function;

public interface Callback<T> {

    void onSuccess(T result);
    
    void onError(Exception e);
}
