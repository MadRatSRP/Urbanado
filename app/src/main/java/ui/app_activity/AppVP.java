package ui.app_activity;

import androidx.fragment.app.Fragment;

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
