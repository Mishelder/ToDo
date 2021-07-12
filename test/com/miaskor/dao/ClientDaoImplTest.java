package com.miaskor.dao;

import com.miaskor.entity.Client;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientDaoImplTest {

    private final ClientDaoImpl clientDao = ClientDaoImpl.getInstance(true);
    private static Integer clientId;

    @Nested
    class Delete {
        @Test
        void should_ReturnTrue_When_DeleteClient() {
            //given
            Integer clientId = ClientDaoImplTest.clientId;
            //when
            boolean actual = clientDao.delete(clientId);
            //then
            assertTrue(actual);
        }

        @Test
        void should_ReturnFalse_When_NotDeleteClient() {
            //given
            Integer clientId = -1;
            //when
            boolean actual = clientDao.delete(clientId);
            //then
            assertFalse(actual);
        }
    }

    @Nested
    class Create {
        @Test
        void should_ReturnClientWithId_When_CreateClient() {
            //given

            Client expected = Client.builder()
                    .email("mail@email.com")
                    .login("client_example")
                    .password("qwerty")
                    .build();
            //when
            var actual = clientDao.create(expected);
            ClientDaoImplTest.clientId = actual.getId();
            //then
            assertAll(
                    () -> assertEquals(expected.getLogin(), actual.getLogin()),
                    () -> assertEquals(expected.getEmail(), actual.getEmail()),
                    () -> assertEquals(expected.getPassword(), actual.getPassword()),
                    () -> assertNotNull(actual.getId())
            );
        }

        @Test
        void should_ReturnException_When_ClientIsExist() {
            //given
            Client client = Client.builder()
                    .email("example1@mail.com")
                    .login("killer888")
                    .password("qwerty")
                    .build();
            //when
            Executable executable = () -> clientDao.create(client);
            //then
            assertThrows(SQLException.class, executable);
        }
    }

    @Nested
    class Read {
        @Test
        @SuppressWarnings("all")
        void should_ReturnClient_When_ClientIsExist() {
            //given
            int index = 3;
            Client expected = Client.builder()
                    .id(index)
                    .email("example3@mail.com")
                    .login("HellenGeller")
                    .password("4321")
                    .build();
            //when
            var actual = clientDao.read(index).get();
            //then
            assertAll(
                    () -> assertEquals(expected.getLogin(), actual.getLogin()),
                    () -> assertEquals(expected.getEmail(), actual.getEmail()),
                    () -> assertEquals(expected.getPassword(), actual.getPassword()),
                    () -> assertNotNull(actual.getId())
            );
        }

        @Test
        void should_ReturnNullableOptional_When_ClientIsNotExist() {
            //given
            int index = -1;
            //when
            var actual = clientDao.read(index);
            //then
            assertTrue(actual.isEmpty());
        }
    }

    @Nested
    class ReadByLogin{
        @Test
        @SuppressWarnings("all")
        void should_ReturnClientByLogin_When_ClientIsExist(){
            //given
            int index = 3;
            Client expected = Client.builder()
                    .id(index)
                    .email("example3@mail.com")
                    .login("HellenGeller")
                    .password("4321")
                    .build();
            //when
            var actual = clientDao.readByLogin(expected.getLogin()).get();
            //then
            assertAll(
                    () -> assertEquals(expected.getLogin(), actual.getLogin()),
                    () -> assertEquals(expected.getEmail(), actual.getEmail()),
                    () -> assertEquals(expected.getPassword(), actual.getPassword()),
                    () -> assertNotNull(actual.getId())
            );
        }

        @Test
        void should_ReturnClientByLogin_When_ClientIsNotExist(){
            //given
            Client expected = Client.builder()
                    .login("notExistLogin")
                    .build();
            //when
            var actual = clientDao.readByLogin(expected.getLogin());
            //then
            assertTrue(actual.isEmpty());
        }
    }

    @Nested
    class ReadByEmail{
        @Test
        @SuppressWarnings("all")
        void should_ReturnClientByEmail_When_ClientIsExist(){
            //given
            int index = 3;
            Client expected = Client.builder()
                    .id(index)
                    .email("example3@mail.com")
                    .login("HellenGeller")
                    .password("4321")
                    .build();
            //when
            var actual = clientDao.readByEmail(expected.getEmail()).get();
            //then
            assertAll(
                    () -> assertEquals(expected.getLogin(), actual.getLogin()),
                    () -> assertEquals(expected.getEmail(), actual.getEmail()),
                    () -> assertEquals(expected.getPassword(), actual.getPassword()),
                    () -> assertNotNull(actual.getId())
            );
        }

        @Test
        void should_ReturnClientByEmail_When_ClientIsNotExist(){
            //given
            Client expected = Client.builder()
                    .email("notExistEmail")
                    .build();
            //when
            var actual = clientDao.readByEmail(expected.getEmail());
            //then
            assertTrue(actual.isEmpty());
        }
    }

    @Nested
    class Update {
        @Test
        void should_ReturnTrue_When_ClientIsUpdate() {
            //given
            int index = 12;
            Client client = Client.builder()
                    .id(index)
                    .email("example_mail@gmail.com")
                    .login("example")
                    .password("234")
                    .build();
            //when
            var actual = clientDao.update(client);
            //then
            assertTrue(actual);
        }

        @Test
        void should_ReturnFalse_When_ClientIsNotUpdate() {
            //given
            int index = 24;
            Client client = Client.builder()
                    .id(index)
                    .email("123@gmail.com")
                    .login("qwertyClient")
                    .password("234")
                    .build();
            //when
            var actual = clientDao.update(client);
            //then
            assertFalse(actual);
        }
    }

    @Nested
    class ReadAll {

        @Test
        void should_ReturnListClients() {
            //given
            List<Client> expected = createClientsList();
            //when
            var actual = clientDao.findAll();

            assertEquals(expected.get(0).getLogin(),actual.get(0).getLogin());
            //then
            assertAll(
                    ()-> assertEquals(expected.get(0).getId(),actual.get(0).getId()),
                    ()-> assertEquals(expected.get(0).getEmail(),actual.get(0).getEmail()),
                    ()-> assertEquals(expected.get(0).getLogin(),actual.get(0).getLogin()),
                    ()-> assertEquals(expected.get(0).getPassword(),actual.get(0).getPassword()),

                    ()->  assertEquals(expected.get(1).getId(),actual.get(1).getId()),
                    ()->  assertEquals(expected.get(1).getEmail(),actual.get(1).getEmail()),
                    ()->  assertEquals(expected.get(1).getLogin(),actual.get(1).getLogin()),
                    ()->  assertEquals(expected.get(1).getPassword(),actual.get(1).getPassword()),

                    ()->  assertEquals(expected.get(2).getId(),actual.get(2).getId()),
                    ()->   assertEquals(expected.get(2).getEmail(),actual.get(2).getEmail()),
                    ()->   assertEquals(expected.get(2).getLogin(),actual.get(2).getLogin()),
                    ()->   assertEquals(expected.get(2).getPassword(),actual.get(2).getPassword()),

                    ()->   assertEquals(expected.get(3).getId(),actual.get(3).getId()),
                    ()->   assertEquals(expected.get(3).getEmail(),actual.get(3).getEmail()),
                    ()->  assertEquals(expected.get(3).getLogin(),actual.get(3).getLogin()),
                    ()->   assertEquals(expected.get(3).getPassword(),actual.get(3).getPassword())
            );
        }

        private List<Client> createClientsList() {
            return List.of(
                    Client.builder()
                            .id(1)
                            .email("example1@mail.com")
                            .login("killer888")
                            .password("qwerty")
                            .build(),
                    Client.builder()
                            .id(2)
                            .email("example2@mail.com")
                            .login("goblin")
                            .password("123")
                            .build(),
                    Client.builder()
                            .id(3)
                            .email("example3@mail.com")
                            .login("HellenGeller")
                            .password("4321")
                            .build(),
                    Client.builder()
                            .id(12)
                            .email("example_mail@gmail.com")
                            .login("example")
                            .password("234")
                            .build()
            );
        }
    }




}