package dev.subscripted.clans.modules.clans.manager;

import dev.subscripted.clans.data.FileManager;
import dev.subscripted.clans.enums.UIText;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemInteraction implements Listener {


    @EventHandler
    public void onInteract(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(FileManager.getUIText(UIText.CLANUI_TITLE))) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }

    }


}
