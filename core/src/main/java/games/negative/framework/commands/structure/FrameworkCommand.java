package games.negative.framework.commands.structure;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import games.negative.framework.commands.Command;
import games.negative.framework.commands.CommandBuilder;
import games.negative.framework.commands.Context;
import games.negative.framework.message.FrameworkMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a command that can be executed by the server with all Framework inclusions.
 */
public class FrameworkCommand extends org.bukkit.command.Command {

    private final Command component;
    private final List<FrameworkCommand> subCommands;
    private final String[] permissions;
    private final String[] params;
    private final boolean playerOnly;
    private final FrameworkCommand parent;

    public FrameworkCommand(@Nullable FrameworkCommand parent, @NotNull CommandBuilder builder) {
        super(builder.getName(), builder.getDescription(), builder.getUsage(), builder.getAliases());

        this.parent = parent;

        Preconditions.checkNotNull(builder, "Command Builder cannot be null.");
        Preconditions.checkNotNull(builder.getComponent(), "Command component cannot be null.");
        this.component = builder.getComponent();
        this.subCommands = Lists.newArrayList();
        this.playerOnly = builder.isPlayerOnly();
        this.permissions = builder.getPermissions();
        this.params = builder.getParams();

        if (builder.getUsage() != null)
            this.setUsage(builder.getUsage());

        for (CommandBuilder cmd : builder.getSubCommands()) {
            FrameworkCommand subCommand = new FrameworkCommand(this, cmd);
            this.subCommands.add(subCommand);
        }
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (playerOnly && !(sender instanceof Player)) {
            FrameworkMessage.COMMAND_CANNOT_USE_THIS_AS_CONSOLE.send(sender);
            return true;
        }

        if (!checkPermissions(sender)) {
            FrameworkMessage.COMMAND_NO_PERMISSION.send(sender);
            return true;
        }

        if (!checkParams(sender, args))
            return true;

        if (checkSubCommands(sender, args))
            return true;

        Context context = new CommandContext(this, sender, args);
        component.execute(context);
        return true;
    }

    private boolean checkPermissions(@NotNull CommandSender sender) {
        if (this.permissions == null)
            return true;

        for (String permission : this.permissions) {
            if (sender.hasPermission(permission))
                return true;
        }
        return false;
    }

    private boolean checkSubCommands(@NotNull CommandSender sender, @NotNull String[] args) {
        if (args.length == 0 || subCommands.isEmpty())
            return false;

        String begin = args[0];
        String[] snippet = Arrays.copyOfRange(args, 1, args.length);

        FrameworkCommand subCommand = getAvailableSubCommand(begin);
        return (subCommand != null && subCommand.execute(sender, begin, snippet));
    }

    @Nullable
    public FrameworkCommand getAvailableSubCommand(@NotNull String argument) {
        for (FrameworkCommand subCommand : subCommands) {
            if (subCommand.getName().equalsIgnoreCase(argument) || subCommand.getAliases().contains(argument.toLowerCase()))
                return subCommand;
        }
        return null;
    }

    public boolean checkParams(@NotNull CommandSender sender, @NotNull String[] args) {
        if (this.params == null)
            return true;

        if (args.length < params.length) {
            StringBuilder builder = new StringBuilder();
            for (String param : params) {
                builder.append("<").append(param).append(">").append(" ");
            }

            ArrayList<String> parentNames = Lists.newArrayList();
            parentNames.add(getName());

            FrameworkCommand search = this;
            while (search.getParent() != null) {
                parentNames.add(search.getParent().getName());
                search = search.getParent();
            }

            Collections.reverse(parentNames);

            StringBuilder parentBuilder = new StringBuilder();

            int iteration = 0;
            for (String parentName : parentNames) {
                if (iteration != 0)
                    parentBuilder.append(" ");

                parentBuilder.append(parentName);

                iteration++;
            }

            FrameworkMessage.COMMAND_USAGE.replace("%command%", parentBuilder.toString()).replace("%usage%", builder.toString()).send(sender);
            return false;
        }

        return true;
    }

    public Command getComponent() {
        return component;
    }

    public List<FrameworkCommand> getSubCommands() {
        return subCommands;
    }

    public String[] getParams() {
        return params;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public boolean isPlayerOnly() {
        return playerOnly;
    }

    public void addSubCommand(@NotNull FrameworkCommand command) {
        this.subCommands.add(command);
    }

    public FrameworkCommand getParent() {
        return parent;
    }
}
