package homework.verify;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VerifyHelperC01 extends IdVerifyHelper{

    /*
        新增長度驗證，如果輸入證號長度不為10
        驗證失敗回傳：
            isVerifySuccess = false
            id = 輸入證號
            message = 證號長度不為10
        新增證號格式檢查，須為英文字母大寫+九個數字，
        驗證失敗回傳：
            isVerifySuccess = false
            id = 輸入證號
            message = 證號格式錯誤
     */

    public VerifyHelperC01(String fileName) {
        super(fileName);
    }

    // 覆寫 validate
    public List<VerifyResult> validate(String filePath) {

        List<VerifyResult> dataList = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();

        // 讀
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
            VerifyResult verifyResult = new VerifyResult();

            if (s.matches(".{10}")) {
                if (s.matches("[A-Z][0-9]{9}")) {                 // 查看字串 s 是否符合身分證字號規則
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

                    if (w10Nb == numberOfRest[s.length() - 2]) {
                        verifyResult.setVerifySuccess(true);
                        verifyResult.setId(s);
                        verifyResult.setMessage("驗證成功");
                    } else {
                        verifyResult.setVerifySuccess(false);
                        verifyResult.setId(s);
                        verifyResult.setMessage("驗證失敗");
                    }

                } else {
                    verifyResult.setVerifySuccess(false);
                    verifyResult.setId(s);
                    verifyResult.setMessage("證號格式錯誤");
                }
            }else {
                verifyResult.setVerifySuccess(false);
                verifyResult.setId(s);
                verifyResult.setMessage("證號長度不為10");
            }

            dataList.add(verifyResult);

        }

        return dataList;

    }

}