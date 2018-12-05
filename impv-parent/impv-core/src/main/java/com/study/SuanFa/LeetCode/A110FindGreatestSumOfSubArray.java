package com.study.SuanFa.LeetCode;

/**
 * 给定无序整数序列，求连续子串最大和
 * 时间复杂度O(n)，空间复杂度O(1)
 *
 * 输入：为整数序列，数字用空格分隔，如：-23 17 -7 11 -2 1 -34
 * 返回：21
 * https://blog.csdn.net/Butterfly_resting/article/details/81507580
 */
public class A110FindGreatestSumOfSubArray {

    public static void main(String[] args) {
        System.out.println(doFind(new int[]{-23,17,-7,11,-2,1,-34}));
    }

    public static int doFind(int[] array) {
        int curSum = 0;         //统计当前子数组总和
        int maxSum = array[0];  //统计目前最大的子数组最大和
        for(int i = 0; i < array.length; i++){
            if(curSum <= 0){    //如果curSum<=0，则将下一个数赋值给它，重新统计
                curSum = array[i];
            }else{              //否则继续累加array[i]
                curSum += array[i];
            }
            if(curSum > maxSum){//如果curSum大于maxSum，则刷新maxSum的值
                maxSum = curSum;
            }
        }
        return maxSum;
    }
}
