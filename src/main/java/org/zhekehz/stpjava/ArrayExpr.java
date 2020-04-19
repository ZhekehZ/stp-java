package org.zhekehz.stpjava;

public class ArrayExpr extends Expr {

    private final int indexWidth;
    private final int elementWidth;

    public ArrayExpr(ValidityChecker vc, String name, int indexWidth, int elementWidth) {
        super(vc, Native.vc_varExpr(vc.getRef(), name,
                Native.vc_arrayType(vc.getRef(),
                        Native.vc_bvType(vc.getRef(), indexWidth),
                        Native.vc_bvType(vc.getRef(), elementWidth))));
        this.indexWidth = indexWidth;
        this.elementWidth = elementWidth;
    }

    public ArrayExpr(ValidityChecker vc, int indexWidth, int elementWidth, long ref) {
        super(vc, ref);
        this.indexWidth = indexWidth;
        this.elementWidth = elementWidth;
    }

    BitVectorExpr read(BitVectorExpr index) {
        return new BitVectorExpr(vc, elementWidth, Native.vc_readExpr(vc.getRef(), exprRef, index.exprRef));
    }

    ArrayExpr write(BitVectorExpr index, BitVectorExpr expr) {
        if (expr.getWidth() != elementWidth) {
            throw new IllegalArgumentException("expr type is invalid");
        }
        return new ArrayExpr(vc, indexWidth, elementWidth,
                Native.vc_writeExpr(vc.getRef(), exprRef, index.exprRef, expr.exprRef));
    }

    @Override
    public ArrayExpr fromRef(long ref) {
        return new ArrayExpr(vc, indexWidth, elementWidth, ref);
    }

    @Override
    protected boolean sameTypeWith(Expr other) {
        if (other == this) return true;
        if (!(other instanceof ArrayExpr)) return false;
        ArrayExpr array = (ArrayExpr) other;
        return indexWidth == array.indexWidth && elementWidth == array.elementWidth;
    }

    @Override
    public int hashCode() {
        return (Integer.hashCode(indexWidth) * 31 + Integer.hashCode(elementWidth)) * 13 + super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
