package com.moviedb.johan.moviedb.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;

import com.moviedb.johan.moviedb.R;
import com.moviedb.johan.moviedb.viewmodels.SearchViewModel;
import com.moviedb.johan.moviedb.views.SearchView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class SearchActivity extends AppCompatActivity {

    @InjectView(R.id.search_view)SearchView searchView;
    SearchViewModel searchViewModel;

    @InjectView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);

        toolbar.setTitle(getTitle());

        searchViewModel = new SearchViewModel();
        searchViewModel.bind(searchView);


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

    }

}
