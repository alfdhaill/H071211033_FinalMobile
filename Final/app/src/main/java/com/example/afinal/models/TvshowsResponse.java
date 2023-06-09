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
    private int id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private Float voteAverage;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String synopsis;

    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    private String airYear;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String posterPath;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String backdropPath;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getAirYear() {
        return airYear;
    }

    public void setAirYear(String airYear) {
        this.airYear = airYear;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public int getContentType() {
        return contentType;
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

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

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
