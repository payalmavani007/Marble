package com.marble;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment {
    private static final String TAG = "CalculatorFragment";
    Dialog dialog;
    Spinner shape;
    Spinner feet;
    EditText editText;
    Spinner currency;
    String curr_syb;
    double tiles_sf;
    ArrayAdapter<CharSequence> adapter;
Context context;
    TextInputLayout length_feet,width_feet,length_inch,width_inch,quantity;

    Button btn_result, btn_reset;

    public CalculatorFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_calculator, container, false);
        init(view);
        return  view;
    }

    private void init(View view) {


        Bundle extras= this.getArguments();
        if (extras != null) {
            String value = extras.getString("price");
            Log.e("price",value);
            editText = view.findViewById(R.id.price_per_square);
            editText.setText(value);
        }

        length_feet = view.findViewById(R.id.length_feet);
        length_inch = view.findViewById(R.id.length_in);
        width_feet = view.findViewById(R.id.width_feet);
        width_inch = view.findViewById(R.id.width_in);
        quantity = view.findViewById(R.id.quantity);

      btn_result = (Button) view.findViewById(R.id.btn_result);
      btn_reset = view.findViewById(R.id.btn_reset);
      dialog = new Dialog(getContext());
      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      dialog.setContentView(R.layout.dialog_calculateresult);
      dialog.setCancelable(true);
        shape = (Spinner)view.findViewById(R.id.spinner1);
        adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Shape,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shape.setAdapter(adapter);
        shape.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "You selected an item shape", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        currency = (Spinner)view.findViewById(R.id.spinner3);
        adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Currency,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currency.setAdapter(adapter);
        currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String curren = parent.getItemAtPosition(position).toString().trim();
                Toast.makeText(getActivity(), "You selected an item currency", Toast.LENGTH_SHORT).show();
                if (curren.equals("$(dollor)")){
                    curr_syb = "$";
                } else if (curren.equals("Rs.(INR)")){
                    curr_syb = "Rs.";
                } else if (curren.equals("£(pound)")){
                    curr_syb = "£";
                }else if (curren.equals("€(euro)")){
                    curr_syb = "€";
                } else {
//                    curr_syb = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        feet = (Spinner)view.findViewById(R.id.spinner2);
        adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Feet,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        feet.setAdapter(adapter);
        feet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String feet = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), "You selected an item "+feet, Toast.LENGTH_SHORT).show();

                if (feet.equals("2*2")){
                    tiles_sf = 4;
                } else if (feet.equals("1*1")){
                    tiles_sf = 1;
                } else if (feet.equals("1.5*1.5")){
                    tiles_sf = 3;
                }else if (feet.equals("3*3")){
                    tiles_sf = 9;
                } else {
                    tiles_sf = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width_feet.getEditText().setText("");
                length_feet.getEditText().setText("");
                width_inch.getEditText().setText("");
                length_inch.getEditText().setText("");
                quantity.getEditText().setText("");
            }
        });
      btn_result.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              TextView total = dialog.findViewById(R.id.total_price);
              TextView sqf_price = dialog.findViewById(R.id.sf_price);
              TextView tiles_total = dialog.findViewById(R.id.tiles_total_no);

              double len_feet = Double.parseDouble(length_feet.getEditText().getText().toString());
              double wid_feet = Double.parseDouble(width_feet.getEditText().getText().toString());
              double len_in = Double.parseDouble(length_inch.getEditText().getText().toString());
              double wid_in = Double.parseDouble(width_inch.getEditText().getText().toString());
              double pri = Double.parseDouble(quantity.getEditText().getText().toString());

              double total_length = len_feet + inchTofeet(len_in);
              double total_width = wid_feet + inchTofeet(wid_in);
              double total_sf = total_length * total_width;
              double total_price = total_length * total_width * pri ;

              String re = String.valueOf(total_price);
              double no_tiles = total_sf / tiles_sf;
              String no = String.valueOf(no_tiles);
              Log.e(TAG, "onClick: " +no );
              tiles_total.setText(no);
              total.setText(re+" "+curr_syb);
              sqf_price.setText(quantity.getEditText().getText().toString());

            dialog.show();
          }
      });
    }

    public double inchTofeet(double inch){
        double result;
        String in = String.valueOf(inch);
        if (in.equals("")){
            result = 0;
        } else {
            result = inch * 0.08333;
        }
        return result;
    }

}
