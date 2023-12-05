package dev.subscripted.clans.registery;

import dev.subscripted.clans.Main;
import dev.subscripted.clans.modules.clans.commands.ClanInvite;
import dev.subscripted.clans.modules.clans.commands.MyClanCommand;
import dev.subscripted.clans.modules.clans.manager.ClanInviteManager;
import dev.subscripted.clans.modules.clans.manager.ClanManager;


public class CommandRegistery {

    private static ClanManager clanmanager;
    private static ClanInviteManager inviteManager;

    public void initializeCommands(Main instance){
        instance.getCommand("myclan").setExecutor(new MyClanCommand(clanmanager));
        instance.getCommand("clan").setExecutor(new ClanInvite(clanmanager, inviteManager));
        instance.getCommand("clan").setTabCompleter(new ClanInvite(clanmanager, inviteManager));
    }
}
