package org.zhekehz.stpjava;

public abstract class Expr {

    protected final ValidityChecker vc;
    protected final long exprRef;

    protected Expr(ValidityChecker vc, long exprRef) {
        this.exprRef = exprRef;
        this.vc = vc;
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

    public Kind getKind() {
        return Kind.fromInt(Native.getExprKind(exprRef));
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
}
