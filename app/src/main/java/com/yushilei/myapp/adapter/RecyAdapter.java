package com.yushilei.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yushilei.myapp.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther by yushilei.
 * @time 2017/3/13-15:34
 * @desc
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.VH> implements View.OnClickListener {
    List<String> data = new LinkedList<>();
    Context context;

    public RecyAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < 50; i++) {
            data.add("item+" + i);
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        VH tag = new VH(view);
        view.setTag(tag);
        return tag;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.tv.setText(data.get(position));
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(R.attr.key_id, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        Integer index = (Integer) v.getTag(R.attr.key_id);
        Toast.makeText(context, "top=" + v.getTop() + " y=" + v.getY() + " " + index, Toast.LENGTH_SHORT).show();
    }

    public static final class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.item_tv)
        TextView tv;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
