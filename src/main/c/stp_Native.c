#include <stdio.h>

#include "stp_Native.h"
#include <stp/c_interface.h>

JNIEXPORT jlong JNICALL Java_org_zhekehz_stpjava_Native_createValidityChecker
  (JNIEnv * env, jclass cls) {
    return (jlong) vc_createValidityChecker();
}

JNIEXPORT jlong JNICALL Java_org_zhekehz_stpjava_Native_bvType
  (JNIEnv * env, jclass cls, jlong vc, jint bits) {
  return (jlong) vc_bvType((VC) vc, (int) bits);
}

JNIEXPORT jlong JNICALL Java_org_zhekehz_stpjava_Native_varExpr
  (JNIEnv * env, jclass cls, jlong vc, jstring name, jlong type) {
    return (jlong) vc_varExpr((VC) vc, (const char*) (*env)->GetStringUTFChars(env, name, NULL), (Type) type);
}

JNIEXPORT jlong JNICALL Java_org_zhekehz_stpjava_Native_bvConstExprFromInt
  (JNIEnv * env, jclass cls, jlong vc, jint width, jint value) {
    return (jlong) vc_bvConstExprFromInt((VC) vc, (int) width, (unsigned int) value);
}

JNIEXPORT jlong JNICALL Java_org_zhekehz_stpjava_Native_vc_1bvPlusExpr
  (JNIEnv * env, jclass cls, jlong vc, jint width, jlong left_expr, jlong right_expr) {
    return (jlong) vc_bvPlusExpr((VC) vc, (int) width, (Expr) left_expr, (Expr) right_expr);
}

JNIEXPORT jlong JNICALL Java_org_zhekehz_stpjava_Native_vc_1eqExpr
  (JNIEnv * env, jclass cls, jlong vc, jlong child0, jlong child1) {
    return (jlong) vc_eqExpr((VC) vc, (Expr) child0, (Expr) child1);
}

JNIEXPORT jlong JNICALL Java_org_zhekehz_stpjava_Native_vc_1notExpr
  (JNIEnv * env, jclass cls, jlong vc, jlong expr) {
    return (jlong) vc_notExpr((VC) vc, (Expr) expr);
}

JNIEXPORT jint JNICALL Java_org_zhekehz_stpjava_Native_vc_1query
  (JNIEnv * env, jclass cls, jlong vc, jlong expr) {
    return (jint) vc_query((VC) vc, (Expr) expr);
}

JNIEXPORT void JNICALL Java_org_zhekehz_stpjava_Native_vc_1printCounterExample
  (JNIEnv * env, jclass cls, jlong vc) {
    vc_printCounterExample((VC) vc);
}

JNIEXPORT void JNICALL Java_org_zhekehz_stpjava_Native_vc_1Destroy
  (JNIEnv * env, jclass cls, jlong vc) {
    vc_Destroy((VC) vc);
}


#define CREATE_FUNCTION_1(        \
            name, cname,          \
            type1_from, type1_to, \
            ret)                  \
JNIEXPORT ret JNICALL Java_org_zhekehz_stpjava_Native_##name \
    (JNIEnv * env, jclass cls, type1_from a) {               \
    return (ret) cname((type1_to) a);                        \
}
#define CREATE_FUNCTION_2(        \
            name, cname,          \
            type1_from, type1_to, \
            type2_from, type2_to, \
            ret)                  \
JNIEXPORT ret JNICALL Java_org_zhekehz_stpjava_Native_##name   \
    (JNIEnv * env, jclass cls, type1_from a1, type2_from a2) { \
    return (ret) cname((type1_to) a1, (type2_to) a2);          \
}
#define CREATE_FUNCTION_3(        \
            name, cname,          \
            type1_from, type1_to, \
            type2_from, type2_to, \
            type3_from, type3_to, \
            ret)                  \
JNIEXPORT ret JNICALL Java_org_zhekehz_stpjava_Native_##name                  \
    (JNIEnv * env, jclass cls, type1_from a1, type2_from a2, type3_from a3) { \
    return (ret) cname((type1_to) a1, (type2_to) a2, (type3_to) a3);          \
}
#define CREATE_FUNCTION_4(        \
            name, cname,          \
            type1_from, type1_to, \
            type2_from, type2_to, \
            type3_from, type3_to, \
            type4_from, type4_to, \
            ret)                  \
JNIEXPORT ret JNICALL Java_org_zhekehz_stpjava_Native_##name                                 \
    (JNIEnv * env, jclass cls, type1_from a1, type2_from a2, type3_from a3, type4_from a4) { \
    return (ret) cname((type1_to) a1, (type2_to) a2, (type3_to) a3, (type4_to) a4);          \
}

CREATE_FUNCTION_1(vc_1trueExpr, vc_trueExpr, jlong, VC, jlong)
CREATE_FUNCTION_1(vc_1falseExpr, vc_falseExpr, jlong, VC, jlong)
//CREATE_FUNCTION_2(vc_1boolToBVExpr, vc_boolToBVExpr, jlong, VC, jlong, Expr, jlong)
CREATE_FUNCTION_3(vc_1andExpr, vc_andExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)
CREATE_FUNCTION_3(vc_1orExpr, vc_orExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)
CREATE_FUNCTION_3(vc_1xorExpr, vc_xorExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)
CREATE_FUNCTION_3(vc_1impliesExpr, vc_impliesExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)
CREATE_FUNCTION_3(vc_1iffExpr, vc_iffExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong)
//CREATE_FUNCTION_4(vc_1iteExpr, vc_iteExpr, jlong, VC, jlong, Expr, jlong, Expr, jlong, Expr, jlong)

CREATE_FUNCTION_2(vc_1assertFormula, vc_assertFormula, jlong, VC, jlong, Expr, void)
CREATE_FUNCTION_2(vc_1simplify, vc_simplify, jlong, VC, jlong, Expr, jlong)
CREATE_FUNCTION_2(vc_1printExpr, vc_printExpr, jlong, VC, jlong, Expr, void)