package com.company.project.client.util.math;

import java.util.Random;

/**
 * Created by rock on 6/14/16.
 */
public class Rondom {

    public static int genRandomNum(int min,int max){
        Random rdm = new Random();
        return rdm.nextInt(max-min+1)+min;
    }
    //生成min->max之间的数,最小生成的随机数为min，最大生成的随机数为max
    public static double getRandomNum2(int min,int max){
        return Math.round(Math.random()*(max-min))+min;
    }

    public static int genRandomNum(int card_len){
        //35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; //生成的随机数
        int count = 0; //生成的密码的长度
        char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < card_len){
            //生成随机数，取绝对值，防止生成负数
            i = Math.abs(r.nextInt(maxNum)); //生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return new Integer(pwd.toString());
    }
    public static void main(String[] args) {
        for(int l=0;l<100;l++){
            System.out.println(genRandomNum(-3,3));//生成6位的随机数
        }
    }
}