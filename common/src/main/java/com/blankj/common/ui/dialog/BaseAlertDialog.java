package com.blankj.common.ui.dialog;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.blankj.common.R;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/03/20
 *     desc  :
 * </pre>
 */
public class BaseAlertDialog extends BaseDialog {

    private TextView tvAlertMessage;
    private OnCreateBuilderListener mListener;

    public void setOnCreateBuildListener(OnCreateBuilderListener listener) {
        mListener = listener;
    }

    @Override
    protected int bindContentLayout() {
        return R.layout.dialog_alert;
    }

    @Override
    protected void build(BaseDialog.Builder builder) {
        if (mListener != null) {
            mListener.onCreateBuilder(builder);
        }
        builder.setBackgroundDrawableResource(R.drawable.shape_dialog)
                .setAlpha(0.6f)
                .setDivide0(Color.WHITE);
    }

    public interface OnCreateBuilderListener {
        void onCreateBuilder(BaseDialog.Builder builder);
    }

    @Override
    protected void setContentView(View contentView) {
        tvAlertMessage = (TextView) contentView.findViewById(R.id.tv_alert_message);
        tvAlertMessage.setText("message");
    }
}
