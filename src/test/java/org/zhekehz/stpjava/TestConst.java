package org.zhekehz.stpjava;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestConst extends TestBase {

    @Test
    public void test1() {
        BitVectorExpr test = BitVectorExpr.fromInt(vc, 32, 144)
                .plus(BitVectorExpr.fromInt(vc, 32, 112))
                .extract(31, 8);
        assertTrue(test.isConst());
        assertEquals(1, test.toInt());
    }

    @Test
    public void test2() {
        BitVectorExpr test = BitVectorExpr.fromInt(vc, 4, 15)
                .concat(BitVectorExpr.fromInt(vc, 4, 15))
                .leftShift(BitVectorExpr.fromInt(vc, 4, 4), 9)
                .equiv(BitVectorExpr.fromLong(vc, 9, 0b111110000))
                .toBitVector();
        assertEquals(1, test.toInt());
    }

    @Test
    public void test3() {
        BitVectorExpr ff = BitVectorExpr.fromInt(vc, 4, 0b1111);
        BitVectorExpr i3 = BitVectorExpr.fromInt(vc, 4, 3);
        BitVectorExpr one = BitVectorExpr.fromInt(vc, 4, 1);

        BoolExpr test1 = ff.rightShift(i3, 4).equiv(one);
        assertTrue(test1.toBoolean());

        BoolExpr test2 = ff.signedRightShift(i3, 4).equiv(ff);
        assertTrue(test2.toBoolean());
    }

    @Test
    public void test4() {
        assertTrue(BoolExpr.getTrue(vc).toBitVector().extractOne(0).toBoolean());
    }
}
