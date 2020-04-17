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

    static native long vc_trueExpr(long vc);
    static native long vc_falseExpr(long vc);
    static native long vc_andExpr(long vc, long left, long right);
    static native long vc_orExpr(long vc, long left, long right);
    static native long vc_xorExpr(long vc, long left, long right);
    static native long vc_impliesExpr(long vc, long hyp, long conc);
    static native long vc_iffExpr(long vc, long left, long right);
    static native long vc_iteExpr(long vc, long conditional, long thenExpr, long elseExpr);
    static native long vc_boolToBVExpr(long vc, long form);

    static native long vc_orExprN(long vc, long[] children, int numOfChildNodes);
    static native long vc_andExprN(long vc, long[] children, int numOfChildNodes);

    static native void vc_assertFormula(long vc, long e);
    static native long vc_simplify(long vc, long e);

    static native void vc_printExpr(long vc, long e);

//    DLL_PUBLIC Expr vc_readExpr(VC vc, Expr array, Expr index);
//    DLL_PUBLIC Expr vc_writeExpr(VC vc, Expr array, Expr index, Expr newValue);

//    DLL_PUBLIC Expr vc_getCounterExample(VC vc, Expr e);

//    DLL_PUBLIC void vc_getCounterExampleArray(VC vc, Expr e, Expr** outIndices,
//                                              Expr** outValues, int* outSize);

    static native void vc_push(long vc);
    static native void vc_pop(long vc);

    static native int getBVInt(long e);
    static native long getBVUnsignedLong(long e);

    static native boolean isConst(long e);

    static native int vc_getBVLength(long vc, long e);

//    DLL_PUBLIC Expr vc_bvConstExprFromInt(VC vc, int bitWidth, unsigned int value);
//    DLL_PUBLIC Expr vc_bvConstExprFromLL(VC vc, int bitWidth,
//                                         unsigned long long value);

//    DLL_PUBLIC Expr vc_bvConcatExpr(long vc, long left, long right);
//    DLL_PUBLIC Expr vc_bvPlusExprN(VC vc, int bitWidth, Expr* children,
//                                   int numOfChildNodes);

    static native long vc_bvMinusExpr(long vc, int bitWidth, long left, long right);
    static native long vc_bvMultExpr(long vc, int bitWidth, long left, long right);

    static native long vc_bvDivExpr(long vc, int bitWidth, long dividend, long divisor);
    static native long vc_bvModExpr(long vc, int bitWidth, long dividend, long divisor);
    static native long vc_sbvDivExpr(long vc, int bitWidth, long dividend, long divisor);
    static native long vc_sbvModExpr(long vc, int bitWidth, long dividend, long divisor);
    static native long vc_sbvRemExpr(long vc, int bitWidth, long dividend, long divisor);

    static native long vc_bvLtExpr(long vc, long left, long right);
    static native long vc_bvLeExpr(long vc, long left, long right);
    static native long vc_bvGtExpr(long vc, long left, long right);
    static native long vc_bvGeExpr(long vc, long left, long right);
    static native long vc_sbvLtExpr(long vc, long left, long right);
    static native long vc_sbvLeExpr(long vc, long left, long right);
    static native long vc_sbvGtExpr(long vc, long left, long right);
    static native long vc_sbvGeExpr(long vc, long left, long right);

    static native long vc_bvUMinusExpr(long vc, long child);
    static native long vc_bvAndExpr(long vc, long left, long right);
    static native long vc_bvOrExpr(long vc, long left, long right);
    static native long vc_bvXorExpr(long vc, long left, long right);
    static native long vc_bvNotExpr(long vc, long child);

    static native long vc_bvLeftShiftExprExpr(long vc, int bitWidth, long left, long right);
    static native long vc_bvRightShiftExprExpr(long vc, int bitWidth, long left, long right);
    static native long vc_bvSignedRightShiftExprExpr(long vc, int bitWidth, long left, long right);

}
