package org.zhekehz.stpjava;

public class BoolExpr extends Expr {

    // Boolean variables are not supported

    public BoolExpr(ValidityChecker vc, long ref) {
        super(vc, ref);
    }

    public static BoolExpr getTrue(ValidityChecker vc) {
        return new BoolExpr(vc, Native.vc_trueExpr(vc.getRef()));
    }

    public static BoolExpr getFalse(ValidityChecker vc) {
        return new BoolExpr(vc, Native.vc_falseExpr(vc.getRef()));
    }

    public static BoolExpr andAll(ValidityChecker vc, BoolExpr[] children) {
        if (children.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        long[] refs = new long[children.length];
        for (int i = 0; i < children.length; i++) {
            refs[i] = children[i].exprRef;
        }
        return new BoolExpr(vc, Native.vc_andExprN(vc.getRef(), refs, children.length));
    }

    public static BoolExpr orAll(ValidityChecker vc, BoolExpr[] children) {
        if (children.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        long[] refs = new long[children.length];
        for (int i = 0; i < children.length; i++) {
            refs[i] = children[i].exprRef;
        }
        return new BoolExpr(vc, Native.vc_orExprN(vc.getRef(), refs, children.length));
    }

    public boolean toBoolean() {
        if (!isConst()) {
            throw new IllegalStateException("BoolExpr is not constant");
        }
        return 1 == Native.vc_isBool(exprRef);
    }

    public BoolExpr not() {
        return new BoolExpr(vc, Native.vc_notExpr(vc.getRef(), exprRef));
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
    public <T extends Expr> T ifThenElse(T thenE, T elseE) {
        if (!thenE.sameTypeWith(elseE)) {
            throw new IllegalArgumentException("arguments must be of the same type");
        }
        return (T) thenE.fromRef(Native.vc_iteExpr(vc.getRef(), exprRef, thenE.exprRef, elseE.exprRef));
    }

    public void assertFormula() {
        Native.vc_assertFormula(vc.getRef(), exprRef);
    }

    public BitVectorExpr toBitVector() {
        return new BitVectorExpr(vc, 1, Native.vc_boolToBVExpr(vc.getRef(), exprRef));
    }

    public BoolExpr getCounterExample() {
        return new BoolExpr(vc, Native.vc_getCounterExample(vc.getRef(), exprRef));
    }

    public final QueryResult queryWithTimeout(int timeoutMaxConflicts, int timeoutMaxTime) {
        if (!vc.isUsingCryptominisat()) {
            throw new IllegalStateException("Only the cryptominisat solver supports timeoutMaxTime");
        }
        return QueryResult.fromInt(
                Native.vc_query_with_timeout(vc.getRef(), exprRef, timeoutMaxConflicts, timeoutMaxTime));
    }

    public final QueryResult query() {
        return QueryResult.fromInt(Native.vc_query(vc.getRef(), exprRef));
    }

    @Override
    public BoolExpr fromRef(long ref) {
        return new BoolExpr(vc, ref);
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 23 + 7;
    }

    @Override
    protected boolean sameTypeWith(Expr other) {
        return other instanceof BoolExpr;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}
