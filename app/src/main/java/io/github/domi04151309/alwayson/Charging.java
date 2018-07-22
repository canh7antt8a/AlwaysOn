package io.github.domi04151309.alwayson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class Charging extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        View mContentView = findViewById(R.id.chargingLayout);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        animation();
    }

    private void animation() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    ImageView CI = findViewById(R.id.chargingImage);
                    CI.animate().alpha(0).setDuration(500);
                    Thread.sleep(500);
                    close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    private void close() {
        try {
            Process proc = Runtime.getRuntime()
                    .exec(new String[]{ "su", "-c", "input keyevent KEYCODE_POWER" });
            proc.waitFor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Charging.this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }
}
