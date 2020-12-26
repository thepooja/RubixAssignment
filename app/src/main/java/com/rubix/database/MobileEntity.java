package com.rubix.database;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tbl_Mobile")
public class MobileEntity {

    @PrimaryKey(autoGenerate = true)
    public long mobile_id;

    @ForeignKey
            (entity = BrandEntity.class,
                    parentColumns = "brand_id",
                    childColumns = "brand_id_fk",
                    onDelete = CASCADE
            )
    private long brand_id_fk;

    @NonNull
    @ColumnInfo(name = "model_name")
    private String model_name;

    @NonNull
    @ColumnInfo(name = "model_date")
    private String model_date;

    @NonNull
    @ColumnInfo(name = "model_image")
    private byte[] model_image;

    @NonNull
    @ColumnInfo(name = "model_quantity")
    private float model_quantity;

    @NonNull
    @ColumnInfo(name = "model_rating")
    private float model_rating;

    public long getMobile_id() {
        return mobile_id;
    }

    public void setMobile_id(long mobile_id) {
        this.mobile_id = mobile_id;
    }

    public long getBrand_id_fk() {
        return brand_id_fk;
    }

    public void setBrand_id_fk(long brand_id_fk) {
        this.brand_id_fk = brand_id_fk;
    }

    @NonNull
    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(@NonNull String model_name) {
        this.model_name = model_name;
    }

    @NonNull
    public String getModel_date() {
        return model_date;
    }

    public void setModel_date(@NonNull String model_date) {
        this.model_date = model_date;
    }

    @NonNull
    public byte[] getModel_image() {
        return model_image;
    }

    public void setModel_image(@NonNull byte[] model_image) {
        this.model_image = model_image;
    }

    public float getModel_quantity() {
        return model_quantity;
    }

    public void setModel_quantity(float model_quantity) {
        this.model_quantity = model_quantity;
    }

    public float getModel_rating() {
        return model_rating;
    }

    public void setModel_rating(float model_rating) {
        this.model_rating = model_rating;
    }

    public MobileEntity(@NonNull String model_name, @NonNull String model_date, @NonNull byte[] model_image, float model_quantity, float model_rating) {
        this.model_name = model_name;
        this.model_date = model_date;
        this.model_image = model_image;
        this.model_quantity = model_quantity;
        this.model_rating = model_rating;
    }
}
