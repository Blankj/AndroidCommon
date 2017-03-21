package com.blankj.common.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.common.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/03/15
 *     desc  :
 * </pre>
 */
public abstract class BaseDialog extends DialogFragment {

    private static final String TAG = "BaseDialog";
    public static final byte POSITIVE = -1;
    public static final byte NEGATIVE = -2;
    public static final byte NEUTRAL = -3;

    public static final int START = -1;
    public static final int CENTER = -2;
    public static final int END = -3;

    @IntDef({START, CENTER, END})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gravity {
    }

    private Context mContext;
    protected static final int DEFAULT_COLOR = -1; //default
    private LinearLayout llDialog;
    private TextView tvDialogTitle;
    private TextView tvDialogNegative;
    private TextView tvDialogNeutral;


    private TextView tvDialogPositive;
    private FrameLayout flDialogContent;
    private View dialogView;

    private View contentView;
    private CharSequence mTitle;
    private CharSequence mPositiveButtonText;
    private OnClickListener mPositiveButtonListener;
    private CharSequence mNegativeButtonText;
    private OnClickListener mNegativeButtonListener;
    private CharSequence mNeutralButtonText;
    private OnClickListener mNeutralButtonListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mContext = getActivity();
        dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_base, null);
        llDialog = (LinearLayout) dialogView.findViewById(R.id.ll_dialog);
        tvDialogTitle = (TextView) dialogView.findViewById(R.id.tv_dialog_title);
        flDialogContent = (FrameLayout) dialogView.findViewById(R.id.fl_dialog_content);


        tvDialogNegative = (TextView) dialogView.findViewById(R.id.tv_dialog_negative);
        tvDialogNeutral = (TextView) dialogView.findViewById(R.id.tv_dialog_neutral);
        tvDialogPositive = (TextView) dialogView.findViewById(R.id.tv_dialog_positive);

        contentView = LayoutInflater.from(mContext).inflate(bindContentView(), null);
        flDialogContent.addView(contentView);

        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        Builder builder = new Builder();
        build(builder);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = alertDialog.getWindow();
        window.setBackgroundDrawableResource(R.drawable.shape_white_corner8);

        return alertDialog;
    }

    protected abstract void build(Builder builder);

    protected abstract int bindContentView();

    public class Builder {

        protected Builder setBackGround(Drawable background) {
            llDialog.setBackground(background);
            return this;
        }

        protected Builder setBackgroundColor(@ColorInt int color) {
            llDialog.setBackgroundColor(color);
            return this;
        }

        protected Builder setBackgroundResource(@DrawableRes int resId) {
            llDialog.setBackgroundResource(resId);
            return this;
        }

        protected Builder setTitle(CharSequence title) {
            if (!TextUtils.isEmpty(title)) {
                tvDialogTitle.setVisibility(View.VISIBLE);
                tvDialogTitle.setText(title);
            }
            return this;
        }

        protected Builder setTitle(CharSequence title, @Gravity int gravity) {
            if (!TextUtils.isEmpty(title)) {
                tvDialogTitle.setVisibility(View.VISIBLE);
                tvDialogTitle.setText(title);
                switch (gravity) {
                    case START:
                        tvDialogTitle.setGravity(android.view.Gravity.START);
                        break;
                    case CENTER:
                        tvDialogTitle.setGravity(android.view.Gravity.CENTER);
                        break;
                    case END:
                        tvDialogTitle.setGravity(android.view.Gravity.CENTER);
                        break;
                }
            }
            return this;
        }

        protected Builder setPositiveButton(@StringRes int textId, final OnClickListener listener) {
            return setPositiveButton(mContext.getText(textId), listener);
        }

        protected Builder setPositiveButton(CharSequence text, OnClickListener listener) {
            if (!TextUtils.isEmpty(text)) {
                setButton(POSITIVE, text, listener);
            }
            return this;
        }

        protected Builder setNegativeButton(@StringRes int textId, OnClickListener listener) {
            return setNegativeButton(mContext.getText(textId), listener);
        }

        protected Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            if (!TextUtils.isEmpty(text)) {
                setButton(NEGATIVE, text, listener);
            }
            return this;
        }

        protected Builder setNeutralButton(@StringRes int textId, OnClickListener listener) {
            return setNeutralButton(mContext.getText(textId), listener);
        }

        protected Builder setNeutralButton(CharSequence text, OnClickListener listener) {
            if (!TextUtils.isEmpty(text)) {
                setButton(NEUTRAL, text, listener);
            }
            return this;
        }

        protected Builder setContentView(View contentView) {
            return this;
        }
    }

    private void setButton(int which, CharSequence text, final OnClickListener listener) {
        switch (which) {
            case POSITIVE:
                tvDialogTitle.setVisibility(View.VISIBLE);
                tvDialogPositive.setText(text);
                if (listener == null) return;
                tvDialogPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(getDialog());
                    }
                });
                break;
            case NEGATIVE:
                tvDialogNegative.setVisibility(View.VISIBLE);
                tvDialogNegative.setText(text);
                if (listener == null) return;
                tvDialogNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(getDialog());
                    }
                });
                break;
            case NEUTRAL:
                tvDialogNeutral.setVisibility(View.VISIBLE);
                tvDialogNeutral.setText(text);
                if (listener == null) return;
                tvDialogNeutral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(getDialog());
                    }
                });
                break;
        }
    }

    public interface OnClickListener {
        void onClick(Dialog dialog);
    }
}
