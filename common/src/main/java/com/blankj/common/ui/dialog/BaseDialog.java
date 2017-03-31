package com.blankj.common.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.common.R;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/03/29
 *     desc  :
 * </pre>
 */
public abstract class BaseDialog extends DialogFragment {

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

    public Builder getBuilder() {
        return mBuilder;
    }

    private Builder mBuilder = new Builder();

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
        dialog = new AlertDialog.Builder(mContext)
                .setView(dialogView)
                .create();
        dialog.setOnShowListener(mListener);
        window = dialog.getWindow();
        return dialog;
    }

    private DialogInterface.OnShowListener mListener = new DialogInterface.OnShowListener() {
        @Override
        public void onShow(DialogInterface dialog) {
            setContentView(contentView, mBuilder);
            mBuilder.apply();
        }
    };

    protected abstract int bindContentLayout();


    protected abstract void setContentView(View contentView, Builder builder);

    public class Builder {

        private float widthScale = -1;
        private float heightScale = -1;
        private int width = -1;
        private int height = -1;
        private int gravity = -1;
        private float alpha = -1;
        private Drawable drawable;
        private int resId = -1;
        private boolean cancelable = true;
        private boolean cancel = true;
        private CharSequence title;
        private int titleGravity = -1;
        private int divide0Color = -1;
        private CharSequence positiveText;
        private OnClickListener positiveListener;
        private int divide1Color = -1;
        private CharSequence negativeText;
        private OnClickListener negativeListener;
        private int divide2Color = -1;
        private CharSequence neutralText;
        private OnClickListener neutralListener;

        public Builder setSize(@FloatRange(from = 0, to = 1, fromInclusive = false) float widthScale,
                               @FloatRange(from = 0, to = 1, fromInclusive = false) float heightScale) {
            this.widthScale = widthScale;
            this.heightScale = heightScale;
            return this;
        }

        public Builder setWidth(@FloatRange(from = 0, to = 1, fromInclusive = false) float widthScale) {
            this.widthScale = widthScale;
            return this;
        }

        public Builder setHeight(@FloatRange(from = 0, to = 1, fromInclusive = false) float heightScale) {
            this.heightScale = heightScale;
            return this;
        }

        public Builder setWidth(@IntRange(from = 8) int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(@IntRange(from = 8) int height) {
            this.height = height;
            return this;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setAlpha(@FloatRange(from = 0, to = 1, fromInclusive = false) float alpha) {
            this.alpha = alpha;
            return this;
        }

        public Builder setBackGround(Drawable drawable) {
            this.drawable = drawable;
            return this;
        }

        public Builder setBackgroundDrawableResource(@DrawableRes int resId) {
            this.resId = resId;
            return this;
        }

        public Builder setCanceled(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean cancel) {
            this.cancel = cancel;
            return this;
        }

        public Builder setTitle(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder setTitle(CharSequence title, int gravity) {
            this.title = title;
            this.titleGravity = gravity;
            return this;
        }

        public Builder setDivide0(@ColorInt int color) {
            this.divide0Color = color;
            return this;
        }

        public Builder setPositiveButton(@StringRes int textId, final OnClickListener listener) {
            this.positiveText = mContext.getText(textId);
            this.positiveListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, OnClickListener listener) {
            this.positiveText = text;
            this.positiveListener = listener;
            return this;
        }

        public Builder setDivide1(@ColorInt int color) {
            this.divide1Color = color;
            return this;
        }

        public Builder setNegativeButton(@StringRes int textId, OnClickListener listener) {
            this.negativeText = mContext.getText(textId);
            this.negativeListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            this.negativeText = text;
            this.negativeListener = listener;
            return this;
        }

        public Builder setDivide2(@ColorInt int color) {
            divide2Color = color;
            return this;
        }

        public Builder setNeutralButton(@StringRes int textId, OnClickListener listener) {
            this.negativeText = mContext.getText(textId);
            this.negativeListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence text, OnClickListener listener) {
            this.negativeText = text;
            this.negativeListener = listener;
            return this;
        }

        private void apply() {
            if (widthScale != -1) {
                window.getAttributes().width = (int) (widthScale * getScreenWidth());
            } else if (width != -1) {
                window.getAttributes().width = width;
            }
            if (heightScale != -1) {
                window.getAttributes().height = (int) (heightScale * getScreenHeight());
            } else if (height != -1) {
                window.getAttributes().height = height;
            }
            if (gravity != -1) {
                window.setGravity(gravity);
            }
            if (alpha != -1) {
                window.getAttributes().alpha = alpha;
            }
            if (drawable != null) {
                window.setBackgroundDrawable(drawable);
            }
            if (resId != -1) {
                window.setBackgroundDrawableResource(resId);
            }
            if (!cancelable) {
                setCancelable(false);
            }
            if (!cancel) {
                dialog.setCanceledOnTouchOutside(false);
            }
            if (title != null) {
                tvDialogTitle.setVisibility(View.VISIBLE);
                tvDialogTitle.setText(title);
                if (titleGravity != -1) {
                    tvDialogTitle.setGravity(titleGravity);
                }
            }
            if (divide0Color != -1) {
                viewDivide0.setVisibility(View.VISIBLE);
                viewDivide0.setBackgroundColor(divide0Color);
            }
            if (!isSpace((String) positiveText)) {
                setButton(POSITIVE, positiveText, positiveListener);
            }
            if (divide1Color != -1) {
                viewDivide1.setVisibility(View.VISIBLE);
                viewDivide1.setBackgroundColor(divide1Color);
            }
            if (!isSpace((String) negativeText)) {
                setButton(NEGATIVE, negativeText, negativeListener);
            }
            if (divide2Color != -1) {
                viewDivide2.setVisibility(View.VISIBLE);
                viewDivide2.setBackgroundColor(divide2Color);
            }
            if (!isSpace((String) neutralText)) {
                setButton(NEUTRAL, neutralText, neutralListener);
            }
        }

        private CharSequence message;

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public CharSequence getMessage() {
            return message;
        }

    }

    private final byte POSITIVE = -1;
    private final byte NEGATIVE = -2;
    private final byte NEUTRAL = -3;

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

    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    private int getScreenHeight() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    private static boolean isSpace(String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
