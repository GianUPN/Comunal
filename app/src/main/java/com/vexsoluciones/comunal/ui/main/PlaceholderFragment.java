package com.vexsoluciones.comunal.ui.main;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;
import com.vexsoluciones.comunal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_SECTION_JSON = "section_json";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index, String jsonObject) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        bundle.putString(ARG_SECTION_JSON,  jsonObject);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        JSONObject jsonObject = null;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            try {
                jsonObject = new JSONObject(getArguments().getString(ARG_SECTION_JSON));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        pageViewModel.setIndex(index,jsonObject);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView titulo = root.findViewById(R.id.titulo);
        final TextView descripcion = root.findViewById(R.id.descripcion);
        final ImageView imageView = root.findViewById(R.id.imgProducto);

        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        pageViewModel.getJSON().observe(this, new Observer<JSONObject>() {
            @Override
            public void onChanged(@Nullable JSONObject json) {
                try {
                    JSONArray jsonArray = json.getJSONArray("benefits");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String Stitulo = jsonObject.getString("business_name");
                    String Sdesc = jsonObject.getString("name");
                    String Simg = jsonObject.getString("image");
                    titulo.setText(Stitulo);
                    descripcion.setText(Sdesc);
                    Picasso.get()
                            .load(Simg)
                            .into(imageView);
                    //imageView.setImageURI();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return root;
    }
}