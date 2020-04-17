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
            throw new IllegalArgumentException("BV must have the same bit-width");
        }
    }

    public int toInt() {
        if (!isConst()) {
            throw new IllegalStateException("BV is not constant");
        }
        return Native.getBVInt(exprRef);
    }

    public long toLong() {
        if (!isConst()) {
            throw new IllegalStateException("BV is not constant");
        }
        return Native.getBVUnsignedLong(exprRef);
    }

    public BitVector minus(BitVector other) {
        checkWidth(other);
        return new BitVector(vc, width, Native.vc_bvMinusExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVector mult(BitVector other) {
        checkWidth(other);
        return new BitVector(vc, width, Native.vc_bvMultExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVector div(BitVector other) {
        checkWidth(other);
        return new BitVector(vc, width, Native.vc_bvDivExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVector mod(BitVector other) {
        checkWidth(other);
        return new BitVector(vc, width, Native.vc_bvModExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVector signedDiv(BitVector other) {
        checkWidth(other);
        return new BitVector(vc, width, Native.vc_sbvDivExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVector signedMod(BitVector other) {
        checkWidth(other);
        return new BitVector(vc, width, Native.vc_sbvModExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVector signedRem(BitVector other) {
        checkWidth(other);
        return new BitVector(vc, width, Native.vc_sbvRemExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BoolExpr lt(BitVector other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_bvLtExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr le(BitVector other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_bvLeExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr gt(BitVector other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_bvGtExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr ge(BitVector other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_bvGeExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr signedLt(BitVector other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_sbvLtExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr signedLe(BitVector other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_sbvLeExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr signedGt(BitVector other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_sbvGtExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr signedGe(BitVector other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_sbvGeExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BitVector minus() {
        return new BitVector(vc, width, Native.vc_bvUMinusExpr(vc.getRef(), exprRef));
    }

    public BitVector not() {
        return new BitVector(vc, width, Native.vc_bvNotExpr(vc.getRef(), exprRef));
    }

    public BitVector and(BitVector other) {
        checkWidth(other);
        return new BitVector(vc, width, Native.vc_bvAndExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BitVector or(BitVector other) {
        checkWidth(other);
        return new BitVector(vc, width, Native.vc_bvOrExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BitVector xor(BitVector other) {
        checkWidth(other);
        return new BitVector(vc, width, Native.vc_bvXorExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BitVector leftShift(BitVector by, int resultWidth) {
        return new BitVector(vc, resultWidth,
                Native.vc_bvLeftShiftExprExpr(vc.getRef(), resultWidth, exprRef, by.exprRef));
    }

    public BitVector rightShift(BitVector by, int resultWidth) {
        return new BitVector(vc, resultWidth,
                Native.vc_bvRightShiftExprExpr(vc.getRef(), resultWidth, exprRef, by.exprRef));
    }

    public BitVector signedRightShift(BitVector by, int resultWidth) {
        return new BitVector(vc, resultWidth,
                Native.vc_bvSignedRightShiftExprExpr(vc.getRef(), resultWidth, exprRef, by.exprRef));
    }

    public int getWidth() {
        return width;
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
