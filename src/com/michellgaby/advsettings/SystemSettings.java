package com.michellgaby.advsettings;

import android.os.Bundle;
import android.preference.*;

public class SystemSettings extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.system_settings);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        return true;
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        try {
            if (preference.getIntent().getAction().equals("com.michellgaby.advsettings.shutdown")) {
                AdvUtils.reboot(0);
                return true;
            }
            if (preference.getIntent().getAction().equals("com.michellgaby.advsettings.quickreboot")) {
                AdvUtils.reboot(1);
                return true;
            }
            if (preference.getIntent().getAction().equals("com.michellgaby.advsettings.recreboot")) {
                AdvUtils.reboot(2);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

