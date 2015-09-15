package com.moviedb.johan.moviedb.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.moviedb.johan.moviedb.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnTextChanged;

/**
 * Created by Johan on 15/09/15.
 */
public class SearchView extends FrameLayout{

    @InjectView(R.id.movie_search_text)
    EditText movieSearchText;

    @InjectView(R.id.list_results)
    RecyclerView moviesList;

    Context context;
    private TextChangedListener listener;
    LinearLayoutManager layoutManager;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_search, this);
        ButterKnife.inject(this);
        this.context = context;
    }

    public void setup(TextChangedListener listener, RecyclerView.Adapter mAdapter){
        this.listener =  listener;
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        moviesList.setLayoutManager(layoutManager);

        moviesList.setAdapter(mAdapter);
    }

    @OnTextChanged(R.id.movie_search_text) void onTextChanged(CharSequence text) {
        listener.onTextChanged(text.toString());
    }

    public interface TextChangedListener {
        void onTextChanged(String textChanged);
    }

}
