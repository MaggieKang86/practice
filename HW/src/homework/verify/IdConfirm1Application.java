package com.example.idconfirm1;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdConfirm1Application {

    public static void main(String[] args) {

        System.out.println("請輸入身分證字號");
        IdVerifyHelper idVerifyHelper = new IdVerifyHelper();
        //System.out.println("您輸入的長度是" + idVerifyHelper.idLength);
        //System.out.println("您輸入的字串是" + idVerifyHelper.userInput);

        //System.out.println("進入w1計算");
        idVerifyHelper.w1();
        //System.out.println("w1的值 =" + idVerifyHelper.w1Nb);

        //System.out.println("進入w2To9計算");
        idVerifyHelper.w2To9();
        //System.out.println("w2To9sum =" + idVerifyHelper.w2To9sum);

        //System.out.println("進入w10計算");
        idVerifyHelper.w10();
        //System.out.println("w10Nb =" + idVerifyHelper.w10Nb);

        idVerifyHelper.ConfirmId();

    }

}
