package com.miaskor.mapper;

public interface Mapper <F,T>{
    T map(F object);
}
