package com.monitoratec.monitora.dagger;

import com.monitoratec.monitora.dagger.scope.PerActivity;
import com.monitoratec.monitora.presentation.ui.auth.AuthActivity;
import com.monitoratec.monitora.dagger.module.presentation.*;
import dagger.Subcomponent;

/**
 * Created by ricardo.sgobbe on 14/01/2017.
 */

@PerActivity
@Subcomponent(modules = {PresenterModule.class})
public interface UiComponent {
    void inject(AuthActivity activity);
}
