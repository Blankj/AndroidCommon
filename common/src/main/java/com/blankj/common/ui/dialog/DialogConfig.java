package com.blankj.common.ui.dialog;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/03/16
 *     desc  :
 * </pre>
 */
public class DialogConfig {

    public static final int NEGATIVE = -1;
    public static final int NEUTRAL = 0;
    public static final int POSITIVE = 1;

    @IntDef({NEGATIVE, NEUTRAL, POSITIVE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Action {
    }

    public static final int START = -1;
    public static final int CENTER = 0;
    public static final int END = 1;

    @IntDef({START, CENTER, END})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gravity {
    }


}
