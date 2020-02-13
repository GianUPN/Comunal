package com.vexsoluciones.comunal.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vexsoluciones.comunal.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        try{
            String json;
            InputStream imput = mContext.getAssets().open("data.json");
            int size = imput.available();
            byte[] buffer = new byte[size];
            imput.read(buffer);
            imput.close();
            json = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            return PlaceholderFragment.newInstance(position + 1,jsonObject.toString());
        }catch (Exception e){
            e.printStackTrace();
            return PlaceholderFragment.newInstance(position + 1,null);
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}