package games.negative.framework.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public class Events {

    public static void call(@NotNull Event event) {
        Bukkit.getPluginManager().callEvent(event);
    }
}
