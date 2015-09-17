package com.moviedb.johan.moviedb.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moviedb.johan.moviedb.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Johan on 17/09/15.
 */
public class HeaderCell extends LinearLayout{


    @InjectView(R.id.header_text)
    TextView headerCell;

    public HeaderCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.header_cell, this);
        ButterKnife.inject(this);

    }

    public void setText(String text){
        headerCell.setText(text);
    }




}
