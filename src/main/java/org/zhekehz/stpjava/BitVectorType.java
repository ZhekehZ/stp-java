package org.zhekehz.stpjava;

public class BitVectorType implements ExprType {
    private final int width;

    public BitVectorType(int width) {
        this.width = width;
    }

    @Override
    public BitVectorExpr buildExpr(ValidityChecker vc, long ref) {
        return new BitVectorExpr(vc, width, ref);
    }

    @Override
    public long getRef(ValidityChecker vc) {
        return Native.vc_bvType(vc.getRef(), width);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(width);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof BitVectorType)) return false;
        return width == ((BitVectorType) o).width;
    }
}
