package com.example.navigation_driver;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.xml.transform.Templates;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private EditText editText;
    private Button button;
    private TextView textView;
    private int counter=0;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView=inflater.inflate(R.layout.fragment_search,container,false);
        editText=rootView.findViewById(R.id.editText1);
        button=rootView.findViewById(R.id.button_search);
        textView=rootView.findViewById(R.id.kitap_ozet);
        final String text=textView.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(counter==0){
                    String criteria = editText.getText().toString();
                    String fullText = textView.getText().toString();

                    if (fullText.contains(criteria)) {
                        int indexOfCriteria = fullText.indexOf(criteria);
                        int lineNumber = textView.getLayout().getLineForOffset(indexOfCriteria);
                        String highlighted = "<font color='red'>" + criteria + "</font>";
                        fullText = fullText.replace(criteria, highlighted);
                        textView.setText(Html.fromHtml(fullText));
                        button.setText("REMOVE HIGHLIGHT");
                        counter++;
                    }
                }
                else {
                    textView.setText(Html.fromHtml(text));
                    button.setText("SEARCH");
                    counter=0;

                }
                }


            });


                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.length() == 0) {
                            String fullText = textView.getText().toString();
                            fullText = fullText.replace("<font color='red'>", "");
                            fullText = fullText.replace("</font>", "");
                            textView.setText(fullText);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });


                // Inflate the layout for this fragment
                return rootView;
            }





}


