package ui.app_activity

import androidx.fragment.app.Fragment

class AppPresenter(private val av: AppVP.View) : AppVP.Presenter {

    override fun addFragment(fragment: Fragment) {
        //av.setFragment(fragment)
    }
}
