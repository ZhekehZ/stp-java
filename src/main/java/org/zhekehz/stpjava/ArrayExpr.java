package org.zhekehz.stpjava;

public class ArrayExpr extends Expr {

    private final ExprType indexType;
    private final ExprType elementType;

    public ArrayExpr(ValidityChecker vc, ExprType indexType, ExprType elementType, long ref) {
        super(vc, ref);
        this.indexType = indexType;
        this.elementType = elementType;
    }

    @Override
    public Expr fromRef(long ref) {
        return new ArrayExpr(vc, indexType, elementType, ref);
    }

    @Override
    public ExprType getType() {
        return new ArrayType(indexType, elementType);
    }

    @Override
    public int hashCode() {
        return (indexType.hashCode() * 31 + elementType.hashCode()) * 13 + super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ArrayExpr)) return false;
        ArrayExpr other = (ArrayExpr) o;
        return indexType == other.indexType && elementType == other.elementType && super.equals(o);
    }
}
