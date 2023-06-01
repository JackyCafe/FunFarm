package tw.edu.pu.funfarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class FarmActivity extends AppCompatActivity {
    List<Farm> farms;
    boolean is_insert = true;
    private static final String API_URL = "https://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvAttractions.aspx";
    private RecyclerView mRecyclerView;
    FarmDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        AttractionsApiManager apiManager = new AttractionsApiManager();
        dao = new FarmDAO(FarmActivity.this);
        if (!is_insert) {
            apiManager.getAttractionsData(new AttractionsApiManager.OnDataReceivedListener() {
                @Override
                public void onDataReceived(String data) {
                    // 在這裡處理從 API 收到的資料
                    Log.d("MainActivity", "Received data: " + data);
                    Gson gson = new Gson();
                    farms = gson.fromJson(data.toString()
                            , new TypeToken<List<Farm>>() {
                            }.getType());
                    for (Farm farm:farms) {

                        Farm s=dao.insert(farm);
                        Log.v("MainActivity", "farm: " + s);
                    }
                }
            });
        }else{
            Intent it = getIntent();
            String city = it.getStringExtra("city");
            Toast.makeText(this,city,Toast.LENGTH_LONG).show();
//            List<Farm> datas = dao.getAll();
            List<Farm> datas = dao.getbyCity(city);
            FarmAdapter adapter = new FarmAdapter(datas);
//            if (mRecyclerView!=null)
//                Toast.makeText(this,""+dao.getAll().size(),Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(this,"ccccc",Toast.LENGTH_SHORT).show();
            mRecyclerView.setAdapter(adapter);
        }



    }
}