package com.yushilei.myapp.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yushilei.myapp.R;
import com.yushilei.myapp.entitys.XferMode;
import com.yushilei.myapp.widget.XferModeView;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther by yushilei.
 * @time 2017/4/6-13:47
 * @desc
 */

public class XfermodeAdapter extends BaseAdapter {
    private List<XferMode> data = new LinkedList<>();
    private Context context;

    public XfermodeAdapter(Context context) {
        this.context = context;
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR), "Clear"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.SRC), "SRC"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.DST), "DST"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER), "SRC_OVER"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER), "DST_OVER"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN), "SRC_IN"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN), "DST_IN"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT), "SRC_OUT"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT), "DST_OUT"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP), "SRC_ATOP"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP), "DST_ATOP"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.XOR), "XOR"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN), "DARKEN"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN), "LIGHTEN"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY), "MULTIPLY"));
        data.add(new XferMode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN), "SCREEN"));
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_xfer, parent, false);
            convertView.setTag(new VH(convertView));
        }
        XferMode xferMode = data.get(position);
        VH tag = (VH) convertView.getTag();
        tag.xfer.setXfermode(xferMode.getXfermode(), xferMode.getMode());

        return convertView;
    }

    public static final class VH {
        @BindView(R.id.xfer)
        XferModeView xfer;

        public VH(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
