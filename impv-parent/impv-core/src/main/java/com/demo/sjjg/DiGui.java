package com.demo.sjjg;

import org.junit.Test;

/**
 * @Description: 求阶乘：6!=6*5*4*3*2*1
 */
public class DiGui {
    @Test
    public void test(){
        System.out.println(diGui(6));;
    }

    public int diGui( int num){
        if(num<=1){
            return 1;
        }else{
            return num*diGui(num-1);
        }
    }
}
