package org.zhekehz.stpjava;

public class MemoryArrayExpr extends ArrayExpr {

    public MemoryArrayExpr(ValidityChecker vc, String name) {
        super(vc, 32, 8, Native.vc_bvCreateMemoryArray(vc.getRef(), name));
    }

    public MemoryArrayExpr(ValidityChecker vc, long ref) {
        super(vc, 32, 8, ref);
    }

    @Override
    public ArrayExpr fromRef(long ref) {
        return new MemoryArrayExpr(vc, ref);
    }
}
