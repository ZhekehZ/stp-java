package org.zhekehz.stpjava;

import java.util.HashMap;

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

    public HashMap<Integer, Integer> getCounterExampleIntInt() {
        long[] ce = Native.vc_getCounterExampleArray(vc.getRef(), exprRef);
        HashMap<Integer, Integer> result = new HashMap<>();
        int size = ce.length / 2;
        for (int i = 0; i < size; ++i) {
            result.put(Native.getBVInt(ce[i]), Native.getBVInt(ce[size + i]));
        }
        return result;
    }

    public HashMap<Long, Long> getCounterExampleLongLong() {
        long[] ce = Native.vc_getCounterExampleArray(vc.getRef(), exprRef);
        HashMap<Long, Long> result = new HashMap<>();
        int size = ce.length / 2;
        for (int i = 0; i < size; ++i) {
            result.put(Native.getBVUnsignedLong(ce[i]), Native.getBVUnsignedLong(ce[size + i]));
        }
        return result;
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
