package com.moviedb.johan.moviedb.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moviedb.johan.moviedb.R;
import com.moviedb.johan.moviedb.utils.BitmapUtils;
import com.moviedb.johan.moviedb.utils.CalendarUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Johan on 16/09/15.
 */
public class MovieView extends LinearLayout {

    @InjectView(R.id.movie_image) CircleImageView movieView;
    @InjectView(R.id.image_movie_background) ImageView movieBackgroundView;
    @InjectView(R.id.movie_title) TextView movieTitle;
    @InjectView(R.id.movie_description) TextView movieDescription;

    @InjectView(R.id.rating_average_cell) InfoCell ratingAverageView;
    @InjectView(R.id.rating_count_cell) InfoCell ratingCountView;
    @InjectView(R.id.language_cell) InfoCell languageView;
    @InjectView(R.id.release_date) InfoCell releaseDatView;


    public MovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_movie, this);
        ButterKnife.inject(this);
    }

    public void setMovieImage(String url, String posterPath) {
        if (posterPath == null || posterPath.isEmpty()){
            movieView.setVisibility(GONE);
            movieBackgroundView.setVisibility(GONE);

            return;
        }
        url = url+posterPath;

        Picasso.with(getContext()).load(url).into(movieView, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable)movieView.getDrawable()).getBitmap();
                setMovieBackground(bitmap);
            }

            @Override
            public void onError() {
                movieView.setVisibility(GONE);
                movieBackgroundView.setVisibility(GONE);
            }
        });

    }

    public void setMovieBackground(Bitmap bitmap){
        Bitmap blurredImage = BitmapUtils.blurImage(bitmap, 15);
        movieBackgroundView.setImageBitmap(blurredImage);

    }

    public void setMovieTitle(String title) {
        movieTitle.setText(title);
    }

    public void setMovieDescription(String description) {
        if(description == null || description.isEmpty()){
            movieDescription.setVisibility(GONE);
            return;
        }
        movieDescription.setText(description);
    }


    public void setRatingAverage(float ratingAverage) {

        ratingAverageView.setKey(getContext().getString(R.string.rating_average));
        ratingAverageView.setValue("" +ratingAverage);

    }

    public void setRatingCount(int ratingCount) {

        ratingCountView.setKey(getContext().getString(R.string.rating_count));
        ratingCountView.setValue("" +ratingCount);

    }

    public void setReleaseDate(Calendar releaseDate) {

        releaseDatView.setKey(getContext().getString(R.string.rating_count));
        releaseDatView.setValue(CalendarUtils.calendarToString(releaseDate));

    }

    public void setLanguage(String originalLanguage) {

        languageView.setKey(getContext().getString(R.string.language));
        languageView.setValue(originalLanguage);

    }
}
