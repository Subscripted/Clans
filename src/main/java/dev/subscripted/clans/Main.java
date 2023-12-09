package dev.subscripted.clans;

import dev.subscripted.clans.data.FileManager;
import dev.subscripted.clans.modules.database.MySQL;
import dev.subscripted.clans.registery.CommandRegistery;
import dev.subscripted.clans.registery.ListenerRegistery;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Getter
    private static Main instance;


    @Override
    public void onEnable() {
        instance = this;
        initMySQLFile();
        initializeListeners();
        initializeCommands();
    }

    @Override
    public void onDisable() {
        MySQL.close();
    }

    private static void initializeCommands() {
        CommandRegistery.initializeCommands(instance);
    }

    private static void initializeListeners() {
        ListenerRegistery.initializeListeners(instance);
    }

    private static void initMySQLFile() {
        FileManager.setupFiles(instance);
    }
}
