package ui.app_activity;

import android.support.v4.app.Fragment;

public interface AppVP {

    interface View {
        void setUp();
        void setMVP();
        void setFragment(Fragment fragment);
    }

    interface Presenter {
        void addFragment(Fragment fragment);
    }
}
