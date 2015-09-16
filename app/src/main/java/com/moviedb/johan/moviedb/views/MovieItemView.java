package com.moviedb.johan.moviedb.views;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.moviedb.johan.moviedb.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Johan on 15/09/15.
 */
public class MovieItemView extends RecyclerView.ViewHolder{

    TextView movieTitle;
    TextView movieYear;
    TextView movieRating;

    CircleImageView movieImage;
    View itemView;


    public MovieItemView(View itemView) {
        super(itemView);
        this.itemView = itemView;
        movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
        movieYear = (TextView) itemView.findViewById(R.id.movie_year);
        movieRating = (TextView) itemView.findViewById(R.id.movie_rating);

        movieImage = (CircleImageView) itemView.findViewById(R.id.movie_image);
    }


    public void setTitleText(String title) {
        movieTitle.setText(title);
    }

    public void setMovieYear(String year) {
        movieYear.setText(year);
    }

    public void setMovieRating(float rating) {
        movieRating.setText("" + rating);
    }

    public void setImageFromUrl(final String url) throws IOException {

        Picasso.with(itemView.getContext()).load(url).noFade().into(movieImage, new Callback() {
            @Override
            public void onSuccess() {
                Animator fadeInAnim = AnimatorInflater.loadAnimator(movieImage.getContext(), R.animator.fade_in);
                fadeInAnim.setTarget(movieImage);
                fadeInAnim.start();
            }

            @Override
            public void onError() {

            }
        });

    }


    public void setOnClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
    }
}
