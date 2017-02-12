package com.ahmaddev.xgram;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.ahmaddev.xgram.Help.Helpers;

public class MiscFragment extends Fragment {

    SwitchCompat mSDisableComment;
    SwitchCompat mSDisableDirect;

    SwitchCompat mSHideCameraIcon;
    SwitchCompat mSHideCameraCenter;

    SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_misc, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sp = getActivity().getSharedPreferences("XGram", Context.MODE_WORLD_READABLE);

        mSDisableComment = (SwitchCompat) view.findViewById(R.id.s_disable_c);
        mSDisableDirect = (SwitchCompat) view.findViewById(R.id.s_disable_d);


        mSDisableComment.setOnCheckedChangeListener(new SwitchCompat.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Helpers.SpIntertBoolean(getActivity(), "disable_comment", true);
                }
                if (!isChecked) {
                    Helpers.SpIntertBoolean(getActivity(), "disable_comment", false);
                }
            }
        });

        mSDisableDirect.setOnCheckedChangeListener(new SwitchCompat.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Helpers.SpIntertBoolean(getActivity(), "disable_direct", true);
                }
                if (!isChecked) {
                    Helpers.SpIntertBoolean(getActivity(), "disable_direct", false);
                }
            }
        });





        mSHideCameraIcon = (SwitchCompat) view.findViewById(R.id.s_hide_video_icon);
        mSHideCameraCenter = (SwitchCompat) view.findViewById(R.id.s_hide_video_center);

        mSHideCameraIcon.setOnCheckedChangeListener(new SwitchCompat.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Helpers.SpIntertBoolean(getActivity(), "hide_camera", true);
                }
                if (!isChecked) {
                    Helpers.SpIntertBoolean(getActivity(), "hide_camera", false);
                }
            }
        });

        mSHideCameraCenter.setOnCheckedChangeListener(new SwitchCompat.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Helpers.SpIntertBoolean(getActivity(), "hide_camera_center", true);
                }
                if (!isChecked) {
                    Helpers.SpIntertBoolean(getActivity(), "hide_camera_center", false);
                }
            }
        });



        returnValues();
    }

    public void returnValues () {
        if (sp.contains("disable_direct")) {
            mSDisableDirect.setChecked(sp.getBoolean("disable_direct", false));
        }

        if (sp.contains("disable_comment")) {
            mSDisableComment.setChecked(sp.getBoolean("disable_comment", false));
        }


        if (sp.contains("hide_camera_center")) {
            mSHideCameraCenter.setChecked(sp.getBoolean("hide_camera_center", false));
        }

        if (sp.contains("hide_camera")) {
            mSHideCameraIcon.setChecked(sp.getBoolean("hide_camera", false));
        }


    }

}

