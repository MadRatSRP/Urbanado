package ui.map;

import android.support.v4.app.Fragment;

public class MapPresenter implements MapVP.Presenter{
    MapVP.View mv;

    public MapPresenter(MapVP.View mv) {
        this.mv = mv;
    }

    @Override
    public void addFragment(Fragment fragment) {mv.setFragment(fragment);}
}
