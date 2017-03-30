package com.blankj.common.ui.dialog;

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
    private TextView tvLoadingTips;
    private ImageView ivLoadingIc;

    @Override
    protected int bindContentLayout() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void build(Builder builder) {
        builder.setBackgroundDrawableResource(R.color.white_light)
                .setCanceledOnTouchOutside(false);
    }

    @Override
    protected void setContentView(View contentView) {
        contentView.findViewById(R.id.tv_loading_tips);
        tvLoadingTips = (TextView) contentView.findViewById(R.id.tv_loading_tips);
        ivLoadingIc = (ImageView) contentView.findViewById(R.id.iv_loading_ic);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        ivLoadingIc.setAnimation(rotateAnimation);

    }

}
