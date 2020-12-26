package com.rubix.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
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
   private  LayoutListItemBinding itemBinding;
   private LayoutListHeaderBinding headerBinding;

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
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            headerBinding = DataBindingUtil.inflate(inflater, R.layout.layout_list_header, parent, false);

            convertView = headerBinding.getRoot();

        }

        BrandEntity brandEntity = (BrandEntity)getGroup(groupPosition);

        headerBinding.txtHeader.setText(brandEntity.getBrandName());


        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {


        if (convertView == null) {
           /* LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_list_item, null);*/


            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
           itemBinding = DataBindingUtil.inflate(inflater, R.layout.layout_list_item, parent, false);

           convertView = itemBinding.getRoot();
        }


       MobileEntity entity = (MobileEntity)getChild(groupPosition,childPosition);
        //itemBinding.imgLogo.setImageResource(entity.getModel_image().);
        itemBinding.txtModelName.setText(entity.getModel_name());
        itemBinding.txtDate.setText(entity.getModel_date());
        itemBinding.modelRating.setRating(entity.getModel_rating());
        itemBinding.txtModelQuantity.setText(String.valueOf(entity.getModel_quantity()));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
