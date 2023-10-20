package homework.apbcounting;

import java.util.Scanner;

public class Apbcounting {

    public static void main(String[] args) {

        System.out.println("請輸入字串：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        count(str);

    }

    /*
        1.使用者輸入
        2.判斷輸入的字串是否為英文字，
        3.若是則進入運算
        4.若否則回傳"0"
    */

    public static void count(String str){

        // 輸入字串總和的相加結果
        int sum = 0;

        // 檢查輸入的字串是否是英文字
        if (str.matches("[A-Za-z]+")){
            // 將使用者輸入的字串塞進 strline
            String[] strline = str.toUpperCase().split("");
            String[] Apb = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
            for (int i = 0; i < strline.length; i++){
                for (int x = 0; x < Apb.length; x++){
                    if (strline[i].equals(Apb[x])){
                        sum += (x + 1);
                    }
                }
            }
            System.out.println("您所輸入的字串總和相加為：" + sum);
        }else {
            System.out.println("0");
        }

    }

}
