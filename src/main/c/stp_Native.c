#include <stdio.h>
#include <stdlib.h>

#include "stp_Native.h"
#include <stp/c_interface.h>

// HELPER MACROS

#define NEW_FUN_0(name, cname, ret)                          \
JNIEXPORT ret JNICALL Java_org_zhekehz_stpjava_Native_##name \
    (JNIEnv * env, jclass cls) {                             \
    return (ret) cname();                                    \
}

#define NEW_FUN_1(name, cname, t1_from, t1_to, ret)          \
JNIEXPORT ret JNICALL Java_org_zhekehz_stpjava_Native_##name \
    (JNIEnv * env, jclass cls, t1_from a) {                  \
    return (ret) cname((t1_to) a);                           \
}

#define NEW_FUN_2(name, cname, t1_from, t1_to, t2_from, t2_to, ret) \
JNIEXPORT ret JNICALL Java_org_zhekehz_stpjava_Native_##name        \
    (JNIEnv * env, jclass cls, t1_from a1, t2_from a2) {            \
    return (ret) cname((t1_to) a1, (t2_to) a2);                     \
}

#define NEW_FUN_3(name, cname, t1_from, t1_to, t2_from, t2_to, t3_from, t3_to, ret) \
JNIEXPORT ret JNICALL Java_org_zhekehz_stpjava_Native_##name                        \
    (JNIEnv * env, jclass cls, t1_from a1, t2_from a2, t3_from a3) {                \
    return (ret) cname((t1_to) a1, (t2_to) a2, (t3_to) a3);                         \
}

#define NEW_FUN_4(name, cname, t1_from, t1_to, t2_from, t2_to, t3_from, t3_to, t4_from, t4_to, ret) \
JNIEXPORT ret JNICALL Java_org_zhekehz_stpjava_Native_##name                                        \
    (JNIEnv * env, jclass cls, t1_from a1, t2_from a2, t3_from a3, t4_from a4) {                    \
    return (ret) cname((t1_to) a1, (t2_to) a2, (t3_to) a3, (t4_to) a4);                             \
}

// \ HELPER MACROS



// ANY

NEW_FUN_0(vc_1createValidityChecker, vc_createValidityChecker, jlong)

// \ ANY


// EXPR MANIPULATION METHODS

JNIEXPORT jlong JNICALL Java_org_zhekehz_stpjava_Native_vc_1varExpr
  (JNIEnv * env, jclass cls, jlong vc, jstring name, jlong type) {
    return (jlong) vc_varExpr((VC) vc, (const char*) (*env)->GetStringUTFChars(env, name, NULL), (Type) type);
}

NEW_FUN_2(vc_1getBVLength, vc_getBVLength, jlong, VC, jlong, Expr, jint)

NEW_FUN_3(vc_1eqExpr, vc_eqExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

// \ EXPR MANIPULATION METHODS


// BOOLEAN EXPRESSIONS

NEW_FUN_1(vc_1boolType, vc_boolType, jlong, VC, jlong)

NEW_FUN_1(vc_1trueExpr, vc_trueExpr, jlong, VC, jlong)

NEW_FUN_1(vc_1falseExpr, vc_falseExpr, jlong, VC, jlong)

NEW_FUN_2(vc_1notExpr, vc_notExpr, jlong, VC, jlong, Expr, jlong);

NEW_FUN_3(vc_1andExpr, vc_andExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1orExpr, vc_orExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1xorExpr, vc_xorExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1impliesExpr, vc_impliesExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1iffExpr, vc_iffExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_4(vc_1iteExpr, vc_iteExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_2(vc_1boolToBVExpr, vc_boolToBVExpr, jlong, VC, jlong, Expr, jlong)

JNIEXPORT jlong JNICALL Java_org_zhekehz_stpjava_Native_vc_1andExprN
  (JNIEnv * env, jclass cls, jlong vc, jlongArray expressions, jint size) {
      jboolean is_copy;
      jlong* children = (*env)->GetLongArrayElements(env, expressions, &is_copy);
      Expr* res = vc_andExprN((VC) vc, (Expr*) children, (int) size);
      if (is_copy == JNI_TRUE) (*env)->ReleaseLongArrayElements(env, expressions, children, JNI_ABORT);
      return (jlong) res;
}

JNIEXPORT jlong JNICALL Java_org_zhekehz_stpjava_Native_vc_1orExprN
  (JNIEnv * env, jclass cls, jlong vc, jlongArray expressions, jint size) {
      jboolean is_copy;
      jlong* children = (*env)->GetLongArrayElements(env, expressions, &is_copy);
      Expr* res = vc_orExprN((VC) vc, (Expr*) children, (int) size);
      if (is_copy == JNI_TRUE) (*env)->ReleaseLongArrayElements(env, expressions, children, JNI_ABORT);
      return (jlong) res;
}

// \ BOOLEAN EXPRESSIONS


// ARRAY EXPRESSIONS

NEW_FUN_3(vc_1arrayType, vc_arrayType, jlong, VC, jlong, Type, jlong, Type, jlong)

NEW_FUN_3(vc_1readExpr, vc_readExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_4(vc_1writeExpr, vc_writeExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong, Expr, jlong)

// \ ARRAY EXPRESSIONS


NEW_FUN_2(vc_1printExpr, vc_printExpr, jlong, VC, jlong, Expr, void)

NEW_FUN_1(vc_1printCounterExample, vc_printCounterExample, jlong, VC, void)


// CONTEXT RELATED METHODS

NEW_FUN_2(vc_1assertFormula, vc_assertFormula, jlong, VC, jlong, Expr, void)

NEW_FUN_2(vc_1simplify, vc_simplify, jlong, VC, jlong, Expr, jlong)

NEW_FUN_2(vc_1query, vc_query, jlong, VC, jlong, Expr, jint)

NEW_FUN_2(vc_1getCounterExample, vc_getCounterExample, jlong, VC, jlong, Expr, jlong)

NEW_FUN_1(vc_1push, vc_push, jlong, VC, void)

NEW_FUN_1(vc_1pop, vc_pop, jlong, VC, void)

NEW_FUN_1(getBVInt, getBVInt, jlong, Expr, jint)

NEW_FUN_1(getBVUnsignedLong, getBVUnsignedLongLong, jlong, Expr, jlong)

JNIEXPORT jlongArray JNICALL Java_org_zhekehz_stpjava_Native_vc_1getCounterExampleArray
  (JNIEnv * env, jclass cls, jlong vc, jlong expr) {
    int size = -1;
    Expr * indices, * values;
    vc_getCounterExampleArray((VC) vc, (Expr) expr, &indices, &values, &size);
    long * longs = malloc(2 * size * sizeof(long));
    for (int i = 0; i < size; ++i) {
        longs[i] = (long) indices[i];
        longs[size + i] = (long) values[i];
    }
    jlongArray result = (jlongArray) (*env)->NewLongArray(env, 2 * size);
    (*env)->SetLongArrayRegion(env, result, 0, 2 * size, longs);
    return result;
}

// \ CONTEXT RELATED METHODS


// BITVECTOR OPERATIONS

NEW_FUN_2(vc_1bvType, vc_bvType, jlong, VC, jint, int, jlong)

NEW_FUN_3(vc_1bvConstExprFromInt, vc_bvConstExprFromInt, jlong, VC, jint, int, jint, unsigned int, jlong)

NEW_FUN_3(vc_1bvConstExprFromLL, vc_bvConstExprFromLL, jlong, VC, jint, int, jlong, long, jlong)

// \ BITVECTOR OPERATIONS


// BITVECTOR ARITHMETIC OPERATIONS

NEW_FUN_3(vc_1bvConcatExpr, vc_bvConcatExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_4(vc_1bvPlusExpr, vc_bvPlusExpr, jlong, VC, jint, int, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_4(vc_1bvMinusExpr, vc_bvMinusExpr, jlong, VC, jint, int, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_4(vc_1bvMultExpr, vc_bvMultExpr, jlong, VC, jint, int, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_4(vc_1bvDivExpr, vc_bvDivExpr, jlong, VC, jint, int, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_4(vc_1bvModExpr, vc_bvModExpr, jlong, VC, jint, int, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_4(vc_1sbvDivExpr, vc_sbvDivExpr, jlong, VC, jint, int, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_4(vc_1sbvModExpr, vc_sbvModExpr, jlong, VC, jint, int, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_4(vc_1sbvRemExpr, vc_sbvRemExpr, jlong, VC, jint, int, jlong, Expr, jlong, Expr, jlong)

JNIEXPORT jlong JNICALL Java_org_zhekehz_stpjava_Native_vc_1bvPlusExprN
  (JNIEnv * env, jclass cls, jlong vc, jint width, jlongArray expressions, jint size) {
      jboolean is_copy;
      jlong* children = (*env)->GetLongArrayElements(env, expressions, &is_copy);
      Expr* res = vc_bvPlusExprN((VC) vc, (int) width, (Expr*) children, (int) size);
      if (is_copy == JNI_TRUE) (*env)->ReleaseLongArrayElements(env, expressions, children, JNI_ABORT);
      return (jlong) res;
}

// \ BITVECTOR ARITHMETIC OPERATIONS


// BITVECTOR COMPARISON OPERATIONS

NEW_FUN_3(vc_1bvLtExpr, vc_bvLtExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1bvLeExpr, vc_bvLeExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1bvGtExpr, vc_bvGtExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1bvGeExpr, vc_bvGeExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1sbvLtExpr, vc_sbvLtExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1sbvLeExpr, vc_sbvLeExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1sbvGtExpr, vc_sbvGtExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1sbvGeExpr, vc_sbvGeExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

// \ BITVECTOR COMPARISON OPERATIONS


// BITVECTOR BITWISE OPERATIONS

NEW_FUN_2(vc_1bvUMinusExpr, vc_bvUMinusExpr, jlong, VC, jlong, Expr, jlong)

NEW_FUN_3(vc_1bvAndExpr, vc_bvAndExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1bvOrExpr, vc_bvOrExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_3(vc_1bvXorExpr, vc_bvXorExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_2(vc_1bvNotExpr, vc_bvNotExpr, jlong, VC, jlong, Expr, jlong)

// \ BITVECTOR BITWISE OPERATIONS


// BITVECTOR SHIFT OPERATIONS

NEW_FUN_4(vc_1bvLeftShiftExprExpr, vc_bvLeftShiftExprExpr, jlong, VC, jint, int, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_4(vc_1bvRightShiftExprExpr, vc_bvSignedRightShiftExprExpr, jlong, VC, jint, int, jlong, Expr, jlong, Expr, jlong)

NEW_FUN_4(vc_1bvSignedRightShiftExprExpr, vc_bvSignedRightShiftExprExpr, jlong, VC, jint, int, jlong, Expr, jlong, Expr, jlong)

// \ BITVECTOR SHIFT OPERATIONS


//  BITVECTOR EXTRACTION & EXTENSION

NEW_FUN_4(vc_1bvExtract, vc_bvExtract, jlong, VC, jlong, Expr, jint, int, jint, int, jlong)

NEW_FUN_3(vc_1bvBoolExtract_1Zero, vc_bvBoolExtract_Zero, jlong, VC, jlong, Expr, jint, int, jlong)

NEW_FUN_3(vc_1bvBoolExtract_1One, vc_bvBoolExtract_One, jlong, VC, jlong, Expr, jint, int, jlong)

//  \ BITVECTOR EXTRACTION & EXTENSION


//  CONVENIENCE FUNCTIONS FOR ARRAYS

JNIEXPORT jlong JNICALL Java_org_zhekehz_stpjava_Native_vc_1bvCreateMemoryArray
  (JNIEnv * env, jclass cls, jlong vc, jstring name) {
    return (jlong) vc_bvCreateMemoryArray((VC) vc, (const char*) (*env)->GetStringUTFChars(env, name, NULL));
}

//  \ CONVENIENCE FUNCTIONS FOR ARRAYS


// GENERAL EXPRESSION OPERATIONS

NEW_FUN_1(vc_1Destroy, vc_Destroy, jlong, VC, void)

NEW_FUN_1(vc_1DeleteExpr, vc_DeleteExpr, jlong, Expr, void)

NEW_FUN_1(getExprKind, getExprKind, jlong, Expr, jint)

NEW_FUN_1(vc_1isBool, vc_isBool, jlong, Expr, jint)

NEW_FUN_1(vc_1getWholeCounterExample, vc_getWholeCounterExample, jlong, VC, jlong)

NEW_FUN_1(vc_1deleteWholeCounterExample, vc_deleteWholeCounterExample, jlong, WholeCounterExample, void)

NEW_FUN_3(vc_1getTermFromCounterExample, vc_getTermFromCounterExample, jlong, VC, jlong, Expr, jlong, WholeCounterExample, jlong)

// \ GENERAL EXPRESSION OPERATIONS


// ADDITIONAL

JNIEXPORT jboolean JNICALL Java_org_zhekehz_stpjava_Native_isConst
  (JNIEnv * env, jclass cls, jlong expr) {
    enum exprkind_t type = getExprKind((Expr) expr);
    return (jboolean) (type == BVCONST || type == FALSE || type == TRUE);
}

// \ ADDITIONAL
