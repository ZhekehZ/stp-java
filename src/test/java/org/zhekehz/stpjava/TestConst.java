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

        assertEquals(0b11111111, ff.signExtend(8).toInt());
        assertEquals(0b00000011, i3.signExtend(8).toInt());
    }

    @Test
    public void test4() {
        assertTrue(BoolExpr.getTrue(vc).toBitVector().extractOne(0).toBoolean());
    }

    @Test
    public void test5() {
        MemoryArrayExpr array = new MemoryArrayExpr(vc, "mem_array");

        BitVectorExpr idx1 = BitVectorExpr.fromInt(vc, 32, 0);
        BitVectorExpr idx2 = BitVectorExpr.fromInt(vc, 32, 1);

        BitVectorExpr bv1 = array.read(idx1, 2);
        BitVectorExpr bv2 = array.read(idx2, 1);

        bv1.equiv(BitVectorExpr.fromInt(vc, 16, 0b10010101010)).assertFormula();
        BoolExpr.getFalse(vc).query();

        assertEquals(0b10010101010, bv1.getCounterExample().toInt());
        assertEquals(0b10101010, array.read(idx1, 1).getCounterExample().toInt());
        assertEquals(0b100, bv2.getCounterExample().toInt());
    }

    @Test
    public void test6() {
        BitVectorExpr bv1 = new BitVectorExpr(vc, "123", 123);
        BitVectorExpr bv2 = BitVectorExpr.fromInt(vc, 123, 1);

        assertEquals(bv1.getSort(), bv2.getSort());
        assertEquals(bv1.asBool().getSort(), BoolExpr.getFalse(vc).getSort());
        assertEquals(bv1.extractZero(1).getSort(), BoolExpr.getFalse(vc).getSort());
    }

    @Test
    public void test7() {
        int testNum = 1876;

        BitVectorExpr bv1 = BitVectorExpr.fromInt(vc, 11, testNum);
        BitVectorExpr bv2 = BitVectorExpr.fromInt(vc, 17, testNum);

        assertTrue(bv1.zeroExtend(17).equiv(bv2).toBoolean());
    }
}
