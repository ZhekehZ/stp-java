package org.zhekehz.stpjava;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.zhekehz.stpjava.QueryResult.VALID;

public class TestSat extends TestBase {

    @Test
    public void test1() {
        BitVectorExpr a = new BitVectorExpr(vc, "a", 32);
        BitVectorExpr b = new BitVectorExpr(vc, "b", 32);

        assertEquals(VALID, a.equiv(b).iff(b.equiv(a)).query());
    }

    @Test
    public void test2() {
        BitVectorExpr a = new BitVectorExpr(vc, "a", 32);

        BoolExpr p1 = a.gt(BitVectorExpr.fromInt(vc, 32, 17));
        BoolExpr p2 = a.le(BitVectorExpr.fromInt(vc, 32, 24));

        BoolExpr x = p1.not().or(p2.not()).not();
        BoolExpr y = p1.and(p2);
        assertEquals(VALID, x.iff(y).query());
    }

    @Test
    public void test3() {
        BitVectorExpr a = new BitVectorExpr(vc, "a", 32);

        BitVectorExpr c = BitVectorExpr.fromLong(vc, 32, 1234);

        Function<BitVectorExpr, BoolExpr> P = x -> x.lt(c.plus(BitVectorExpr.fromInt(vc, 32, 1)));
        Function<BitVectorExpr, BoolExpr> Q = x -> x.ge(c);
        Function<BitVectorExpr, BoolExpr> PQ = x -> x.equiv(c);

        assertEquals(VALID, PQ.apply(a).implies(P.apply(a)).query());
        assertEquals(VALID, PQ.apply(a).implies(Q.apply(a)).query());
    }

}