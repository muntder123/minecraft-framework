package games.negative.framework.commands;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandBuilder {

    private final Command component;

    // Command Settings
    private String name;
    private String description;
    private String usage;
    private List<String> aliases;


    public CommandBuilder(@NotNull Command component) {
        Preconditions.checkNotNull(component, "Command component cannot be null.");
        this.component = component;
    }

    public CommandBuilder name(@NotNull String name) {
        Preconditions.checkNotNull(name, "Command name cannot be null.");
        this.name = name;
        return this;
    }

    public CommandBuilder description(@NotNull String description) {
        Preconditions.checkNotNull(description, "Command description cannot be null.");
        this.description = description;
        return this;
    }

    public CommandBuilder usage(@NotNull String usage) {
        Preconditions.checkNotNull(usage, "Command usage message cannot be null.");
        this.usage = usage;
        return this;
    }

    public CommandBuilder aliases(@NotNull String... aliases) {
        Preconditions.checkNotNull(aliases, "Command aliases cannot be null.");
        return aliases(Lists.newArrayList(aliases));
    }

    public CommandBuilder aliases(@NotNull List<String> aliases) {
        Preconditions.checkNotNull(aliases, "Command aliases cannot be null.");
        this.aliases = aliases;
        return this;
    }
}
