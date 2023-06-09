package com.example.afinal.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class TvshowsResponse implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    private final int id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private final String name;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private final Float voteAverage;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private final String synopsis;

    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    private final String airYear;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private final String posterPath;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private final String backdropPath;

    @ColumnInfo(name = "content_type")
    private int contentType;

    public TvshowsResponse(int id, String name, Float voteAverage, String synopsis, String airYear,
                           String posterPath, String backdropPath, int contentType) {

        this.id = id;
        this.name = name;
        this.voteAverage = voteAverage;
        this.synopsis = synopsis;
        this.airYear = airYear;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getAirYear() {
        return airYear;
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

    protected TvshowsResponse(Parcel in) {
        id = in.readInt();
        name = in.readString();
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readFloat();
        }
        synopsis = in.readString();
        airYear = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        contentType = in.readInt();
    }

    public static final Creator<TvshowsResponse> CREATOR = new Creator<TvshowsResponse>() {
        @Override
        public TvshowsResponse createFromParcel(Parcel in) {
            return new TvshowsResponse(in);
        }

        @Override
        public TvshowsResponse[] newArray(int size) {
            return new TvshowsResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        if (voteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(voteAverage);
        }
        dest.writeString(synopsis);
        dest.writeString(airYear);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeInt(contentType);
    }
}
