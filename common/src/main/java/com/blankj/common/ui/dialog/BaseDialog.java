package com.blankj.common.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ecarx.thememanager.R;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/03/29
 *     desc  :
 * </pre>
 */
public abstract class BaseDialog extends DialogFragment {

    private static final String TAG = "BaseDialog";
    public static final byte POSITIVE = -1;
    public static final byte NEGATIVE = -2;
    public static final byte NEUTRAL = -3;

    private Context mContext;
    private Window window;
    private Dialog dialog;
    private View dialogView;
    private TextView tvDialogTitle;
    private FrameLayout flDialogContent;
    private View viewDivide0;
    private View contentView;
    private TextView tvDialogNegative;
    private View viewDivide1;
    private TextView tvDialogNeutral;
    private View viewDivide2;
    private TextView tvDialogPositive;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mContext = getActivity();
        dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_base, null);
        tvDialogTitle = (TextView) dialogView.findViewById(R.id.tv_dialog_title);
        flDialogContent = (FrameLayout) dialogView.findViewById(R.id.fl_dialog_content);
        viewDivide0 = dialogView.findViewById(R.id.view_dialog_divide0);
        tvDialogNegative = (TextView) dialogView.findViewById(R.id.tv_dialog_negative);
        viewDivide1 = dialogView.findViewById(R.id.view_dialog_divide1);
        tvDialogNeutral = (TextView) dialogView.findViewById(R.id.tv_dialog_neutral);
        viewDivide2 = dialogView.findViewById(R.id.view_dialog_divide2);
        tvDialogPositive = (TextView) dialogView.findViewById(R.id.tv_dialog_positive);

        contentView = LayoutInflater.from(mContext).inflate(bindContentLayout(), null);
        flDialogContent.addView(contentView);
        build(new Builder());
        setContentView(contentView);
        return dialog;
    }

    protected abstract int bindContentLayout();

    protected abstract void build(Builder builder);

    protected abstract void setContentView(View contentView);

    public class Builder {

        Builder() {
            dialog = new AlertDialog.Builder(mContext)
                    .setView(dialogView)
                    .create();
            window = dialog.getWindow();
            dialog.setContentView(dialogView);
        }

        public Builder setSize(@FloatRange(from = 0, to = 1, fromInclusive = false) float widthScale,
                               @FloatRange(from = 0, to = 1, fromInclusive = false) float heightScale) {
            WindowManager.LayoutParams lp = window.getAttributes();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(dm);
            lp.width = (int) (dm.widthPixels * widthScale);
            lp.height = (int) (dm.heightPixels * heightScale);
            return this;
        }

        public Builder setWidth(@FloatRange(from = 0, to = 1, fromInclusive = false) float widthScale) {
            WindowManager.LayoutParams lp = window.getAttributes();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(dm);
            lp.width = (int) (dm.widthPixels * widthScale);
            return this;
        }

        public Builder setHeight(@FloatRange(from = 0, to = 1, fromInclusive = false) float heightScale) {
            WindowManager.LayoutParams lp = window.getAttributes();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(dm);
            lp.height = (int) (dm.heightPixels * heightScale);
            return this;
        }

        public Builder setWidth(int width) {
            window.getAttributes().width = width;
            return this;
        }

        public Builder setHeight(int height) {
            window.getAttributes().height = height;
            return this;
        }

        public Builder setGravity(int gravity) {
            window.setGravity(gravity);
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean cancel){
            dialog.setCanceledOnTouchOutside(cancel);
            return this;
        }

        public Builder setAlpha(@FloatRange(from = 0, to = 1, fromInclusive = false) float alpha) {
            window.getAttributes().alpha = alpha;
            return this;
        }

        public Builder setBackGround(Drawable drawable) {
            window.setBackgroundDrawable(drawable);
            return this;
        }

        public Builder setBackgroundDrawableResource(@DrawableRes int resId) {
            window.setBackgroundDrawableResource(resId);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            if (!TextUtils.isEmpty(title)) {
                tvDialogTitle.setVisibility(View.VISIBLE);
                tvDialogTitle.setText(title);
            }
            return this;
        }

        public Builder setTitle(CharSequence title, int gravity) {
            if (!TextUtils.isEmpty(title)) {
                tvDialogTitle.setVisibility(View.VISIBLE);
                tvDialogTitle.setText(title);
                tvDialogTitle.setGravity(gravity);
            }
            return this;
        }

        public Builder setDivide0(@ColorInt int color) {
            viewDivide0.setVisibility(View.VISIBLE);
            viewDivide0.setBackgroundColor(color);
            return this;
        }

        public Builder setPositiveButton(@StringRes int textId, final OnClickListener listener) {
            return setPositiveButton(mContext.getText(textId), listener);
        }

        public Builder setPositiveButton(CharSequence text, OnClickListener listener) {
            if (!TextUtils.isEmpty(text)) {
                setButton(POSITIVE, text, listener);
            }
            return this;
        }

        public Builder setDivide1(@ColorInt int color) {
            viewDivide1.setVisibility(View.VISIBLE);
            viewDivide1.setBackgroundColor(color);
            return this;
        }

        public Builder setNegativeButton(@StringRes int textId, OnClickListener listener) {
            return setNegativeButton(mContext.getText(textId), listener);
        }

        public Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            if (!TextUtils.isEmpty(text)) {
                setButton(NEGATIVE, text, listener);
            }
            return this;
        }

        public Builder setDivide2(@ColorInt int color) {
            viewDivide2.setVisibility(View.VISIBLE);
            viewDivide2.setBackgroundColor(color);
            return this;
        }

        public Builder setNeutralButton(@StringRes int textId, OnClickListener listener) {
            return setNeutralButton(mContext.getText(textId), listener);
        }

        public Builder setNeutralButton(CharSequence text, OnClickListener listener) {
            if (!TextUtils.isEmpty(text)) {
                setButton(NEUTRAL, text, listener);
            }
            return this;
        }
    }

    private void setButton(int which, CharSequence text, final OnClickListener listener) {
        TextView tvWhich;
        switch (which) {
            default:
            case POSITIVE:
                tvWhich = tvDialogPositive;
                break;
            case NEGATIVE:
                tvWhich = tvDialogNegative;
                break;
            case NEUTRAL:
                tvWhich = tvDialogNeutral;
                break;
        }
        tvWhich.setVisibility(View.VISIBLE);
        tvWhich.setText(text);
        if (listener == null) return;
        tvWhich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(dialog);
            }
        });
    }

    protected View getDialogView() {
        return dialogView;
    }

    protected View getContentView() {
        return contentView;
    }

    protected Window getWindow() {
        return window;
    }

    public interface OnClickListener {
        void onClick(Dialog dialog);
    }

    protected void show(String tag) {
        show(getActivity().getSupportFragmentManager(), tag);
    }
}
