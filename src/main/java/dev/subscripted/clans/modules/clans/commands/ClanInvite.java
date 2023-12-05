package dev.subscripted.clans.modules.clans.commands;

import dev.subscripted.clans.modules.clans.manager.ClanInviteManager;
import dev.subscripted.clans.modules.clans.manager.ClanManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class ClanInvite implements CommandExecutor, TabCompleter {

    private ClanManager clanManager;
    private ClanInviteManager inviteManager;

    public ClanInvite(ClanManager clanManager, ClanInviteManager inviteManager) {
        this.clanManager = clanManager;
        this.inviteManager = inviteManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Du musst ein Spieler sein, um diesen Befehl zu verwenden.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Verwendung: /clan invite <Spieler> oder /clan accept <Clantag>");
            return true;
        }

        if (args[0].equalsIgnoreCase("invite")) {
            if (args.length < 2) {
                player.sendMessage("Verwendung: /clan invite <Spieler>");
                return true;
            }

            Player invitedPlayer = Bukkit.getPlayer(args[1]);

            if (invitedPlayer != null && invitedPlayer.isOnline()) {
                inviteManager.invitePlayer(player, invitedPlayer, clanManager.getClan(player.getName()).getClanTag());
            } else {
                player.sendMessage("Spieler nicht gefunden oder offline.");
            }
        } else if (args[0].equalsIgnoreCase("accept")) {
            if (args.length < 2) {
                player.sendMessage("Verwendung: /clan accept <Clantag>");
                return true;
            }

            inviteManager.acceptInvite(player);
        } else {
            player.sendMessage("Unbekannter Befehl. Verwendung: /clan invite <Spieler> oder /clan accept <Clantag>");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.add("accept");
        }


        return null;
    }
}
