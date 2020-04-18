package org.zhekehz.stpjava;

public interface ExprType {

    long getRef(ValidityChecker vc);

    Expr buildExpr(ValidityChecker vc, long ref);

}
