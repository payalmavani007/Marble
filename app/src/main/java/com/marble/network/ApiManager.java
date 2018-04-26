package com.marble.network;

/**
 * Created by arti on 2/9/2016.
 */
public class ApiManager {

    // http://216.55.169.45/~kidstats/master/api/ws_signin



    // Base url
    public static String BASE_URL = " http://192.168.43.186/admin_them/jsoncontroller.php?";
    public static String IMAGE_BASE_URL = "";

    // Custom api url
    public static String USER_LOGIN = BASE_URL + "ws_login";
    public static String USER_SIGNUP = BASE_URL + "ws_register";
    public static String USER_FEEDBACK = BASE_URL + "ws_feedback";
    public static String USER_CHANGE_PASSWORD = BASE_URL + "ws_changepassword";
    public static String USER_FORGOT_PASSWORD = BASE_URL + "ws_forgotpassword";
    public static String USER_UPDATE = BASE_URL + "ws_updateuser";
    public static String DISPLAY_CATEGORY = BASE_URL + "display";
    public static String DISPLAY_DEALER = BASE_URL + "display_dealer";
    public static String DISPLAY_RETAILER = BASE_URL + "display_retailer";
    public static String DISPLAY_CATELOG = BASE_URL + "display_catelog";


}
