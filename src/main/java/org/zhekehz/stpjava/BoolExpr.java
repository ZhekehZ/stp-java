package org.zhekehz.stpjava;

import java.lang.reflect.InvocationTargetException;

public class BoolExpr extends Expr {

    public BoolExpr(ValidityChecker vc, long ref) {
        super(vc, ref);
    }

    public BoolExpr not() {
        return new BoolExpr(vc, Native.vc_notExpr(vc.getRef(), exprRef));
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 23 + 7;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BoolExpr)) return false;
        return super.equals(o);
    }

    @Override
    public BoolExpr fromRef(long ref) {
        return new BoolExpr(vc, ref);
    }

    public static BoolExpr getTrue(ValidityChecker vc) {
        return new BoolExpr(vc, Native.vc_trueExpr(vc.getRef()));
    }

    public static BoolExpr getFalse(ValidityChecker vc) {
        return new BoolExpr(vc, Native.vc_falseExpr(vc.getRef()));
    }

    public BoolExpr and(BoolExpr other) {
        return new BoolExpr(vc, Native.vc_andExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr or(BoolExpr other) {
        return new BoolExpr(vc, Native.vc_orExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr xor(BoolExpr other) {
        return new BoolExpr(vc, Native.vc_xorExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr implies(BoolExpr other) {
        return new BoolExpr(vc, Native.vc_impliesExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public BoolExpr iff(BoolExpr other) {
        return new BoolExpr(vc, Native.vc_iffExpr(vc.getRef(), exprRef, other.exprRef));
    }

    @SuppressWarnings({"unchecked"})
    public <T extends Expr> T ifThenElse(T lhs, T rhs) {
        return (T) lhs.fromRef(Native.vc_iffExpr(vc.getRef(), lhs.exprRef, rhs.exprRef));
    }

    public void assertTrue() {
        Native.vc_assertFormula(vc.getRef(), exprRef);
    }

//    public BitVector toBitVector() {
//        return new BitVector(vc, Native.vc_boolToBVExpr(vc.getRef(), exprRef));
//    }

}
