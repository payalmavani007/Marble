package com.marble;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.marble.network.ApiManager;
import com.marble.network.RequestCode;
import com.marble.network.RequestParam;
import com.marble.utils.CommonUtil;
import com.marble.utils.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import hp.harsh.library.interfaces.OkHttpInterface;
import hp.harsh.library.okhttp.OkHttpRequest;

/**
 * Created by hp-pc on 21-12-2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener , OkHttpInterface {


    private static final String TAG = "LoginActivity";
    private Button btn_login, btn_signup;
    private CheckBox checkBox;
    private EditText edt_email, edt_password;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
        rememberMe();
    }

    private void rememberMe() {
        Boolean ischeck = PreferenceManager.isUserLoggedIn(this);
        Log.e(TAG,"**ischeck**" + ischeck);

        String emailPref = PreferenceManager.getprefCheckEmail(LoginActivity.this);
        Log.e(TAG, "**EmailPref**" + emailPref);

        if (ischeck)
        {
            checkBox.setChecked(true);
            edt_email.setText(emailPref);
        }
        else {
            checkBox.setChecked(false);
            edt_email.setText("");
        }
    }

    private void init()
    {

     /*   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
        toolbar.setTitle("Log In");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
     */
        btn_signup = findViewById(R.id.btn_signup);
        btn_login = (Button) findViewById(R.id.btn_login);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        btn_login.setOnClickListener(this);
        btn_signup.setOnClickListener(this);
    }
    private void checkValidation()
    {
        if (CommonUtil.isNullString(edt_email.getText().toString())) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
        } else if (!CommonUtil.checkEmail(edt_email.getText().toString())) {
            Toast.makeText(this, "Email  is invalid", Toast.LENGTH_SHORT).show();
        } else if (CommonUtil.isNullString(edt_password.getText().toString())) {
            Toast.makeText(this, "password is required", Toast.LENGTH_SHORT).show();
        } else if (edt_password.length() < 8 || edt_password.length() > 15) {
            Toast.makeText(this, "password must be min of 8 and max of 15 character", Toast.LENGTH_SHORT).show();
        } 
        else
        {
            String strEmail =edt_email.getText().toString();
            if (checkBox.isChecked())
            {
                Log.e(TAG,"Remember Me");
                Log.e(TAG,"StrEmail");
                PreferenceManager.saveUserLoggedIn(this,true,"isUserLoggedIn");
                PreferenceManager.setPref(this,strEmail,"USER_CHECK_EMAIL");
            }else {
                PreferenceManager.removePref(getApplicationContext(), PreferenceManager.IS_USER_LOGGED_IN);
                PreferenceManager.removePref(getApplicationContext(),"USER_CHECK_EMAIL");
            }
            // callapi
            callApiForLogin();
        }

    }

    private void callApiForLogin() {
        new OkHttpRequest(LoginActivity.this,
                OkHttpRequest.Method.POST,
                ApiManager.USER_LOGIN,
                RequestParam.userLogin("" + edt_email.getText().toString(),
                        "" + edt_password.getText().toString()),

                RequestParam.getNull(),
                RequestCode.CODE_USER_LOGIN,
                true, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case (R.id.btn_login):
                checkValidation();
                Intent i= new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
                break;
            case (R.id.btn_signup):

                Intent i1= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i1);
                break;

        }


    }

    @Override
    public void onOkHttpStart(int requestId) {
        
    }

    @Override
    public void onOkHttpSuccess(int requestId, int statusCode, String response) {
        Log.e(TAG, "Responce:" + response);

        switch (requestId){
            case RequestCode.CODE_USER_LOGIN:
                try
                {
                    JSONObject root = new JSONObject("" +response);
                    int responceCode = root.getInt("success");
                    final String responceMessage = root.getString("message");
                    if (responceCode ==  RequestCode.SUCCESS_CODE)
                    {
                        if (root.has("user_id"))
                        {
                            String mStrUserId = root.getString("user_id");
                            Log.e(TAG, "mStrUserId==" + mStrUserId);
                            PreferenceManager.setPref(LoginActivity.this, mStrUserId, "USER_ID");
                        }
                        if (root.has("Username"))
                        {
                            String mStrUserName = root.getString("Username");
                            Log.e(TAG,"StrUerName==" + mStrUserName);
                            PreferenceManager.setPref(LoginActivity.this, mStrUserName, "Username");
                        }
                        if (root.has("email"))
                        {
                            String mStremail = root.getString("email");
                            Log.e(TAG, "mStremail==" + mStremail);
                            PreferenceManager.setPref(LoginActivity.this, mStremail, "email");


                        }
                        this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "" + responceMessage, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

        }

    }

    @Override
    public void onOkHttpFailure(int requestId, int statusCode, String response, Throwable error) {

    }

    @Override
    public void onOkHttpFinish(int requestId) {

    }
}
