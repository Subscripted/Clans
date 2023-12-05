package dev.subscripted.clans.modules.clans.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClanInviteManager {

    private Map<UUID, String> pendingInvites;
    private ClanManager clanManager;

    public ClanInviteManager() {
        this.pendingInvites = new HashMap<>();
        this.clanManager = new ClanManager();
    }

    public void invitePlayer(Player inviter, Player invited, String clantag) {
        UUID invitedUUID = invited.getUniqueId();
        pendingInvites.put(invitedUUID, clantag);

        // Spieler der eingeladen wird bekommt diese Nachricht (kann noch abgeändert werden).

        invited.sendMessage("Du wurdest in den Clan '" + clantag + "' eingeladen. Verwende /clan accept " + clantag + " zum Beitritt.");
        inviter.sendMessage("Einladung an " + invited.getName() + " gesendet!");
    }

    public void acceptInvite(Player player) {
        UUID playerUUID = player.getUniqueId();

        if (!pendingInvites.containsKey(playerUUID)) {

            String clantag = pendingInvites.get(playerUUID);
            clanManager.addClanMember(clantag, playerUUID);

            player.sendMessage("Du bist dem Clan '" + clantag + "' beigetreten!");
            pendingInvites.remove(playerUUID);
        } else {
            player.sendMessage("Du hast keine ausstehenden Einladungen.");
        }

    }
}

