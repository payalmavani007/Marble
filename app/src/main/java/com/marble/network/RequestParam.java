package com.marble.network;

import android.util.Log;

import com.marble.RegisterActivity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Harsh on 2/10/2016.
 */
public class RequestParam {

    private static String TAG = "RequestParam";
    private static String DEVICE_TYPE = "android";




    public enum SOCIAL_TYPE {
        google,facebook,twitter
    }

    public enum FAVOURITE_TYPE {
        add,remove
    }

    public static Map<String, String> getNull() {
        Map<String, String> mParam = new HashMap<String, String>();
        return mParam;
    }
    public static Map<String, String> userfeedback(String Username, String email_id, String comment) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("user_name", Username.trim());
        requestBody.put("email", email_id.trim());
        requestBody.put("comment", comment.trim());

        //requestBody.put("city", city.trim());

        return requestBody;
    }
   /* public static Map<String, String> dealer(String dealer_name, String dealer_address, String dealer_city) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("dealer_name", dealer_name.trim());
        requestBody.put("dealer_address", dealer_address.trim());
        requestBody.put("dealer_city", dealer_city.trim());

        //requestBody.put("city", city.trim());

        return requestBody;
    }
   */ public static Map<String, String> userSignUp(String Username, String email_id, String password, String mobile) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("Username", Username.trim());
        requestBody.put("email", email_id.trim());
        requestBody.put("password", password.trim());
        requestBody.put("contact_number", mobile.trim());
        //requestBody.put("city", city.trim());

        return requestBody;
    }

    public static Map<String, String> userLogin(String email_id, String password) {
        Map<String, String> requestBody = new HashMap<>();
                requestBody.put("email", email_id.trim());
                requestBody.put("password", password.trim());
        return requestBody;
    }


    public static Map<String, String> userChangePassword(String user_id, String old_password, String new_password) {
        Map<String, String> requestBody = new HashMap<>();
                requestBody.put("user_id", user_id.trim());
                requestBody.put("old_password", old_password.trim());
                requestBody.put("new_password", new_password.trim());
        return requestBody;
    }

    public static Map<String, String> userForgotPassword(String email_id) {
        Map<String, String> requestBody = new HashMap<>();
                requestBody.put("email_id", email_id.trim());
        return requestBody;
    }

    public static Map<String, String> userUpdate(String user_id,String first_name, String last_name, String email_id, String phone_no) {
        Map<String, String> requestBody = new HashMap<>();
                requestBody.put("user_id", user_id.trim());
                requestBody.put("first_name", first_name.trim());
                requestBody.put("last_name", last_name.trim());
                requestBody.put("email_id", email_id.trim());
                requestBody.put("phone_no", phone_no.trim());
        return requestBody;
    }

    public static Map<String, String> getAllCategories(String user_id) {
        Map<String, String> requestBody = new HashMap<>();
                requestBody.put("user_id", user_id.trim());
        return requestBody;
    }


}