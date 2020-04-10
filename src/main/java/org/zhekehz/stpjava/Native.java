package org.zhekehz.stpjava;

public final class Native {

    static native long createValidityChecker();

    static native long bvType(long vc, int width);

    static native long varExpr(long vc, String name, long type);

    static native long bvConstExprFromInt(long vc, int width, int value);

    static native long vc_bvPlusExpr(long vc, int width, long leftExpr, long rightExpr);

    static native long vc_eqExpr(long vc, long child0, long child1);

    static native long vc_notExpr(long vc, long child);

    static native int vc_query(long vc, long expr);

    static native void vc_printCounterExample(long vc);

    static native void vc_Destroy(long vc);

}
