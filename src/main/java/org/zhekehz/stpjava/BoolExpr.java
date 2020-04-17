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

    public static BoolExpr andAll(ValidityChecker vc, BoolExpr[] children) {
        long[] refs = new long[children.length];
        for (int i = 0; i < children.length; i++) {
            refs[i] = children[i].exprRef;
        }
        return new BoolExpr(vc, Native.vc_andExprN(vc.getRef(), refs, children.length));
    }

    public static BoolExpr orAll(ValidityChecker vc, BoolExpr[] children) {
        long[] refs = new long[children.length];
        for (int i = 0; i < children.length; i++) {
            refs[i] = children[i].exprRef;
        }
        return new BoolExpr(vc, Native.vc_orExprN(vc.getRef(), refs, children.length));
    }

    @SuppressWarnings({"unchecked"})
    public <T extends Expr> T ifThenElse(T thenE, T elseE) {
        return (T) thenE.fromRef(Native.vc_iteExpr(vc.getRef(), exprRef, thenE.exprRef, elseE.exprRef));
    }

    public void assertFormula() {
        Native.vc_assertFormula(vc.getRef(), exprRef);
    }

    public BitVector toBitVector() {
        return new BitVector(vc, 1, Native.vc_boolToBVExpr(vc.getRef(), exprRef));
    }

}
