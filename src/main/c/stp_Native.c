#include <stdio.h>

#include "stp_Native.h"
#include <stp/c_interface.h>

JNIEXPORT jlong JNICALL Java_Native_createValidityChecker
  (JNIEnv * env, jclass cls) {
    return (jlong) vc_createValidityChecker();
}

JNIEXPORT jlong JNICALL Java_Native_bvType
  (JNIEnv * env, jclass cls, jlong vc, jint bits) {
  return (jlong) vc_bvType((VC) vc, (int) bits);
}

JNIEXPORT jlong JNICALL Java_Native_varExpr
  (JNIEnv * env, jclass cls, jlong vc, jstring name, jlong type) {
    return (jlong) vc_varExpr((VC) vc, (const char*) name, (Type) type);
}

JNIEXPORT jlong JNICALL Java_Native_bvConstExprFromInt
  (JNIEnv * env, jclass cls, jlong vc, jint width, jint value) {
    return (jlong) vc_bvConstExprFromInt((VC) vc, (int) width, (unsigned int) value);
}

JNIEXPORT jlong JNICALL Java_Native_vc_1bvPlusExpr
  (JNIEnv * env, jclass cls, jlong vc, jint width, jlong left_expr, jlong right_expr) {
    return (jlong) vc_bvPlusExpr((VC) vc, (int) width, (Expr) left_expr, (Expr) right_expr);
}

JNIEXPORT jlong JNICALL Java_Native_vc_1eqExpr
  (JNIEnv * env, jclass cls, jlong vc, jlong child0, jlong child1) {
    return (jlong) vc_eqExpr((VC) vc, (Expr) child0, (Expr) child1);
}

JNIEXPORT jlong JNICALL Java_Native_vc_1notExpr
  (JNIEnv * env, jclass cls, jlong vc, jlong expr) {
    return (jlong) vc_notExpr((VC) vc, (Expr) expr);
}

JNIEXPORT jint JNICALL Java_Native_vc_1query
  (JNIEnv * env, jclass cls, jlong vc, jlong expr) {
    return (jint) vc_query((VC) vc, (Expr) expr);
}

JNIEXPORT void JNICALL Java_Native_vc_1printCounterExample
  (JNIEnv * env, jclass cls, jlong vc) {
    vc_printCounterExample((VC) vc);
}

JNIEXPORT void JNICALL Java_Native_vc_1Destroy
  (JNIEnv * env, jclass cls, jlong vc) {
    vc_Destroy((VC) vc);
}
