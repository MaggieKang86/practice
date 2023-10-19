package homework.verify;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IdVerifyHelper {

    public static void main(String[] args) {
        // .gitignore 要放在最外層
        // 好我在push一次
        ArrayList<String> lines = new ArrayList<>();
        String filePath = "HW/src/homework/verify/idList.txt";   // 檔案路徑
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
        }


    }
}

