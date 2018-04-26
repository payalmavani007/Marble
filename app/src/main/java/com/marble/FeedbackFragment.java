package com.marble;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marble.network.ApiManager;
import com.marble.network.RequestCode;
import com.marble.network.RequestParam;
import com.marble.utils.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import hp.harsh.library.interfaces.OkHttpInterface;
import hp.harsh.library.okhttp.OkHttpRequest;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment implements View.OnClickListener,OkHttpInterface{
    private EditText edt_username, edt_email, edt_comment;
    private Button btn_send, btn_cancle;
    private static final String TAG = "FeedbackFragment";
    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        btn_send = (Button) view.findViewById(R.id.btn_send);
        btn_cancle = (Button) view.findViewById(R.id.btn_cancle);
        edt_username = (EditText) view.findViewById(R.id.edt_name);
        edt_email = (EditText) view.findViewById(R.id.edt_email);
        edt_comment = (EditText) view.findViewById(R.id.edt_comment);
        btn_send.setOnClickListener((View.OnClickListener) this);
        btn_cancle.setOnClickListener((View.OnClickListener) this);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Feedback & Support");

    }
    private  void callApiforfeedback()
    {
        new OkHttpRequest(getActivity(),
                OkHttpRequest.Method.POST,
                ApiManager.USER_FEEDBACK,
                RequestParam.userfeedback("" + edt_username.getText().toString(),
                        "" + edt_email.getText().toString(),
                        "" + edt_comment.getText().toString()),
                RequestParam.getNull(),
                RequestCode.CODE_USER_FEEDBACK,
                true, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case (R.id.btn_send):
                Toast.makeText(getActivity(), "Send feedback successfully.", Toast.LENGTH_SHORT).show();
                callApiforfeedback();
                break;
            case (R.id.btn_cancle):
                Intent i1= new Intent(getActivity(),MainActivity.class);
                startActivity(i1);
                Toast.makeText(getActivity(), " you cancle your feedback.", Toast.LENGTH_SHORT).show();
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
            case RequestCode.CODE_USER_FEEDBACK:
                try
                {
                    JSONObject root = new JSONObject("" +response);
                    int responceCode = root.getInt("success");
                    final String responceMessage = root.getString("message");
                    if (responceCode ==  RequestCode.SUCCESS_CODE)
                    {

                        if (root.has("user_name"))
                        {
                            String mStrUserName = root.getString("user_name");
                            Log.e(TAG,"StrUerName==" + mStrUserName);

                        }
                        if (root.has("email"))
                        {
                            String mStremail = root.getString("email");
                            Log.e(TAG, "mStremail==" + mStremail);

                        }
                        if (root.has("comment"))
                        {
                            String strcomment = root.getString("comment");
                            Log.e(TAG, "strcomment==" + strcomment);

                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                edt_username.setText("");
                                edt_email.setText("");
                                edt_comment.setText("");

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
