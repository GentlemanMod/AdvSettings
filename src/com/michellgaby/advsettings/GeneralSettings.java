package com.michellgaby.advsettings;

import android.os.Bundle;
import android.preference.*;
import android.provider.Settings;
import android.util.Log;

public class GeneralSettings extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String STATUS_BACKLIGHT = "status_backlight";
    private static final String STATUS_EMAIL_SERVICE = "status_email_service";
    private static final String STATUS_GOOGLE_SERVICE = "status_google_service";
    private static final String STATUS_CAMERA_CLICK = "status_camera_click";
    private static final String STATUS_CAMERA_FOCUS = "status_camera_focus";
    private static final String STATUS_CAMERA_SHUTTER = "status_camera_shutter";
    private static final String STATUS_CAMERA_VIDEORECORD = "status_videorecord";
    private static final String STATUS_SWITCH_STORAGE = "status_switch_storage";

    private ListPreference mStatusSwitchStorage;
    private CheckBoxPreference mStatusBacklight;
    private CheckBoxPreference mStatusEmailService;
    private CheckBoxPreference mStatusGoogleService;
    private CheckBoxPreference mStatusCameraClick;
    private CheckBoxPreference mStatusCameraFocus;
    private CheckBoxPreference mStatusCameraShutter;
    private CheckBoxPreference mStatusVideoRecord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.general_settings);

        mStatusSwitchStorage = (ListPreference)
                getPreferenceScreen().findPreference(STATUS_SWITCH_STORAGE);
        mStatusBacklight = (CheckBoxPreference)
                findPreference(STATUS_BACKLIGHT);
        mStatusEmailService = (CheckBoxPreference)
                findPreference(STATUS_EMAIL_SERVICE);
        mStatusGoogleService = (CheckBoxPreference)
                findPreference(STATUS_GOOGLE_SERVICE);
        mStatusCameraClick = (CheckBoxPreference)
                findPreference(STATUS_CAMERA_CLICK);
        mStatusCameraFocus = (CheckBoxPreference)
                findPreference(STATUS_CAMERA_FOCUS);
        mStatusCameraShutter = (CheckBoxPreference)
                findPreference(STATUS_CAMERA_SHUTTER);
        mStatusVideoRecord = (CheckBoxPreference)
                findPreference(STATUS_CAMERA_VIDEORECORD);

		  int statusBarBattery = Settings.System.getInt(getActivity()
				  .getApplicationContext().getContentResolver(),
				  "status_switch_storage", 0);
		  mStatusSwitchStorage.setValue(String.valueOf(statusBarBattery));
        mStatusSwitchStorage.setSummary(mStatusSwitchStorage.getEntry());
        mStatusSwitchStorage.setOnPreferenceChangeListener(this);

    }

	@Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mStatusSwitchStorage) {
            int statusSwitchStorage = Integer.valueOf((String) newValue);
            int index = mStatusSwitchStorage.findIndexOfValue((String) newValue);
            if(index == 0){
                AdvUtils.runCmd("/system/etc/Advsettings.sh 9");
    			Settings.System.putInt(getActivity().getContentResolver(),
    					STATUS_SWITCH_STORAGE, statusSwitchStorage);
            } else {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 10");
    			Settings.System.putInt(getActivity().getContentResolver(),
    					STATUS_SWITCH_STORAGE, statusSwitchStorage);
            }
            mStatusSwitchStorage
                    .setSummary(mStatusSwitchStorage.getEntries()[index]);
            return true;
        }

        return true;
	}

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
                                         Preference preference) {
        boolean value;
        if (preference == mStatusBacklight) {
            value = mStatusBacklight.isChecked();
            if(value) {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 1");
            } else {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 2");
            }
            return true;
        }
        if (preference == mStatusEmailService) {
            value = mStatusEmailService.isChecked();
            if(value) {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 11");
            } else {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 12");
            }
            return true;
        }
        if (preference == mStatusGoogleService) {
            value = mStatusGoogleService.isChecked();
            if(value) {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 3");
            } else {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 4");
            }
            return true;
        }
        if (preference == mStatusCameraClick) {
            value = mStatusCameraClick.isChecked();
            if(value) {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 5");
            } else {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 5");
            }
            return true;
        }
        if (preference == mStatusCameraFocus) {
            value = mStatusCameraFocus.isChecked();
            if(value) {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 6");
            } else {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 6");
            }
            return true;
        }
        if (preference == mStatusCameraShutter) {
            value = mStatusCameraShutter.isChecked();
            if(value) {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 7");
            } else {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 7");
            }
            return true;
        }
        if (preference == mStatusVideoRecord) {
            value = mStatusVideoRecord.isChecked();
            if(value) {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 8");
            } else {
                AdvUtils.runCmd("/system/etc/Advsettings.sh 8");
            }
            return true;
        }

        return false;
    }
}