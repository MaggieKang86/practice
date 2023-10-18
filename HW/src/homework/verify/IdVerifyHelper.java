package com.example.idconfirm1;

import java.util.Scanner;

public class IdVerifyHelper {

    // 掃描使用者輸入
    Scanner scanner = new Scanner(System.in);
    // 將使用者輸入轉為字串
    String userInput = scanner.nextLine();
    // 使用者輸入的字串長度
    int idLength = userInput.length();
    // 將使用者輸入的字串放進 idNumber 數組中
    String[] idNumber = userInput.split("");

    // =====================================================================

    // 1st 字母數值計算
    int w1Nb;
    public void w1() {

        String[] Apb = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        // 抓取使用者輸入的 w1 並 toUpperCase
        String w1 = idNumber[0].toUpperCase();
        // 查找 w1 在 Apb 裡的 index 位置
        for (int i = 0; i < Apb.length; i++) {
            if (Apb[i].equals(w1)) {
                w1Nb = i;
                break;
            }
        }
        // 計算 w1Nb 的值
        if (w1Nb <= 7) {
            w1Nb = w1Nb + 10;
        } else if (w1Nb == 8) {
            w1Nb = 34;
        } else if (w1Nb <= 13) {
            w1Nb = w1Nb + 9;
        } else if (w1Nb == 14) {
            w1Nb = 35;
        } else if (w1Nb <= 21) {
            w1Nb = w1Nb + 8;
        } else if (w1Nb == 22) {
            w1Nb = 32;
        } else if (w1Nb <= 25) {
            w1Nb = w1Nb + 7;
        }

        String w1NbStr = Integer.toString(w1Nb);
        char fistNumber = w1NbStr.charAt(0);
        char secondNumber = w1NbStr.charAt(1);
        w1Nb = Character.getNumericValue(fistNumber) + Character.getNumericValue(secondNumber) * 9;

    }

    // 2nd 以後的字母數值計算
    int w2To9sum;
    public void w2To9() {

        // 將使用者輸入之字串 index 2~9 的部分放進新的 nemberOfRest 數組中
        int[] numberOfRest = new int[idLength - 1];
        numberOfRest[0] = Integer.parseInt(idNumber[1]);
        numberOfRest[1] = Integer.parseInt(idNumber[2]);
        numberOfRest[2] = Integer.parseInt(idNumber[3]);
        numberOfRest[3] = Integer.parseInt(idNumber[4]);
        numberOfRest[4] = Integer.parseInt(idNumber[5]);
        numberOfRest[5] = Integer.parseInt(idNumber[6]);
        numberOfRest[6] = Integer.parseInt(idNumber[7]);
        numberOfRest[7] = Integer.parseInt(idNumber[8]);
        numberOfRest[8] = Integer.parseInt(idNumber[9]);

        // 計算值
        for (int i = 0; i < numberOfRest.length; i++) {
            w2To9sum += numberOfRest[i] * (8-i);
        }

    }

    // 10th 字母值計算
    int w10Nb;
    public void w10() {
        int y = (w1Nb + w2To9sum) % 10;  // 餘數

        if (y == 0) {
            w10Nb = 0;
        }else {
            w10Nb = 10 - y;
        }
    }

    // =====================================================================

    // 驗證
    public void ConfirmId() {
        if (w10Nb == Integer.parseInt(idNumber[9])){
            System.out.println("==== 您輸入的身分證字號 " + userInput + " ====");
            System.out.println("==== 驗證成功 ====");
        }else {
            System.out.println("==== 您輸入的身分證字號 " + userInput + " ====");
            System.out.println("==== 驗證失敗 ====");
        }
    }

}