package org.zhekehz.stpjava;

import org.junit.After;
import org.junit.Before;

public abstract class TestBase {

    protected ValidityChecker vc;

    @Before
    public void setUp() {
        vc = new ValidityChecker();
    }

    @After
    public void tearDown() {
        vc.destroy();
    }

}
