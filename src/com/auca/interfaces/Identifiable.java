package com.auca.interfaces;

public interface Identifiable {
    String ID_PREFIX= "AUCA";
    int MIN_ID_LENGTH= 4;
    int MAX_ID_LENGHT= 30;
    String ID_SEPARATOR="-";
    String DEFAULT_STATUS= "active";
    int ENTITY_VERSION= 1;

    String getId();
    String getDisplayName();
    boolean isActive();
}
