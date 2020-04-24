package org.zhekehz.stpjava;

public final class Native {

    // ANY

    static native long vc_createValidityChecker();

    // \ ANY


    // EXPR MANIPULATION METHODS

    static native long vc_varExpr(long vc, String name, long type);

    static native int vc_getBVLength(long vc, long e);

    static native long vc_eqExpr(long vc, long child0, long child1);

    // \ EXPR MANIPULATION METHODS


    // BOOLEAN EXPRESSIONS

    static native long vc_boolType(long vc);

    static native long vc_trueExpr(long vc);

    static native long vc_falseExpr(long vc);

    static native long vc_notExpr(long vc, long child);

    static native long vc_andExpr(long vc, long left, long right);

    static native long vc_orExpr(long vc, long left, long right);

    static native long vc_xorExpr(long vc, long left, long right);

    static native long vc_impliesExpr(long vc, long hyp, long conclusion);

    static native long vc_iffExpr(long vc, long left, long right);

    static native long vc_iteExpr(long vc, long conditional, long thenExpr, long elseExpr);

    static native long vc_boolToBVExpr(long vc, long form);

    static native long vc_andExprN(long vc, long[] children, int numOfChildNodes);

    static native long vc_orExprN(long vc, long[] children, int numOfChildNodes);

    // \ BOOLEAN EXPRESSIONS


    // ARRAY EXPRESSIONS

    static native long vc_arrayType(long vc, long typeIndex, long typeData);

    static native long vc_readExpr(long vc, long array, long index);

    static native long vc_writeExpr(long vc, long array, long index, long newValue);

    static native void vc_printExpr(long vc, long e);

    static native void vc_printCounterExample(long vc);

    static native String vc_printExprToBuffer(long vc, long e);

    // \ ARRAY EXPRESSIONS


    // CONTEXT RELATED METHODS

    static native void vc_assertFormula(long vc, long e);

    static native long vc_simplify(long vc, long e);

    static native int vc_query(long vc, long expr);

    static native long vc_getCounterExample(long vc, long e);

    /**/
    static native long[] vc_getCounterExampleArray(long vc, long e);
    /**/

    static native void vc_push(long vc);

    static native void vc_pop(long vc);

    static native int getBVInt(long e);

    static native long getBVUnsignedLong(long e);

    // \ CONTEXT RELATED METHODS


    // BITVECTOR OPERATIONS

    static native long vc_bvType(long vc, int width);

    static native long vc_bvConstExprFromInt(long vc, int width, int value);

    static native long vc_bvConstExprFromLL(long vc, int bitWidth, long value);

    // \ BITVECTOR OPERATIONS


    // BITVECTOR ARITHMETIC OPERATIONS

    static native long vc_bvConcatExpr(long vc, long left, long right);

    static native long vc_bvPlusExpr(long vc, int width, long leftExpr, long rightExpr);

    static native long vc_bvMinusExpr(long vc, int bitWidth, long left, long right);

    static native long vc_bvMultExpr(long vc, int bitWidth, long left, long right);

    static native long vc_bvDivExpr(long vc, int bitWidth, long dividend, long divisor);

    static native long vc_bvModExpr(long vc, int bitWidth, long dividend, long divisor);

    static native long vc_sbvDivExpr(long vc, int bitWidth, long dividend, long divisor);

    static native long vc_sbvModExpr(long vc, int bitWidth, long dividend, long divisor);

    static native long vc_sbvRemExpr(long vc, int bitWidth, long dividend, long divisor);

    static native long vc_bvPlusExprN(long vc, int bitWidth, long[] children, int numOfChildNodes);

    // \ BITVECTOR ARITHMETIC OPERATIONS


    // BITVECTOR COMPARISON OPERATIONS

    static native long vc_bvLtExpr(long vc, long left, long right);

    static native long vc_bvLeExpr(long vc, long left, long right);

    static native long vc_bvGtExpr(long vc, long left, long right);

    static native long vc_bvGeExpr(long vc, long left, long right);

    static native long vc_sbvLtExpr(long vc, long left, long right);

    static native long vc_sbvLeExpr(long vc, long left, long right);

    static native long vc_sbvGtExpr(long vc, long left, long right);

    static native long vc_sbvGeExpr(long vc, long left, long right);

    // \ BITVECTOR COMPARISON OPERATIONS


    // BITVECTOR BITWISE OPERATIONS

    static native long vc_bvUMinusExpr(long vc, long child);

    static native long vc_bvAndExpr(long vc, long left, long right);

    static native long vc_bvOrExpr(long vc, long left, long right);

    static native long vc_bvXorExpr(long vc, long left, long right);

    static native long vc_bvNotExpr(long vc, long child);

    // \ BITVECTOR BITWISE OPERATIONS


    // BITVECTOR SHIFT OPERATIONS

    static native long vc_bvLeftShiftExprExpr(long vc, int bitWidth, long left, long right);

    static native long vc_bvRightShiftExprExpr(long vc, int bitWidth, long left, long right);

    static native long vc_bvSignedRightShiftExprExpr(long vc, int bitWidth, long left, long right);

    // \ BITVECTOR SHIFT OPERATIONS


    // BITVECTOR EXTRACTION & EXTENSION

    static native long vc_bvExtract(long vc, long child, int high_bit_no, int low_bit_no);

    static native long vc_bvBoolExtract_Zero(long vc, long x, int bit_no);

    static native long vc_bvBoolExtract_One(long vc, long x, int bit_no);

    static native long vc_bvSignExtend(long vc, long child, int newWidth);

    // \ BITVECTOR EXTRACTION & EXTENSION


    // CONVENIENCE FUNCTIONS FOR ARRAYS

    static native long vc_bvCreateMemoryArray(long vc, String name);

    // CONVENIENCE FUNCTIONS FOR ARRAYS


    // GENERAL EXPRESSION OPERATIONS

    static native void vc_Destroy(long vc);

    static native void vc_DeleteExpr(long e);

    static native int vc_isBool(long e);

    // Not sure about these methods
//    static native int getExprKind(long e);
//    static native long vc_getWholeCounterExample(long vc);
//    static native long vc_getTermFromCounterExample(long vc, long e, long c);
//    static native void vc_deleteWholeCounterExample(long cc);

    // \ GENERAL EXPRESSION OPERATIONS


    // ADDITIONAL

    static native boolean isConst(long e);

    // \ ADDITIONAL

}
