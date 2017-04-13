package ${packageName};

import com.ldy.flexframe.base.view.FlexBaseActivity;
<#if applicationPackage??>
import ${applicationPackage}.databinding.Activity${business}Binding;
import ${applicationPackage}.R;
</#if>
public class ${activityClass} extends FlexBaseActivity<${presenterClass},Activity${business}Binding> {

    @Override
    public int getContentId() {
        return R.layout.${layoutName};
    }
}