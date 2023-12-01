package dev.subscripted.clans.registery;

import dev.subscripted.clans.Main;
import dev.subscripted.clans.modules.clans.commands.MyClanCommand;
import dev.subscripted.clans.modules.clans.manager.ClanManager;


public class CommandRegistery {

    private static ClanManager clanmanager;

    public void initializeCommands(Main instance){
        instance.getCommand("clan").setExecutor(new MyClanCommand(clanmanager));
    }
}
