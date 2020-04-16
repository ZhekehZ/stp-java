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


    // TODO
    static native long vc_trueExpr(long vc);
    static native long vc_falseExpr(long vc);
    static native long vc_andExpr(long vc, long left, long right);
    static native long vc_orExpr(long vc, long left, long right);
    static native long vc_xorExpr(long vc, long left, long right);
    static native long vc_impliesExpr(long vc, long hyp, long conc);
    static native long vc_iffExpr(long vc, long left, long right);
    static native long vc_iteExpr(long vc, long conditional, long thenExpr, long elseExpr);
//    static native long vc_boolToBVExpr(long vc, long form);


//    static native long vc_orExprN(long vc, long* children, int numOfChildNodes);
//    static native long vc_andExprN(long vc, Expr* children, int numOfChildNodes);

    static native void vc_assertFormula(long vc, long e);
    static native long vc_simplify(long vc, long e);

    static native void vc_printExpr(long vc, long e);

}
