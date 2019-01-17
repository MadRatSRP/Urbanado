package ui.map

import androidx.fragment.app.Fragment

class MapPresenter(private val mv: MapVP.View) : MapVP.Presenter {

    override fun addFragment(fragment: Fragment) {
        mv.setFragment(fragment)
    }
}
