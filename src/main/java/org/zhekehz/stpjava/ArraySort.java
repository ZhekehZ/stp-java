package org.zhekehz.stpjava;

public class ArraySort implements Sort {
    private final int indexWidth;
    private final int elementWidth;

    public ArraySort(int indexWidth, int elementWidth) {
        this.indexWidth = indexWidth;
        this.elementWidth = elementWidth;
    }

    public int getIndexWidth() {
        return indexWidth;
    }

    public int getElementWidth() {
        return elementWidth;
    }

    @Override
    public ArraySort asArraySort() {
        return this;
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(indexWidth) * 1237 + Integer.hashCode(elementWidth);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ArraySort)) return false;
        return indexWidth == ((ArraySort) o).indexWidth && elementWidth == ((ArraySort) o).elementWidth;
    }
}
