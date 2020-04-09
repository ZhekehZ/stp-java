import java.io.IOException;

public class Main {

    static {
        System.loadLibrary("stp");
        System.loadLibrary("stpnative");
        System.out.println("LOADED SUCCESSFULLY\n");
    }

    public static void main(String[] args) {
        long vc = Native.createValidityChecker();
        long c = Native.varExpr(vc, "c", Native.bvType(vc, 32));
        long a = Native.bvConstExprFromInt(vc, 32, 5);
        long b = Native.bvConstExprFromInt(vc, 32, 6);
        long xp1 = Native.vc_bvPlusExpr(vc, 32, a, b);
        long eq = Native.vc_eqExpr(vc, xp1, c);
        long eq2 = Native.vc_notExpr(vc, eq);
        int ret = Native.vc_query(vc, eq2);
        assert (ret == 0);
        Native.vc_printCounterExample(vc);
        Native.vc_Destroy(vc);
    }

}