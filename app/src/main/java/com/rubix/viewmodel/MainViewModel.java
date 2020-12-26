package com.rubix.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rubix.database.BrandEntity;
import com.rubix.database.BrandWithMobiles;
import com.rubix.database.MobileEntity;
import com.rubix.repository.BrandRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private BrandRepository repository;
    private LiveData<List<BrandEntity>> brandEntityLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new BrandRepository(application);

        brandEntityLiveData = repository.getBrands();
    }


    public void insertData(List<BrandWithMobiles> brandWithMobiles)
    {
        repository.insert(brandWithMobiles);
    }


    public LiveData<List<BrandEntity>> getBrandEntityLiveData() {
        return brandEntityLiveData;
    }

    public List<MobileEntity> getMobilesList(long brand_id,String sortType)
    {
        return repository.getMobileList(brand_id,sortType);
    }




}
