package com.moviedb.johan.moviedb.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.moviedb.johan.moviedb.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnTextChanged;

/**
 * Created by Johan on 15/09/15.
 */
public class SearchView extends FrameLayout{

    private TextChangedListener listener;

    public interface TextChangedListener {
        void onTextChanged(String textChanged);
    }

    @InjectView(R.id.movie_search_text)
    EditText movieSearchText;
    Context context;


    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_search, this);
        ButterKnife.inject(this);
        this.context = context;
    }

    public void setup(TextChangedListener listener){

        this.listener =  listener;

    }

    @OnTextChanged(R.id.movie_search_text) void onTextChanged(CharSequence text) {
        listener.onTextChanged(text.toString());
    }
}
