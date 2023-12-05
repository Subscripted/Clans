package dev.subscripted.clans.modules.clans.commands;

import dev.subscripted.clans.modules.clans.gui.ClanMenu;
import dev.subscripted.clans.modules.clans.manager.ClanManager;
import dev.subscripted.clans.modules.clans.pojo.Clan;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MyClanCommand implements CommandExecutor {

    private ClanManager clanManager;

    public MyClanCommand(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Du musst ein Spieler sein, um diesen Befehl zu verwenden.");
            return true;
        }

        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();

        Clan playerClan = clanManager.getClan(player.getName());

        if (playerClan != null) {
            player.sendMessage("Du bist Mitglied im Clan: " + playerClan.getClanName());
            ClanMenu.openMenu(player);
        } else {
            player.sendMessage("Du bist in keinem Clan.");
        }

        return true;
    }
}
