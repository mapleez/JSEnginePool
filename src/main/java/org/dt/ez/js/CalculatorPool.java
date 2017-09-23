package org.dt.ez.js;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;

import org.apache.commons.pool2.impl.DefaultPooledObject;

import javax.script.ScriptEngineManager;

public class CalculatorPool extends BasePooledObjectFactory <Calculator> {

    static ScriptEngineManager engineManager;

    static {
        engineManager = new ScriptEngineManager ();
    }

    public void invalidateObject (Calculator calc) throws Exception {
        // TODO...
    }


    @Override
    public Calculator create () throws Exception {
        return new Calculator (CalculatorPool.engineManager);
    }

    @Override
    public PooledObject <Calculator> wrap (Calculator calc) {
        return new DefaultPooledObject <Calculator> (calc);
    }

}
