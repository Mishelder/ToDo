package com.miaskor.service;

import com.miaskor.dao.TaskDaoImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteTaskService {
    private static final DeleteTaskService INSTANCE = new DeleteTaskService();
    private final TaskDaoImpl taskDao = TaskDaoImpl.getInstance();

    public void deleteTasksByDayAndClientId(Integer clientId, LocalDate date){
        taskDao.deleteTaskByDateAndClientId(date, clientId);
    }

    public static DeleteTaskService getInstance(){
        return INSTANCE;
    }
}
