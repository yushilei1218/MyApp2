package com.yushilei.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yushilei.myapp.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther by yushilei.
 * @time 2017/4/6-15:06
 * @desc
 */

public class XuanTingAdapter extends RecyclerView.Adapter<XuanTingAdapter.VH> {
    private List<Bean> data = new LinkedList<>();
    Context context;

    public XuanTingAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < 100; i++) {
            Bean bean = new Bean("i=" + i);
            if (i % 5 == 0) {
                bean.setName("主: " + bean.getName() + "------------------------------");
                bean.setIsxuanting(true);
            }
            data.add(bean);

        }
    }

    public Bean getPreviousBean(int index) {

        for (int i = index - 1; i >= 0; i--) {
            if (data.get(i).isxuanting) {

                return data.get(i);
            }
        }
        return null;
    }

    public Bean getBean(int index) {
        return data.get(index);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_xuanting, parent, false);
        VH tag = new VH(view);
        view.setTag(tag);

        return tag;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.tv.setText(data.get(position).getName());
        holder.isxuanting = data.get(position).isxuanting;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static final class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.xuanting_tv)
        TextView tv;
        boolean isxuanting;

        public boolean isxuanting() {
            return isxuanting;
        }

        public void setIsxuanting(boolean isxuanting) {
            this.isxuanting = isxuanting;
        }

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static final class Bean {
        private String name;
        private boolean isxuanting;

        public Bean(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isxuanting() {
            return isxuanting;
        }

        public void setIsxuanting(boolean isxuanting) {
            this.isxuanting = isxuanting;
        }
    }
}

