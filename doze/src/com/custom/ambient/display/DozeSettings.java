/*
 * Copyright (C) 2015 The CyanogenMod Project
 * Copyright (C) 2020 The Dirty Unicorns Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.custom.ambient.display;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.SwitchPreference;

import static com.custom.ambient.display.Utils.GESTURE_HAND_WAVE_KEY;
import static com.custom.ambient.display.Utils.GESTURE_POCKET_KEY;
import static com.custom.ambient.display.Utils.PICK_UP_KEY;
import static com.custom.ambient.display.Utils.WAKE_ON_GESTURE_KEY;
import static com.custom.ambient.display.Utils.enableHandWave;
import static com.custom.ambient.display.Utils.enablePickUp;
import static com.custom.ambient.display.Utils.enablePocketMode;
import static com.custom.ambient.display.Utils.wakeOnGesture;

import com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity;
import com.android.settingslib.collapsingtoolbar.R;

public class DozeSettings extends CollapsingToolbarBaseActivity {

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            getFragmentManager().beginTransaction().replace(R.id.content_frame, new DozeSettingsFragment()).commit();
        }
    }

    public static class DozeSettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        private Context mContext;

        private SwitchPreference mPickUpPreference;
        private SwitchPreference mHandwavePreference;
        private SwitchPreference mPocketPreference;
        private SwitchPreference mWakeOnGesturePreference;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.doze_settings, rootKey);

            mContext = getActivity();

            mPickUpPreference = (SwitchPreference) findPreference(PICK_UP_KEY);
            mPickUpPreference.setOnPreferenceChangeListener(this);

            mHandwavePreference = (SwitchPreference) findPreference(GESTURE_HAND_WAVE_KEY);
            mHandwavePreference.setOnPreferenceChangeListener(this);

            mPocketPreference = (SwitchPreference) findPreference(GESTURE_POCKET_KEY);
            mPocketPreference.setOnPreferenceChangeListener(this);

            mWakeOnGesturePreference = (SwitchPreference) findPreference(WAKE_ON_GESTURE_KEY);
            mWakeOnGesturePreference.setOnPreferenceChangeListener(this);
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            final String key = preference.getKey();
            final boolean value = (Boolean) newValue;
            if (PICK_UP_KEY.equals(key)) {
                mPickUpPreference.setChecked(value);
                enablePickUp(value, mContext);
                return true;
            } else if (GESTURE_HAND_WAVE_KEY.equals(key)) {
                mHandwavePreference.setChecked(value);
                enableHandWave(value, mContext);
                return true;
            } else if (GESTURE_POCKET_KEY.equals(key)) {
                mPocketPreference.setChecked(value);
                enablePocketMode(value, mContext);
                return true;
            } else if (WAKE_ON_GESTURE_KEY.equals(key)) {
                mWakeOnGesturePreference.setChecked(value);
                wakeOnGesture(value, mContext);
                return true;
            }
            return false;
        }
    }

    public static void goUpToTopLevelSetting(Activity activity) {
        activity.finish();
    }
}
