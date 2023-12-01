package dev.subscripted.clans.modules.database;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Getter
@Setter
public class MySQL {

    private static Connection con;


    public static String username;
    public static String password;
    public static String database;
    public static String host;
    public static String port;

    private final static String COULD_NOT_CREATE_DATABASE_ERROR = "MySQL Connection could not created. Maybe check your MySQL.yml!";
    private final static String DATABASE_CONNECTED = "MySQL connected!";
    private final static String DATABASE_CLOSED = "MySQL connection closed!";

    public static CompletableFuture<Connection> getConnection() {
        return CompletableFuture.supplyAsync(() -> {
            if (!isConnected()) {
                connect();
            }
            return con;
        });
    }

    @SneakyThrows
    public static void connect() {
        if (isConnected()) {
            return;
        } else {
            System.out.println(COULD_NOT_CREATE_DATABASE_ERROR);
        }

        con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
        System.out.println(DATABASE_CONNECTED);
    }

    @SneakyThrows
    public static void close() {
        if (isConnected()) {
            con.close();
            System.out.println(DATABASE_CLOSED);
        }
    }

    public static boolean isConnected() {
        return con != null;
    }

    @SneakyThrows
    public static void createTable() {
        if (isConnected()) {
            List<String> tables = new ArrayList<>();
            tables.add("CREATE TABLE IF NOT EXISTS Clans (Clanname VARCHAR(100), Clantag VARCHAR(100), OwnerUUID VARCHAR(100), Members VARCHAR(100))");
            createTable(tables);
        }
    }

    @SneakyThrows
    public static void createTable(List<String> tableQueries) {
        if (isConnected()) {
            try (Statement statement = con.createStatement()) {
                for (String query : tableQueries) {
                    statement.executeUpdate(query);
                }
            }
        }
    }

    @SneakyThrows
    public static void update(String query) {
        if (isConnected()) {
            con.createStatement().executeUpdate(query);
        }
    }
}
