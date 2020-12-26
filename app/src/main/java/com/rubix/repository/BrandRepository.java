package com.rubix.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.rubix.database.BrandDao;
import com.rubix.database.BrandEntity;
import com.rubix.database.BrandWithMobiles;
import com.rubix.database.MobileEntity;
import com.rubix.database.MyDatabase;

import java.util.List;

import static android.content.ContentValues.TAG;

public class BrandRepository {

    private BrandDao brandDao;
    private MyDatabase database;

    public BrandRepository(Application application)
    {

        database = MyDatabase.getDatabaseInstance(application);

        brandDao = database.dao();
    }


  /*  public void insert(BrandWithMobiles brandWithMobiles)
    {
        new insertAsyncTask(brandDao).execute(brandWithMobiles);
    }*/



    public void insert(List<BrandWithMobiles> brandWithMobiles)
    {
        new insertAsyncTask(brandDao).execute(brandWithMobiles);
    }

    private static class insertAsyncTask extends AsyncTask<List<BrandWithMobiles>,Void,Void>
    {

        private BrandDao brandDao;

        public insertAsyncTask(BrandDao brandDao) {
            this.brandDao = brandDao;
        }

        @Override
        protected Void doInBackground(List<BrandWithMobiles>... brandWithMobiles) {


            for (int i = 0; i <brandWithMobiles[0].size() ; i++)
           {

                long identifier = brandDao.insertBrand(brandWithMobiles[0].get(i).brand);

                for (MobileEntity mobileEntity :brandWithMobiles[0].get(i).mobiles)
                {
                    mobileEntity.setBrand_id_fk(identifier);
                }

                brandDao.insertMobiles(brandWithMobiles[0].get(i).mobiles);

            }

            return null;
        }

       /* @Override
        protected Void doInBackground(BrandWithMobiles... brandWithMobiles) {

            long identifier = brandDao.insertBrand(brandWithMobiles[0].brand);

            for (MobileEntity mobileEntity : brandWithMobiles[0].mobiles)
            {
                mobileEntity.setBrand_id_fk(identifier);
            }

            brandDao.insertMobiles(brandWithMobiles[0].mobiles);

            return null;


        }*/
    }


    public LiveData<List<BrandEntity>> getBrands()
    {

        return brandDao.getBrandsList();
    }



    public List<MobileEntity> getMobileList(long brand_id)
    {
        List<MobileEntity> mobileEntities =null;

        try {
            mobileEntities = new getMobiles(brandDao,brand_id).execute().get();
        }catch (Exception e)
        {

        }

        return mobileEntities;
    }

    private static class getMobiles extends AsyncTask<Void,Void,List<MobileEntity>>
    {
        private BrandDao brandDao;
        private long brand_id;

        public getMobiles(BrandDao brandDao,long brand_id) {
            this.brandDao = brandDao;
            this.brand_id = brand_id;
        }


        @Override
        protected List<MobileEntity> doInBackground(Void... voids) {
            return brandDao.getMobileList(brand_id);
        }
    }

}
