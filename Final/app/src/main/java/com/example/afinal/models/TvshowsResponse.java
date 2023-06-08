package com.example.afinal.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TvshowsResponse implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("vote_average")
    private Float voteAverage;

    @SerializedName("overview")
    private String synopsis;

    @SerializedName("first_air_date")
    private String airYear;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    private int db_id;

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
        db_id = in.readInt();
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

    public int getDb_id() {
        return db_id;
    }

    public void setDb_id(int db_id) {
        this.db_id = db_id;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAirYear() {
        return airYear;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public String getSynopsis() {
        return synopsis;
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
        dest.writeInt(db_id);
    }
}
