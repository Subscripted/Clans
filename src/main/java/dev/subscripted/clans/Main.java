package dev.subscripted.clans;

import dev.subscripted.clans.registery.CommandRegistery;
import dev.subscripted.clans.registery.ListenerRegistery;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Getter
    private static Main instance;



    @Override
    public void onEnable() {
        initializeListeners();
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
}
