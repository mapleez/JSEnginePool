package org.dt.ez.js;

import org.apache.commons.pool2.impl.GenericObjectPool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleJS {

    static GenericObjectPool pool = new GenericObjectPool (new CalculatorPool ());

    private static void testExec1 () {
        final String jsScript = "x=2.0";
        Calculator obj = null;
        double res = -1;
        try {
            obj = (Calculator) pool.borrowObject ();
            res = (Double) obj.evalJSScript (jsScript);
        } catch (Exception ex) {
            ex.printStackTrace ();
        }

        System.out.printf ("testExec1 = %f\n", res);
        pool.returnObject (obj);
    }

    private static void testExec2 () {
        final String jsScript = "x;";
        Calculator obj = null;
        double res = -1;
        try {
            obj = (Calculator) pool.borrowObject ();
            res = (Double) obj.set ("x", 0.23).evalJSScript (jsScript);
        } catch (Exception ex) {
            ex.printStackTrace ();
        }

        System.out.printf ("testExec2 = %f\n", res);
        pool.returnObject (obj);
    }

    public static void main (String [] args) {

        ExecutorService threads = Executors.newFixedThreadPool (10);
        for (int i = 0; i < 100; i ++)
            threads.execute (new TestThread (i + 1));
        threads.shutdown ();

    }

    static class TestThread implements Runnable {

        private int subrouting;
        public TestThread (int type) {
            subrouting = type;
        }

        @Override
        public void run () {
            if (subrouting % 2 == 0)
                testExec2 ();
            else
                testExec1 ();
        }
    }
}

