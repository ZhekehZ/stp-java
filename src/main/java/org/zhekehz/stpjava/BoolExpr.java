package org.zhekehz.stpjava;

public class BoolExpr extends Expr {

    public BoolExpr(ValidityChecker vc, long ref) {
        super(vc, ref);
    }

    BoolExpr not() {
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
}
