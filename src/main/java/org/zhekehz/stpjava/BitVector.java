package org.zhekehz.stpjava;

public class BitVector extends Expr {

    private final int width;

    public BitVector(ValidityChecker vc, String name, int width) {
        super(vc, Native.varExpr(vc.getRef(), name, Native.bvType(vc.getRef(), width)));
        this.width = width;
    }

    public BitVector(ValidityChecker vc, int width, long ref) {
        super(vc, ref);
        this.width = width;
    }

    static BitVector constant(ValidityChecker vc, int width, int value) {
        return new BitVector(vc, width, Native.bvConstExprFromInt(vc.getRef(), width, value));
    }

    BitVector plus(BitVector other) {
        checkWidth(other);
        return new BitVector(vc, width, Native.vc_bvPlusExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    BoolExpr equiv(BitVector other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_eqExpr(vc.getRef(), exprRef, other.exprRef));
    }

    private void checkWidth(BitVector other) {
        super.checkVC(other);
        if (other.width != width) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public BitVector fromRef(long ref) {
        return new BitVector(vc, width, ref);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(width) * 17 + super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BitVector)) return false;
        return width == ((BitVector) o).width && super.equals(o);
    }
}
