package tk.skyblocksandbox.dungeonsandbox.classes;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class ClassAbility implements Listener {

    private final Map<UUID, Integer> cooldownArray = new HashMap<>();

    /**
     * @param abilityName The name of the ability to be shown on the class select screen.
     * @param description The description that should be shown on the class leveling page. Use '\n' to make line breaks.
     */
    public ClassAbility(String abilityName, String description) {

    }

    /**
     * @param abilityName The name of the ability to be shown on the class select screen.
     * @param description The description that should be shown on the class leveling page. Add another string to the constructor to make line breaks.
     */
    public ClassAbility(String abilityName, String... description) {

    }

    /**
     * Triggers when a player interacts with a block.
     * Called on right/left click air & right/left click block.
     * @param event The player event. Contains the player variable.
     */
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {}

    /**
     * Triggers when a player moves. Counts on the X, Y, Z & Pitch + Yaw.
     * @param event The player event. Contains the player variable.
     */
    @EventHandler
    public void onMove(PlayerMoveEvent event) {}

    /**
     * Gets the array used for storing ability cooldowns.
     * @return The hashmap used for storing cooldowns. UUID & Integer respectively.
     */
    public final Map<UUID, Integer> getCooldownArray() {
        return cooldownArray;
    }

}