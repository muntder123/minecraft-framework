package games.negative.framework.json.item;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
@Deprecated
public class JsonItemEnchantment {

    @SerializedName("enchantment-key")
    private final String enchantmentKey;

    @SerializedName("enchantment-level")
    private final int level;

}
