package org.zhekehz.stpjava;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinimalTest {

    @Test
    public void minimalTest() {
        ValidityChecker vc = new ValidityChecker();
        BitVector c = new BitVector(vc, "c", 32);
        BitVector a = BitVector.fromInt(vc, 32, 5);
        BitVector b = BitVector.fromInt(vc, 32, 6);
        BitVector xp1 = a.plus(b);
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
        BitVector a1 = new BitVector(vc, "c", 32);
        BitVector b1 = BitVector.fromInt(vc, 32, 5);
        BitVector b2 = BitVector.fromInt(vc, 32, 6);
        BoolExpr e = a1.equiv(b1).ifThenElse(a1.le(b2).not(), BoolExpr.getTrue(vc));
        assertEquals(QueryResult.INVALID, e.query());
        vc.printCounterExample();
        e.print();
        assertEquals(5, BitVector.fromInt(vc, 3, 2).concat(BitVector.fromLong(vc, 1, 1)).toInt());
        vc.destroy();
    }
}