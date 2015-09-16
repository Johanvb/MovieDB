package com.moviedb.johan.moviedb.views;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
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

    @InjectView(R.id.progress_bar)
    ProgressBar loadingIndicator;

    @InjectView(R.id.error_message)
    TextView errorMessageView;

    @InjectView(R.id.layoutRefresh)
    SwipeRefreshLayout layoutRefresh;

    Context context;
    private TextChangedListener listener;
    LinearLayoutManager layoutManager;


    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_search, this);
        ButterKnife.inject(this);
        this.context = context;
        layoutRefresh.setEnabled(false);

    }

    public void setup(TextChangedListener listener, RecyclerView.Adapter mAdapter){
        this.listener =  listener;
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        moviesList.setLayoutManager(layoutManager);
        moviesList.setAdapter(mAdapter);

        showSearchMessage();
    }

    @OnTextChanged(R.id.movie_search_text) void onTextChanged(CharSequence text) {
        listener.onTextChanged(text.toString());
    }

    public void showSearchMessage() {
        errorMessageView.setText(getContext().getString(R.string.type_movie_message));
        errorMessageView.setVisibility(VISIBLE);

        Animator fadeInAnim = AnimatorInflater.loadAnimator(errorMessageView.getContext(), R.animator.fade_in);
        fadeInAnim.setTarget(errorMessageView);
        fadeInAnim.start();
    }

    public interface TextChangedListener {
        void onTextChanged(String textChanged);
    }

    public void setupRefresh(SwipeRefreshLayout.OnRefreshListener onRefreshListener){
        layoutRefresh.setProgressBackgroundColor(R.color.green_dark);
        layoutRefresh.setColorSchemeResources(R.color.background_white, R.color.green_light);
        layoutRefresh.setOnRefreshListener(onRefreshListener);
    }

    public void setRefreshable(boolean refreshable){
        layoutRefresh.setEnabled(refreshable);
    }

    public void setRefreshing(boolean b) {
        layoutRefresh.setRefreshing(b);
    }

    public void setLoading(boolean isLoading){

        if(loadingIndicator.getVisibility() == VISIBLE && isLoading){
            return;
        }
        loadingIndicator.setVisibility(isLoading ? VISIBLE : INVISIBLE);

        Animator fadeInAnim = AnimatorInflater.loadAnimator(loadingIndicator.getContext(), R.animator.fade_in);
        fadeInAnim.setTarget(loadingIndicator);
        fadeInAnim.start();

    }

    public void setErrorMessage(boolean showError){
        if(showError){
            errorMessageView.setText(getContext().getString(R.string.movie_loading_error));
            errorMessageView.setVisibility(VISIBLE);

            Animator fadeInAnim = AnimatorInflater.loadAnimator(errorMessageView.getContext(), R.animator.fade_in);
            fadeInAnim.setTarget(errorMessageView);
            fadeInAnim.start();
        }else{
            errorMessageView.setVisibility(INVISIBLE);
        }

    }


}
