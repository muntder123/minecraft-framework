package games.negative.framework.util;

import games.negative.framework.base.gui.anvil.AnvilVersionWrapper;
import games.negative.framework.util.version.ServerVersion;
import games.negative.framework.v1_10.AnvilWrapperProvider1_10;
import games.negative.framework.v1_11.AnvilWrapperProvider1_11;
import games.negative.framework.v1_12.AnvilWrapperProvider1_12;
import games.negative.framework.v1_13.AnvilWrapperProvider1_13;
import games.negative.framework.v1_14.AnvilWrapperProvider1_14;
import games.negative.framework.v1_15.AnvilWrapperProvider1_15;
import games.negative.framework.v1_16.AnvilWrapperProvider1_16;
import games.negative.framework.v1_17.AnvilWrapperProvider1_17;
import games.negative.framework.v1_18.AnvilWrapperProvider1_18;
import games.negative.framework.v1_19.AnvilWrapperProvider1_19;
import games.negative.framework.v1_8.AnvilWrapperProvider1_8;
import games.negative.framework.v1_9.AnvilWrapperProvider1_9;

public class AnvilVersionWrapperMatcher {

    private final ServerVersion version;

    public AnvilVersionWrapperMatcher(ServerVersion version) {
        this.version = version;
    }

    public AnvilVersionWrapper match() {
        switch (version) {
            case V1_8: return new AnvilWrapperProvider1_8();
            case V1_9: return new AnvilWrapperProvider1_9();
            case V1_10: return new AnvilWrapperProvider1_10();
            case V1_11: return new AnvilWrapperProvider1_11();
            case V1_12: return new AnvilWrapperProvider1_12();
            case V1_13: return new AnvilWrapperProvider1_13();
            case V1_14: return new AnvilWrapperProvider1_14();
            case V1_15: return new AnvilWrapperProvider1_15();
            case V1_16: return new AnvilWrapperProvider1_16();
            case V1_17: return new AnvilWrapperProvider1_17();
            case V1_18: return new AnvilWrapperProvider1_18();
            case V1_19: return new AnvilWrapperProvider1_19();
            default: return null;
        }
    }
}
