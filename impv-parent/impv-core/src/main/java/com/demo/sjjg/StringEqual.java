package com.demo.sjjg;

import org.junit.Test;

/**
 * @Description: Created by Admin on 2016/10/24.
 */
public class StringEqual {
    private final static String targetFormal = "goodgoogle";
    private final static String modelFormal = "google";

    @Test
    public void testString() {
        strEqual(targetFormal, modelFormal);
    }

    public void strEqual(String target, String model) {
        if(target.contains(model)){
//            for (int i = 0; i < model.length(); i++) {
//                if(target.indexOf(i) == model.indexOf(i)){
//
//                }
//            }
            for (int i = 0; i < model.length(); i++) {

                char c = target.charAt(model.indexOf(i));
                for (int j = 0; j < target.length(); j++) {
                    for (int k = 0; k < model.length();k++) {

                    }
                }
            }
        }
    }
}
