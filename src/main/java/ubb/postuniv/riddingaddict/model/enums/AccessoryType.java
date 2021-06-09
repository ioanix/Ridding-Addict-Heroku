package ubb.postuniv.riddingaddict.model.enums;

import lombok.Getter;

@Getter
public enum AccessoryType {

    LIGHTS("Lights"),
    LOCKS("Locks");

    private String accessoryType;

    AccessoryType(String accessoryType) {
        this.accessoryType = accessoryType;
    }
}
