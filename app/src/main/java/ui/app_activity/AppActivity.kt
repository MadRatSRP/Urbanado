package ui.app_activity

import androidx.fragment.app.Fragment
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

import butterknife.BindView
import butterknife.ButterKnife
import idd.urbanido.R
import ui.map.MapFragment

class AppActivity : AppCompatActivity(), AppVP.View {

    @BindView(R.id.drawer)
    internal var drawerLayout: DrawerLayout? = null

    @BindView(R.id.toolbar)
    internal var toolbar: Toolbar? = null

    private var appPresenter: AppPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        ButterKnife.bind(this)
        //toolbar = findViewById(R.id.toolbar);
        setUp()
    }

    override fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment).commit()
    }

    override fun setUp() {
        setMVP()
        setSupportActionBar(toolbar)
        //getSupportActionBar().setTitle("AppMain");
        appPresenter!!.addFragment(MapFragment())
    }

    override fun setMVP() {
        appPresenter = AppPresenter(this)
    }
}
