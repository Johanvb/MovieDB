package com.moviedb.johan.moviedb.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.moviedb.johan.moviedb.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Johan on 15/09/15.
 */
public class SearchView extends FrameLayout{

    @InjectView(R.id.movie_search_text)
    EditText movieSearchText;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);

//        ButterKnife.inject(this);
    }

    public void setText(String text) {

    }
}
