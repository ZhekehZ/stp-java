package org.zhekehz.stpjava;

public abstract class Expr {

    protected final ValidityChecker vc;
    protected final long exprRef;

    public Expr(ValidityChecker vc, long exprRef) {
        this.exprRef = exprRef;
        this.vc = vc;
    }

    public final QueryResult query() {
        return QueryResult.fromInt(Native.vc_query(vc.getRef(), exprRef));
    }

    protected final void checkVC(Expr other) {
        if (vc != other.vc) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int hashCode() {
        return Long.hashCode(exprRef) * 13 + vc.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Expr)) return false;
        return vc.equals(((Expr) o).vc) && exprRef == ((Expr) o).exprRef;
    }
}
