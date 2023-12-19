package dev.subscripted.clans.modules.clans.gui;

import dev.subscripted.clans.modules.clans.manager.ClanManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.UUID;

public class MemberMenu {

    private static ClanManager clanManager;

    public MemberMenu(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    public void openMemberMenu(Player player, String clanName) {
        Inventory menu = Bukkit.createInventory(player, 9, "Clan Members");

        List<UUID> clanMembers = clanManager.getClanMembers(clanName);

        for (UUID memberUUID : clanMembers) {
            ItemStack skull = getSkullItem(memberUUID);
            menu.addItem(skull);
        }
        player.openInventory(menu);
    }

    private ItemStack getSkullItem(UUID playerUUID) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        Player owner = Bukkit.getPlayer(playerUUID);

        if (owner != null) {
            assert meta != null;
            meta.setOwningPlayer(owner);
        } else {
            assert meta != null;
            meta.setOwningPlayer(Bukkit.getOfflinePlayer(playerUUID));
        }

        skull.setItemMeta(meta);
        return skull;
    }
}
