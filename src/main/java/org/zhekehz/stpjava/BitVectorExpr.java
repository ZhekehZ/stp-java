package org.zhekehz.stpjava;

public class BitVectorExpr extends Expr {

    private final int width;

    public BitVectorExpr(ValidityChecker vc, String name, int width) {
        super(vc, Native.vc_varExpr(vc.getRef(), name, Native.vc_bvType(vc.getRef(), width)));
        this.width = width;
    }

    public BitVectorExpr(ValidityChecker vc, int width, long ref) {
        super(vc, ref);
        this.width = width;
    }

    static BitVectorExpr fromInt(ValidityChecker vc, int width, int value) {
        return new BitVectorExpr(vc, width, Native.vc_bvConstExprFromInt(vc.getRef(), width, value));
    }

    static BitVectorExpr fromLong(ValidityChecker vc, int width, long value) {
        return new BitVectorExpr(vc, width, Native.vc_bvConstExprFromLL(vc.getRef(), width, value));
    }

    public static BitVectorExpr sumAll(ValidityChecker vc, BitVectorExpr[] children) {
        if (children.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        int width = children[0].width;
        long[] refs = new long[children.length];
        for (int i = 0; i < children.length; i++) {
            if (width != children[i].width) {
                throw new IllegalArgumentException("BVs must have the same bit-width");
            }
            refs[i] = children[i].exprRef;
        }
        return new BitVectorExpr(vc, width, Native.vc_bvPlusExprN(vc.getRef(), width, refs, children.length));
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

    BoolExpr equiv(BitVectorExpr other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_eqExpr(vc.getRef(), exprRef, other.exprRef));
    }

    BitVectorExpr plus(BitVectorExpr other) {
        checkWidth(other);
        return new BitVectorExpr(vc, width, Native.vc_bvPlusExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVectorExpr minus(BitVectorExpr other) {
        checkWidth(other);
        return new BitVectorExpr(vc, width, Native.vc_bvMinusExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVectorExpr mult(BitVectorExpr other) {
        checkWidth(other);
        return new BitVectorExpr(vc, width, Native.vc_bvMultExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVectorExpr div(BitVectorExpr other) {
        checkWidth(other);
        return new BitVectorExpr(vc, width, Native.vc_bvDivExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVectorExpr mod(BitVectorExpr other) {
        checkWidth(other);
        return new BitVectorExpr(vc, width, Native.vc_bvModExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVectorExpr signedDiv(BitVectorExpr other) {
        checkWidth(other);
        return new BitVectorExpr(vc, width, Native.vc_sbvDivExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVectorExpr signedMod(BitVectorExpr other) {
        checkWidth(other);
        return new BitVectorExpr(vc, width, Native.vc_sbvModExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BitVectorExpr signedRem(BitVectorExpr other) {
        checkWidth(other);
        return new BitVectorExpr(vc, width, Native.vc_sbvRemExpr(vc.getRef(), width, exprRef, other.exprRef));
    }

    public BoolExpr lt(BitVectorExpr other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_bvLtExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr le(BitVectorExpr other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_bvLeExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr gt(BitVectorExpr other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_bvGtExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr ge(BitVectorExpr other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_bvGeExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr signedLt(BitVectorExpr other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_sbvLtExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr signedLe(BitVectorExpr other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_sbvLeExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr signedGt(BitVectorExpr other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_sbvGtExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr signedGe(BitVectorExpr other) {
        checkWidth(other);
        return new BoolExpr(vc, Native.vc_sbvGeExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BitVectorExpr minus() {
        return new BitVectorExpr(vc, width, Native.vc_bvUMinusExpr(vc.getRef(), exprRef));
    }

    public BitVectorExpr not() {
        return new BitVectorExpr(vc, width, Native.vc_bvNotExpr(vc.getRef(), exprRef));
    }

    public BitVectorExpr and(BitVectorExpr other) {
        checkWidth(other);
        return new BitVectorExpr(vc, width, Native.vc_bvAndExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BitVectorExpr or(BitVectorExpr other) {
        checkWidth(other);
        return new BitVectorExpr(vc, width, Native.vc_bvOrExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BitVectorExpr xor(BitVectorExpr other) {
        checkWidth(other);
        return new BitVectorExpr(vc, width, Native.vc_bvXorExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BitVectorExpr leftShift(BitVectorExpr by, int resultWidth) {
        return new BitVectorExpr(vc, resultWidth,
                Native.vc_bvLeftShiftExprExpr(vc.getRef(), resultWidth, exprRef, by.exprRef));
    }

    public BitVectorExpr rightShift(BitVectorExpr by, int resultWidth) {
        return new BitVectorExpr(vc, resultWidth,
                Native.vc_bvRightShiftExprExpr(vc.getRef(), resultWidth, exprRef, by.exprRef));
    }

    public BitVectorExpr signedRightShift(BitVectorExpr by, int resultWidth) {
        return new BitVectorExpr(vc, resultWidth,
                Native.vc_bvSignedRightShiftExprExpr(vc.getRef(), resultWidth, exprRef, by.exprRef));
    }

    public BitVectorExpr concat(BitVectorExpr other) {
        return new BitVectorExpr(vc, width + other.width, Native.vc_bvConcatExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public int getWidth() {
        return width;
    }

    @Override
    public ExprType getType() {
        return new BitVectorType(width);
    }

    @Override
    public BitVectorExpr fromRef(long ref) {
        return new BitVectorExpr(vc, width, ref);
    }

    private void checkWidth(BitVectorExpr other) {
        super.checkVC(other);
        if (other.width != width) {
            throw new IllegalArgumentException("BV must have the same bit-width");
        }
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(width) * 17 + super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BitVectorExpr)) return false;
        return width == ((BitVectorExpr) o).width && super.equals(o);
    }
}