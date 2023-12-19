package dev.subscripted.clans.data;

import dev.subscripted.clans.Main;
import dev.subscripted.clans.enums.UIText;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static File mysqlFile;
    private static File configFile;

    @Getter
    private static FileConfiguration mysqlConfig;

    @Getter
    private static FileConfiguration config;


    public static void setupFiles(Main instance) {
        mysqlFile = new File(instance.getDataFolder(), "mysql.yml");
        configFile = new File(instance.getDataFolder(), "config.yml");

        if (!mysqlFile.exists()) {
            instance.saveResource("mysql.yml", false);
        }
        if (!configFile.exists()) {
            instance.saveResource("config.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
        mysqlConfig = YamlConfiguration.loadConfiguration(mysqlFile);
        setDefaultMySQL();
        setDefaultConfig();
    }

    private static void setDefaultConfig() {
        FileConfiguration cfg = getConfig();

        if (!cfg.contains("ui.clanui_title")) {
            cfg.set("ui.clanui_title", "&c&lClans");
        }
        if (!cfg.contains("ui.you_are_member_in_clan_name")){
            cfg.set("ui.you_are_member_in_clan_name", "&7You are a Member in Clan '&c%clan_name%'&7!");
        }
        if (!cfg.contains("ui.you_are_in_no_clan")){
            cfg.set("ui.you_are_in_no_clan", "&cYou are in no Clan.");
        }
        try {
            cfg.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public static String getUIText(UIText uiText) {
        return config.getString("ui." + uiText.name().toLowerCase()).replace("&", "§");
    }
}
