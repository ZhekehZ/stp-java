package org.zhekehz.stpjava;

public enum QueryResult {
    INVALID,
    VALID,
    ERROR,
    TIMEOUT;

    public static QueryResult fromInt(int i) {
        switch (i) {
            case 0:
                return INVALID;
            case 1:
                return VALID;
            case 2:
                return ERROR;
            case 3:
                return TIMEOUT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
