package com.ahmaddev.xgram;

import android.content.res.XResources;
import android.content.res.XResources.DrawableLoader;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AutoCompleteTextView;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import java.util.StringTokenizer;

public class Module implements IXposedHookZygoteInit, IXposedHookInitPackageResources {
    public static String INSTGRAM_PACKAGE_NAME = "com.instagram.android";
    public static String MODULE_PACKAGE_NAME = BuildConfig.APPLICATION_ID;
    static String MODULE_PATH = null;

    XSharedPreferences pref;


    public void initZygote(StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
    }

    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        if (resparam.packageName.equals(INSTGRAM_PACKAGE_NAME)) {
            InitDisableComment(resparam);
            InitDisableDirect(resparam);
            InitHeart(resparam);
            Init2T2C(resparam);
            InitMisc(resparam);
        }
    }

    //Hide heart or Just show the image?
    public void InitHeart (InitPackageResourcesParam resparam) {

        final Drawable heartDrawable = Drawable.createFromPath(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.ahmaddev.xgram/image/heart.png");

        Boolean HIDE_HEART = XSPBoolean("heart_hide", false);
        final Integer HEART_ROTATION = XSPInt("heart_rot", 0);

        if (HIDE_HEART) {
            resparam.res.setReplacement(INSTGRAM_PACKAGE_NAME, "drawable", "feed_like_big", new DrawableLoader() {
                public Drawable newDrawable(XResources res, int id) throws Throwable {
                    return new ColorDrawable(0);
                }
            });
        }
        else  {
            resparam.res.setReplacement(INSTGRAM_PACKAGE_NAME, "drawable", "feed_like_big", new DrawableLoader() {
                public Drawable newDrawable(XResources xResources, int i) throws Throwable {
                    return heartDrawable;
                }
            });

            if (XSPContain("img_rot")) {
                resparam.res.hookLayout(INSTGRAM_PACKAGE_NAME, "layout", "row_feed_media_media_group", new XC_LayoutInflated() {
                    public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                        ( liparam.view.findViewById(liparam.res.getIdentifier("like_heart", "id", Module.INSTGRAM_PACKAGE_NAME))).setRotation(HEART_ROTATION);
                    }
                });
            }
        }
    }

    //Disable Commenting?
    public void InitDisableComment (InitPackageResourcesParam resparam) {

        Boolean DISABLE_COMMENT = XSPBoolean("disable_comment", false);

        if (DISABLE_COMMENT) {
            resparam.res.hookLayout(INSTGRAM_PACKAGE_NAME, "layout", "comment_textview_layout", new XC_LayoutInflated() {
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    ( liparam.view.findViewById(liparam.res.getIdentifier("layout_comment_thread_button_send", "id", Module.INSTGRAM_PACKAGE_NAME))).setEnabled(false);
                }
            });
            resparam.res.hookLayout(INSTGRAM_PACKAGE_NAME, "layout", "comment_textview_layout", new XC_LayoutInflated() {
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    liparam.view.findViewById(liparam.res.getIdentifier("comment_edittext_divider", "id", Module.INSTGRAM_PACKAGE_NAME)).setEnabled(false);
                }
            });
            resparam.res.hookLayout(INSTGRAM_PACKAGE_NAME, "layout", "comment_textview_layout", new XC_LayoutInflated() {
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    ( liparam.view.findViewById(liparam.res.getIdentifier("layout_comment_thread_button_send", "id", Module.INSTGRAM_PACKAGE_NAME))).setEnabled(false);
                }
            });
            resparam.res.hookLayout(INSTGRAM_PACKAGE_NAME, "layout", "comment_textview_layout", new XC_LayoutInflated() {
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    ( liparam.view.findViewById(liparam.res.getIdentifier("layout_comment_thread_edittext", "id", Module.INSTGRAM_PACKAGE_NAME))).setEnabled(false);
                }
            });
            resparam.res.hookLayout(INSTGRAM_PACKAGE_NAME, "layout", "comment_textview_layout", new XC_LayoutInflated() {
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    ( liparam.view.findViewById(liparam.res.getIdentifier("layout_comment_direct_button_send", "id", Module.INSTGRAM_PACKAGE_NAME))).setEnabled(false);
                }
            });
            resparam.res.hookLayout(INSTGRAM_PACKAGE_NAME, "layout", "comment_textview_layout", new XC_LayoutInflated() {
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    liparam.view.findViewById(liparam.res.getIdentifier("direct_button_send_notch", "id", Module.INSTGRAM_PACKAGE_NAME)).setEnabled(false);
                }
            });
        }

    }

    //Disable Directing
    public void InitDisableDirect (InitPackageResourcesParam resparam) {

        Boolean DISABLE_DIRECT = XSPBoolean("disable_direct", false);

        if (DISABLE_DIRECT) {
            resparam.res.hookLayout(INSTGRAM_PACKAGE_NAME, "layout", "direct_row_message_composer", new XC_LayoutInflated() {
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    ( liparam.view.findViewById(liparam.res.getIdentifier("row_thread_composer_button_camera", "id", Module.INSTGRAM_PACKAGE_NAME))).setEnabled(false);
                }
            });
            resparam.res.hookLayout(INSTGRAM_PACKAGE_NAME, "layout", "direct_row_message_composer", new XC_LayoutInflated() {
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    ( liparam.view.findViewById(liparam.res.getIdentifier("row_thread_composer_button_like", "id", Module.INSTGRAM_PACKAGE_NAME))).setEnabled(false);
                }
            });
            resparam.res.hookLayout(INSTGRAM_PACKAGE_NAME, "layout", "direct_row_message_composer", new XC_LayoutInflated() {
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    ( liparam.view.findViewById(liparam.res.getIdentifier("row_thread_composer_edittext", "id", Module.INSTGRAM_PACKAGE_NAME))).setEnabled(false);
                }
            });
            resparam.res.hookLayout(INSTGRAM_PACKAGE_NAME, "layout", "direct_row_message_composer", new XC_LayoutInflated() {
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    ( liparam.view.findViewById(liparam.res.getIdentifier("row_thread_composer_button_send", "id", Module.INSTGRAM_PACKAGE_NAME))).setEnabled(false);
                }
            });
        }

    }

    //Disable Directing
    public void InitMisc (InitPackageResourcesParam resparam) {

        Boolean HIDE_CAMERA = XSPBoolean("hide_camera", false);
        Boolean HIDE_CAMERA_CENTER = XSPBoolean("hide_camera_center", false);

        if (HIDE_CAMERA) {
            resparam.res.setReplacement(INSTGRAM_PACKAGE_NAME, "drawable", "feed_camera", new DrawableLoader() {
                public Drawable newDrawable(XResources res, int id) throws Throwable {
                    return new ColorDrawable(Color.TRANSPARENT);
                }
            });
        }
        if (HIDE_CAMERA_CENTER) {
            resparam.res.setReplacement(INSTGRAM_PACKAGE_NAME, "drawable", "feed_camera_center", new DrawableLoader() {
                public Drawable newDrawable(XResources res, int id) throws Throwable {
                    return new ColorDrawable(Color.TRANSPARENT);
                }
            });
        }

    }


    AutoCompleteTextView mEtComment;
    String comms = XSPString("auto_comms", "");
    StringTokenizer tokens = new StringTokenizer(this.comms, ",");
    public void Init2T2C (InitPackageResourcesParam resparam) {

        Boolean ENABLE_2T2C = XSPBoolean("enable_2t2c", false);

        if (ENABLE_2T2C) {

            resparam.res.hookLayout(INSTGRAM_PACKAGE_NAME, "layout", "comment_textview_layout", new XC_LayoutInflated() {
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    mEtComment = (AutoCompleteTextView) liparam.view.findViewById(liparam.res.getIdentifier("layout_comment_thread_edittext", "id", Module.INSTGRAM_PACKAGE_NAME));
                    final GestureDetector gestureDetector = new GestureDetector(new SimpleOnGestureListener() {
                        public boolean onDoubleTap(MotionEvent e) {
                            if (tokens.hasMoreTokens()) {
                                mEtComment.setText(tokens.nextToken());
                            } else {
                                resetTokens();
                                mEtComment.setText(tokens.nextToken());
                            }
                            return true;
                        }
                        public void onLongPress(MotionEvent e) {
                            resetTokens();
                            mEtComment.setText(tokens.nextToken());

                        }
                    });
                    mEtComment.setOnTouchListener(new OnTouchListener() {
                        public boolean onTouch(View v, MotionEvent event) {
                            return gestureDetector.onTouchEvent(event);
                        }
                    });
                }
            });
        }


    }
    public void resetTokens() {
        this.tokens = new StringTokenizer(this.comms, ",");
    }



    //Needs

    public String XSPString(String value, String defValue) {
        pref = new XSharedPreferences(MODULE_PACKAGE_NAME, "XGram");
        pref.reload();
        pref.makeWorldReadable();
        return pref.getString(value, defValue);
    }
    public Boolean XSPBoolean(String value, Boolean defValue) {
        pref = new XSharedPreferences(MODULE_PACKAGE_NAME, "XGram");
        pref.reload();
        pref.makeWorldReadable();
        return pref.getBoolean(value, defValue);
    }
    public Integer XSPInt(String value, Integer defValue) {
        pref = new XSharedPreferences(MODULE_PACKAGE_NAME, "XGram");
        pref.reload();
        pref.makeWorldReadable();
        return pref.getInt(value, defValue);
    }

    public Boolean XSPContain(String value) {
        pref = new XSharedPreferences(MODULE_PACKAGE_NAME, "XGram");
        pref.reload();
        pref.makeWorldReadable();
        return pref.contains(value);
    }
}