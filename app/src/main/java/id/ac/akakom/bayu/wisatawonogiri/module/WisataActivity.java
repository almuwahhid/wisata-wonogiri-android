package id.ac.akakom.bayu.wisatawonogiri.module;

import android.content.Context;
import android.os.Bundle;

import id.ac.akakom.bayu.wisatawonogiri.R;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import lib.alframeworkx.Activity.ActivityGeneral;
import lib.alframeworkx.Activity.ActivityPermission;

public class WisataActivity extends ActivityPermission {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewPump.init(new ViewPump.Builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/SanFransisco-Regular.otf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
