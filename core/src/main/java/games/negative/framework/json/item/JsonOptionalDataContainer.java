package games.negative.framework.json.item;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public class JsonOptionalDataContainer {

    private final JsonOptionalDataValue[] values;

    @Nullable
    public JsonOptionalDataValue get(@NotNull String key) {
        for (JsonOptionalDataValue value : values) {
            if (value.getKey().equals(key)) {
                return value;
            }
        }

        return null;
    }

    @Nullable
    public JsonOptionalDataValue get(int index) {
        if (index >= values.length) {
            return null;
        }

        return values[index];
    }

    @Nullable
    public JsonOptionalDataValue[] values() {
        return values;
    }

}
