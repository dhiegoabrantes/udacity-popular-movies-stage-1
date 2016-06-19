package education.udacity.com.popularmovies_stage1;

/**
 * Created by dhiegoabrantes on 19/06/16.
 */
public class TheMovieDBUtils {

    public enum SortBy{
        SORT_BY_RATE, SORT_BY_POPULARITY;
    }

    public static final String SORT_BY_CLAUSE_INTANCE_NAME = "SORT_BY_CLAUSE_INTANCE_NAME";
    public static final String TMDB_IMAGE_BASE_UTL = "http://image.tmdb.org/t/p/";
    public static final String TMDB_IMAGE_SIZE = "w185";

    public static final String API_KEY = "13034cc828e0ddacdb023210d56f8d50";


    public static String buildUrl(String imageFileName){
        return new String(TMDB_IMAGE_BASE_UTL + TMDB_IMAGE_SIZE + imageFileName);
    }

    public static String getPopularMoviesUrl(){
        return new String("https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY);
    }

    public static String getTopRatedMoviesUrl(){
        return new String("https://api.themoviedb.org/3/movie/top_rated?api_key=" + API_KEY);
    }
}
