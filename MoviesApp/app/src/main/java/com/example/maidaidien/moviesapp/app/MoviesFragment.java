package com.example.maidaidien.moviesapp.app;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.maidaidien.moviesapp.app.adapter.MovieAdapter;
import com.example.maidaidien.moviesapp.app.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mai.dai.dien on 10/01/2017.
 */
public class MoviesFragment extends Fragment implements AdapterView.OnItemClickListener {
    private MovieAdapter mMovieAdapter;

    public MoviesFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.movies_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            new FetchMoviesTask().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        mMovieAdapter = new MovieAdapter(getActivity(), new ArrayList<Movie>());
        GridView gridView = (GridView) rootView.findViewById(R.id.movies_gridview);
        gridView.setAdapter(mMovieAdapter);
        gridView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    }

    public class FetchMoviesTask extends AsyncTask<Void, Void, List<Movie>> {
        public final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

        private List<Movie> getMovieDataFromJson(String movieJsonStr) throws JSONException{
            List<Movie> results = new ArrayList<>();
            // These are the names of the JSON objects that need to be extracted.
            final String MDB_RESULT_ARRAY = "results";
            final String MDB_POSTER_PATH = "poster_path";
            final String MDB_ORIGINAL_TITLE = "original_title";
            final String MDB_RATED = "vote_average";
            final String MDB_ID = "id";

            JSONObject forecastJson = new JSONObject(movieJsonStr);
            JSONArray movieArray = forecastJson.getJSONArray(MDB_RESULT_ARRAY);

            int length = movieArray.length();
            for (int index = 0; index < length; index++) {
                Movie movie = new Movie();
                JSONObject movieObject = movieArray.getJSONObject(index);
                movie.setMovieId(movieObject.getInt(MDB_ID));
                movie.setMoviePosterPath(movieObject.getString(MDB_POSTER_PATH));
                movie.setOriginalTitle(movieObject.getString(MDB_ORIGINAL_TITLE));
                movie.setRated(movieObject.getDouble(MDB_RATED));
                results.add(movie);
            }

            return results;
        }

        @Override
        protected List<Movie> doInBackground(Void... voids) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String moviesJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                final String MOVIES_BASE_URL =
                    "https://api.themoviedb.org/3/movie/popular?";
                final String APPID_PARAM = "api_key";

                Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                    .appendQueryParameter(APPID_PARAM, BuildConfig.THE_MOVIE_DB_KEY)
                    .build();

                URL url = new URL(builtUri.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                moviesJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            try {
                return getMovieDataFromJson(moviesJsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error Json: ", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            if (movies == null) return;
            mMovieAdapter.clear();
            mMovieAdapter.addAll(movies);
        }
    }
}
