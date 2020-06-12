package org.zhekehz.stpjava;

import jdk.nashorn.internal.runtime.BitVector;

public abstract class Expr {

    protected final ValidityChecker vc;
    protected final long exprRef;

    protected Expr(ValidityChecker vc, long exprRef) {
        this.exprRef = exprRef;
        this.vc = vc;
    }

    public BoolExpr equiv(Expr other) {
        if (!other.getSort().equals(getSort())) {
            throw new IllegalArgumentException("Expressions must have the same type");
        }
        return new BoolExpr(vc, Native.vc_eqExpr(vc.getRef(), exprRef, other.exprRef));
    }

    public abstract Expr fromRef(long ref);

    public Expr simplify() {
        return this.fromRef(Native.vc_simplify(vc.getRef(), exprRef));
    }

    public boolean isConst() {
        return Native.isConst(exprRef);
    }

    public void print() {
        Native.vc_printExpr(vc.getRef(), exprRef);
    }

    protected final void checkVC(Expr other) {
        if (vc != other.vc) {
            throw new IllegalStateException();
        }
    }

    protected abstract boolean sameTypeWith(Expr other);

    public void destroy() {
        Native.vc_DeleteExpr(exprRef);
    }

    public String toSMTLib2() {
        return Native.vc_printSMTLIB(vc.getRef(), exprRef);
    }

    @Override
    public String toString() {
        return Native.exprString(exprRef);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(exprRef) * 13 + vc.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Expr)) return false;
        return sameTypeWith((Expr) o) && vc.equals(((Expr) o).vc) && exprRef == ((Expr) o).exprRef;
    }

    public abstract Sort getSort();

    public BitVectorExpr asBitVector() {
        throw new IllegalStateException("Is not a BitVector");
    }

    public BoolExpr asBool() {
        throw new IllegalStateException("Is not a Bool");
    }

    public ArrayExpr asArray() {
        throw new IllegalStateException("Is not an Array");
    }
}
