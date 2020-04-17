package org.zhekehz.stpjava;

public class ArrayType implements ExprType {

    private final ExprType indexType;
    private final ExprType elementType;

    public ArrayType(ExprType indexType, ExprType elementType) {
        this.indexType = indexType;
        this.elementType = elementType;
    }

    @Override
    public long getRef(ValidityChecker vc) {
        return Native.vc_arrayType(vc.getRef(), indexType.getRef(vc), elementType.getRef(vc));
    }

    @Override
    public int hashCode() {
        return indexType.hashCode() * 13 + elementType.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ArrayType)) return false;
        ArrayType other = (ArrayType) o;
        return indexType.equals(other.indexType) && elementType.equals(other.elementType);
    }
}
