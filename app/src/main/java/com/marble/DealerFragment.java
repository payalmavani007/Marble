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
public class DealerFragment extends Fragment implements OkHttpInterface {
    RecyclerView recyclerView;
    private TextView textView;
    private static final String TAG = "DealerFragment";
    ArrayList<DelModel> delModelArraylist;
    private DelModel dealerModel;
    String path;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dealer, container, false);
        init(view);
        return view;

    }

    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recylr2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        textView = (TextView) view.findViewById(R.id.txt_dealerdata);
        loadCategory();
    }

    private void loadCategory() {
        new OkHttpRequest(getActivity(),
                OkHttpRequest.Method.GET,
                ApiManager.DISPLAY_DEALER,
                RequestCode.CODE_DISPLAY_DEALER,
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

                        delModelArraylist = new ArrayList<>();
                        if (jsonfatchAllCat.has("dealer")) {
                            JSONArray jsonArrayData = jsonfatchAllCat.getJSONArray("dealer");
                            Log.e(TAG, "" + jsonArrayData.length());
                            for (int i = 0; i < jsonArrayData.length(); i++) {
                                Log.e(TAG, "" + i + "==========");
                                dealerModel = new DelModel();
                                JSONObject dataObject = jsonArrayData.getJSONObject(i);
                                Log.i("contactObject", "++" + dataObject);

                                /*if (dataObject.has("dealer_id")) {
                                    String mid = String.valueOf(dataObject.getInt("dealer_id"));
                                    Log.e(TAG, "strid" + mid);
                                    dealerModel.setDealer_id(mid);
                                }
                                */
                                if (dataObject.has("dealer_name")) {
                                    String dealer_name = dataObject.getString("dealer_name");
                                    Log.e(TAG, "dealer_name" + dealer_name);
                                    dealerModel.setDealer_name(dealer_name);
                                }
                                if (dataObject.has("dealer_address")) {
                                    String dealer_address = dataObject.getString("dealer_address");
                                    Log.e(TAG, "dealer_address" + dealer_address);
                                    dealerModel.setDealer_address(dealer_address);
                                }
                                if (dataObject.has("dealer_city")) {
                                    String dealer_city = dataObject.getString("dealer_city");
                                    Log.e(TAG, "dealer_city" + dealer_city);
                                    dealerModel.setDealer_city(dealer_city);
                                }
                                delModelArraylist.add(dealerModel);
                            }

                        }

                    }
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (delModelArraylist.size() > 0) {
                            for (int i = 0; i < delModelArraylist.size(); i++) {
                                Log.e("IMAGE" + i + ";", delModelArraylist.get(i).getDealer_name());
                            }
                            DelAdapter adapter = new DelAdapter(getContext(), delModelArraylist);
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
        getActivity().setTitle("Dealer");
    }
}
