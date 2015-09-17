package com.moviedb.johan.moviedb.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moviedb.johan.moviedb.R;
import com.moviedb.johan.moviedb.entities.Genre;
import com.moviedb.johan.moviedb.entities.Language;
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

    @InjectView(R.id.movie_image)
    CircleImageView movieView;
    @InjectView(R.id.image_movie_background)
    ImageView movieBackgroundView;
    @InjectView(R.id.movie_tagline)
    TextView movieTagLineView;
    @InjectView(R.id.movie_title)
    TextView movieTitleView;


    @InjectView(R.id.rating_average_cell)
    InfoCell ratingAverageView;
    @InjectView(R.id.rating_count_cell)
    InfoCell ratingCountView;
    @InjectView(R.id.release_date_cell)
    InfoCell releaseDatView;
    @InjectView(R.id.description_cell)
    InfoCell movieDescription;
    @InjectView(R.id.budget_cell)
    InfoCell budgetCell;
    @InjectView(R.id.genres_cell)
    InfoCell genresCell;
    @InjectView(R.id.spoken_languages_cell)
    InfoCell languagesCell;
    @InjectView(R.id.status_cell)
    InfoCell statusCell;


    public MovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_movie, this);
        ButterKnife.inject(this);
    }

    public void setMovieImage(String url, String posterPath) {
        if (posterPath == null || posterPath.isEmpty()) {
            movieView.setVisibility(GONE);
            movieBackgroundView.setVisibility(GONE);

            return;
        }
        url = url + posterPath;

        Picasso.with(getContext()).load(url).into(movieView, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) movieView.getDrawable()).getBitmap();
                setMovieBackground(bitmap);
            }

            @Override
            public void onError() {
                movieView.setVisibility(GONE);
                movieBackgroundView.setVisibility(GONE);
            }
        });

    }

    public void setMovieBackground(Bitmap bitmap) {
        Bitmap blurredImage = BitmapUtils.blurImage(bitmap, 15);
        movieBackgroundView.setImageBitmap(blurredImage);

    }

    public void setMovieTitle(String title) {
        movieTitleView.setText(title);
    }

    public void setTagLine(String tagLine) {
        if (tagLine == null || tagLine.isEmpty()) {
            movieTagLineView.setVisibility(GONE);
            return;
        }
        movieTagLineView.setText(tagLine);
    }


    public void setMovieDescription(String description) {
        if (description == null || description.isEmpty()) {
            movieDescription.setVisibility(GONE);
            return;
        }
        movieDescription.setKey(getContext().getString(R.string.movie_description));
        movieDescription.setValue(description);
    }

    public void setBudget(long budget) {
        if (budget == 0) {
            budgetCell.setVisibility(GONE);
            return;
        }
        budgetCell.setVisibility(VISIBLE);
        budgetCell.setKey(getContext().getString(R.string.movie_budget));
        budgetCell.setValue("$" + budget);
    }

    public void setGenres(Genre[] genres) {
        if (genres == null || genres.length == 0) {
            genresCell.setVisibility(GONE);
            return;
        }
        genresCell.setVisibility(VISIBLE);
        genresCell.setKey(genres.length == 1 ? getContext().getString(R.string.movie_genre) : getContext().getString(R.string.movie_genres));
        genresCell.setValue("$");
    }

    public void setLanguages(Language[] languages) {
        if (languages == null || languages.length == 0) {
            languagesCell.setVisibility(GONE);
            return;
        }
        languagesCell.setVisibility(VISIBLE);
        languagesCell.setKey(getContext().getString(R.string.movie_languages));
        languagesCell.setValue("$");
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            statusCell.setVisibility(GONE);
            return;
        }
        statusCell.setVisibility(VISIBLE);
        statusCell.setKey(getContext().getString(R.string.movie_status));
        statusCell.setValue(status);
    }


    public void setRatingAverage(float ratingAverage) {

        ratingAverageView.setKey(getContext().getString(R.string.rating_average));
        ratingAverageView.setValue("" + ratingAverage);

    }

    public void setRatingCount(int ratingCount) {

        ratingCountView.setKey(getContext().getString(R.string.rating_count));
        ratingCountView.setValue("" + ratingCount);

    }

    public void setReleaseDate(Calendar releaseDate) {

        releaseDatView.setKey(getContext().getString(R.string.rating_count));
        releaseDatView.setValue(CalendarUtils.calendarToString(releaseDate));

    }

}
