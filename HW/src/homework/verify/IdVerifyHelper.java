package homework.verify;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IdVerifyHelper {

    public static void main(String[] args) {
        // 好 現在來處理問題 如果第二行執行到會有甚麼問題?
        // 到計算w2~w9的時候會錯誤 甚麼錯誤 這個方法是你自己想的嗎
        // 對啊XD  我是把每個身分證字號拆開來看，先把每一個身分證字號放進String[]裡，
        // 選 index1 得去看看字母換成數字變成多少
        // 再抓 index 2~9 轉成 int 並放進 int[] 去算總和
        // 最後再算出 index 10
        
        // 最後驗證
        // 那為什麼 error404 不行 它是哪一個字母不行
        // 因為他 index 2 是 r ，不能參與 w2~9 的計算
        // 為甚麼 r 不行
        // 我把 index 2~9 從 String 換成 int 去算 然後呢
        // 所以 r 不是 int 就報錯惹  那為什麼 e 沒有抱錯
        // 因為 index 1 的算法是用他去比對 A~Z 是=哪個位置 推出對應的數字
        // 所以身分證應該是 第一個碼是英文 但第二行變成 error 你是這樣判斷的 後面幾個是數字 後面幾個不是數字所以判段失敗 這樣嗎
        // 對
        // 那你是不是可以再多加一個邏輯 
        // 就是只要第二碼不是數字就打印驗證失敗嗎
        // 對 你可以去看正則表達式，正則表達式就可以不用迴圈判斷字母 你丟一個字串進去看這個字串有沒有符合你的規則 沒有你就直接打印失敗了
        // 所以你要在之前就把他擋掉，因為要符合你的第二個字母開始是數字才能執行你的方法
        // 這樣可以嗎
        // 懂！我也是這麼想，只是我不豬道要怎麼擋掉他 偶來去看正則表達式
        // 寫完就line我跟我說你寫完了
        // 豪
        // 你上面提到的 只要第二碼不是數字就打印驗證失敗嗎 這邊會有一個問題 那我今天把 error404 改成 e2ror404 你這樣不就還要再繼續去判斷後面幾碼後面幾碼
        // 一樣報錯
        // 對 所以比較好就是用正則表達式 去組成
        // 豪 我看一下說明
        // https://hsueh-jen.gitbooks.io/webcrawler/content/zheng-gui-biao-shi-shi/zheng-gui-biao-shi-shi.html
        // 上面這個網址看一下
        // 好
        ArrayList<String> lines = new ArrayList<>();
        String filePath = "HW/src/homework/verify/idList.txt";   // 檔案路徑
        // String filePath = "/Users/maggie/Downloads/idList.txt";   // 檔案路徑
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] fileContent = lines.toArray(new String[0]);  // 將 ArrayList 轉換為 String[] fileContent 數組


        for (int i = 0; i < fileContent.length; i++) {                 // 開始計算檔案內的每條數據
            String s = fileContent[i];                                 // 獲取 index i 的字串 s
            String[] fileDetail = s.split("");                   // 將 s 裝進 String[] fileDetail 中

            if (s.matches("[A-Za-z][0-9]{9}")) {

                // 計算 w1 ============================================

                int w1Nb = 0;
                String[] Apb = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

                for (int r = 0; r < 26; r++) {
                    if (Apb[r].equals(fileDetail[0])) {
                        w1Nb = r;
                        break;
                    }
                }
                // 查找 w1 在 Apb 裡的 index 位置
                for (int y = 0; y < Apb.length; y++) {
                    if (Apb[y].equals(fileDetail[0])) {
                        w1Nb = y;
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

                // 計算 w2~9 ============================================

                int w2To9sum = 0;
                int[] numberOfRest = new int[s.length() - 1];              // 創建 int[] numberOfRest
                for (int x = 0; x < numberOfRest.length; x++) {            // 將 fileDetail 中的字串轉換成整數並放進 numberOfRest
                    numberOfRest[x] = Integer.parseInt(fileDetail[x + 1]);
                }
                // 計算值
                for (int q = 0; q < numberOfRest.length; q++) {
                    w2To9sum += numberOfRest[q] * (8 - q);
                }

                // 計算 w10 ============================================
                int w10Nb;
                w1Nb = Character.getNumericValue(fistNumber) + Character.getNumericValue(secondNumber) * 9;
                int y = (w1Nb + w2To9sum) % 10;  // 餘數
                if (y == 0) {
                    w10Nb = 0;
                } else {
                    w10Nb = 10 - y;
                }

                // 打印
                if (w10Nb == numberOfRest[s.length() - 2]) {
                    System.out.println("==== 您輸入的身分證字號 " + s + " ====");
                    System.out.println("==== 驗證成功 ====");
                    System.out.println("-----------------------\n");
                } else {
                    System.out.println("==== 您輸入的身分證字號 " + s + " ====");
                    System.out.println("==== 驗證失敗 ====");
                    System.out.println("-----------------------\n");
                }

            }else {
                System.out.println("==== 您輸入的身分證字號 " + s + " ====");
                System.out.println("==== 驗證失敗 ====");
                System.out.println("-----------------------\n");
            }
        }


    }
}

