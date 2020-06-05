package org.zhekehz.stpjava;

public class BitVectorSort implements Sort {
    private final int width;

    public BitVectorSort(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean isBitVector() {
        return true;
    }

    @Override
    public BitVectorSort asBitVectorSort() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((!(o instanceof BitVectorSort))) return false;
        return width == ((BitVectorSort) o).width;
    }

    @Override
    public int hashCode() {
        return width;
    }
}
