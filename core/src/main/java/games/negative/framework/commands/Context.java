package games.negative.framework.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class represents a command context which holds information about the command executed.
 */
public interface Context {

    /**
     * Returns the name of the command executed.
     * @return the name of the command executed.
     */
    @NotNull
    String getCommandName();

    /**
     * Returns the arguments of the command executed.
     * @return the arguments of the command executed.
     */
    @NotNull
    String[] getArgs();

    /**
     * Returns the sender of the command executed.
     * @return the sender of the command executed.
     */
    @NotNull
    CommandSender getCommandSender();

    /**
     * Returns the player who executed the command.
     * @throws NullPointerException if the sender is not a player.
     * @return the player who executed the command.
     */
    @Nullable
    default Player getPlayer() {
        return getCommandSender() instanceof Player ? (Player) getCommandSender() : null;
    }

}
