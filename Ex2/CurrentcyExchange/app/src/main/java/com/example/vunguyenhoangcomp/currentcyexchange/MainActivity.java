package com.example.vunguyenhoangcomp.currentcyexchange;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText number;
    private TextView textView;
    private RadioGroup radioGroup;
    private Button button;
    private int id = 0;
    //id cuả item
    //2131230850
    //2131230925
    //2131230912


    private float change(float number,int order){
        float result = 0;
        boolean out=false;
        switch (order){
            case 0:
                result = number/22800;
                break;
            case 2131230851://usd -> vnd
                result = number*22.8f ;
                break;

            case 2131230926://usd -> eur
                result = number*0.81f;
                break;
            case 2131230913://eur -> usd
                result = number*1.23f;
                break;

            case 2131230795://vnd -> eur
                result = (number/1000)*0.04f;
                break;
            case 2131230792://eur -> vnd
                result = number*28000;
                break;
            default:
                break;
        }
        return result;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number =    findViewById(R.id.number);
        textView =  findViewById(R.id.result);
        button =    findViewById(R.id.change);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,button);
                popupMenu.getMenuInflater().inflate(R.menu.menu_pop_up,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        button.setText(menuItem.getTitle().toString());

                        id = menuItem.getItemId();
                        if(!number.getText().toString().equals("")){
                            textView.setText(
                                    String.valueOf(
                                            change(
                                                    Float.parseFloat(number.getText().toString()),
                                                    id
                                                    )
                                    )
                            );
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

//        int selecradiobtn = radioGroup.getCheckedRadioButtonId();
//        Log.e("Eror","this is id: "+selecradiobtn);

        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value = editable.toString();
                if(value.equals("")){
                    number.setText("");
                    textView.setText("");
                }else {
                    textView.setText(String.valueOf(change(Float.parseFloat(value),id)));

                }
                //cơ bản là đã xong h chỉ cần chỉnh sửa lại tỷ lệ hối đoái của các loại tiền
            }
        });


    }
}
