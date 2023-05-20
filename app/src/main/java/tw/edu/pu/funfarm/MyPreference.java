package tw.edu.pu.funfarm;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class MyPreference extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.my_preferences);
    }
}
