package com.michellgaby.advsettings;

import java.io.File;

import android.content.Intent;
import android.os.Bundle;
import android.preference.*;
import android.provider.Settings;

public class SystemSettings extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

	public static final String RIGHT_LOCKSCREEN = "right_lockscreen";
	public static final String LEFT_LOCKSCREEN = "left_lockscreen";
	public static final String TRAFFIC = "traffic";
	public static final String STATUS_SEMIPERMEABLE = "status_semipermeable";

	private CheckBoxPreference mRightLockscreen;
	private CheckBoxPreference mLeftLockscreen;
	private CheckBoxPreference mTraffic;
	private CheckBoxPreference mStatus_Semipermeable;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.system_settings);

        mTraffic = (CheckBoxPreference)
                findPreference(TRAFFIC);
        mRightLockscreen = (CheckBoxPreference)
                findPreference(RIGHT_LOCKSCREEN);
        mLeftLockscreen = (CheckBoxPreference)
                findPreference(LEFT_LOCKSCREEN);
        mStatus_Semipermeable = (CheckBoxPreference)
                findPreference(STATUS_SEMIPERMEABLE);
        
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        return true;
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
		boolean value;
		if (preference == mRightLockscreen) {
			value = mRightLockscreen.isChecked();
			if(value) {
				AdvUtils.runCmd("rm /data/data/com.michellgaby.advsettings/right_lockscreen");
			} else {
				AdvUtils.runCmd("busybox touch /data/data/com.michellgaby.advsettings/right_lockscreen");
			}
			return true;
		}
		if (preference == mLeftLockscreen) {
			value = mLeftLockscreen.isChecked();
			if(value) {
				AdvUtils.runCmd("rm /data/data/com.michellgaby.advsettings/left_lockscreen");
			} else {
				AdvUtils.runCmd("busybox touch /data/data/com.michellgaby.advsettings/left_lockscreen");
			}
			return true;
		}
		if (preference == mTraffic) {
			value = mTraffic.isChecked();
			if(value) {
		    	File file = new File("/data/.networkrun");
			    getActivity().sendBroadcast(new Intent("fx.heriawan.Traffic"));
			    if(file.exists()){
				    getActivity().sendBroadcast(new Intent("fx.heriawan.Traffic"));
			    }
				AdvUtils.runCmd("busybox rm /data/.networkrun");
			    AdvUtils.runCmd("busybox rm /data/data/com.michellgaby.advsettings/networkspeend");
			} else {
			  getActivity().sendBroadcast(new Intent("fx.heriawan.Traffic"));
			  AdvUtils.runCmd("busybox touch /data/data/com.michellgaby.advsettings/networkspeend");
			  AdvUtils.runCmd("busybox rm /data/.networkrun");
			}
			return true;
		}
		if (preference == mStatus_Semipermeable) {
			value = mStatus_Semipermeable.isChecked();
			Settings.System.putInt(getActivity().getContentResolver(),
					"status_semipermeable", value ? 1 : 0);
			return true;
		}
        return false;
    }

}

