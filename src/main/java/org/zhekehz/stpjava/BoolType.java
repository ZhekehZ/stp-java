package org.zhekehz.stpjava;

public class BoolType implements ExprType {
    @Override
    public long getRef(ValidityChecker vc) {
        return Native.vc_boolType(vc.getRef());
    }

    @Override
    public int hashCode() {
        return 1231;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        return o instanceof BoolType;
    }
}
