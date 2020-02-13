package com.vexsoluciones.comunal.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });
    private MutableLiveData<JSONObject> mjsonObjectIndex = new MutableLiveData<>();
    private LiveData<JSONObject> mjsonObject = Transformations.map(mjsonObjectIndex, new Function<JSONObject, JSONObject>() {
        @Override
        public JSONObject apply(JSONObject input) {
            return input;
        }
    });

    public void setIndex(int index,JSONObject json) {
        mIndex.setValue(index);
        mjsonObjectIndex.setValue(json);
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<JSONObject> getJSON() {
        return mjsonObject;
    }
}