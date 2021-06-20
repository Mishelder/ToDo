package com.miaskor.dao;

import com.miaskor.entity.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TaskDao<K,T> extends Dao<K,T> {
    void createTasks(List<T> object);
    void deleteTaskByDate(LocalDate day);
    Map<LocalDate,List<T>> readAllTaskByPeriod(LocalDate from,LocalDate to,Integer clientIndex);
    List<T> readByDate(LocalDate day,Integer clientIndex);
}
