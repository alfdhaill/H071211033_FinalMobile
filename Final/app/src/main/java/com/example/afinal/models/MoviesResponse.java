package com.example.afinal.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class MoviesResponse implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    private final int id;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private final String title;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private final Float voteAverage;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private final String synopsis;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private final String releaseYear;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private final String posterPath;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private final String backdropPath;

    @ColumnInfo(name = "content_type")
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

    public void setContentType(int contentType) {
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
    }
}
