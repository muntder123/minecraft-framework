package games.negative.framework.json.item;

import com.google.common.collect.Lists;
import games.negative.framework.key.Keyd;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class JsonOptionalDataValue implements Keyd<String> {

    private final String key;
    private final Object value;

    public String asString() {
        return (String) value;
    }

    public String asString(@Nullable String defaultValue) {
        return value == null ? defaultValue : (String) value;
    }
    public boolean asBoolean() {
        return Boolean.parseBoolean(asString());
    }

    public boolean asBoolean(boolean defaultValue) {
        return value == null ? defaultValue : Boolean.parseBoolean(asString());
    }

    public Number asNumber() {
        return (Number) value;
    }

    public ArrayList<String> asStringList() {
        ArrayList<String> list = Lists.newArrayList();
        asObjectList().stream().map(o -> (String) o).forEach(list::add);
        return list;
    }

    public ArrayList<String> asStringList(@Nullable ArrayList<String> defaultValue) {
        return value == null ? defaultValue : asStringList();
    }

    public ArrayList<Number> asNumberList() {
        ArrayList<Number> list = Lists.newArrayList();
        asObjectList().stream().map(o -> (Number) o).forEach(list::add);
        return list;
    }
    public ArrayList<Object> asObjectList() {
        return convertObjectToList(asObject());
    }

    public Object asObject() {
        return value;
    }

    public Object asObject(@Nullable Object defaultValue) {
        return value == null ? defaultValue : value;
    }

    @Override
    public @NotNull String getKey() {
        return key;
    }

    @Override
    public void setKey(@NotNull String s) {
        throw new UnsupportedOperationException();
    }

    private ArrayList<Object> convertObjectToList(Object obj) {
        ArrayList<Object> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Lists.newArrayList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }
}
