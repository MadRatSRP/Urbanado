package ui.map

import androidx.fragment.app.Fragment

interface MapVP {

    interface View {
        fun setMVP()
        fun setFragment(fragment: Fragment)
    }

    interface Presenter {
        fun addFragment(fragment: Fragment)
    }
}
