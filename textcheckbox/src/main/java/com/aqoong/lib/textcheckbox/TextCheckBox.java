package com.aqoong.lib.textcheckbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * [TextCheckBoxSample]
 * <p>
 * Class: TextCheckBox
 * <p>
 * Created by aqoong on 2019-08-04.
 * - Email  : cooldnjsdn@gmail.com
 * - GitHub : https://github.com/aqoong
 * <p>
 * Description:
 */
public class TextCheckBox extends RelativeLayout {
    /**
     * attrs
     */
    private int mDefColor;
    private int mCheckColor;


    public TextCheckBox(Context context) {
        this(context, null);
    }
    public TextCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextCheckBox);
        try{
            mDefColor   = ta.getColor(R.styleable.TextCheckBox_defaultColor, R.styleable.TextCheckBox_defaultColor);
            mCheckColor = ta.getColor(R.styleable.TextCheckBox_checkedColor, R.styleable.TextCheckBox_checkedColor);
        }finally {
            ta.recycle();

            setupData();
            setupView();
        }

    }

    private void setupView(){

    }
    private void setupData(){

    }

}


