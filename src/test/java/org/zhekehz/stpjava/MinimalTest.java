package org.zhekehz.stpjava;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinimalTest {
    @Test
    public void minimalTest() {
        ValidityChecker vc = new ValidityChecker();
        BitVector c = new BitVector(vc, "c", 32);
        BitVector a = BitVector.constant(vc, 32, 5);
        BitVector b = BitVector.constant(vc, 32, 6);
        BitVector xp1 = a.plus(b);
        BoolExpr eq = xp1.equiv(c);
        BoolExpr eq2 = eq.not();
        QueryResult ret = eq2.query();
        assertEquals(ret, QueryResult.INVALID);
        vc.printCounterExample();
        vc.destroy();
    }
}