package org.zhekehz.stpjava;

public class BoolSort implements Sort {
    @Override
    public boolean isBool() {
        return true;
    }

    @Override
    public BitVectorSort asBitVectorSort() {
        return new BitVectorSort(1);
    }

    @Override
    public BoolSort asBoolSort() {
        return this;
    }

    @Override
    public int hashCode() {
        return asBitVectorSort().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sort)) return false;
        if (o instanceof BoolSort) return true;
        return asBitVectorSort().equals(((Sort) o).asBitVectorSort());
    }
}
