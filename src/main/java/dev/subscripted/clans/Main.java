package dev.subscripted.clans;

import dev.subscripted.clans.modules.data.FileManager;
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
        initializeFiles();
        initializeCommands();

    }

    @Override
    public void onDisable() {

    }

    private static void initializeCommands(){
        CommandRegistery commandRegistery = new CommandRegistery();
        commandRegistery.initializeCommands(instance);
    }
    private static void initializeListeners(){
        ListenerRegistery listenerRegistery = new ListenerRegistery();
        listenerRegistery.initializeListeners(instance);
    }
    private static void initializeFiles(){
        FileManager fileManager = new FileManager();
        fileManager.setup(instance);
    }
}
