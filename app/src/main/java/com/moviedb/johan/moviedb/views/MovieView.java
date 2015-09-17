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
    KeyValueCell ratingAverageView;
    @InjectView(R.id.rating_count_cell)
    KeyValueCell ratingCountView;
    @InjectView(R.id.release_date_cell)
    KeyValueCell releaseDateView;
    @InjectView(R.id.description_cell)
    TextCell movieDescription;
    @InjectView(R.id.budget_cell)
    KeyValueCell budgetCell;
    @InjectView(R.id.genres_cell)
    KeyValueCell genresCell;
    @InjectView(R.id.spoken_languages_cell)
    KeyValueCell languagesCell;
    @InjectView(R.id.status_cell)
    KeyValueCell statusCell;
    @InjectView(R.id.revenue_cell)
    KeyValueCell revenueCell;
    @InjectView(R.id.runtime_cell)
    KeyValueCell runtimeCell;

    @InjectView(R.id.header_money)
    HeaderCell moneyHeader;
    @InjectView(R.id.header_rating)
    HeaderCell ratingHeader;
    @InjectView(R.id.header_release)
    HeaderCell releaseHeader;
    @InjectView(R.id.header_summary)
    HeaderCell summaryHeader;

    public MovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_movie, this);
        ButterKnife.inject(this);

        ratingCountView.setKey(getContext().getString(R.string.rating_count));
        releaseDateView.setKey(getContext().getString(R.string.movie_release));
        runtimeCell.setKey(getContext().getString(R.string.movie_runtime));
        revenueCell.setKey(getContext().getString(R.string.movie_revenue));
        budgetCell.setKey(getContext().getString(R.string.movie_budget));
        statusCell.setKey(getContext().getString(R.string.movie_status));
        ratingAverageView.setKey(getContext().getString(R.string.rating_average));

        moneyHeader.setText(getContext().getString(R.string.movie_economy));
        ratingHeader.setText(getContext().getString(R.string.movie_rating));
        releaseHeader.setText(getContext().getString(R.string.movie_release_header));
        summaryHeader.setText(getContext().getString(R.string.movie_summary));

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
        movieDescription.setText(description);
    }

    public void setBudget(long budget) {
        if (budget == 0) {
            budgetCell.setVisibility(GONE);
            return;
        }
        moneyHeader.setVisibility(VISIBLE);
        budgetCell.setVisibility(VISIBLE);
        budgetCell.setValue("$" + budget);
    }

    public void setGenres(Genre[] genres) {
        if (genres == null || genres.length == 0) {
            genresCell.setVisibility(GONE);
            return;
        }
        summaryHeader.setVisibility(VISIBLE);
        genresCell.setVisibility(VISIBLE);
        genresCell.setKey(genres.length == 1 ? getContext().getString(R.string.movie_genre) : getContext().getString(R.string.movie_genres));

        String genresString = "";

        for (int i = 0; i < genres.length; i++) {
            genresString += genres[i].getGenreName() + (i == genres.length - 1 ? "" : "\n");
        }

        genresCell.setValue(genresString);
    }

    public void setLanguages(Language[] languages) {
        if (languages == null || languages.length == 0) {
            languagesCell.setVisibility(GONE);
            return;
        }
        summaryHeader.setVisibility(VISIBLE);
        languagesCell.setVisibility(VISIBLE);
        languagesCell.setKey(languages.length == 1 ? getContext().getString(R.string.movie_language) : getContext().getString(R.string.movie_languages));

        String languagesString = "";

        for (int i = 0; i < languages.length; i++) {
            languagesString += languages[i].getName() + (i == languages.length - 1 ? "" : "\n");
        }

        languagesCell.setValue(languagesString);
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            statusCell.setVisibility(GONE);
            return;
        }
        releaseHeader.setVisibility(VISIBLE);
        statusCell.setVisibility(VISIBLE);
        statusCell.setValue(status);
    }


    public void setRatingAverage(float ratingAverage) {
        if(ratingAverage == 0.0f)return;

        ratingHeader.setVisibility(VISIBLE);
        ratingAverageView.setVisibility(VISIBLE);
        ratingAverageView.setValue("" + ratingAverage);

    }

    public void setRatingCount(int ratingCount) {
        if(ratingCount == 0)return;

        ratingHeader.setVisibility(VISIBLE);
        ratingCountView.setVisibility(VISIBLE);
        ratingCountView.setValue("" + ratingCount);

    }

    public void setReleaseDate(Calendar releaseDate) {

        releaseHeader.setVisibility(VISIBLE);
        releaseDateView.setValue(CalendarUtils.calendarToString(releaseDate));

    }

    public void setRuntime(int runtime) {
        if (runtime == 0) {
            runtimeCell.setVisibility(GONE);
            return;
        }
        summaryHeader.setVisibility(VISIBLE);
        runtimeCell.setVisibility(VISIBLE);
        runtimeCell.setValue(runtime + " min");
    }

    public void setRevenue(long revenue) {
        if (revenue == 0) {
            revenueCell.setVisibility(GONE);
            return;
        }
        moneyHeader.setVisibility(VISIBLE);
        revenueCell.setVisibility(VISIBLE);
        revenueCell.setValue("$" + revenue);
    }
}
