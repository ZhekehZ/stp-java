package org.zhekehz.stpjava;

import java.util.Arrays;

public class FunctionSort implements Sort {
    private final int[] argWidth;
    private final int resultWidth;

    public FunctionSort(int[] argWidth, int resultWidth) {
        this.argWidth = argWidth;
        this.resultWidth = resultWidth;
    }

    public int[] getArgWidth() {
        return argWidth;
    }

    public int getResultWidth() {
        return resultWidth;
    }

    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public FunctionSort asFunctionSort() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FunctionSort)) return false;
        if (resultWidth != ((FunctionSort) o).resultWidth) return false;
        return Arrays.equals(argWidth, ((FunctionSort) o).argWidth);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(argWidth);
        result = 31 * result + resultWidth;
        return result;
    }
}
