package com.miaskor.dao;

import com.miaskor.database.ConnectionManager;
import com.miaskor.database.MainConnectionManager;
import com.miaskor.database.TestConnectionManager;
import com.miaskor.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskDaoImpl implements TaskDao<Integer, Task> {

    private static final TaskDaoImpl INSTANCE = new TaskDaoImpl();
    private static ConnectionManager connectionManager = MainConnectionManager.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskDaoImpl.class.getName());

    private static final String CREATE_TASK = """
            INSERT INTO task
            (client_id, task_name, done, date) VALUES (?,?,?,?)
            """;
    private static final String READ_TASK = """
            SELECT id, client_id, task_name, done, date
            FROM task
            WHERE id = ?
            """;
    private static final String READ_TASK_BY_DATE = """
            SELECT id, client_id, task_name, done, date
            FROM task
            WHERE date = ? AND client_id = ? ORDER BY id
            """;
    private static final String UPDATE_TASK = """
            UPDATE task
            SET task_name=?, done=?
            WHERE id=?
            """;
    private static final String DELETE_TASK = """
            DELETE FROM task
            WHERE id=?
            """;
    private static final String READ_ALL_TASK = """
            SELECT id, client_id, task_name, done, date
            FROM task ORDER BY id
            """;


    public static TaskDaoImpl getInstance(boolean isTest) {
        if(isTest)
            connectionManager = TestConnectionManager.getInstance();
        return INSTANCE;
    }

    /*
     * clientDao.read(resultSet.getObject(2,Integer.class)).get()
     * won't throw NoSuchElementException because cell in database cannot be null
     *
     * maybe fetch all clients by one request will enhance performance
     * */
    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(READ_ALL_TASK)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                tasks.add(buildTask(resultSet));
            LOGGER.info("Tasks have been executed found");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public Task create(Task task) {
        try (var connection = connectionManager.getConnection();
             var statement = connection.prepareStatement(CREATE_TASK,PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, task.getClientId());
            statement.setObject(2, task.getTaskName());
            statement.setObject(3, task.getDone());
            statement.setObject(4, Date.valueOf(task.getDate()));
            statement.executeUpdate();
            var generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                task.setId(generatedKeys.getObject(1,Integer.class));
            }
            LOGGER.info("{} has been executed create",task);
        }catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        return task;
    }

    @Override
    public void createTasks(List<Task> objects) {
        for (Task task : objects) {
            create(task);
        }
    }

    @Override
    public Optional<Task> read(Integer index) {
        Task task = null;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(READ_TASK)) {
            preparedStatement.setInt(1, index);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                task = buildTask(resultSet);
            }
            LOGGER.info("{} has been executed read",task);
        }catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(task);
    }

    @Override
    public List<Task> readByDate(LocalDate day,Integer clientIndex) {
        List<Task> tasks = new ArrayList<>();
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(READ_TASK_BY_DATE)) {
            preparedStatement.setDate(1, Date.valueOf(day));
            preparedStatement.setInt(2, clientIndex);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                tasks.add(buildTask(resultSet));
            if(!tasks.isEmpty())
                LOGGER.info("{} have been read by date",tasks);
        }catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        return tasks;
    }

    /*
        from - start day included
        to - end day included
     */
    @Override
    public Map<LocalDate, List<Task>> readAllTaskByPeriod(LocalDate from, LocalDate to,Integer clientIndex) {
        if(to.toString().compareTo(from.toString())<0) {
            LOGGER.error("Date from cannot be more than date to (readAllTaskByPeriod)");
            throw new IllegalArgumentException("Date from cannot be more than date to");
        }
        Map<LocalDate, List<Task>> tasks = new HashMap<>();
        while (!from.equals(to.plusDays(1))) {
            var tasksByDay = readByDate(from, clientIndex);
            if(!tasksByDay.isEmpty())
                tasks.put(from, tasksByDay);
            from = from.plusDays(1);
        }
        return tasks;
    }


    @Override
    public boolean update(Task task) {
        try (var connection = connectionManager.getConnection();
             var statement = connection.prepareStatement(UPDATE_TASK)) {
            statement.setString(1,task.getTaskName());
            statement.setBoolean(2,task.getDone());
            statement.setInt(3,task.getId());
            LOGGER.info("{} has been executed update",task);
            return statement.executeUpdate()>0;
        }catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Integer index) {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(DELETE_TASK)) {
            preparedStatement.setInt(1, index);
            LOGGER.info("Task has been executed delete by id {}",index);
            return preparedStatement.executeUpdate()>0;
        }catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }

    private Task buildTask(ResultSet resultSet) throws SQLException {
        return Task.builder()
                .id(resultSet.getObject(1, Integer.class))
                .clientId(resultSet.getObject(2, Integer.class))
                .taskName(resultSet.getObject(3, String.class))
                .done(resultSet.getObject(4, Boolean.class))
                .date(resultSet.getObject(5, LocalDate.class)).build();
    }
}
