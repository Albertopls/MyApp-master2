package com.example.eduardopalacios.myapp;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import java.util.List;

/**
 * Created by Ely'z on 30/05/2017.
 */

public class Prefs extends PreferenceActivity {


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    // Required because of a vulnerability.  See
    // http://stackoverflow.com/questions/19973034/isvalidfragment-android-api-19

    protected boolean isValidFragment(String fragmentName) {
        return Prefs1Fragment.class.getName().equals(fragmentName); // ||
        // Prefs2Fragment.class.getName().equals(fragmentName);
    }

    // Fragment showing preferences corresponding to the first header.  See
    //   http://developer.android.com/reference/android/preference/PreferenceFragment.html
    // for documentation.

    public static class Prefs1Fragment extends PreferenceFragment {

        public Prefs1Fragment(){

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Set default values from XML preference file. See
            //     http://developer.android.com/reference/android/preference
            //       /PreferenceManager.html#setDefaultValues(android.content.Context, int, boolean)
            // The first argument is the context of the shared preference, the second is the resource
            // ID of the preference defaults file, and the third argument is false if the defaults
            // file is to be read ONLY if this method has never been called in the past.

            PreferenceManager.setDefaultValues(getActivity(), R.xml.pref_general, false);

            // This method inflates the XML resource specified in the argument and adds the preference
            // hierarchy to the current preference hierarchy.

            addPreferencesFromResource(R.xml.pref_general);
        }

    }

    // Fragment to show preferences corresponding to the second header

  /*  public static class Prefs2Fragment extends PreferenceFragment {

        public Prefs2Fragment(){

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Set default values
            PreferenceManager.setDefaultValues(getActivity(), R.xml.pref_general, false);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.pref_general);
        }
    }
*/

}