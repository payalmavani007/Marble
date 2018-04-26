package com.marble;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marble.network.ApiManager;
import com.marble.network.RequestCode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hp.harsh.library.interfaces.OkHttpInterface;
import hp.harsh.library.okhttp.OkHttpRequest;


/**
 * A simple {@link Fragment} subclass.
 */
public class RetailerFragment extends Fragment implements OkHttpInterface {
    RecyclerView recyclerView;
    private TextView textView;
    private static final String TAG = "RetailerFragment";
    ArrayList<RelModel> relModelArraylist;
    private RelModel retailerModel;
    String path;


    public RetailerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retailer, container, false);
        init(view);
        return view;

    }

    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recylr1);
        textView = (TextView) view.findViewById(R.id.txt_retailerdata);
        loadCategory();
    }

    private void loadCategory() {
        new OkHttpRequest(getActivity(),
                OkHttpRequest.Method.GET,
                ApiManager.DISPLAY_RETAILER,
                RequestCode.CODE_DISPLAY_RETAILER,
                true, this);
    }

    @Override
    public void onOkHttpStart(int requestId) {
        Log.e(TAG, "onLoopjStart==1" + requestId);
    }

    @Override
    public void onOkHttpSuccess(int requestId, int statusCode, String response) {
        Log.e(TAG, "onLoopjsuccess==1" + response);
        if (response != null) {
            try {
                JSONObject jsonfatchAllCat = new JSONObject(response);
                if (jsonfatchAllCat.has("success")) {
                    int code = jsonfatchAllCat.getInt("success");
                    Log.e(TAG, "code==" + code);
                    if (code == 1) {
                        Log.e(TAG, "code==1" + code);

                        relModelArraylist = new ArrayList<>();

                        if (jsonfatchAllCat.has("path")) {
                            path = jsonfatchAllCat.getString("path");
                        }
                        if (jsonfatchAllCat.has("retailer")) {
                            JSONArray jsonArrayData = jsonfatchAllCat.getJSONArray("retailer");
                            Log.e(TAG, "" + jsonArrayData.length());
                            for (int i = 0; i < jsonArrayData.length(); i++) {
                                Log.e(TAG, "" + i + "==========");
                                retailerModel = new RelModel();
                                JSONObject dataObject = jsonArrayData.getJSONObject(i);
                                Log.i("contactObject", "++" + dataObject);

                                if (dataObject.has("retailer_id")) {
                                    String mid = String.valueOf(dataObject.getInt("retailer_id"));
                                    Log.e(TAG, "strid" + mid);
                                    retailerModel.setRetailer_id(mid);
                                }
                                if (dataObject.has("retailer_name")) {
                                    String retailer_name = dataObject.getString("retailer_name");
                                    Log.e(TAG, "retailer_name" + retailer_name);
                                    retailerModel.setRetailer_name(retailer_name);
                                }
                                if (dataObject.has("retailer_address")) {
                                    String retailer_address = dataObject.getString("retailer_address");
                                    Log.e(TAG, "retailer_address" + retailer_address);
                                    retailerModel.setRetailer_address(retailer_address);
                                }
                                if (dataObject.has("retailer_city")) {
                                    String retailer_city = dataObject.getString("retailer_city");
                                    Log.e(TAG, "retailer_city" + retailer_city);
                                    retailerModel.setRetailer_city(retailer_city);
                                }

                                relModelArraylist.add(retailerModel);
                            }

                        }

                    }
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (relModelArraylist.size() > 0) {
                            for (int i = 0; i < relModelArraylist.size(); i++) {
                                Log.e("IMAGE" + i + ";", relModelArraylist.get(i).getRetailer_name());

                            }
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(mLayoutManager);
                            RelAdapter adapter = new RelAdapter(getContext(), relModelArraylist);
                            recyclerView.setAdapter(adapter);
                            textView.setVisibility(View.GONE);

                        } else {

                        }
                    }
                });
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Retailer");
    }
}
