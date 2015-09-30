package me.liupei.materialplaybutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btDL, btDM, btDR;
    private MaterialPlayDrawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView iv = (ImageView) findViewById(R.id.iv);
        drawable = new MaterialPlayDrawable(this, iv, dp2px(24));
        iv.setImageDrawable(drawable);

        btDL = (Button) findViewById(R.id.down_left);
        btDM = (Button) findViewById(R.id.down_mid);
        btDR = (Button) findViewById(R.id.down_right);

        btDL.setOnClickListener(this);
        btDM.setOnClickListener(this);
        btDR.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.down_left:
                drawable.switchStatus(MaterialPlayDrawable.STATUS_PLAY);
                break;
            case R.id.down_mid:
                drawable.switchStatus(MaterialPlayDrawable.STATUS_PAUSE);
                break;
            case R.id.down_right:
                drawable.switchStatus(MaterialPlayDrawable.STATUS_STOP);
                break;

        }
    }

    private int dp2px(int dp) {
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        return (int) (dp * metrics.density);
    }
}
