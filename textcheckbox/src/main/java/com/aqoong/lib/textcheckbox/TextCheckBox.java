package com.aqoong.lib.textcheckbox;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
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
    public static final int    MODE_TEXT   = 0;
    public static final int    MODE_IMAGE  = 1;
    private int mStrokeWidth;
    private int mImageRes;
    private String  mText;

    /**
     *  Views
     */
    private AppCompatImageView  vImage;
    private AppCompatTextView   vText;
    private CheckBox            vCheckbox;

    private OnCheckedListener   checkedListener;



    public TextCheckBox(Context context) {
        this(context, null);
    }
    public TextCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextCheckBox);
        try{
            mDefColor   = ta.getColor(R.styleable.TextCheckBox_defaultColor, ContextCompat.getColor(getContext(), R.color.textcheckbox_default_color));
            mCheckColor = ta.getColor(R.styleable.TextCheckBox_checkedColor, ContextCompat.getColor(getContext(), R.color.textcheckbox_checked_color));
            mMode       = ta.getInt(R.styleable.TextCheckBox_checkMode, 0);
            mStrokeWidth= ta.getDimensionPixelSize(R.styleable.TextCheckBox_strokeWidth, dpToPx(getContext(), 1));
            mImageRes   = ta.getResourceId(R.styleable.TextCheckBox_src, R.drawable.ic_check);
            mText       = ta.getString(R.styleable.TextCheckBox_text);
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

        setImage(mImageRes);
        setColor(mCheckColor, mDefColor);
        setText(mText);

        vCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                vText.setTextColor(isChecked ? mDefColor : mCheckColor);
                if(checkedListener != null) {
                    checkedListener.OnChecked(isChecked);
                }
            }
        });

        setMode(mMode);
    }

    public void setMode(int mode){
        mMode = mode;
        switch(mMode){
            case MODE_TEXT:         //text
                vText.setVisibility(VISIBLE);
                vImage.setVisibility(INVISIBLE);
                break;
            case MODE_IMAGE:         //image
                vText.setVisibility(INVISIBLE);
                vImage.setVisibility(VISIBLE);
                break;
        }
    }

    public void setColor(int checkedColor, int defaultColor){
        mCheckColor = checkedColor;
        mDefColor   = defaultColor;

        GradientDrawable drawable = (GradientDrawable) vCheckbox.getBackground().getCurrent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.setStroke(mStrokeWidth, createColorStateList(mCheckColor, mCheckColor));
            drawable.setColor(createColorStateList(mCheckColor, mDefColor));
            vCheckbox.setBackground(drawable);
        }
        vText.setTextColor(mCheckColor);
    }

    public void setText(String text){
        mText = text;
        vText.setText(text);
    }

    public String getText(){
        return vText.getText().toString();
    }

    /**
     * Bitmap, Drawable, Integer(resource), Uri
     * @param image
     */
    public void setImage(Object image){

        if (image instanceof Bitmap) {
            vImage.setImageBitmap((Bitmap) image);
        } else if (image instanceof Drawable) {
            vImage.setImageDrawable((Drawable) image);
        } else if (image instanceof Integer) {
            vImage.setImageResource((int) image);
        } else if (image instanceof Uri) {
            vImage.setImageURI((Uri) image);
        } else {

            Log.e("TextCheckBox", "Image setting error. check image type.");
        }
    }

    public AppCompatImageView getImageView(){
        return vImage;
    }

    public boolean isChecked(){
        return vCheckbox.isChecked();
    }

    private ColorStateList createColorStateList(int checkedColor, int defaultColor){
        int[][] states = new int[][]{
            new int[] {android.R.attr.state_checked},
            new int[] {-android.R.attr.state_checked}
        };
        int[] colors = new int[]{
                checkedColor,
                defaultColor
        };

        return new ColorStateList(states, colors);
    }

    private int dpToPx(Context context, float dp){
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return px;
    }

    public boolean setCheck(boolean check){
        vCheckbox.setChecked(check);
        return check;
    }

    public void setCheck(boolean check, Object image){
        if(setCheck(check)){
            setImage(image);
            vImage.setVisibility(VISIBLE);
        }else{
            vImage.setVisibility(INVISIBLE);
        }
    }
    public void setCheck(boolean check, String text){
        if(setCheck(check)) {
            setText(text);
            vText.setVisibility(VISIBLE);
        }else{
            vText.setVisibility(INVISIBLE);
        }
    }

    public void setOnCheckedListener(OnCheckedListener listener){
        this.checkedListener    = listener;
    }
}


