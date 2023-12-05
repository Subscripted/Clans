package dev.subscripted.clans.modules.data;

import dev.subscripted.clans.Main;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static File mysqlFile;

    @Getter
    private static FileConfiguration mysqlConfig;


    public void setup(Main instance) {
        mysqlFile = new File(instance.getDataFolder(), "mysql.yml");

        if (!mysqlFile.exists()) {
            instance.saveResource("mysql.yml", false);
        }

        mysqlConfig = YamlConfiguration.loadConfiguration(mysqlFile);
        setDefaultMySQL();
    }

    private static void setDefaultMySQL() {
        FileConfiguration cfg = getMysqlConfig();

        if (!cfg.contains("username")) {
            cfg.set("username", "root");
        }
        if (!cfg.contains("password")) {
            cfg.set("password", "password");
        }

        if (!cfg.contains("database")) {
            cfg.set("database", "localhost");
        }

        if (!cfg.contains("host")) {
            cfg.set("host", "localhost");
        }

        if (!cfg.contains("port")) {
            cfg.set("port", "3306");
        }
        try {
            cfg.save(mysqlFile);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}
