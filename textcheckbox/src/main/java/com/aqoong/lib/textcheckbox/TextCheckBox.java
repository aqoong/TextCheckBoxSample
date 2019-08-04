package com.aqoong.lib.textcheckbox;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    /*
        Text : 0 (default)
        Image: 1
     */
    private int mMode;

    /**
     *  Views
     */
    private RelativeLayout      vParent;
    private AppCompatImageView  vImage;
    private AppCompatTextView   vText;
    private CheckBox            vCheckbox;

    /**
     *  Value
     */
    private ColorStateList  mColorListBackground;
    private ColorStateList  mColorListText;


    public TextCheckBox(Context context) {
        this(context, null);
    }
    public TextCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextCheckBox);
        try{
            mDefColor   = ta.getColor(R.styleable.TextCheckBox_defaultColor, R.styleable.TextCheckBox_defaultColor);
            mCheckColor = ta.getColor(R.styleable.TextCheckBox_checkedColor, R.styleable.TextCheckBox_checkedColor);
            mMode       = ta.getInt(R.styleable.TextCheckBox_mode, 0);
        }finally {
            ta.recycle();

            setupView();
            setupData();
        }

    }

    private void setupView(){
        LayoutInflater.from(getContext()).inflate(R.layout.textcheckbox_layout_item, this, true);

        vImage      = findViewById(R.id.textcheckbox_image);
        vText       = findViewById(R.id.textcheckbox_text);
        vCheckbox   = findViewById(R.id.textcheckbox_checkbox);

    }
    private void setupData(){

    }

    private ColorStateList createColorStateList(int color1, int color2){
        int[][] states = new int[][]{
            new int[] {android.R.attr.state_checked},
            new int[] {-android.R.attr.state_checked}
        };
        int[] colors = new int[]{
                ContextCompat.getColor(getContext(), color1),
                ContextCompat.getColor(getContext(), color2)
        };

        return new ColorStateList(states, colors);
    }

}


