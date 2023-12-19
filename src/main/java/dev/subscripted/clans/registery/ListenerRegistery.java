package dev.subscripted.clans.registery;

import dev.subscripted.clans.Main;
import dev.subscripted.clans.modules.clans.manager.ItemInteraction;
import org.bukkit.Bukkit;

public class ListenerRegistery {

    public static void initializeListeners(Main instance){

        Bukkit.getPluginManager().registerEvents(new ItemInteraction(), instance);

    }
}
