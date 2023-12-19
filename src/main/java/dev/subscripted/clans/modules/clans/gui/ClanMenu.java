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
    private static final String YOU_ARE_MEMBER_IN_CLAN_NAME = FileManager.getUIText(UIText.YOU_ARE_MEMBER_IN_CLAN_NAME);
    private static final String YOU_ARE_IN_NO_CLAN = FileManager.getUIText(UIText.YOU_ARE_IN_NO_CLAN);


    public ClanMenu(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    public static void openMenu(Player player) {
        Inventory menu = Bukkit.createInventory(player, 9, CLAN_UI_TITLE);

        Clan playerClan = clanManager.getClan(player.getName());
        String PLACEHOLDER_CLAN_NAME = playerClan.getClanName();
        ItemBuilder playerHead = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(player.getName());
        ItemBuilder clanInfoItem = new ItemBuilder(Material.GREEN_WOOL).setDisplayName(YOU_ARE_MEMBER_IN_CLAN_NAME.replace("%clan_name%", PLACEHOLDER_CLAN_NAME));
        ItemBuilder noClanItem = new ItemBuilder(Material.RED_WOOL).setDisplayName(YOU_ARE_IN_NO_CLAN);

        menu.setItem(4, playerHead.build());

        if (playerClan != null) {
            menu.setItem(6, clanInfoItem.build());
        } else {
            menu.setItem(6, noClanItem.build());
        }
        player.openInventory(menu);
    }
}
