package tk.skyblocksandbox.skyblocksandbox.listener;

import com.kingrainbow44.customplayer.player.ICustomPlayer;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import tk.skyblocksandbox.skyblocksandbox.SkyblockSandbox;
import tk.skyblocksandbox.skyblocksandbox.item.SandboxItem;
import tk.skyblocksandbox.skyblocksandbox.player.SkyblockPlayer;

public final class ItemListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ICustomPlayer customPlayer = SkyblockSandbox.getApi().getPlayerManager().isCustomPlayer(player);
        if(!(customPlayer instanceof SkyblockPlayer)) return;
        SkyblockPlayer sbPlayer = (SkyblockPlayer) customPlayer;

        int ability;
        switch(event.getAction()) {
            default:
            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK:
                ability = SandboxItem.INTERACT_RIGHT_CLICK;
                break;
            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:
                ability = SandboxItem.INTERACT_LEFT_CLICK;
                break;
        }

        SandboxItem sbItem = sbPlayer.getItemInHand(true);
        if(sbItem == null) return;

        sbItem.ability(ability, sbPlayer);
    }

    @EventHandler
    public void onArmorStandInteract(PlayerInteractAtEntityEvent event) {
        if(event.getRightClicked() instanceof ArmorStand) {
            Event interactEvent = new PlayerInteractEvent(event.getPlayer(), Action.RIGHT_CLICK_AIR, null, null, BlockFace.SELF);
            Bukkit.getPluginManager().callEvent(interactEvent);

            event.setCancelled(true);
        }
    }

}
