package org.zhekehz.stpjava;

public class BoolSort implements Sort {
    @Override
    public BoolSort asBoolSort() {
        return this;
    }

    @Override
    public boolean isBool() {
        return true;
    }

    @Override
    public int hashCode() {
        return 4321;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BoolSort;
    }
}
