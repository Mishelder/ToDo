package com.miaskor.dao;

import com.miaskor.database.ConnectionManager;
import com.miaskor.database.MainConnectionManager;
import com.miaskor.database.TestConnectionManager;
import com.miaskor.entity.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientDaoImpl implements ClientDao<Integer, Client> {

    private static final ClientDaoImpl INSTANCE = new ClientDaoImpl();
    private static ConnectionManager connectionManager = MainConnectionManager.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientDaoImpl.class.getName());

    private static final String CREATE_CLIENT = """ 
            INSERT INTO client
            (login, email, password) VALUES (?,?,?)""";
    private static final String READ_CLIENT = """
            SELECT id,login,email,password FROM client WHERE id = ?""";
    private static final String UPDATE_CLIENT = """
            UPDATE client
            SET login = ?,email=?,password=? WHERE id = ?""";
    private static final String DELETE_CLIENT_BY_ID = """
            DELETE FROM client WHERE id = ?""";
    private static final String READ_ALL_CLIENT = """
            SELECT id,login,email,password FROM client ORDER BY id""";
    private static final String READ_BY_LOGIN = """
            SELECT id,login,email,password FROM client WHERE login = ?""";
    private static final String READ_BY_EMAIL = """
            SELECT id,login,email,password FROM client WHERE email = ?""";

    public static ClientDaoImpl getInstance(boolean isTest) {
        if(isTest)
            connectionManager = TestConnectionManager.getInstance();
        return INSTANCE;
    }

    @Override
    public Client create(Client client) {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(CREATE_CLIENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setString(3, client.getPassword());
            preparedStatement.execute();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next())
                client.setId(generatedKeys.getObject(1, Integer.class));
            LOGGER.info("{} has been created", client);
        }catch (SQLException e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return client;
    }

    @Override
    public Optional<Client> read(Integer index) {
        Client client = null;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(READ_CLIENT)) {
            preparedStatement.setInt(1, index);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client = buildClient(resultSet);
            }
            LOGGER.info("{} has been read", client);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(client);
    }

    @Override
    public boolean update(Client client) {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(UPDATE_CLIENT)) {
            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setString(3, client.getPassword());
            preparedStatement.setInt(4, client.getId());
            LOGGER.info("{} has been updated ", client);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Integer index) {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(DELETE_CLIENT_BY_ID)) {
            preparedStatement.setInt(1, index);
            LOGGER.info("Client has been updated with id {}", index);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(READ_ALL_CLIENT)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(buildClient(resultSet));
            }
            LOGGER.info("Clients have been found");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return clients;
    }

    @Override
    public Optional<Client> readByLogin(String login) {
        Client client = null;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(READ_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                client = buildClient(resultSet);
            LOGGER.info("{} have been read",client);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(client);
    }

    @Override
    public Optional<Client> readByEmail(String email) {
        Client client = null;
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(READ_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                client = buildClient(resultSet);
            LOGGER.info("{} have been read",client);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(client);
    }

    private Client buildClient(ResultSet resultSet) throws SQLException {
        return Client.builder().id(resultSet.getObject(1, Integer.class))
                .login(resultSet.getObject(2, String.class))
                .email(resultSet.getObject(3, String.class))
                .password(resultSet.getObject(4, String.class))
                .build();
    }
}
