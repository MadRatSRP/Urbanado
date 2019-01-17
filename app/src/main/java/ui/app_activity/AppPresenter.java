package ui.app_activity;

import android.support.v4.app.Fragment;

public class AppPresenter implements AppVP.Presenter{
    AppVP.View av;

    public AppPresenter(AppVP.View av) {
        this.av = av;
    }

    @Override
    public void addFragment(Fragment fragment) {av.setFragment(fragment);}
}
