package org.zhekehz.stpjava;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimalTest {

    @Test
    public void minimalTest() {
        ValidityChecker vc = new ValidityChecker();
        BitVectorExpr c = new BitVectorExpr(vc, "c", 32);
        BitVectorExpr a = BitVectorExpr.fromInt(vc, 32, 5);
        BitVectorExpr b = BitVectorExpr.fromInt(vc, 32, 6);
        BitVectorExpr xp1 = a.plus(b);
        BoolExpr eq = xp1.equiv(c);
        BoolExpr eq2 = eq.not();
        QueryResult ret = eq2.query();
        assertEquals(QueryResult.INVALID, ret);
        vc.printCounterExample();
        vc.destroy();
    }

    @Test
    public void minimalTest2() {
        ValidityChecker vc = new ValidityChecker();
        BitVectorExpr a1 = new BitVectorExpr(vc, "c", 32);
        BitVectorExpr b1 = BitVectorExpr.fromInt(vc, 32, 5);
        BitVectorExpr b2 = BitVectorExpr.fromInt(vc, 32, 6);
        BoolExpr e = a1.equiv(b1).ifThenElse(a1.le(b2).not(), BoolExpr.getTrue(vc));
        assertEquals(QueryResult.INVALID, e.query());
        vc.printCounterExample();
        e.print();
        assertEquals(5, BitVectorExpr.fromInt(vc, 3, 2).concat(BitVectorExpr.fromLong(vc, 1, 1)).toInt());
        vc.destroy();
    }

    @Test
    public void arraysTest() {
        ValidityChecker vc = new ValidityChecker();

        ArrayExpr array = new ArrayExpr(vc, "myArray", 2, 13);

        BitVectorExpr i = BitVectorExpr.fromInt(vc, 2, 1);
        BitVectorExpr v = BitVectorExpr.fromLong(vc, 13, 1024);

        array = array.write(i, v);
        System.out.println(array.read(i).toInt());

        vc.destroy();
    }
}