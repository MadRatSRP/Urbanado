package ui.map;

import androidx.fragment.app.Fragment;

public class MapPresenter implements MapVP.Presenter{
    private MapVP.View mv;

    public MapPresenter(MapVP.View mv) {
        this.mv = mv;
    }

    @Override
    public void addFragment(Fragment fragment) {mv.setFragment(fragment);}
}
