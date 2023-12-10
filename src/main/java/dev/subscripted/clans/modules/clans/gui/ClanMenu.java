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
    private static String CLAN_UI_TITLE = FileManager.getUIText(UIText.CLANUI_TITLE);
    private static String YOU_ARE_MEMBER_IN_CLAN_NAME = FileManager.getUIText(UIText.YOU_ARE_MEMBER_IN_CLAN_NAME);


    public ClanMenu(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    public static void openMenu(Player player) {
        Inventory menu = Bukkit.createInventory(player, 9, CLAN_UI_TITLE);
        ItemBuilder playerHead = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(player.getName());
        menu.setItem(4, playerHead.build());
        Clan playerClan = clanManager.getClan(player.getName());
        if (playerClan != null) {
            String PLACEHOLDER_CLAN_NAME = playerClan.getClanName();
            ItemBuilder clanInfoItem = new ItemBuilder(Material.GREEN_WOOL).setDisplayName(YOU_ARE_MEMBER_IN_CLAN_NAME.replace("%clan_name%", PLACEHOLDER_CLAN_NAME));
            menu.setItem(6, clanInfoItem.build());
        } else {
            ItemBuilder noClanItem = new ItemBuilder(Material.RED_WOOL).setDisplayName("Du bist in keinem Clan.");
            menu.setItem(6, noClanItem.build());
        }
        player.openInventory(menu);
    }
}
