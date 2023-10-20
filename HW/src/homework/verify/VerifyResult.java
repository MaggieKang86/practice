package homework.verify;

public class VerifyResult {

    private Boolean isVerifySuccess;
    private String id;
    private String message;

    public VerifyResult(Boolean isVerifySuccess, String id, String message) {
        this.isVerifySuccess = isVerifySuccess;
        this.id = id;
        this.message = isVerifySuccess ? "驗證成功" : "驗證失敗";
    }

    public VerifyResult() {
    }


    public Boolean getVerifySuccess() {
        return isVerifySuccess;
    }

    public void setVerifySuccess(Boolean verifySuccess) {
        isVerifySuccess = verifySuccess;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
