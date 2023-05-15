package games.negative.framework.commands;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import games.negative.framework.commands.structure.FrameworkCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * This class represents a command builder.
 */
public class CommandBuilder {

    private final Command component;

    // Command Settings
    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private boolean playerOnly;
    private String[] permissions;
    private String[] params;

    /**
     * Creates a new command builder.
     * @param component The command component.
     */
    public CommandBuilder(@NotNull Command component) {
        Preconditions.checkNotNull(component, "Command component cannot be null.");
        this.component = component;
    }

    /**
     * Set the name of the command.
     * @param name The name of the command.
     * @return The command builder.
     */
    public CommandBuilder name(@NotNull String name) {
        Preconditions.checkNotNull(name, "Command name cannot be null.");
        this.name = name;
        return this;
    }

    /**
     * Set the description of the command.
     * @param description The description of the command.
     * @return The command builder.
     */
    public CommandBuilder description(@NotNull String description) {
        Preconditions.checkNotNull(description, "Command description cannot be null.");
        this.description = description;
        return this;
    }

    /**
     * Set the usage message of the command.
     * @param usage The usage of the command.
     * @return The command builder.
     */
    public CommandBuilder usage(@NotNull String usage) {
        Preconditions.checkNotNull(usage, "Command usage message cannot be null.");
        this.usage = usage;
        return this;
    }

    /**
     * Set the aliases of the command.
     * @param aliases The aliases of the command.
     * @return The command builder.
     */
    public CommandBuilder aliases(@NotNull String... aliases) {
        Preconditions.checkNotNull(aliases, "Command aliases cannot be null.");
        return aliases(Lists.newArrayList(aliases));
    }

    /**
     * Set the aliases of the command.
     * @param aliases The aliases of the command.
     * @return The command builder.
     */
    public CommandBuilder aliases(@NotNull List<String> aliases) {
        Preconditions.checkNotNull(aliases, "Command aliases cannot be null.");
        this.aliases = aliases;
        return this;
    }

    /**
     * Set the permissions of the command.
     * @param permissions The permissions of the command.
     * @return The command builder.
     */
    public CommandBuilder permission(@NotNull String... permissions) {
        Preconditions.checkNotNull(permissions, "Command permissions cannot be null.");
        this.permissions = permissions;
        return this;
    }

    /**
     * Set the permission of the command.
     * @param permission The permission of the command.
     * @return The command builder.
     */
    public CommandBuilder permission(@NotNull String permission) {
        Preconditions.checkNotNull(permission, "Command permission cannot be null.");
        this.permissions = new String[] { permission };
        return this;
    }

    /**
     * Set the permissions of the command.
     * @param permission The permissions of the command.
     * @return The command builder.
     */
    public CommandBuilder permission(@NotNull List<String> permission) {
        Preconditions.checkNotNull(permission, "Command permission cannot be null.");
        this.permissions = permission.toArray(new String[0]);
        return this;
    }

    /**
     * Set the parameters of the command.
     * @param params The parameters of the command.
     * @return The command builder.
     */
    public CommandBuilder params(@NotNull String... params) {
        Preconditions.checkNotNull(params, "Command params cannot be null.");
        this.params = params;
        return this;
    }

    /**
     * Set the state of the command to "player only", meaning
     * the command can only be executed by players.
     *
     * @return The command builder.
     */
    public CommandBuilder playerOnly() {
        this.playerOnly = true;
        return this;
    }

    /**
     * Set the parameters of the command.
     * @return The command builder.
     */
    public FrameworkCommand build() {
        return build(null);
    }

    /**
     * Set the parameters of the command.
     * @param parent The parent command.
     * @return The command builder.
     */
    public FrameworkCommand build(@Nullable FrameworkCommand parent) {
        return new FrameworkCommand(parent, this);
    }

    /**
     * Get the name of the command.
     * @return The name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the aliases of the command.
     * @return The aliases of the command.
     */
    public List<String> getAliases() {
        return aliases;
    }

    /**
     * Get the description of the command.
     * @return The description of the command.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the usage of the command.
     * @return The usage of the command.
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Get the state of the command.
     * @return The state of the command.
     */
    public boolean isPlayerOnly() {
        return playerOnly;
    }

    /**
     * Get the permissions of the command.
     * @return The permissions of the command.
     */
    public String[] getPermissions() {
        return permissions;
    }

    /**
     * Get the parameters of the command.
     * @return The parameters of the command.
     */
    public String[] getParams() {
        return params;
    }

    /**
     * Get the component of the command.
     * @return The component of the command.
     */
    public Command getComponent() {
        return component;
    }
}
