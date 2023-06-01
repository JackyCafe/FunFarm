package tw.edu.pu.funfarm;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.ViewHolder>{
    List<Farm> datas = new ArrayList<>();
    public FarmAdapter(List<Farm> datas){
        this.datas = datas;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        private TextView tv1,tv2,tv3,tv4;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView1);
            tv1 = (TextView) view.findViewById(R.id.tvTitle);
            tv2 = (TextView) view.findViewById(R.id.tvDetail);
            tv3 = (TextView) view.findViewById(R.id.tvAddress);
            tv4 = (TextView) view.findViewById(R.id.tvCity);

        }
    }


    @NonNull
    @Override
    public ViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        try {

            String sUrl = datas.get(position).getPhoto();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try  {
                        URL url = new URL(sUrl);
                        Bitmap  bm = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        holder.imageView.setImageBitmap(bm);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();




        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        holder.tv1.setText(datas.get(position).getName());
        holder.tv2.setText(datas.get(position).getTel());
        holder.tv3.setText(datas.get(position).getAddress());
        holder.tv4.setText(datas.get(position).getCity());
    }


    @Override
    public int getItemCount() {
        Log.v("Adapter",datas.size()+"");
        return datas.size();
    }
}