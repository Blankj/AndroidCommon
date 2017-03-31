package com.blankj.common.ui.dialog;

import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
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
public class LoadingDialog extends BaseDialog {

    @Override
    protected int bindContentLayout() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void setContentView(View contentView, Builder builder) {
        builder.setBackgroundDrawableResource(R.drawable.shape_dialog)
                .setCanceled(false)
                .setWidth(0.4f);

        TextView tvLoadingMessage = (TextView) contentView.findViewById(R.id.tv_loading_message);
        ImageView ivLoadingIc = (ImageView) contentView.findViewById(R.id.iv_loading_ic);

        CharSequence msg = builder.getMessage();
        if (!TextUtils.isEmpty(msg)) {
            tvLoadingMessage.setText(msg);
        }

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        ivLoadingIc.setAnimation(rotateAnimation);
    }


}
