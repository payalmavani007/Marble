package com.marble.network;


public class RequestCode {

    // Response Variable
    public static final int SUCCESS_CODE = 1;
    public static final int FAILURE_CODE = 0;
    public static final int SOCIAL_CODE = 2;
    public static final int NOT_FOUND_CODE = 404;
    public static final int NO_CONTENT_CODE = 204;
    public static final int INTERNAL_ERROR_CODE = 500;
    public static final int BAD_REQUEST_CODE = 400;
    public static final int CONFLICT_CODE = 409;
    public static final int UNAUTHORIZED_CODE = 401;

    // Network Request code
    public static final int CODE_USER_LOGIN = 100;
    public static final int CODE_USER_SIGNUP = 101;
    public static final int CODE_USER_FEEDBACK = 201;
    public static final int CODE_DISPLAY_CATEGORY = 102;
    public static final int CODE_DISPLAY_RETAILER = 103;
    public static final int CODE_DISPLAY_DEALER = 104;
    public static final int CODE_DISPLAY_CATELOG = 104;



    public static final int CODE_USER_SOCIAL_LOGIN = 102;
    public static final int CODE_USER_CHANGE_PASSWORD = 103;
    public static final int CODE_USER_FORGOT_PASSWORD = 104;
    public static final int CODE_USER_UPDATE = 105;

}
