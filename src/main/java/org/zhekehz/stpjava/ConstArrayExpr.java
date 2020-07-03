package org.zhekehz.stpjava;

import java.util.HashMap;

public class ConstArrayExpr extends ArrayExpr {
    private final BitVectorExpr defaultValue;

    public ConstArrayExpr(ValidityChecker vc, int indexWidth, BitVectorExpr value) {
        super(vc, indexWidth, value.getWidth(), 0);
        defaultValue = value;
    }

    @Override
    public BitVectorExpr read(BitVectorExpr index) {
        if (index.getWidth() != indexWidth) {
            throw new IllegalArgumentException("Argument has invalid bit-width");
        }
        return defaultValue;
    }

    @Override
    public ArrayExpr write(BitVectorExpr index, BitVectorExpr expr) {
        throw new IllegalArgumentException("ConstArrayExpr: unsupported operation");
    }

    @Override
    public ArrayExpr fromRef(long ref) {
        return this;
    }

    @Override
    protected boolean sameTypeWith(Expr other) {
        return super.sameTypeWith(other);
    }

    @Override
    public HashMap<Integer, Integer> getCounterExampleIntInt() {
        throw new IllegalArgumentException("ConstArrayExpr: unsupported operation");
    }

    @Override
    public HashMap<Long, Long> getCounterExampleLongLong() {
        throw new IllegalArgumentException("ConstArrayExpr: unsupported operation");
    }

    @Override
    public HashMap<String, Long> getCounterExampleStringLong() {
        throw new IllegalArgumentException("ConstArrayExpr: unsupported operation");
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 31 + defaultValue.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ConstArrayExpr)) return false;
        if (((ArrayExpr) o).indexWidth != indexWidth) return false;
        return defaultValue.equals(((ConstArrayExpr) o).defaultValue);
    }

    @Override
    public Sort getSort() {
        return super.getSort();
    }

    @Override
    public ArrayExpr asArray() {
        return this;
    }

    @Override
    public BoolExpr equiv(Expr other) {
        if (!(other instanceof ConstArrayExpr) || ((ConstArrayExpr) other).indexWidth != indexWidth) {
            throw new IllegalArgumentException("ConstArrayExpr: unsupported operation");
        }
        return defaultValue.equiv(((ConstArrayExpr) other).defaultValue);
    }

    @Override
    public Expr simplify() {
        return this;
    }

    @Override
    public boolean isConst() {
        return true;
    }

    @Override
    public void print() {
        throw new IllegalArgumentException("ConstArrayExpr: unsupported operation");
    }

    @Override
    public void destroy() {
    }

    @Override
    public String toString() {
        return "Const Array [ indexWidth = " + indexWidth+ ", value = " + defaultValue + " ]";
    }
}
