package com.rubix.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface BrandDao {


    @Transaction
    @Insert
    long insertBrand(BrandEntity brandEntity);

    @Insert
    void insertMobiles(List<MobileEntity> mobiles);

    @Query("Select * from tbl_Brand")
    LiveData<List<BrandEntity>> getBrandsList();

    @Query("Select * from tbl_Mobile where brand_id_fk=:brand_id")
    List<MobileEntity> getMobileList(long brand_id);




}
