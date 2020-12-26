package com.rubix.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.rubix.R;
import com.rubix.activity.adapter.ExpandAdapter;
import com.rubix.database.BrandEntity;
import com.rubix.database.BrandWithMobiles;
import com.rubix.database.MobileEntity;
import com.rubix.databinding.ActivityMainBinding;
import com.rubix.viewmodel.MainViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ExpandAdapter adapter;

    private HashMap<BrandEntity, List<MobileEntity>> expandList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initView();

        insertData();

        setList();

    }

    private void initView() {

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        expandList = new HashMap<>();


    }

    private void setList() {
        viewModel.getBrandEntityLiveData().observe(this, new Observer<List<BrandEntity>>() {
            @Override
            public void onChanged(List<BrandEntity> brandEntities) {

                List<MobileEntity> mobileEntities = new ArrayList<>();

                for (int i = 0; i < brandEntities.size(); i++) {

                    mobileEntities = viewModel.getMobilesList(brandEntities.get(i).getBrand_id(), "");

                    expandList.put(brandEntities.get(i), mobileEntities);

                }
                adapter = new ExpandAdapter(getApplicationContext(), brandEntities, expandList);

                binding.expandableList.setAdapter(adapter);

                adapter.notifyDataSetChanged();

            }
        });


    }

    private void insertData() {


        // Check Entries is Exist Or Not

        if (!doesDatabaseExist(getApplicationContext(), "mobile.db")) {
            // First Category
            BrandEntity brandEntity = new BrandEntity();
            brandEntity.setBrandName("Apple");

            // Second Category
            BrandEntity brandEntity1 = new BrandEntity();
            brandEntity1.setBrandName("Samsung");


            BrandWithMobiles mobiles = new BrandWithMobiles(brandEntity, getAppleBrandList());
            BrandWithMobiles mobiles1 = new BrandWithMobiles(brandEntity1, getAndroidMobileList());


            List<BrandWithMobiles> withMobiles = new ArrayList<>();
            withMobiles.add(mobiles);
            withMobiles.add(mobiles1);

            // Insert Data in Database
            viewModel.insertData(withMobiles);
        }


    }


    // Convert Bitmap to byte[]
    private byte[] getByteFromBitmap(int logo) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), logo);
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream1);
        byte[] bitmapIcon = stream1.toByteArray();

        return bitmapIcon;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_sort_date:
                sortByDate();
                return true;
            case R.id.menu_sort_qty:
                sortByQuantity();
                return true;
            case R.id.menu_sort_rating:
                sortByRating();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<MobileEntity> getAppleBrandList() {
        List<MobileEntity> mobileEntities = new ArrayList<>();

        byte[] appleLogo = getByteFromBitmap(R.drawable.ic_apple_logo);

        mobileEntities.add(new MobileEntity("iphone 6 ", "2014-03-08", appleLogo, 15.0f, 3.5f));
        mobileEntities.add(new MobileEntity("Iphone 6 s", "2014-08-15", appleLogo, 20.0f, 4.5f));
        mobileEntities.add(new MobileEntity("iphone 7", "2014-10-20", appleLogo, 34.0f, 4.5f));
        mobileEntities.add(new MobileEntity("Iphone 8", "2016-12-20", appleLogo, 49.0f, 4.0f));
        mobileEntities.add(new MobileEntity("Iphone X", "2018-01-12", appleLogo, 55.0f, 3.5f));
        mobileEntities.add(new MobileEntity("Iphone XR", "2019-09-20", appleLogo, 105.0f, 4.0f));


        return mobileEntities;
    }

    private List<MobileEntity> getAndroidMobileList() {
        List<MobileEntity> mobileEntities = new ArrayList<>();

        byte[] androidLogo = getByteFromBitmap(R.drawable.ic_android_logo);

        mobileEntities.add(new MobileEntity("Samsung S7 ", "2014-02-08", androidLogo, 45.0f, 4.5f));
        mobileEntities.add(new MobileEntity("Samsung S8", "2014-04-25", androidLogo, 98.0f, 3.5f));
        mobileEntities.add(new MobileEntity("Samsung S9", "2016-05-20", androidLogo, 40.0f, 4.0f));
        mobileEntities.add(new MobileEntity("Samsung S10", "2017-07-20", androidLogo, 99.0f, 4.0f));
        mobileEntities.add(new MobileEntity("Samsung Note 7", "2018-01-12", androidLogo, 78.0f, 2.5f));
        mobileEntities.add(new MobileEntity("Samsung Note 8", "2019-01-20", androidLogo, 68.0f, 4.5f));
        mobileEntities.add(new MobileEntity("Samsung Note 9", "2019-09-17", androidLogo, 57.0f, 4.0f));
        mobileEntities.add(new MobileEntity("Samsung Note 10", "2020-01-20", androidLogo, 145.0f, 4.5f));

        return mobileEntities;
    }


    private void sortByDate() {
        viewModel.getBrandEntityLiveData().observe(this, new Observer<List<BrandEntity>>() {
            @Override
            public void onChanged(List<BrandEntity> brandEntities) {

                List<MobileEntity> mobileEntities = new ArrayList<>();

                for (int i = 0; i < brandEntities.size(); i++) {

                    mobileEntities = viewModel.getMobilesList(brandEntities.get(i).getBrand_id(), "date");

                    expandList.put(brandEntities.get(i), mobileEntities);

                    adapter = new ExpandAdapter(getApplicationContext(), brandEntities, expandList);

                    binding.expandableList.setAdapter(adapter);

                }

                adapter.notifyDataSetChanged();


            }
        });
    }

    private void sortByQuantity() {
        viewModel.getBrandEntityLiveData().observe(this, new Observer<List<BrandEntity>>() {
            @Override
            public void onChanged(List<BrandEntity> brandEntities) {

                List<MobileEntity> mobileEntities = new ArrayList<>();

                for (int i = 0; i < brandEntities.size(); i++) {

                    mobileEntities = viewModel.getMobilesList(brandEntities.get(i).getBrand_id(), "qty");

                    expandList.put(brandEntities.get(i), mobileEntities);

                    adapter = new ExpandAdapter(getApplicationContext(), brandEntities, expandList);

                    binding.expandableList.setAdapter(adapter);

                }
                adapter.notifyDataSetChanged();


            }
        });
    }

    private void sortByRating() {
        viewModel.getBrandEntityLiveData().observe(this, new Observer<List<BrandEntity>>() {
            @Override
            public void onChanged(List<BrandEntity> brandEntities) {

                List<MobileEntity> mobileEntities = new ArrayList<>();

                for (int i = 0; i < brandEntities.size(); i++) {

                    mobileEntities = viewModel.getMobilesList(brandEntities.get(i).getBrand_id(), "rating");

                    expandList.put(brandEntities.get(i), mobileEntities);

                    adapter = new ExpandAdapter(getApplicationContext(), brandEntities, expandList);

                    binding.expandableList.setAdapter(adapter);

                }
                adapter.notifyDataSetChanged();


            }
        });
    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }


}


