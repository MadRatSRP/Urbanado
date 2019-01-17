package ui.app_activity;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import idd.urbanido.R;
import ui.map.MapFragment;
import ui.map.MapVP;

public class AppActivity extends AppCompatActivity
    implements AppVP.View{

    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private AppPresenter appPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        ButterKnife.bind(this);
        //toolbar = findViewById(R.id.toolbar);
        setUp();
    }

    @Override
    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, fragment).commit();
    }

    @Override
    public void setUp() {
        setMVP();
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("AppMain");
        appPresenter.addFragment(new MapFragment());
    }

    @Override
    public void setMVP() {
        appPresenter = new AppPresenter(this);
    }
}
