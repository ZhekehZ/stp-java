package org.zhekehz.stpjava;

import org.junit.Test;

import java.util.HashMap;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import static org.junit.Assert.*;
import static org.zhekehz.stpjava.QueryResult.INVALID;
import static org.zhekehz.stpjava.QueryResult.TIMEOUT;

public class TestUnSat extends TestBase {

    @Test
    public void testBVBasicArithmetic() {
        BitVectorExpr c = new BitVectorExpr(vc, "c", 32);
        BitVectorExpr a = BitVectorExpr.fromInt(vc, 32, 5);
        BitVectorExpr b = BitVectorExpr.fromInt(vc, 32, 6);

        QueryResult ret = a.plus(b).equiv(c).not().query();
        IntPredicate check = i -> !((5 + 6) == i);

        assertEquals(INVALID, ret);
        assertFalse(check.test(c.getCounterExample().toInt()));
    }

    @Test
    public void testBVBasicLogic() {
        BitVectorExpr a = new BitVectorExpr(vc, "a", 8);
        BitVectorExpr b = new BitVectorExpr(vc, "b", 8);
        BitVectorExpr c = BitVectorExpr.fromInt(vc, 8, 5);

        BoolExpr expr = a.xor(b).equiv(c).and(a.ge(c)).not();
        BiPredicate<Integer, Integer> check = (i, j) -> !((i ^ j) == 5 && i >= 5);

        assertEquals(INVALID, expr.query());
        assertFalse(check.test(a.getCounterExample().toInt(), b.getCounterExample().toInt()));
    }

    @Test
    public void testArray() {
        ArrayExpr array = new ArrayExpr(vc, "AII", 4, 13);
        BitVectorExpr i = BitVectorExpr.fromInt(vc, 4, 14);
        BitVectorExpr v = new BitVectorExpr(vc, "v", 13);

        ArrayExpr array1 = array.write(i, array.read(i).minus(v));
        BoolExpr expr = array1.read(i).equiv(BitVectorExpr.fromLong(vc, 13, 123)).not();

        int idx = 14;
        BiPredicate<HashMap<Integer, Integer>, Integer> check = (arr, val) -> {
            arr.put(idx, arr.get(idx) - val);
            return !(arr.get(idx) == 123);
        };

        assertEquals(INVALID, expr.query());
        assertFalse(check.test(array.getCounterExampleIntInt(), v.getCounterExample().toInt()));
    }

    @Test
    public void testAssert() {
        BitVectorExpr bv = new BitVectorExpr(vc, "bv", 30);
        BitVectorExpr subBv = bv.extract(4, 1);

        BitVectorExpr t1 = BitVectorExpr.fromInt(vc, 8, 0b11111111).concat(subBv);
        BoolExpr expr = t1.equiv(BitVectorExpr.fromInt(vc, 12, 0b111111110111));

        expr.not().assertFormula();
        BiPredicate<Integer, Integer> check = (val, subVal) -> {
            assertEquals((int) subVal, (val & 0b11110) >> 1);
            return (0b11111111 << 4) + subVal == 0b111111101111;
        };

        assertEquals(INVALID, BoolExpr.getFalse(vc).query());
        assertFalse(check.test(bv.getCounterExample().toInt(), subBv.getCounterExample().toInt()));
    }

    @Test
    public void testMemoryArray() {
        MemoryArrayExpr array = new MemoryArrayExpr(vc, "arr");
        BitVectorExpr idx = BitVectorExpr.fromInt(vc, 32, 8);

        BoolExpr expr = array.read(idx).extractOne(2);
        Predicate<HashMap<Integer, Integer>> check = arr -> (arr.get(8) & 0b10) == 0b10;

        assertEquals(INVALID, expr.not().query());
        assertFalse(check.test(array.getCounterExampleIntInt()));
        assertTrue(expr.getCounterExample().toBoolean());
    }

    @Test
    public void testTimeout() {
        vc.useCryptominisat();
        assertTrue(vc.isUsingCryptominisat());

        MemoryArrayExpr array = new MemoryArrayExpr(vc, "arr");

        BitVectorExpr bv1 = new BitVectorExpr(vc, "bv1", 8);
        BitVectorExpr bv2 = new BitVectorExpr(vc, "bv2", 8);

        for (int i = 0; i < 10000; ++i) {
            BitVectorExpr idx = BitVectorExpr.fromInt(vc, 32, i);
            bv1.ge(array.read(idx)).assertFormula();
            bv2.le(array.read(idx)).assertFormula();
        }

        BoolExpr q = bv1.ge(bv2);
        assertEquals(TIMEOUT, q.queryWithTimeout(1, 1));
    }

    @Test
    public void testFunctionVar1() {
        FunctionExpr function = new FunctionExpr(vc, "func", 2, 4, 16);
        BitVectorExpr arg1 = BitVectorExpr.fromInt(vc, 4, 7);
        BitVectorExpr arg2 = new BitVectorExpr(vc, "arg2", 4);

        long expected = 42;

        assertEquals(INVALID,
                function.apply(arg1, arg2)
                        .equiv(BitVectorExpr.fromLong(vc, 16, expected))
                        .not().query());

        FunctionExpr.CounterExampleFunction functionCE = function.getCounterExampleFunction();
        long arg2CE = arg2.getCounterExample().toLong();

        assertEquals(expected, (long) functionCE.apply(7, arg2CE));
    }

    @Test
    public void testFunctionVar2() {
        FunctionExpr function = new FunctionExpr(vc, "func", new int[]{16, 29, 32}, 64);
        BitVectorExpr arg1 = BitVectorExpr.fromInt(vc, 16, 17);
        BitVectorExpr arg2 = BitVectorExpr.fromInt(vc, 29, 19);
        BitVectorExpr arg3 = BitVectorExpr.fromInt(vc, 32, 19);

        long expected = 12345;
        assertEquals(INVALID,
                function.apply(arg1, arg2, arg3)
                        .equiv(BitVectorExpr.fromLong(vc, 64, expected))
                        .not().query());
        FunctionExpr.CounterExampleFunction functionCE = function.getCounterExampleFunction();
        assertEquals(expected, (long) functionCE.apply(arg1.toLong(), arg2.toLong(), arg3.toLong()));
    }

    @Test
    public void testSimpleLogic() {
        BitVectorExpr a = new BitVectorExpr(vc, "a", 32);
        BitVectorExpr b = new BitVectorExpr(vc, "b", 32);
        BitVectorExpr c = new BitVectorExpr(vc, "c", 32);

        BitVectorExpr zero = BitVectorExpr.fromInt(vc, 32, 0);
        BitVectorExpr one = BitVectorExpr.fromInt(vc, 32, 1);

        BoolExpr query = c.equiv(zero).not();
        BoolExpr state = c.equiv(a.gt(b).ifThenElse(zero, one));

        assertEquals(INVALID, query.and(state).not().query());
    }
}
