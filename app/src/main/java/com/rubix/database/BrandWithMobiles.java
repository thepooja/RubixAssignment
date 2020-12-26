package com.rubix.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class BrandWithMobiles {


        @Embedded
        public BrandEntity brand;
        @Relation(
                parentColumn = "brand_id",
                entityColumn = "mobile_id"
        )
        public List<MobileEntity> mobiles;


    public BrandWithMobiles(BrandEntity brand, List<MobileEntity> mobiles) {
        this.brand = brand;
        this.mobiles = mobiles;
    }
}
