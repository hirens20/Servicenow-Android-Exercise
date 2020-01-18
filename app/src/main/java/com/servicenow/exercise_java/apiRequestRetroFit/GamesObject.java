package com.servicenow.exercise_java.apiRequestRetroFit;

import com.google.gson.annotations.SerializedName;

public class GamesObject {
    @SerializedName("games")
    private GameInfo[] games;

    public class GameInfo {
        @SerializedName("name")
        private String name;
        @SerializedName("cover")
        private String cover;
        @SerializedName("shortDescription")
        private String shortDescription;
        @SerializedName("longDescription")
        private String longDescription;

        public String getName() {
             return name;
        }

        public String getCover() {
             return cover;
        }

        public String getShortDescription() {
             return shortDescription;
        }

        public String getLongDescription() {
             return longDescription;
        }
    }

    public GameInfo[] getGames() {
        return games;
    }
}
