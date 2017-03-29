package com.yushilei.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yushilei.myapp.R;
import com.yushilei.myapp.entitys.Location;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther by yushilei.
 * @time 2017/3/29-10:43
 * @desc
 */

public class LocationAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;

    private List<Location> data = new LinkedList<>();

    ListView callLv;

    public LocationAdapter(Context context) {
        this.context = context;
    }

    public void callLv(ListView listView) {
        callLv = listView;
    }

    public void addAll(List<Location> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_location, parent, false);
            convertView.setTag(new VH(convertView));
        }
        VH vh = (VH) convertView.getTag();
        Location location = data.get(position);
        vh.tv.setText(location.getName());
        if (location.isShowTag()) {
            vh.img.setVisibility(View.VISIBLE);
        } else {
            vh.img.setVisibility(View.GONE);
        }
        vh.setPos(position);
        convertView.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int pos = ((VH) v.getTag()).getPos();
        Location location = data.get(pos);
        List<Location> subLocations = location.getSubLocations();
        if (subLocations.size() > 0) {
            ((LocationAdapter) callLv.getAdapter()).addAll(subLocations);
        } else {
            location.setShowTag(true);
            notifyDataSetChanged();
        }
    }

    public static final class VH {
        @BindView(R.id.location_name)
        TextView tv;
        @BindView(R.id.location_star)
        ImageView img;
        int pos;

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public VH(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
