package games.negative.framework.commands.structure;

import com.google.common.base.Preconditions;
import games.negative.framework.commands.Context;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a command context which holds information about the command executed.
 */
public class CommandContext implements Context {

    private final Command command;
    private final CommandSender sender;
    private final String[] arguments;

    /**
     * Creates a new command context.
     * @param command The command that has been executed.
     * @param sender The sender of the command.
     * @param arguments The arguments used in the command.
     */
    public CommandContext(@NotNull Command command, @NotNull CommandSender sender, @NotNull String[] arguments) {
        Preconditions.checkNotNull(command, "Command cannot be null.");
        Preconditions.checkNotNull(sender, "Command Sender cannot be null.");
        Preconditions.checkNotNull(arguments, "Command arguments cannot be null.");

        this.command = command;
        this.sender = sender;
        this.arguments = arguments;
    }

    @Override
    public @NotNull String getCommandName() {
        return command.getName();
    }

    @Override
    public @NotNull String[] getArgs() {
        return arguments;
    }

    @Override
    public @NotNull CommandSender getCommandSender() {
        return sender;
    }
}
