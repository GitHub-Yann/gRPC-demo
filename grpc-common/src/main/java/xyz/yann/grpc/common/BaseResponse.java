package xyz.yann.grpc.common;

public class BaseResponse {

    private String code;
    private String message;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static BaseResponse success(Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("1000200");
        baseResponse.setMessage("success");
        baseResponse.setData(data);
        return baseResponse;
    }

    public static BaseResponse fail(String code, String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMessage(message);
        baseResponse.setData(null);
        return baseResponse;
    }

}
