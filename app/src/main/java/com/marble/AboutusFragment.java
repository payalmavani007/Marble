package com.marble;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutusFragment extends Fragment {
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8;
    Button btn_1, btn_2;


    public AboutusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate(R.layout.fragment_aboutus, container, false);
        btn_1=view.findViewById(R.id.btn_vision);
        btn_2=view.findViewById(R.id.btn_functionality);
        txt1=view.findViewById(R.id.title);
        txt2=view.findViewById(R.id.about_title);
        txt3=view.findViewById(R.id.about_title2);
        txt4=view.findViewById(R.id.about_title3);
        txt5=view.findViewById(R.id.about_title4);
        txt6=view.findViewById(R.id.Development_Purpose);
        txt7=view.findViewById(R.id.detail);
        txt8=view.findViewById(R.id.thank_you);
        return  view;
    }

}
