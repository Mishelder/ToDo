package com.miaskor.dao;

import com.miaskor.entity.Client;
import com.miaskor.entity.Task;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaskDaoImplTest {

    private final TaskDaoImpl taskDao = TaskDaoImpl.getInstance(true);
    private static Integer taskId;

    @Nested
    class Delete {
        @Test
        void should_ReturnTrue_When_TaskIsExist() {
            //given
            int indexTask = TaskDaoImplTest.taskId;
            //when
            var actual = taskDao.delete(indexTask);
            //then
            assertTrue(actual);
        }

        @Test
        void should_ReturnFalse_When_TaskIsNotExist() {
            //given
            int indexTask = -1;
            //when
            var actual = taskDao.delete(indexTask);
            //then
            assertFalse(actual);
        }
    }


    @Nested
    class Create {
        @Test
        void should_ReturnTaskWithId_When_CreateTask() {
            //given
            Task expected = Task.builder()
                    .id(0)
                    .taskName("example_task")
                    .date(LocalDate.parse("2020-12-18"))
                    .done(true)
                    .clientId(2)
                    .build();
            //when
            var actual = taskDao.create(expected);
            TaskDaoImplTest.taskId = actual.getId();
            //then
            assertAll(
                    () -> assertNotNull(actual.getId()),
                    () -> assertEquals(expected.getTaskName(), actual.getTaskName()),
                    () -> assertEquals(expected.getDone(), actual.getDone()),
                    () -> assertEquals(expected.getClientId(), actual.getClientId()),
                    () -> assertEquals(expected.getDate(), actual.getDate())
            );
        }
    }

    @Nested
    class ReadByDate{
        @Test
        void should_ReturnListOfTasksByDate_When_ClientAndTaskOnDateExist() {
            //given
            int indexClient = 2;
            var date = LocalDate.parse("2021-07-12");
            List<Task> expected = listTasksByDate();
            //when
            var actual = taskDao.readByDate(date, indexClient);
            //then
            assertAll(
                    () -> assertEquals(expected.get(0).getId(), actual.get(0).getId()),
                    () -> assertEquals(expected.get(0).getClientId(), actual.get(0).getClientId()),
                    () -> assertEquals(expected.get(0).getDate(), actual.get(0).getDate()),
                    () -> assertEquals(expected.get(0).getTaskName(), actual.get(0).getTaskName()),
                    () -> assertEquals(expected.get(0).getDone(), actual.get(0).getDone()),

                    () -> assertEquals(expected.get(1).getId(), actual.get(1).getId()),
                    () -> assertEquals(expected.get(1).getClientId(), actual.get(1).getClientId()),
                    () -> assertEquals(expected.get(1).getDate(), actual.get(1).getDate()),
                    () -> assertEquals(expected.get(1).getTaskName(), actual.get(1).getTaskName()),
                    () -> assertEquals(expected.get(1).getDone(), actual.get(1).getDone()),

                    () -> assertEquals(expected.get(2).getId(), actual.get(2).getId()),
                    () -> assertEquals(expected.get(2).getClientId(), actual.get(2).getClientId()),
                    () -> assertEquals(expected.get(2).getDate(), actual.get(2).getDate()),
                    () -> assertEquals(expected.get(2).getTaskName(), actual.get(2).getTaskName()),
                    () -> assertEquals(expected.get(2).getDone(), actual.get(2).getDone())
            );
        }

        @Test
        void should_ReturnEmptyListOfTasksByDate_When_ClientIdIsExistDateIsNotExist() {
            //given
            int indexClient = 2;
            var date = LocalDate.parse("2021-01-11");
            //when
            var actual = taskDao.readByDate(date, indexClient);
            //then
            assertTrue(actual.isEmpty());
        }

        @Test
        void should_ReturnEmptyListOfTasksByDate_When_ClientIdIsNotExistDateIsExist() {
            //given
            int indexClient = -1;
            var date = LocalDate.parse("2021-07-12");
            //when
            var actual = taskDao.readByDate(date, indexClient);
            //then
            assertTrue(actual.isEmpty());
        }

        @Test
        void should_ReturnEmptyListOfTasksByDate_When_ClientIdIsNotExistDateIsNotExist() {
            //given
            int indexClient = -1;
            var date = LocalDate.parse("1999-01-10");
            //when
            var actual = taskDao.readByDate(date, indexClient);
            //then
            assertTrue(actual.isEmpty());
        }

        private List<Task> listTasksByDate() {
            return List.of(
                    Task.builder()
                            .id(1)
                            .clientId(2)
                            .taskName("buy the milk")
                            .done(true)
                            .date(LocalDate.parse("2021-07-12"))
                            .build(),
                    Task.builder()
                            .id(5)
                            .clientId(2)
                            .taskName("buy the boat")
                            .done(true)
                            .date(LocalDate.parse("2021-07-12")).build(),
                    Task.builder()
                            .id(9)
                            .clientId(2)
                            .taskName("buy the milk")
                            .done(false)
                            .date(LocalDate.parse("2021-07-12")).build()
            );
        }
    }

    @Nested
    class Read {
        @Test
        @SuppressWarnings("all")
        void should_ReturnTask_When_TaskIsExist() {
            //given
            int index = 3;
            Task expected = Task.builder()
                    .id(index)
                    .clientId(1)
                    .taskName("go to the cinema")
                    .done(true)
                    .date(LocalDate.parse("2021-07-09")).build();
            //when
            var actual = taskDao.read(index).get();
            //then
            assertAll(
                    () -> assertEquals(expected.getId(), actual.getId()),
                    () -> assertEquals(expected.getDate(), actual.getDate()),
                    () -> assertEquals(expected.getTaskName(), actual.getTaskName()),
                    () -> assertEquals(expected.getClientId(), actual.getClientId()),
                    () -> assertEquals(expected.getDone(), actual.getDone())
            );
        }

        @Test
        void should_ReturnNullableOptional_When_TaskIsNotExist() {
            //given
            int index = -1;
            //when
            var actual = taskDao.read(index);
            //then
            assertTrue(actual.isEmpty());
        }

    }

    @Nested
    class ReadAllTasksByPeriod{
        @Test
        void should_ReturnListOfTasksByPeriod() {
            //given
            List<Task> tasks = listTasksByPeriod();
            Map<LocalDate, List<Task>> expected = new HashMap<>();
            var clientIndex = 2;
            var dateFrom = LocalDate.parse("2021-07-11");
            var dateTo = LocalDate.parse("2021-07-12");
            expected.put(dateTo, List.of(tasks.get(0), tasks.get(1), tasks.get(2)));
            expected.put(dateFrom, List.of(tasks.get(3), tasks.get(4)));
            //when
            var actual = taskDao.readAllTaskByPeriod(dateFrom, dateTo, clientIndex);
            //then
            assertAll(
                    () -> assertEquals(expected.get(dateFrom).get(0).getId(), actual.get(dateFrom).get(0).getId()),
                    () -> assertEquals(expected.get(dateFrom).get(0).getClientId(), actual.get(dateFrom).get(0).getClientId()),
                    () -> assertEquals(expected.get(dateFrom).get(0).getDate(), actual.get(dateFrom).get(0).getDate()),
                    () -> assertEquals(expected.get(dateFrom).get(0).getTaskName(), actual.get(dateFrom).get(0).getTaskName()),
                    () -> assertEquals(expected.get(dateFrom).get(0).getDone(), actual.get(dateFrom).get(0).getDone()),

                    () -> assertEquals(expected.get(dateFrom).get(1).getId(), actual.get(dateFrom).get(1).getId()),
                    () -> assertEquals(expected.get(dateFrom).get(1).getClientId(), actual.get(dateFrom).get(1).getClientId()),
                    () -> assertEquals(expected.get(dateFrom).get(1).getDate(), actual.get(dateFrom).get(1).getDate()),
                    () -> assertEquals(expected.get(dateFrom).get(1).getTaskName(), actual.get(dateFrom).get(1).getTaskName()),
                    () -> assertEquals(expected.get(dateFrom).get(1).getDone(), actual.get(dateFrom).get(1).getDone()),

                    () -> assertEquals(expected.get(dateTo).get(0).getId(), actual.get(dateTo).get(0).getId()),
                    () -> assertEquals(expected.get(dateTo).get(0).getClientId(), actual.get(dateTo).get(0).getClientId()),
                    () -> assertEquals(expected.get(dateTo).get(0).getDate(), actual.get(dateTo).get(0).getDate()),
                    () -> assertEquals(expected.get(dateTo).get(0).getTaskName(), actual.get(dateTo).get(0).getTaskName()),
                    () -> assertEquals(expected.get(dateTo).get(0).getDone(), actual.get(dateTo).get(0).getDone()),

                    () -> assertEquals(expected.get(dateTo).get(1).getId(), actual.get(dateTo).get(1).getId()),
                    () -> assertEquals(expected.get(dateTo).get(1).getClientId(), actual.get(dateTo).get(1).getClientId()),
                    () -> assertEquals(expected.get(dateTo).get(1).getDate(), actual.get(dateTo).get(1).getDate()),
                    () -> assertEquals(expected.get(dateTo).get(1).getTaskName(), actual.get(dateTo).get(1).getTaskName()),
                    () -> assertEquals(expected.get(dateTo).get(1).getDone(), actual.get(dateTo).get(1).getDone()),

                    () -> assertEquals(expected.get(dateTo).get(2).getId(), actual.get(dateTo).get(2).getId()),
                    () -> assertEquals(expected.get(dateTo).get(2).getClientId(), actual.get(dateTo).get(2).getClientId()),
                    () -> assertEquals(expected.get(dateTo).get(2).getDate(), actual.get(dateTo).get(2).getDate()),
                    () -> assertEquals(expected.get(dateTo).get(2).getTaskName(), actual.get(dateTo).get(2).getTaskName()),
                    () -> assertEquals(expected.get(dateTo).get(2).getDone(), actual.get(dateTo).get(2).getDone())
            );
        }

        @Test
        void should_ReturnException_When_FromDateIsBiggerThanTo(){
            //given
            var dateFrom = LocalDate.parse("2021-07-22");
            var dateTo = LocalDate.parse("2021-07-12");
            var clientId = 3;
            //when
            Executable actual = () ->taskDao.readAllTaskByPeriod(dateFrom, dateTo, clientId);
            //then
            assertThrows(IllegalArgumentException.class,actual);
        }


        private List<Task> listTasksByPeriod() {
            return List.of(
                    Task.builder()
                            .id(1)
                            .clientId(2)
                            .taskName("buy the milk")
                            .done(true)
                            .date(LocalDate.parse("2021-07-12"))
                            .build(),
                    Task.builder()
                            .id(5)
                            .clientId(2)
                            .taskName("buy the boat")
                            .done(true)
                            .date(LocalDate.parse("2021-07-12")).build(),
                    Task.builder()
                            .id(9)
                            .clientId(2)
                            .taskName("buy the milk")
                            .done(false)
                            .date(LocalDate.parse("2021-07-12")).build(),
                    Task.builder()
                            .id(6)
                            .clientId(2)
                            .taskName("hang out wth friends")
                            .done(false)
                            .date(LocalDate.parse("2021-07-11")).build(),
                    Task.builder()
                            .id(7)
                            .clientId(2)
                            .taskName("buy the buckwheat")
                            .done(true)
                            .date(LocalDate.parse("2021-07-11")).build()
            );
        }
    }

    @Nested
    class UpdateTaskDone{
        @Test
        void should_ReturnTrue_When_DoneStatusIsChanged(){
            //given
            Task task = Task.builder()
                    .id(8)
                    .done(false)
                    .build();
            //when
            var actual = taskDao.updateTaskDone(task);
            //then
            assertTrue(actual);
        }

        @Test
        void should_ReturnFalse_When_TaskIsNotExist(){
            //given
            Task task = Task.builder()
                    .id(-1)
                    .done(false)
                    .build();
            //when
            var actual = taskDao.updateTaskDone(task);
            //then
            assertFalse(actual);
        }
    }

    @Nested
    class Update{
        @Test
        void should_ReturnTrue_When_TaskIsChanged(){
            //given
            Task task = Task.builder()
                    .id(8)
                    .taskName("buy the sugar")
                    .build();
            //when
            var actual = taskDao.update(task);
            //then
            assertTrue(actual);
        }

        @Test
        void should_ReturnFalse_When_TaskIsNotExist(){
            //given
            Task task = Task.builder()
                    .id(-1)
                    .done(false)
                    .build();
            //when
            var actual = taskDao.update(task);
            //then
            assertFalse(actual);
        }
    }

    @Nested
    class ReadAll {
        @Test
        void should_ReturnAllTask() {
            //given
            List<Task> expected = getTasks();
            //when
            List<Task> actual = taskDao.findAll();
            //then
            assertAll(
                    () -> assertEquals(expected.get(0).getId(), actual.get(0).getId()),
                    () -> assertEquals(expected.get(0).getClientId(), actual.get(0).getClientId()),
                    () -> assertEquals(expected.get(0).getDate(), actual.get(0).getDate()),
                    () -> assertEquals(expected.get(0).getTaskName(), actual.get(0).getTaskName()),
                    () -> assertEquals(expected.get(0).getDone(), actual.get(0).getDone()),

                    () -> assertEquals(expected.get(1).getId(), actual.get(1).getId()),
                    () -> assertEquals(expected.get(1).getClientId(), actual.get(1).getClientId()),
                    () -> assertEquals(expected.get(1).getDate(), actual.get(1).getDate()),
                    () -> assertEquals(expected.get(1).getTaskName(), actual.get(1).getTaskName()),
                    () -> assertEquals(expected.get(1).getDone(), actual.get(1).getDone()),

                    () -> assertEquals(expected.get(2).getId(), actual.get(2).getId()),
                    () -> assertEquals(expected.get(2).getClientId(), actual.get(2).getClientId()),
                    () -> assertEquals(expected.get(2).getDate(), actual.get(2).getDate()),
                    () -> assertEquals(expected.get(2).getTaskName(), actual.get(2).getTaskName()),
                    () -> assertEquals(expected.get(2).getDone(), actual.get(2).getDone()),

                    () -> assertEquals(expected.get(3).getId(), actual.get(3).getId()),
                    () -> assertEquals(expected.get(3).getClientId(), actual.get(3).getClientId()),
                    () -> assertEquals(expected.get(3).getDate(), actual.get(3).getDate()),
                    () -> assertEquals(expected.get(3).getTaskName(), actual.get(3).getTaskName()),
                    () -> assertEquals(expected.get(3).getDone(), actual.get(3).getDone()),

                    () -> assertEquals(expected.get(4).getId(), actual.get(4).getId()),
                    () -> assertEquals(expected.get(4).getClientId(), actual.get(4).getClientId()),
                    () -> assertEquals(expected.get(4).getDate(), actual.get(4).getDate()),
                    () -> assertEquals(expected.get(4).getTaskName(), actual.get(4).getTaskName()),
                    () -> assertEquals(expected.get(4).getDone(), actual.get(4).getDone()),

                    () -> assertEquals(expected.get(5).getId(), actual.get(5).getId()),
                    () -> assertEquals(expected.get(5).getClientId(), actual.get(5).getClientId()),
                    () -> assertEquals(expected.get(5).getDate(), actual.get(5).getDate()),
                    () -> assertEquals(expected.get(5).getTaskName(), actual.get(5).getTaskName()),
                    () -> assertEquals(expected.get(5).getDone(), actual.get(5).getDone()),

                    () -> assertEquals(expected.get(6).getId(), actual.get(6).getId()),
                    () -> assertEquals(expected.get(6).getClientId(), actual.get(6).getClientId()),
                    () -> assertEquals(expected.get(6).getDate(), actual.get(6).getDate()),
                    () -> assertEquals(expected.get(6).getTaskName(), actual.get(6).getTaskName()),
                    () -> assertEquals(expected.get(6).getDone(), actual.get(6).getDone()),

                    () -> assertEquals(expected.get(7).getId(), actual.get(7).getId()),
                    () -> assertEquals(expected.get(7).getClientId(), actual.get(7).getClientId()),
                    () -> assertEquals(expected.get(7).getDate(), actual.get(7).getDate()),
                    () -> assertEquals(expected.get(7).getTaskName(), actual.get(7).getTaskName()),
                    () -> assertEquals(expected.get(7).getDone(), actual.get(7).getDone()),

                    () -> assertEquals(expected.get(8).getId(), actual.get(8).getId()),
                    () -> assertEquals(expected.get(8).getClientId(), actual.get(8).getClientId()),
                    () -> assertEquals(expected.get(8).getDate(), actual.get(8).getDate()),
                    () -> assertEquals(expected.get(8).getTaskName(), actual.get(8).getTaskName()),
                    () -> assertEquals(expected.get(8).getDone(), actual.get(8).getDone())
            );
        }

        private List<Task> getTasks() {
            return List.of(Task.builder()
                            .id(1)
                            .clientId(2)
                            .taskName("buy the milk")
                            .done(true)
                            .date(LocalDate.parse("2021-07-12"))
                            .build(),
                    Task.builder()
                            .id(2)
                            .clientId(3)
                            .taskName("buy the meal")
                            .done(false)
                            .date(LocalDate.parse("2021-07-10")).build(),
                    Task.builder()
                            .id(3)
                            .clientId(1)
                            .taskName("go to the cinema")
                            .done(true)
                            .date(LocalDate.parse("2021-07-09")).build(),
                    Task.builder()
                            .id(4)
                            .clientId(3)
                            .taskName("do stretching")
                            .done(false)
                            .date(LocalDate.parse("2021-07-10")).build(),
                    Task.builder()
                            .id(5)
                            .clientId(2)
                            .taskName("buy the boat")
                            .done(true)
                            .date(LocalDate.parse("2021-07-12")).build(),
                    Task.builder()
                            .id(6)
                            .clientId(2)
                            .taskName("hang out wth friends")
                            .done(false)
                            .date(LocalDate.parse("2021-07-11")).build(),
                    Task.builder()
                            .id(7)
                            .clientId(2)
                            .taskName("buy the buckwheat")
                            .done(true)
                            .date(LocalDate.parse("2021-07-11")).build(),
                    Task.builder()
                            .id(8)
                            .clientId(3)
                            .taskName("buy the sugar")
                            .done(false)
                            .date(LocalDate.parse("2021-07-10")).build(),
                    Task.builder()
                            .id(9)
                            .clientId(2)
                            .taskName("buy the milk")
                            .done(false)
                            .date(LocalDate.parse("2021-07-12")).build());
        }
    }

}