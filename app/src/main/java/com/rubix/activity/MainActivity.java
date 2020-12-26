package com.rubix.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG" ;
    ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ExpandAdapter adapter;

    private HashMap<BrandEntity, List<MobileEntity>> expandList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);



       /* BrandRepository repository = new BrandRepository(getApplication());


        List<MobileEntity> mobileEntities = new ArrayList<>();

*/
       /* Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.circle_right_arrow);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapIcon = stream.toByteArray();

        mobileEntities.add(new MobileEntity("Samsung 101","2020-12-20",bitmapIcon,4.0f,3.5f));

        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setBrandName("Samsung");

        BrandWithMobiles mobiles = new BrandWithMobiles(brandEntity,mobileEntities);

        repository.insert(mobiles);*/

        initView();

      //  initData();

        setList();

    }

    private void initView() {

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        expandList = new HashMap<>();

    }

    private void setList()
    {
        viewModel.getBrandEntityLiveData().observe(this, new Observer<List<BrandEntity>>() {
            @Override
            public void onChanged(List<BrandEntity> brandEntities) {

                List<MobileEntity> mobileEntities = new ArrayList<>();

                for (int i = 0; i <brandEntities.size() ; i++) {

                    Log.e(TAG, "onChanged: Brand NAme --- "+brandEntities.get(i).getBrandName());

                    mobileEntities = viewModel.getMobilesList(brandEntities.get(i).getBrand_id());

                    Log.e(TAG, "onChanged: "+mobileEntities.size());

                    expandList.put(brandEntities.get(i),mobileEntities);


                    adapter = new ExpandAdapter(getApplicationContext(),brandEntities,expandList);

                    binding.expandableList.setAdapter(adapter);


                }

            }
        });




    }

    private void initData() {

        List<MobileEntity> mobileEntities = new ArrayList<>();


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.circle_right_arrow);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapIcon = stream.toByteArray();

        mobileEntities.add(new MobileEntity("Samsung 101", "2020-12-20", bitmapIcon, 4.0f, 3.5f));
        mobileEntities.add(new MobileEntity("Samsung 102", "2020-12-20", bitmapIcon, 4.0f, 3.5f));
        mobileEntities.add(new MobileEntity("Samsung 103", "2020-12-20", bitmapIcon, 4.0f, 3.5f));
        mobileEntities.add(new MobileEntity("Samsung 104", "2020-12-20", bitmapIcon, 4.0f, 3.5f));

        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setBrandName("Samsung");
        BrandEntity brandEntity1 = new BrandEntity();

        brandEntity1.setBrandName("Apple");


        BrandWithMobiles mobiles = new BrandWithMobiles(brandEntity, mobileEntities);
        BrandWithMobiles mobiles1 = new BrandWithMobiles(brandEntity1, mobileEntities);


        List<BrandWithMobiles> withMobiles = new ArrayList<>();
        withMobiles.add(mobiles);
        withMobiles.add(mobiles1);
        viewModel.insertData(withMobiles);

        //viewModel.insertData(mobiles1);

    }

    private void insertData() {

    }
}