package com.demo.jsEngine;


import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: Created by Admin on 2016/11/2.
 */
public class MyScriptEngineTest {
    Pattern pattern = Pattern.compile("([a-zA-Z]+)");
    static Map<String,String> map = new HashMap<>();//构造替换表达式的数据

    static {
        map.put("value","160");
        map.put("height","180");
    }


    @Test
    public void test() {
        Matcher matcher = pattern.matcher("a0254");
        boolean matches = matcher.matches();
//        matcher.find();
        System.out.println(matches);
    }

    @Test
    public void test1() throws ScriptException {
        String validate = "value>(height/2-12)*1.2&&value<=180";
        Matcher matcher = pattern.matcher(validate);//匹配出表达式中的字母变量
        if(matcher.groupCount()>0){//匹配的表达式包含几个分组
            while (matcher.find()) {//找到每个表达式 之后移动到下个表达式
                String eValue = matcher.group(1);//逐个取得表达式中的字母变量
                System.out.println(eValue); //value height  value
                validate = validate.replace(eValue, map.get(eValue));
            }
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript"); //获取js引擎
            Object result = engine.eval(validate); //执行替换之后的js片段 返回执行结果
            if(Boolean.valueOf(String.valueOf(result)))
                System.out.println("匹配结果 "+result);
        }
    }

    @Test
    public void testDemo() {
        try {
            doSometh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void doSometh() throws ScriptException, NoSuchMethodException, InterruptedException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");

        //m1
        engine.put("msg", "just a ");
        String str = "msg += 'test！';var user = {name:'tom',age:23,hobbies:['football','basketball']};var name=user.name;var hb =user.hobbies[1];";
//        String str = "msg += 'test!';var user = {name:'tom',age:23,hobbies:['football','basketball']}; var name = user.name; var hb = user.hobbies[1];";
        engine.eval(str);

        System.out.println("m1 " + "msg" + engine.get("msg "));
        System.out.println("m1 " + "name:" + engine.get("name ") + " hb " + engine.get("hb"));

        //m2
        engine.eval("function add(a,b) {c=a+b; return c;}");//执行js方法
        Invocable jsInvoke = (Invocable) engine;
        Object o = jsInvoke.invokeFunction("add", new Object[]{10, 5});//要执行的js方法名
        System.out.println("m2 " + o);

        //m3
        Adder adder = jsInvoke.getInterface(Adder.class);//使用方法2中的js引擎定义的方法 使用接口来调用
        int addResult = adder.add(10, 35);
        System.out.println("m3 " + addResult);

        //m4
        engine.eval("function run(){print('测试js引擎')}");
        Invocable invocable = (Invocable) engine;
        Runnable runner = invocable.getInterface(Runnable.class);
        Thread t = new Thread(runner);
        t.start();
        t.join();

        //m5
        String jsCode = "importPackage(java.util);var list2 = Arrays.asList(['A', 'B', 'C']); ";
        engine.eval(jsCode);
        List<String> list2 = (List<String>) engine.get("list2");
        for (String val : list2) {
            System.out.print(val);
        }
    }
}

interface Adder {
    int add(int a, int b);//对应js引擎中的add方法
}
