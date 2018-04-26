package com.marble;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FaqFragment extends Fragment {
    Spinner Questios;
    ArrayAdapter<CharSequence> adapter;
    TextView ans;


    public FaqFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        init(view);
        return view;

    }

    private void init(View view) {
        ans = view.findViewById(R.id.ans);
        ans.setVisibility(View.GONE);
        Questios = view.findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Questios,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Questios.setAdapter(adapter);
        Questios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "You selected an question and your answer is here", Toast.LENGTH_SHORT).show();
                String que = parent.getItemAtPosition(position).toString();
                if (que.equals("can i purchase online marble or not?")){
                    ans.setVisibility(View.VISIBLE);
                    ans.setText("no you never purchase");
                } else if (que.equals("can you give the detail of dealer?")){
                    ans.setVisibility(View.VISIBLE);
                    ans.setText("only you can see the information  of dealer");
                } else if (que.equals("can you give the detail of manufrecturer?")){
                    ans.setVisibility(View.VISIBLE);
                    ans.setText("only you can see the information  of manufrecturer ");
                }else if (que.equals("can you give the detail of retailer?")){
                    ans.setVisibility(View.VISIBLE);
                    ans.setText("only you can see the information  of retailer");
                } else if (que.equals("can you give the detail of tredres?")){
                    ans.setVisibility(View.VISIBLE);
                    ans.setText("only you can see the information  of tredres");
                } else if (que.equals("can i shop it online or not?")){
                    ans.setVisibility(View.VISIBLE);
                    ans.setText("no, off course not");
                } else if (que.equals("Can i calculate data easily?")){
                    ans.setVisibility(View.VISIBLE);
                    ans.setText("yes, off course");
                } else {

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
