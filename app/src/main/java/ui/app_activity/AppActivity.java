package ui.app_activity;

import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import idd.urbanido.R;
import ui.map.MapFragment;

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
