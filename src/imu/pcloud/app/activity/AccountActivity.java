package imu.pcloud.app.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import imu.pcloud.app.R;
import imu.pcloud.app.utils.FileTool;
import imu.pcloud.app.utils.SysApplication;

/**
 * Created by guyu on 2016/5/28.
 */
public class AccountActivity extends HttpActivity implements View.OnClickListener{

    View password;
    Button logout;
    Button clear_cache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setActionBar(R.layout.actionbar_check_layout, "账户信息");
        setContentView(R.layout.reaccount_activity);
        password = find(R.id.password);
        logout = find(R.id.logout);
        clear_cache = find(R.id.clear_cache);
        password.setOnClickListener(this);
        logout.setOnClickListener(this);
        clear_cache.setOnClickListener(this);
    }

    @Override
    protected void onSuccess() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.password:
                startActivity(RepasswordActivity.class);
                break;
            case R.id.logout:
                get("logout", "cookies", getCookie());
                setCookie("");
                SysApplication.getInstance().exit();
                Bundle data = new Bundle();
                data.putInt("status", 0);
                startActivity(LoginActivity.class, data);
                finish();
                break;
            case R.id.clear_cache:
                imageUtil.clearImage();
                FileTool.deleteDir();
                toast("清空缓存成功");
                break;
        }
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        finish();
        return super.onMenuItemSelected(featureId, item);
    }
}
