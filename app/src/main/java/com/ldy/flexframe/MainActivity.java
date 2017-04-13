package com.ldy.flexframe;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.ldy.flexframe.base.view.FlexBaseActivity;
import com.ldy.flexframe.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends FlexBaseActivity<MainPresenter, ActivityMainBinding> {

    @Override
    public int getContentId() {
        return R.layout.activity_main;
    }

    @BindingAdapter({"bind:list"})
    public static void updateList(RecyclerView recyclerView, List<String> list){
        if (list==null||list.isEmpty()){
            return;
        }
        ((TestRecycleAdapter) recyclerView.getAdapter()).updateList(list);
    }
}
