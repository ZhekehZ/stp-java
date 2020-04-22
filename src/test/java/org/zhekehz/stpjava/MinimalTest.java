package org.zhekehz.stpjava;

import org.junit.Test;

import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;

public class MinimalTest {

    @Test
    public void minimalTest() {
        ValidityChecker vc = new ValidityChecker();
        BitVectorExpr c = new BitVectorExpr(vc, "c", 32);
        BitVectorExpr d = new BitVectorExpr(vc, "d", 32);
        BitVectorExpr a = BitVectorExpr.fromInt(vc, 32, 5);
        BitVectorExpr b = BitVectorExpr.fromInt(vc, 32, 6);
        BitVectorExpr xp1 = a.plus(b);
        BoolExpr eq = xp1.equiv(c.plus(d));
        BoolExpr eq2 = eq.not();
        QueryResult ret = eq2.query();

        BiFunction<Integer, Integer, Boolean> test = (C, D) -> !(5 + 6 == C + D);
        assertEquals(QueryResult.INVALID, ret);
//        vc.printCounterExample();

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
        assertEquals(5, BitVectorExpr.fromInt(vc, 3, 2)
                .concat(BitVectorExpr.fromLong(vc, 1, 1)).toInt());
        vc.destroy();
    }


    @Test
    public void arraysTest() {
        ValidityChecker vc = new ValidityChecker();

        ArrayExpr array = new ArrayExpr(vc, "myArray", 2, 13);

        BitVectorExpr i = BitVectorExpr.fromInt(vc, 2, 1);
        BitVectorExpr v = new BitVectorExpr(vc, "bv", 13);

        BitVectorExpr v1 = array.read(i);
        array = array.write(i, v1.plus(v));
        array.read(i).equiv(BitVectorExpr.fromLong(vc, 13, 123)).query();
//        System.out.println(v.getCounterExample().toInt());


        vc.printCounterExample();
        array.getCounterExampleIntInt().forEach(
                (k, vv) -> {
                    System.out.printf("key = %d, ", k);
                    System.out.printf("val = %d\n", vv);
                }
        );


        vc.destroy();
    }

    @Test
    public void getCTest() {
        ValidityChecker vc = new ValidityChecker();

        BitVectorExpr bv = new BitVectorExpr(vc, "bitvec", 30);
        BitVectorExpr extacred = new BitVectorExpr(vc, "extr", 4);
        BitVectorExpr sub = bv.extract(4, 1);
        BitVectorExpr other = BitVectorExpr.fromInt(vc, 4, 5);

        BitVectorExpr next = BitVectorExpr.fromInt(vc, 8, 0b11111111).concat(extacred);
        BoolExpr boo = next.equiv(BitVectorExpr.fromInt(vc, 12, 0b111111111111));

        boo.not().assertFormula();
        extacred.equiv(sub).assertFormula();
        System.out.printf("sv = %d, vcc = %d\n", sub.getWidth(), other.getWidth());
        assertEquals(QueryResult.INVALID, sub.equiv(other).query());


        vc.printCounterExample();

        System.out.printf("bv = %d\n", bv.getCounterExample().toLong());
        System.out.println("FLAG = " + boo.getCounterExample().toBoolean());
        System.out.println(BitVectorExpr.fromInt(vc, 4, 10).extract(3, 0).toInt());
        System.out.println(extacred.getCounterExample().toInt());


        vc.destroy();
    }

    @Test
    public void memArray() {

        ValidityChecker vc = new ValidityChecker();
        MemoryArrayExpr array = new MemoryArrayExpr(vc, "MARRAY");

        BitVectorExpr idx = BitVectorExpr.fromInt(vc, 32, 8);
        array.read(idx).extractOne(2).not().query();

        System.out.println(array.getCounterExampleIntInt().toString());


        vc.destroy();

    }
}