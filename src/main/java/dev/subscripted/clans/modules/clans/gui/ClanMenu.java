package dev.subscripted.clans.modules.clans.gui;

import dev.subscripted.clans.data.FileManager;
import dev.subscripted.clans.enums.UIText;
import dev.subscripted.clans.modules.builder.ItemBuilder;
import dev.subscripted.clans.modules.clans.manager.ClanManager;
import dev.subscripted.clans.modules.clans.pojo.Clan;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class ClanMenu {

    private static ClanManager clanManager;
    private static final String CLAN_UI_TITLE = FileManager.getUIText(UIText.CLANUI_TITLE);


    public ClanMenu(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    public static void openMenu(Player player) {
        Inventory menu = Bukkit.createInventory(player, 9, CLAN_UI_TITLE);
        ItemBuilder playerHead = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(player.getName());
        menu.setItem(4, playerHead.build());
        Clan playerClan = clanManager.getClan(player.getName());
        if (playerClan != null) {
            ItemBuilder clanInfoItem = new ItemBuilder(Material.GREEN_WOOL).setDisplayName("Du bist Mitglied im Clan: " + playerClan.getClanName());
            menu.setItem(6, clanInfoItem.build());
        } else {
            ItemBuilder noClanItem = new ItemBuilder(Material.RED_WOOL).setDisplayName("Du bist in keinem Clan.");
            menu.setItem(6, noClanItem.build());
        }
        player.openInventory(menu);
    }
}
