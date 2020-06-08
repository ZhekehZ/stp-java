package org.zhekehz.stpjava;

import java.io.IOException;

public class ValidityChecker {

    static {
        try {
            NativeUtils.loadLibrary("stpnative");
            NativeUtils.loadLibrary("stp");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Unable to load dynamic libraries");
        }
    }

    private final long ref;

    public ValidityChecker() {
        ref = Native.vc_createValidityChecker();
    }

    public void destroy() {
        Native.vc_Destroy(ref);
    }

    public long getRef() {
        return ref;
    }

    public void push() {
        Native.vc_push(ref);
    }

    public void pop() {
        Native.vc_pop(ref);
    }

    public void printCounterExample() {
        Native.vc_printCounterExample(ref);
    }

    public void useCryptominisat() {
        if (!Native.vc_supportsCryptominisat(ref)) {
            throw new IllegalStateException("Cryptominisat is not supported");
        }
        Native.vc_useCryptominisat(ref);
    }

    public boolean isUsingCryptominisat() {
        return Native.vc_isUsingCryptominisat(ref);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(ref);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ValidityChecker)) return false;
        return ref == ((ValidityChecker) o).ref;
    }
}
