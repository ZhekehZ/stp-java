package org.zhekehz.stpjava;

import java.util.Arrays;
import java.util.HashMap;

public class FunctionExpr extends ArrayExpr {
    private final int[] argWidth;
    private final int resultWidth;

    public FunctionExpr(ValidityChecker vc, String name, int argumentsNumber, int argumentWidth, int resultWidth) {
        super(vc, name, argumentsNumber * argumentWidth, resultWidth);
        this.argWidth = new int[argumentsNumber];
        this.resultWidth = resultWidth;
        Arrays.fill(argWidth, argumentWidth);
    }

    public FunctionExpr(ValidityChecker vc, String name, int[] argWidths, int resultWidth) {
        super(vc, name, getIndexWidth(argWidths), resultWidth);
        this.argWidth = argWidths;
        this.resultWidth = resultWidth;
    }

    public FunctionExpr(ValidityChecker vc, int[] argWidths, int resultWidth, long ref) {
        super(vc, getIndexWidth(argWidths), resultWidth, ref);
        this.argWidth = argWidths;
        this.resultWidth = resultWidth;
    }

    private static int getIndexWidth(int[] argWidths) {
        return Arrays.stream(argWidths).sum();
    }

    public BitVectorExpr apply(BitVectorExpr... args) {
        if (args.length != argWidth.length) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        for (int i = 0; i < argWidth.length; i++) {
            if (args[i].getWidth() != argWidth[i]) {
                throw new IllegalArgumentException("BV-args must have the same bit-width");
            }
        }
        BitVectorExpr index = args[0];
        for (int i = 1; i < args.length; i++) {
            index = index.concat(args[i]);
        }
        return super.read(index);
    }

    @Override
    public BitVectorExpr read(BitVectorExpr index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayExpr write(BitVectorExpr index, BitVectorExpr expr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FunctionExpr fromRef(long ref) {
        return new FunctionExpr(vc, argWidth, resultWidth, ref);
    }

    public CounterExampleFunction getCounterExampleFunction() {
        return new CounterExampleFunction();
    }

    @Override
    public HashMap<Integer, Integer> getCounterExampleIntInt() {
        throw new UnsupportedOperationException();
    }

    @Override
    public HashMap<Long, Long> getCounterExampleLongLong() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof FunctionExpr)) return false;
        return super.equals(o);
    }

    public class CounterExampleFunction {
        private final HashMap<String, Long> data = getCounterExampleStringLong();

        public Long apply(long... indexes) {
            if (indexes.length != argWidth.length) {
                throw new IllegalArgumentException("Invalid arguments number");
            }
            BitVectorExpr bv = BitVectorExpr.fromLong(vc, argWidth[0], indexes[0]);
            for (int i = 1; i < indexes.length; i++) {
                bv = bv.concat(BitVectorExpr.fromLong(vc, argWidth[i], indexes[i]));
            }
            final BitVectorExpr bvConst = bv;
            return data.get(bv.toString());
        }
    }

    @Override
    public Sort getSort() {
        return new FunctionSort(argWidth, resultWidth);
    }
}
