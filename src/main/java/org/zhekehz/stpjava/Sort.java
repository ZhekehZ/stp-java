package org.zhekehz.stpjava;

public interface Sort {
    default boolean isArray() {
        return false;
    }

    default boolean isBitVector() {
        return false;
    }

    default boolean isBool() {
        return false;
    }

    default boolean isFunction() {
        return false;
    }

    default BitVectorSort asBitVectorSort() {
        throw new IllegalStateException("is not BitVectorSort");
    }

    default ArraySort asArraySort() {
        throw new IllegalStateException("is not ArraySort");
    }

    default FunctionSort asFunctionSort() {
        throw new IllegalStateException("is not FunctionSort");
    }

    default BoolSort asBoolSort() {
        throw new IllegalStateException("is not BoolSort");
    }
}
