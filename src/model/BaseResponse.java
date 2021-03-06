package model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "response")
public class BaseResponse {

    public static final int OK = 1;
    public static final int AUTH_ERROR = 2;
    public static final int REGISTRATION_ERROR = 3;
    public static final int ADD_CONTACT_ERROR = 4;

    @Attribute
    private int resultCode;

    public BaseResponse() {}

    public BaseResponse(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
