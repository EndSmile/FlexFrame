package com.ldy.flexframe;

import com.ldy.flexframe.base.view.FlexBaseActivity;
import com.ldy.flexframe.databinding.ActivityLoginBinding;

public class LoginActivity extends FlexBaseActivity<LoginPresenter,ActivityLoginBinding> {

    @Override
    public int getContentId() {
        return R.layout.activity_login;
    }
}
