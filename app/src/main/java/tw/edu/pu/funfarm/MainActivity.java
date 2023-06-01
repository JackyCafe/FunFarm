package tw.edu.pu.funfarm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvhello;
    private ImageButton calender;
    private final List<String> datas= new ArrayList<>();
    private ListView lv;
    private Spinner spinner;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MyPreference myPreference = new MyPreference(); //宣告剛剛做好的PreferenceFragment
       // transaction.replace(android.R.id.content, myPreference); //將content內容取代為myPreference
        //transaction.commit(); //送出交易

        setContentView(R.layout.activity_main);
        findView();



        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                        R.array.planets_array,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(2, false);
        spinner.setOnItemSelectedListener(spnOnItemSelected);
//        tvhello= findViewById(R.id.hello);
//        tvhello.setText("選項:"+spinner.getSelectedItem().toString());

        calender.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        });
    }
    private void findView() {
        calender = findViewById(R.id.imageButton1);
        spinner = findViewById(R.id.spinner);
        lv = findViewById(R.id.listView);

    }
    private final AdapterView.OnItemSelectedListener spnOnItemSelected
            = new AdapterView.OnItemSelectedListener() {
        @SuppressLint("SetTextI18n")
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            String sPos=String.valueOf(pos);
            String sInfo=parent.getItemAtPosition(pos).toString();
            datas.clear();
            switch (pos){
                case 0:
                    datas.add("台北市");
                    datas.add("新北市");
                    datas.add("基隆市");
                    datas.add("桃園市");
                    datas.add("新竹縣");
                    datas.add("宜蘭縣");
                    break;
                case 1:
                    datas.add("臺中市");
                    datas.add("苗栗縣");
                    datas.add("彰化縣");
                    datas.add("南投縣");
                    datas.add("雲林縣");
                    break;
                case 2:
                    datas.add("高雄市");
                    datas.add("臺南市");
                    datas.add("嘉義縣");
                    datas.add("屏東縣");
                    break;
                case 3:
                    datas.add("台東縣");
                    datas.add("花蓮縣");
                    break;
            }
            AreaAdapter adapter = new AreaAdapter(MainActivity.this,datas);
            lv.setAdapter(adapter);

            //String sInfo=parent.getSelectedItem().toString();
//            tvhello.setText("選項"+sPos+":"+sInfo);
        }
        public void onNothingSelected(AdapterView<?> parent) {
            //
        }
    };
}