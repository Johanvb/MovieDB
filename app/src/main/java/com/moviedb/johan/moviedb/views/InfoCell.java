package com.moviedb.johan.moviedb.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moviedb.johan.moviedb.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Johan on 16/09/15.
 */
public class InfoCell extends LinearLayout{

    @InjectView(R.id.key) TextView keyView;
    @InjectView(R.id.value) TextView valueView;

    public InfoCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.info_cell, this);
        ButterKnife.inject(this);

    }


    public void setKey(String key){
        keyView.setText(key);
    }

    public void setValue(String value){
        valueView.setText(value);
    }


}
