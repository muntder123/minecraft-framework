/*
 *  MIT License
 *
 * Copyright (C) 2022 Negative Games & Developers
 * Copyright (C) 2022 NegativeDev (NegativeKB, Eric)
 * Copyright (C) 2022 Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package games.negative.framework.command;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import games.negative.framework.BasePlugin;
import games.negative.framework.command.annotation.CommandInfo;
import games.negative.framework.command.base.CommandBase;
import games.negative.framework.command.event.SubCommandLogEvent;
import games.negative.framework.command.shortcommand.ShortCommands;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * SubCommand
 *
 * @author Negative
 * @apiNote Must be added to a {@link Command} class in order to work!
 */
@Getter
@Setter
public abstract class SubCommand implements CommandBase {

    // subcommands of subcommands lol
    private final List<SubCommand> subCommands = new ArrayList<>();

    private String argument;
    private List<String> aliases;

    @Deprecated
    private String permission = "";
    private String[] permissions;
    private String description = "";
    private boolean consoleOnly = false;
    private boolean playerOnly = false;
    private boolean disabled;
    private String[] params;
    private Consumer<SubCommandLogEvent> subCommandLogEventConsumer;
    private CommandBase parent;
    private Map<UUID, Long> cooldowns;
    private Function<Player, Long> cooldownFunction;
    private BukkitTask cooldownTask;

    public SubCommand() {
        this(null, null);
    }

    /**
     * SubCommand Constructor
     *
     * @param argument Argument of the SubCommand
     * @apiNote SubCommand argument and aliases are equalsIgnoreCase!
     * @apiNote There are no aliases for this constructor!
     */
    public SubCommand(String argument) {
        this(argument, Collections.emptyList());
    }

    /**
     * SubCommand Constructor
     *
     * @param argument Argument of the SubCommand
     * @param aliases  Aliases of the SubCommand
     * @apiNote SubCommand argument and aliases are equalsIgnoreCase!
     */
    public SubCommand(String argument, List<String> aliases) {
        this.argument = argument;
        this.aliases = aliases;
        this.cooldowns = Maps.newHashMap();

        if (this.getClass().isAnnotationPresent(CommandInfo.class)) {
            CommandInfo annotation = this.getClass().getAnnotation(CommandInfo.class);
            setArgument(annotation.name());

            List<String> a = Lists.newArrayList(annotation.aliases());
            // There will always be an empty index even if no arguments are
            // set. So the way you identify if there are actual arguments in the command
            // is you check if the first index is empty.
            if (!a.get(0).isEmpty()) {
                setAliases(a);
            }

            this.permissions = annotation.permission();

            if (annotation.consoleOnly())
                setConsoleOnly(true);

            if (annotation.playerOnly()) {
                setPlayerOnly(true);
            }

            List<String> shortCmds = new ArrayList<>(Arrays.asList(annotation.shortCommands()));
            if (!shortCmds.get(0).isEmpty()) {
                ShortCommands.getInstance().addShortSubCommand(this, annotation.shortCommands());
            }

            List<String> paramList = new ArrayList<>(Arrays.asList(annotation.args()));
            if (!paramList.get(0).isEmpty()) {
                this.params = annotation.args();
            }

            setDescription(annotation.description());
        }

        cooldownTask = new CommandCooldownTask().runTaskTimerAsynchronously(BasePlugin.getInst(), 0, 20);
    }

    /**
     * Adds SubCommand to the SubCommand's subcommands
     * SubCommand seption
     *
     * @param subCommands SubCommand(s)
     */
    public void addSubCommands(SubCommand... subCommands) {
        for (SubCommand cmd : subCommands) {
            cmd.setParent(this);
            this.subCommands.add(cmd);
        }
    }

    /**
     * Runs a SubCommand
     *
     * @param subCommand SubCommand
     * @param sender     Sender
     * @param args       Arguments
     */
    @Override
    public void runSubCommand(SubCommand subCommand, CommandSender sender, String[] args) {
        subCommand.execute(sender, args);
    }

    @Override
    public boolean runLogEvent(CommandBase base, CommandSender sender, String[] args) {
        if (subCommandLogEventConsumer == null)
            return false;

        SubCommandLogEvent event = new SubCommandLogEvent(sender, args, this);
        Bukkit.getPluginManager().callEvent(event);

        return event.isCancelled();
    }

    @Override
    public void ifHasPermission(@NotNull CommandSender sender, @NotNull String perm, @NotNull Consumer<CommandSender> consumer) {
        if (sender.hasPermission(perm))
            consumer.accept(sender);
    }

    @Override
    public void ifNotHasPermission(@NotNull CommandSender sender, @NotNull String perm, @NotNull Consumer<CommandSender> consumer) {
        if (!sender.hasPermission(perm))
            consumer.accept(sender);
    }

    @Override
    public void ifPlayer(@NotNull CommandSender sender, @NotNull Consumer<Player> consumer) {
        if (sender instanceof Player)
            consumer.accept((Player) sender);
    }

    public void ifConsole(@NotNull CommandSender sender, @NotNull Consumer<ConsoleCommandSender> consumer) {
        if (sender instanceof ConsoleCommandSender)
            consumer.accept((ConsoleCommandSender) sender);
    }

    @Override
    public @Nullable CommandBase getParent() {
        return parent;
    }

    @Override
    public void setParent(@NotNull CommandBase parent) {
        this.parent = parent;
    }

    @Override
    public @NotNull String getName() {
        return argument;
    }

    public Consumer<SubCommandLogEvent> getSubCommandLogEvent() {
        return subCommandLogEventConsumer;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public Long getCooldown(UUID uuid) {
        return cooldowns.getOrDefault(uuid, null);
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Override
    public void addCooldown(UUID uuid, Long time) {
        if (time == null)
            return;

        if (cooldowns.containsKey(uuid))
            cooldowns.replace(uuid, time);
        else
            cooldowns.put(uuid, time);
    }

    @Override
    public void removeCooldown(UUID uuid) {
        cooldowns.remove(uuid);
    }

    public Function<Player, Long> getCooldownFunction() {
        return cooldownFunction;
    }

    public String[] getPermissions() {
        return permissions;
    }

    private class CommandCooldownTask extends BukkitRunnable {

        @Override
        public void run() {
            List<UUID> toRemove = Lists.newArrayList();

            for (Map.Entry<UUID, Long> entry : cooldowns.entrySet()) {
                if (System.currentTimeMillis() >= entry.getValue()) {
                    toRemove.add(entry.getKey());
                }
            }

            toRemove.forEach(cooldowns::remove);
        }
    }
}
