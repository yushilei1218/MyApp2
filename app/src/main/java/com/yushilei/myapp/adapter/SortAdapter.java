package com.yushilei.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yushilei.myapp.R;
import com.yushilei.myapp.entitys.Sort;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther by yushilei.
 * @time 2017/3/28-15:48
 * @desc
 */

public class SortAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<Sort> data = new LinkedList<>();
    private Sort cache;

    public SortAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<Sort> data) {
        this.data.clear();
        this.data.addAll(data);
        for (Sort s : data) {
            if (s.isSelected()) {
                cache = s;
                break;
            }
        }
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sort, parent, false);
            convertView.setTag(new VH(convertView));
        }
        VH vh = (VH) convertView.getTag();
        vh.setPos(position);

        Sort sort = data.get(position);
        vh.tv.setText(sort.getName());
        if (sort.isSelected()) {
            vh.img.setVisibility(View.VISIBLE);
        } else {
            vh.img.setVisibility(View.GONE);
        }
        convertView.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        int pos = ((VH) v.getTag()).getPos();
        data.get(pos).setSelected(true);
        if (cache != null) {
            cache.setSelected(false);
        }
        cache = data.get(pos);
        notifyDataSetChanged();
    }

    public static final class VH {
        @BindView(R.id.sort_tv)
        TextView tv;
        @BindView(R.id.sort_img)
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
