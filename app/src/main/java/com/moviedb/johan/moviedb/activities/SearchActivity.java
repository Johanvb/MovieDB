package com.moviedb.johan.moviedb.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.moviedb.johan.moviedb.R;
import com.moviedb.johan.moviedb.viewmodels.SearchViewModel;
import com.moviedb.johan.moviedb.views.SearchView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class SearchActivity extends ActionBarActivity {

    @InjectView(R.id.search_view)SearchView searchView;
    SearchViewModel searchViewModel;

    @InjectView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Test");
        ButterKnife.inject(this);

        searchViewModel = new SearchViewModel();
        searchViewModel.bind(searchView);

    }


}
