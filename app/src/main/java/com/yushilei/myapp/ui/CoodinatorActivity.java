package com.yushilei.myapp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yushilei.myapp.BaseActivity;
import com.yushilei.myapp.R;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoodinatorActivity extends BaseActivity {
    @BindView(R.id.coodinator_recycler)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_coodinator;
    }

    @Override
    protected void onInitViews() {
        AD adapter = new AD();
        recyclerView.setAdapter(adapter);
        List<String> data = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            data.add("item+" + i);
        }
        adapter.addAll(data);
    }

    public class AD extends RecyclerView.Adapter<AD.VH> {
        private List<String> data = new LinkedList<>();

        public void addAll(List<String> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(CoodinatorActivity.this).inflate(R.layout.item_recycler, parent, false);
            VH tag = new VH(view);
            view.setTag(tag);
            return tag;
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            String text = "item+" + position;
            holder.tv.setText(text);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class VH extends RecyclerView.ViewHolder {
            @BindView(R.id.item_tv)
            TextView tv;

            public VH(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
