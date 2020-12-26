package com.rubix.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_Brand")
public class BrandEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long brand_id;

    @NonNull
    @ColumnInfo(name = "brandName")
    private String brandName;

    public long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(long brand_id) {
        this.brand_id = brand_id;
    }

    @NonNull
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(@NonNull String brandName) {
        this.brandName = brandName;
    }
}
