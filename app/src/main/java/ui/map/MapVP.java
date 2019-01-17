package ui.map;

import android.support.v4.app.Fragment;

public interface MapVP {

    interface View {
        void setMVP();
        void setFragment(Fragment fragment);
    }

    interface Presenter {
        void addFragment(Fragment fragment);
    }
}
