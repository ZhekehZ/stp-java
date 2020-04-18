package org.zhekehz.stpjava;

public class ArrayType<ElementType extends ExprType> implements ExprType {

    private final BitVectorType indexType;
    private final ElementType elementType;

    public ArrayType(BitVectorType indexType, ElementType elementType) {
        this.indexType = indexType;
        this.elementType = elementType;
    }

    @Override
    public ArrayExpr<ElementType> buildExpr(ValidityChecker vc, long ref) {
        return new ArrayExpr<>(vc, indexType, elementType, ref);
    }

    @Override
    public long getRef(ValidityChecker vc) {
        return Native.vc_arrayType(vc.getRef(), indexType.getRef(vc), elementType.getRef(vc));
    }

    @Override
    public int hashCode() {
        return indexType.hashCode() * 13 + elementType.hashCode();
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ArrayType)) return false;
        ArrayType<ElementType> other = (ArrayType<ElementType>) o;
        return indexType.equals(other.indexType) && elementType.equals(other.elementType);
    }
}
