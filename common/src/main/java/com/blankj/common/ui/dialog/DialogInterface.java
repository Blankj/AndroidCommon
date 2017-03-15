package com.blankj.common.ui.dialog;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/03/15
 *     desc  :
 * </pre>
 */
public interface DialogInterface {

    interface OnLeftAndRightClickListener<T> {

        void clickLeftButton(T dialog, View view);

        void clickRightButton(T dialog, View view);
    }

    interface OnSingleClickListener<T> {

        void clickSingleButton(T dialog, View view);
    }

    interface OnItemClickListener<T> {

        void onItemClick(T dialog, View button, int position);
    }
}
