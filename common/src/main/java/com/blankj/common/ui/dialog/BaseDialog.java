package com.blankj.common.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.common.R;

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

    private Context mContext;
    protected static final int DEFAULT_COLOR = -1; //default
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
        tvDialogTitle = (TextView) dialogView.findViewById(R.id.tv_dialog_title);
        tvDialogNegative = (TextView) dialogView.findViewById(R.id.tv_dialog_negative);
        tvDialogNeutral = (TextView) dialogView.findViewById(R.id.tv_dialog_neutral);
        tvDialogPositive = (TextView) dialogView.findViewById(R.id.tv_dialog_positive);
        flDialogContent = (FrameLayout) dialogView.findViewById(R.id.fl_dialog_content);

        contentView = LayoutInflater.from(mContext).inflate(bindContentView(), null);
        flDialogContent.addView(contentView);

        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        Builder builder = new Builder();
        build(builder);
        builder.build();

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        return alertDialog;
    }

    protected abstract void build(Builder builder);


    protected abstract int bindContentView();

    public class Builder {

        byte mask = 0;

        protected Builder setTitle(CharSequence title) {
            if (TextUtils.isEmpty(title)) {
                tvDialogTitle.setVisibility(View.GONE);
            } else {
                tvDialogTitle.setText(title);
            }
            return this;
        }

        protected Builder setPositiveButton(@StringRes int textId, final OnClickListener listener) {
            return setPositiveButton(mContext.getText(textId), listener);
        }

        protected Builder setPositiveButton(CharSequence text, OnClickListener listener) {
            if (!TextUtils.isEmpty(text)) {
                setButton(POSITIVE, text, listener);
                mask |= 0x01;
            }
            return this;
        }

        protected Builder setNegativeButton(@StringRes int textId, OnClickListener listener) {
            return setNegativeButton(mContext.getText(textId), listener);
        }

        protected Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            if (!TextUtils.isEmpty(text)) {
                setButton(NEGATIVE, text, listener);
                mask |= 0x02;
            }
            return this;
        }

        protected Builder setNeutralButton(@StringRes int textId, OnClickListener listener) {
            return setNeutralButton(mContext.getText(textId), listener);
        }

        protected Builder setNeutralButton(CharSequence text, OnClickListener listener) {
            if (!TextUtils.isEmpty(text)) {
                setButton(NEUTRAL, text, listener);
                mask |= 0x04;
            }
            return this;
        }

        protected Builder customContentView() {

            return this;
        }

        private void build() {
            int i = 0;
            if ((mask & 0x03) == 0x03){

            }
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
