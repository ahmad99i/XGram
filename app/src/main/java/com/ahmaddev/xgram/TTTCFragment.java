package com.ahmaddev.xgram;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.ahmaddev.xgram.Help.Helpers;

/**
 * Created by AhmAd on 1/30/2017.
 */
public class TTTCFragment extends Fragment {

    EditText mEtComments;
    SwitchCompat mSEnable2T2C;

    SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tttc, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp = getActivity().getSharedPreferences("XGram", Context.MODE_WORLD_READABLE);

        mEtComments = (EditText) view.findViewById(R.id.et_comment);
        mSEnable2T2C = (SwitchCompat) view.findViewById(R.id.s_tttc);

        mEtComments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Helpers.SpIntertString(getActivity(),"auto_comms" , String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSEnable2T2C.setOnCheckedChangeListener(new SwitchCompat.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Helpers.SpIntertBoolean(getActivity(), "enable_2t2c", true);
                }
                if (!isChecked) {
                    Helpers.SpIntertBoolean(getActivity(), "enable_2t2c", false);
                }
            }
        });


        returnValues();
    }

    public void returnValues () {
        if (sp.contains("enable_2t2c")) {
            mSEnable2T2C.setChecked(sp.getBoolean("enable_2t2c", false));
        }

        if (sp.contains("auto_comms")) {
            mEtComments.setText(sp.getString("auto_comms",""));
        }
    }

}
