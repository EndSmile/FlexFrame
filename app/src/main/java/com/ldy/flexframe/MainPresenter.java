package com.ldy.flexframe;

import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.ldy.flexframe.base.BasePresenter;
import com.ldy.flexframe.base.util.Navigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldy on 2017/3/31.
 */

public class MainPresenter extends BasePresenter {

    public TestRecycleAdapter testRecycleAdapter;
    public ObservableField<List<String>> list = new ObservableField<>();
    public ObservableField<String> text = new ObservableField<>();
    @Bindable
    public String textK = "kgg";

    @Override
    protected void onCreate() {
        super.onCreate();
        List<String> strings = new ArrayList<>();
//        strings.add("1");
//        strings.add("2");
//        strings.add("3");
//        strings.add("4");
//        strings.add("5");
//        strings.add("6");
        testRecycleAdapter = new TestRecycleAdapter(strings);
        list.set(strings);
        text.set("ldy");
    }

    public void load() {
//        List<String> list = this.list.get();
//        if (list == null) {
//            list = new ArrayList<>();
//        }
//        text.set(text.get()+"x");
//        list.add("ldy");
//        setTextK(textK+"1");
//        this.list.notifyChange();
        Navigator.navigation(LoginActivity.class);
    }


//    @Bindable
//    public String getTextK() {
//        return textK;
//    }

    public void setTextK(String textK) {
        this.textK = textK;
        notifyPropertyChanged(BR.textK);
    }
}
