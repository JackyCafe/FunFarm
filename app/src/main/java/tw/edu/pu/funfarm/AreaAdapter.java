package tw.edu.pu.funfarm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AreaAdapter extends BaseAdapter {
    List<String> datas ;
    Context context;
    public AreaAdapter(Context context, List<String> datas){
        this.datas = datas;
        this.context = context;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.item,viewGroup,false);
        TextView textview = v.findViewById(R.id.area);
        textview.setText(datas.get(i));
        return v;
    }
}
