package games.negative.framework.commands;

import org.jetbrains.annotations.NotNull;

/**
 * This class represents a command that can be executed by the server.
 * <p>
 *     This class is a wrapper for {@link org.bukkit.command.Command} that
 *     allows for easier command creation.
 * </p>
 */
public interface Command {

    void execute(@NotNull Context context);

}
