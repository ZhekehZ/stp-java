package org.zhekehz.stpjava;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestConst extends TestBase {

    @Test
    public void testBVPlusExtract() {
        BitVectorExpr test = BitVectorExpr.fromInt(vc, 32, 144)
                .plus(BitVectorExpr.fromInt(vc, 32, 112))
                .extract(31, 8);
        assertTrue(test.isConst());
        assertEquals(1, test.toInt());
    }

    @Test
    public void testBVConcatShift() {
        BitVectorExpr test = BitVectorExpr.fromInt(vc, 4, 15)
                .concat(BitVectorExpr.fromInt(vc, 4, 15))
                .leftShift(BitVectorExpr.fromInt(vc, 4, 4), 9)
                .equiv(BitVectorExpr.fromLong(vc, 9, 0b111110000))
                .toBitVector();
        assertEquals(1, test.toInt());
    }

    @Test
    public void testBVShiftExtend() {
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
    public void testBVExtractOnt() {
        assertTrue(BoolExpr.getTrue(vc).toBitVector().extractOne(0).toBoolean());
    }

    @Test
    public void testMemArray() {
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
    public void testSort() {
        BitVectorExpr bv1 = new BitVectorExpr(vc, "123", 123);
        BitVectorExpr bv2 = BitVectorExpr.fromInt(vc, 123, 1);

        assertEquals(bv1.getSort(), bv2.getSort());
        assertEquals(bv1.asBool().getSort(), BoolExpr.getFalse(vc).getSort());
        assertEquals(bv1.extractZero(1).getSort(), BoolExpr.getFalse(vc).getSort());
    }

    @Test
    public void testBVZeroExtend() {
        int testNum = 1876;

        BitVectorExpr bv1 = BitVectorExpr.fromInt(vc, 11, testNum);
        BitVectorExpr bv2 = BitVectorExpr.fromInt(vc, 17, testNum);

        assertTrue(bv1.zeroExtend(17).equiv(bv2).toBoolean());
    }

    @Test
    public void testBoolBasicCompare() {
        BoolExpr f = BoolExpr.getFalse(vc);
        BoolExpr t = BoolExpr.getTrue(vc);

        assertEquals(QueryResult.VALID, f.equiv(t).not().query());
    }

    @Test
    public void testConstArray() {
        ConstArrayExpr arrayExpr = new ConstArrayExpr(vc, 123, BitVectorExpr.fromInt(vc, 16, 444));

        assertEquals(444, arrayExpr.read(BitVectorExpr.fromInt(vc, 123, 33)).toInt());
    }

    @Test
    public void testSMTPrint() {
        BitVectorExpr bv1 = BitVectorExpr.fromInt(vc, 17, 44);
        BitVectorExpr bv2 = BitVectorExpr.fromInt(vc, 17, 33);

        String res = bv1.plus(bv2).or(bv1.minus(bv2)).toSMTLib2();
        System.out.println(res);
    }
}
