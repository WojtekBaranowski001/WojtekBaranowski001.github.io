package OODCwk;

import java.io.Serializable;

public enum ForceType implements Serializable {
    WING("Wing"), STARSHIP("Starship"), WARBIRD("Warbird");

    private String type;

    ForceType(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }

}
