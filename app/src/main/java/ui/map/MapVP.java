package ui.map;

import androidx.fragment.app.Fragment;

public interface MapVP {

    interface View {
        void setMVP();
        void setFragment(Fragment fragment);
    }

    interface Presenter {
        void addFragment(Fragment fragment);
    }
}
