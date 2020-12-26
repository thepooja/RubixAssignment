package com.rubix.activity.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.rubix.R;
import com.rubix.database.BrandEntity;
import com.rubix.database.MobileEntity;
import com.rubix.databinding.LayoutListHeaderBinding;
import com.rubix.databinding.LayoutListItemBinding;

import java.util.HashMap;
import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<BrandEntity> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<BrandEntity, List<MobileEntity>> _listDataChild;

    public ExpandAdapter(Context _context, List<BrandEntity> _listDataHeader, HashMap<BrandEntity, List<MobileEntity>> _listDataChild) {
        this._context = _context;
        this._listDataHeader = _listDataHeader;
        this._listDataChild = _listDataChild;
    }


    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {

            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_list_header, null);

        }



        BrandEntity brandEntity = (BrandEntity)getGroup(groupPosition);

        TextView txtHeader = convertView.findViewById(R.id.txtHeader);

        txtHeader.setText(brandEntity.getBrandName());

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_list_item, null);


        }


        MobileEntity entity = (MobileEntity)getChild(groupPosition,childPosition);
        TextView txtModelName = (TextView) convertView.findViewById(R.id.txtModelName);
        TextView txtDate = (TextView) convertView.findViewById(R.id.txtDate);
        TextView txtModelQuantity = (TextView) convertView.findViewById(R.id.txtModelQuantity);
        RatingBar ratingBar = convertView.findViewById(R.id.modelRating);
        ImageView imgLogo = convertView.findViewById(R.id.imgLogo);


        txtModelName.setText(entity.getModel_name());
        txtDate.setText(entity.getModel_name());
        txtModelQuantity.setText(String.valueOf(entity.getModel_quantity()));
        ratingBar.setRating(entity.getModel_rating());

        Bitmap b = getConvertedImage(entity.getModel_image());


       // itemBinding.imgLogo.setBackgroundDrawable(new BitmapDrawable(_context.getResources(),getConvertedImage(entity.getModel_image())));

        //imgLogo.setBackground(new BitmapDrawable(b));
        //imgLogo.setImageBitmap(b);

    //    imgLogo.

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private Bitmap getConvertedImage(byte[] image)
    {
        // let this be your byte array
        Bitmap bitmap = BitmapFactory.decodeByteArray(image , 0, image .length);


        return bitmap;
    }
}
