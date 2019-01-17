package ui.app_activity;

import androidx.fragment.app.Fragment;

public class AppPresenter implements AppVP.Presenter{
    private AppVP.View av;

    public AppPresenter(AppVP.View av) {
        this.av = av;
    }

    @Override
    public void addFragment(Fragment fragment) {av.setFragment(fragment);}
}
