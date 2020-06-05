package org.zhekehz.stpjava;

public class MemoryArrayExpr extends ArrayExpr {

    public MemoryArrayExpr(ValidityChecker vc, String name) {
        super(vc, 32, 8, Native.vc_bvCreateMemoryArray(vc.getRef(), name));
    }

    public MemoryArrayExpr(ValidityChecker vc, long ref) {
        super(vc, 32, 8, ref);
    }

    public BitVectorExpr read(BitVectorExpr index, int numberOfBytes) {
        if (index.getWidth() != indexWidth) {
            throw new IllegalArgumentException("argument has invalid bit-width");
        }
        return new BitVectorExpr(vc, numberOfBytes * Byte.SIZE,
                Native.vc_bvReadMemoryArray(vc.getRef(), exprRef, index.exprRef, numberOfBytes));
    }

    @Override
    public MemoryArrayExpr write(BitVectorExpr index, BitVectorExpr expr) {
        if (index.getWidth() != indexWidth) {
            throw new IllegalArgumentException("argument has invalid bit-width");
        }
        return new MemoryArrayExpr(vc,
                Native.vc_bvWriteToMemoryArray(
                        vc.getRef(), exprRef, index.exprRef, expr.exprRef, expr.getWidth() * Byte.SIZE));
    }

    @Override
    public ArrayExpr fromRef(long ref) {
        return new MemoryArrayExpr(vc, ref);
    }
}
