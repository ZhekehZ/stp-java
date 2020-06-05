package org.zhekehz.stpjava;

import java.util.HashMap;

public class ArrayExpr extends Expr {

    protected final int indexWidth;
    protected final int elementWidth;

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

    public BitVectorExpr read(BitVectorExpr index) {
        if (index.getWidth() != indexWidth) {
            throw new IllegalArgumentException("argument has invalid bit-width");
        }
        return new BitVectorExpr(vc, elementWidth, Native.vc_readExpr(vc.getRef(), exprRef, index.exprRef));
    }

    public ArrayExpr write(BitVectorExpr index, BitVectorExpr expr) {
        if (expr.getWidth() != elementWidth || index.getWidth() != indexWidth) {
            throw new IllegalArgumentException("argument has invalid bit-width");
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

    public HashMap<String, Long> getCounterExampleStringLong() {
        long[] ce = Native.vc_getCounterExampleArray(vc.getRef(), exprRef);
        HashMap<String, Long> result = new HashMap<>();
        int size = ce.length / 2;
        for (int i = 0; i < size; ++i) {
            result.put(Native.exprString(ce[i]), Native.getBVUnsignedLong(ce[size + i]));
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

    @Override
    public Sort getSort() {
        return new ArraySort(indexWidth, elementWidth);
    }

    @Override
    public ArrayExpr asArray() {
        return this;
    }
}
