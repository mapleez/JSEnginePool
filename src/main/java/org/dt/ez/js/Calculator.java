package org.dt.ez.js;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java.util.Map;
// import java.util.HashMap;

public class Calculator {

    private ScriptEngine engine;

    public Calculator (ScriptEngineManager manager) {
        engine = manager.getEngineByName ("nashorn");
    }

    public Calculator set (String name, Double value) {
        engine.put (name, value);
        return this;
    }

    public Object get (String name) {
        return engine.get (name);
    }

    public Object evalJSScript (String script)
            throws ScriptException {
        return engine.eval (script);
    }

    public Object evalJSString (String script, Map <String, Double> params)
            throws javax.script.ScriptException {
        for (Map.Entry <String, Double> e : params.entrySet ())
            engine.put (e.getKey (), e.getValue ());
        return engine.eval (script);
    }

    public static void main (String [] args) {

    }

}

