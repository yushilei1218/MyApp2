package com.yushilei.myapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yushilei.myapp.R;
import com.yushilei.myapp.entitys.Star;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther by yushilei.
 * @time 2017/3/28-16:49
 * @desc
 */

public class StarAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<Star> data = new LinkedList<>();
    private Star noLimitStar;

    public StarAdapter(Context context) {
        this.context = context;
    }

    public void addALl(List<Star> data) {
        this.data.clear();
        this.data.addAll(data);
        for (Star s : this.data) {
            if (s.getName().equals("不限"))
                noLimitStar = s;
            if (s.isSelected()) {
                s.setCacheSelected(true);
            } else {
                s.setCacheSelected(false);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_star, parent, false);
            convertView.setTag(new VH(convertView));
        }
        VH vh = (VH) convertView.getTag();
        Star star = data.get(position);

        if (star.isCacheSelected()) {
            Log.i("TAG", "true");
            vh.tv.setSelected(true);
        } else {
            Log.i("TAG", "false");
            vh.tv.setSelected(false);
        }
        vh.setPos(position);

        vh.tv.setText(star.getName());
        convertView.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {

        int pos = ((VH) v.getTag()).getPos();
        Star star = data.get(pos);
        if (star.equals(noLimitStar)) {
            if (!star.isCacheSelected()) {
                for (Star s : data) {
                    if (!s.equals(noLimitStar)) {
                        s.setCacheSelected(false);
                    }
                }
                star.setCacheSelected(true);
            }
        } else {
            if (!star.isCacheSelected()) {
                star.setCacheSelected(true);
                noLimitStar.setCacheSelected(false);

            } else {
                star.setCacheSelected(false);
                boolean isN = true;
                for (Star s : data) {
                    if (!s.equals(noLimitStar)) {
                        if (s.isCacheSelected()) {
                            isN = false;
                            break;
                        }
                    }
                }
                if (isN) {
                    noLimitStar.setCacheSelected(true);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class VH {
        @BindView(R.id.star_tv)
        TextView tv;
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
