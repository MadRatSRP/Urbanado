package ui.app_activity

import androidx.fragment.app.Fragment

interface AppVP {

    interface View {
        fun setUp()
        fun setMVP()
        //fun setFragment(fragment: Fragment)
    }

    interface Presenter {
        fun addFragment(fragment: Fragment)
    }
}
