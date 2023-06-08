package com.example.afinal.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MoviesResponse implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private final String title;

    @SerializedName("vote_average")
    private Float voteAverage;

    @SerializedName("overview")
    private String synopsis;

    @SerializedName("release_date")
    private final String releaseYear;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    private int db_id;

    private int contentType;

    public MoviesResponse(int id, String title, Float voteAverage, String synopsis,
                          String releaseYear, String posterPath, String backdropPath,
                          int contentType) {

        this.id = id;
        this.title = title;
        this.voteAverage = voteAverage;
        this.synopsis = synopsis;
        this.releaseYear = releaseYear;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.contentType = contentType;
    }

    protected MoviesResponse(Parcel in) {
        id = in.readInt();
        title = in.readString();
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readFloat();
        }
        synopsis = in.readString();
        releaseYear = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        contentType = in.readInt();
        db_id = in.readInt();
    }

    public static final Creator<MoviesResponse> CREATOR = new Creator<MoviesResponse>() {
        @Override
        public MoviesResponse createFromParcel(Parcel in) {
            return new MoviesResponse(in);
        }

        @Override
        public MoviesResponse[] newArray(int size) {
            return new MoviesResponse[size];
        }
    };

    public int getDb_id() {
        return db_id;
    }

    public void setDb_id(int db_id) {
        this.db_id = db_id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public int getContentType() {
        return contentType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        if (voteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(voteAverage);
        }
        dest.writeString(synopsis);
        dest.writeString(releaseYear);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeInt(contentType);
        dest.writeInt(db_id);
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }
}
