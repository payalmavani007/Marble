package com.marble;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, OkHttpInterface {
    private static final String TAG = "RegisterActivity";
    private Button btn_register;
    private EditText edt_name, edt_email, edt_pass, edt_cpass, edt_cnum;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        init();
    }

    private void init() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Registration");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });




        btn_register = (Button) findViewById(R.id.btn_reg);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_pass = (EditText) findViewById(R.id.edt_pass);
        edt_cpass = (EditText) findViewById(R.id.edt_Cpass);
        edt_cnum = (EditText) findViewById(R.id.edt_Cnum);
        btn_register.setOnClickListener(this);


    }

    private void checkValidation() {
        if (CommonUtil.isNullString(edt_name.getText().toString())) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
        } else if (edt_name.length() < 2 || edt_name.length() > 20) {
            Toast.makeText(this, "Username must be length of min 2 and max 20 character", Toast.LENGTH_SHORT).show();
        } else if (CommonUtil.isNullString(edt_email.getText().toString())) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
        } else if (!CommonUtil.checkEmail(edt_email.getText().toString())) {
            Toast.makeText(this, "Email  is invalid", Toast.LENGTH_SHORT).show();
        } else if (CommonUtil.isNullString(edt_pass.getText().toString())) {
            Toast.makeText(this, "password is required", Toast.LENGTH_SHORT).show();
        } else if (edt_pass.length() < 8 || edt_pass.length() > 15) {
            Toast.makeText(this, "password must be min of 8 and max of 15 character", Toast.LENGTH_SHORT).show();
        } else if (CommonUtil.isNullString(edt_cpass.getText().toString())) {
            Toast.makeText(this, "confirm password is required", Toast.LENGTH_SHORT).show();
        } else if (edt_cpass.length() < 8 || edt_cpass.length() > 15) {
            Toast.makeText(this, "password must be min of 8 and max of 15 character", Toast.LENGTH_SHORT).show();
        } else if (!(edt_cpass.getText().toString().equals(edt_pass.getText().toString()))) {
            Toast.makeText(this, "Password not matched", Toast.LENGTH_SHORT).show();
        } else if (CommonUtil.isNullString(edt_cnum.getText().toString())) {
            Toast.makeText(this, "contact number is required", Toast.LENGTH_SHORT).show();
        } else if (edt_cnum.length() < 10 || edt_cnum.length() > 10) {
            Toast.makeText(this, "length must be 10 digit", Toast.LENGTH_SHORT).show();
        }
        else {
            callApiforSignup();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_reg:
                 checkValidation();
                break;



        }
    }

    private  void callApiforSignup()
    {
        new OkHttpRequest(RegisterActivity.this,
                OkHttpRequest.Method.POST,
                ApiManager.USER_SIGNUP,
                RequestParam.userSignUp("" + edt_name.getText().toString(),
                        "" + edt_email.getText().toString(),
                        "" + edt_cpass.getText().toString(),
                        "" + edt_cnum.getText().toString()),
                        RequestParam.getNull(),
                        RequestCode.CODE_USER_SIGNUP,
                true, this);
    }

    @Override
    public void onOkHttpStart(int requestId) {

    }

    @Override
    public void onOkHttpSuccess(int requestId, int statusCode, String response) {
        Log.e(TAG, "Responce:" + response);

        switch (requestId){
            case RequestCode.CODE_USER_SIGNUP:
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
                             PreferenceManager.setPref(RegisterActivity.this, mStrUserId, "USER_ID");
                         }
                         if (root.has("Username"))
                         {
                             String mStrUserName = root.getString("Username");
                             Log.e(TAG,"StrUerName==" + mStrUserName);
                            PreferenceManager.setPref(RegisterActivity.this, mStrUserName, "Username");
                         }
                         if (root.has("email"))
                         {
                             String mStremail = root.getString("email");
                             Log.e(TAG, "mStremail==" + mStremail);
                           PreferenceManager.setPref(RegisterActivity.this, mStremail, "email");
                         }
                         if (root.has("password"))
                         {
                             String mStrpassword = root.getString("password");
                             Log.e(TAG, "mStrpassword==" + mStrpassword);
                             PreferenceManager.setPref(RegisterActivity.this, mStrpassword, "password");
                         }
                         if (root.has("contact_number"))
                         {
                             String mStrcnum = root.getString("contact_number");
                             Log.e(TAG, "mStrcnum==" + mStrcnum);
                             PreferenceManager.setPref(RegisterActivity.this, mStrcnum, "contact_number");
                         }
                         this.runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 Toast.makeText(RegisterActivity.this, "" + responceMessage, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                finish();
                             }
                         });
                     }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    @Override
    public void onOkHttpFailure(int requestId, int statusCode, String response, Throwable error) {

    }

    @Override
    public void onOkHttpFinish(int requestId) {

    }
}

