package id.ac.akakom.bayu.wisatawonogiri.app.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.akakom.bayu.wisatawonogiri.R;
import id.ac.akakom.bayu.wisatawonogiri.app.main.MainActivity;
import id.ac.akakom.bayu.wisatawonogiri.module.WisataActivity;
import id.ac.akakom.bayu.wisatawonogiri.utils.UtilWisata;
import lib.alframeworkx.utils.AlStatic;

public class SplashScreenActivity extends WisataActivity {

    @BindView(R.id.img_logo)
    ImageView img_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        YoYo.with(Techniques.FadeIn)
                .duration(1000)
                .repeat(0)
                .playOn(img_logo);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!UtilWisata.isPreLolipop()){
                    Bundle data = new Bundle();
                    data.putSerializable("ok", "");

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this, img_logo, ViewCompat.getTransitionName(img_logo));
                    startActivity(new Intent(getContext(), MainActivity.class).putExtras(data), options.toBundle());
                }else{
                    startActivity(new Intent(getContext(), MainActivity.class));
                }
                finish();
            }
        }, 1500);
    }
}
