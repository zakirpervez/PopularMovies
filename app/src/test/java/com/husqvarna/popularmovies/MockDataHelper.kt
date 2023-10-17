package com.husqvarna.popularmovies

import com.google.gson.Gson
import com.husqvarna.popularmovies.api.models.response.MovieDetailsResponse
import com.husqvarna.popularmovies.api.models.response.PopularMoviesResponse

object MockDataHelper {
    private const val POPULAR_MOVIE_RESPONSE = "{\n" +
        "  \"page\": 1,\n" +
        "  \"results\": [\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/628Dep6AxEtDxjZoGP78TsOxYbK.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        28,\n" +
        "        53\n" +
        "      ],\n" +
        "      \"id\": 575264,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Mission: Impossible - Dead Reckoning Part One\",\n" +
        "      \"overview\": \"Ethan Hunt and his IMF team embark on their most dangerous mission yet: To track down a terrifying new weapon that threatens all of humanity before it falls into the wrong hands. With control of the future and the world's fate at stake and dark forces from Ethan's past closing in, a deadly race around the globe begins. Confronted by a mysterious, all-powerful enemy, Ethan must consider that nothing can matter more than his mission—not even the lives of those he cares about most.\",\n" +
        "      \"popularity\": 4167.922,\n" +
        "      \"poster_path\": \"/NNxYkU70HPurnNCSiCjYAmacwm.jpg\",\n" +
        "      \"release_date\": \"2023-07-08\",\n" +
        "      \"title\": \"Mission: Impossible - Dead Reckoning Part One\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 7.7,\n" +
        "      \"vote_count\": 1621\n" +
        "    },\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/cHNqobjzfLj88lpIYqkZpecwQEC.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        28,\n" +
        "        53,\n" +
        "        80,\n" +
        "        18\n" +
        "      ],\n" +
        "      \"id\": 926393,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"The Equalizer 3\",\n" +
        "      \"overview\": \"Robert McCall finds himself at home in Southern Italy but he discovers his friends are under the control of local crime bosses. As events turn deadly, McCall knows what he has to do: become his friends' protector by taking on the mafia.\",\n" +
        "      \"popularity\": 2755.58,\n" +
        "      \"poster_path\": \"/b0Ej6fnXAP8fK75hlyi2jKqdhHz.jpg\",\n" +
        "      \"release_date\": \"2023-08-30\",\n" +
        "      \"title\": \"The Equalizer 3\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 7.3,\n" +
        "      \"vote_count\": 880\n" +
        "    }\n" +
        "  ],\n" +
        "  \"total_pages\": 1,\n" +
        "  \"total_results\": 2\n" +
        "}"

    const val MOVIE_DETAILS_RESPONSE = "{\n" +
        "    \"adult\": false,\n" +
        "    \"backdrop_path\": \"/iEFuHjqrE059SmflBva1JzDJutE.jpg\",\n" +
        "    \"belongs_to_collection\": null,\n" +
        "    \"budget\": 86000000,\n" +
        "    \"genres\": [\n" +
        "        {\n" +
        "            \"id\": 16,\n" +
        "            \"name\": \"Animation\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 10751,\n" +
        "            \"name\": \"Family\"\n" +
        "        }\n" +
        "    ],\n" +
        "    \"homepage\": \"https://www.netflix.com/title/81383247\",\n" +
        "    \"id\": 496450,\n" +
        "    \"imdb_id\": \"tt10364034\",\n" +
        "    \"original_language\": \"fr\",\n" +
        "    \"original_title\": \"Miraculous - le film\",\n" +
        "    \"overview\": \"A life of an ordinary Parisian teenager Marinette goes superhuman when she becomes Ladybug. Bestowed with magical powers of creation, Ladybug must unite with her opposite, Cat Noir, to save Paris as a new villain unleashes chaos unto the city.\",\n" +
        "    \"popularity\": 237.34,\n" +
        "    \"poster_path\": \"/bBON9XO9Ek0DjRwMBnJNCwC96Cd.jpg\",\n" +
        "    \"production_companies\": [\n" +
        "        {\n" +
        "            \"id\": 84713,\n" +
        "            \"logo_path\": \"/jrdDt2pV5OF6f4JLOhaGUMT3QO9.png\",\n" +
        "            \"name\": \"ON Animation Studios\",\n" +
        "            \"origin_country\": \"FR\"\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 140008,\n" +
        "            \"logo_path\": null,\n" +
        "            \"name\": \"The Awakening Production\",\n" +
        "            \"origin_country\": \"FR\"\n" +
        "        }\n" +
        "    ],\n" +
        "    \"production_countries\": [\n" +
        "        {\n" +
        "            \"iso_3166_1\": \"FR\",\n" +
        "            \"name\": \"France\"\n" +
        "        }\n" +
        "    ],\n" +
        "    \"release_date\": \"2023-07-05\",\n" +
        "    \"revenue\": 37229585,\n" +
        "    \"runtime\": 107,\n" +
        "    \"spoken_languages\": [\n" +
        "        {\n" +
        "            \"english_name\": \"French\",\n" +
        "            \"iso_639_1\": \"fr\",\n" +
        "            \"name\": \"Français\"\n" +
        "        }\n" +
        "    ],\n" +
        "    \"status\": \"Released\",\n" +
        "    \"tagline\": \"The fate of the world is in their hands.\",\n" +
        "    \"title\": \"Miraculous: Ladybug & Cat Noir, The Movie\",\n" +
        "    \"video\": false,\n" +
        "    \"vote_average\": 7.824,\n" +
        "    \"vote_count\": 606\n" +
        "}"

    fun getPopularMovieResponse(): PopularMoviesResponse {
        return Gson().fromJson(POPULAR_MOVIE_RESPONSE, PopularMoviesResponse::class.java)
    }

    fun getMovieDetailsResponse(): MovieDetailsResponse {
        return Gson().fromJson(MOVIE_DETAILS_RESPONSE, MovieDetailsResponse::class.java)
    }
}
