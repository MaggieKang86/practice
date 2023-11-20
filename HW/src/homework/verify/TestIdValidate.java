package homework.verify;
import java.util.List;

public class TestIdValidate {

    public static void main(String[] args) {

        String filePath = "HW/src/homework/static/idList3.txt";

        IdVerifyHelper idVerifyHelper = new IdVerifyHelper(filePath);
        List<VerifyResult> datalist = idVerifyHelper.validate(filePath);
        System.out.println(datalist);
        System.out.println("============================\n");

        VerifyHelperC01 verifyHelperC01 = new VerifyHelperC01(filePath);
        List<VerifyResult> datalist01 = verifyHelperC01.validate(filePath);
        System.out.println(datalist01);
        System.out.println("============================\n");

        VerifyHelperC02 verifyHelperC02 = new VerifyHelperC02(filePath);
        List<VerifyResult> datalist02 = verifyHelperC02.validate(filePath);
        System.out.println(datalist02);
        System.out.println("============================\n");

    }

}
