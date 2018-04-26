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
import android.widget.ListView;
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
public class GalleryFragment extends Fragment implements OkHttpInterface {
    RecyclerView recyclerView;
    private TextView textView;
    private static final String TAG = "GalleryFragment";
    ArrayList<CatModel> categoryModelArrayList;
    private CatModel categoryModel;
    String path;
    private CatAdapter categoryAdapter;


    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.lstview);
        textView = (TextView) view.findViewById(R.id.txt_data);
        loadCategory();
    }

    private void loadCategory() {
        new OkHttpRequest(getActivity(),
                OkHttpRequest.Method.GET,
                ApiManager.DISPLAY_CATEGORY,
                RequestCode.CODE_DISPLAY_CATEGORY,
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

                        categoryModelArrayList = new ArrayList<>();

                        if (jsonfatchAllCat.has("path")) {
                            path = jsonfatchAllCat.getString("path");
                        }
                        if (jsonfatchAllCat.has("categories")) {
                            JSONArray jsonArrayData = jsonfatchAllCat.getJSONArray("categories");
                            Log.e(TAG, "" + jsonArrayData.length());
                            for (int i = 0; i < jsonArrayData.length(); i++) {
                                Log.e(TAG, "" + i + "==========");
                                categoryModel = new CatModel();
                                JSONObject dataObject = jsonArrayData.getJSONObject(i);
                                Log.i("contactObject", "++" + dataObject);

                                if (dataObject.has("cat_id")) {
                                    String mid = String.valueOf(dataObject.getInt("cat_id"));
                                    Log.e(TAG, "strid" + mid);
                                    categoryModel.setCat_id(mid);
                                }
                                if (dataObject.has("category_name")) {
                                    String category_name = dataObject.getString("category_name");
                                    Log.e(TAG, "category_name" + category_name);
                                    categoryModel.setCategory_name(category_name);
                                }
                                if (dataObject.has("category_description")) {
                                    String category_description = dataObject.getString("category_description");
                                    Log.e(TAG, "category_description" + category_description);
                                    categoryModel.setGetCategory_description(category_description);
                                }
                                if (dataObject.has("category_price")) {
                                    String category_price = dataObject.getString("category_price");
                                    Log.e(TAG, "category_price" + category_price);
                                    categoryModel.setCategory_price(category_price);
                                }
                                if (dataObject.has("cat_photo")) {
                                    String strimage = dataObject.getString("cat_photo");
                                    strimage = path + "" + strimage;
                                    categoryModel.setCat_photo(strimage);
                                    Log.e(TAG, "image==" + strimage);
                                }
                                categoryModelArrayList.add(categoryModel);
                            }

                        }

                    }
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (categoryModelArrayList.size() > 0) {
                            for (int i = 0; i < categoryModelArrayList.size(); i++) {
                                Log.e("IMAGE" + i + ";", categoryModelArrayList.get(i).getCategory_name());

                            }
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(mLayoutManager);
                            CatAdapter adapter = new CatAdapter(getActivity(), categoryModelArrayList);
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
        getActivity().setTitle("Gallery");
    }

}
