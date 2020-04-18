package org.zhekehz.stpjava;

public class ArrayExpr<ElementType extends ExprType> extends Expr {

    private final BitVectorType indexType;
    private final ElementType elementType;

    public ArrayExpr(ValidityChecker vc, String name, BitVectorType indexType, ElementType elementType) {
        super(vc, Native.vc_varExpr(vc.getRef(), name,
                Native.vc_arrayType(vc.getRef(), indexType.getRef(vc), elementType.getRef(vc))));
        this.indexType = indexType;
        this.elementType = elementType;
    }

    public ArrayExpr(ValidityChecker vc, BitVectorType indexType, ElementType elementType, long ref) {
        super(vc, ref);
        this.indexType = indexType;
        this.elementType = elementType;
    }

    Expr read(BitVectorExpr index) {
        return elementType.buildExpr(vc, Native.vc_readExpr(vc.getRef(), exprRef, index.exprRef));
    }

    ArrayExpr<ElementType> write(BitVectorExpr index, Expr expr) {
        if (!expr.getType().equals(elementType)) {
            throw new IllegalArgumentException("expr type is invalid");
        }
        return new ArrayExpr<>(vc, indexType, elementType, Native.vc_writeExpr(vc.getRef(), exprRef, index.exprRef, expr.exprRef));
    }

    @Override
    public ArrayExpr<ElementType> fromRef(long ref) {
        return new ArrayExpr<>(vc, indexType, elementType, ref);
    }

    @Override
    public ArrayType<ElementType> getType() {
        return new ArrayType<>(indexType, elementType);
    }

    @Override
    public int hashCode() {
        return (indexType.hashCode() * 31 + elementType.hashCode()) * 13 + super.hashCode();
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ArrayExpr)) return false;
        ArrayExpr<ElementType> other = (ArrayExpr<ElementType>) o;
        return indexType == other.indexType && elementType == other.elementType && super.equals(o);
    }
}
