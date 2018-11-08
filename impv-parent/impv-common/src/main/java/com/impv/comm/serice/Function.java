package com.impv.comm.serice;

public interface Function<T, E> {

    public T callback(E e);

}
