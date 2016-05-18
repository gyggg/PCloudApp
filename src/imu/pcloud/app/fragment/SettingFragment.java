package imu.pcloud.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import imu.pcloud.app.R;
import imu.pcloud.app.activity.PlanCircleActivity;
import imu.pcloud.app.activity.UserSharingActivity;

/**
 * Created by acer on 2016/5/11.
 */
public class SettingFragment extends HttpFragment implements View.OnClickListener{
    View head;
    View mySharing;
    View myAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.individual_activity, container, false);
        head = view.findViewById(R.id.personal_info);
        mySharing = view.findViewById(R.id.my_sharing);
        myAccount = view.findViewById(R.id.my_account);
        head.setOnClickListener(this);
        mySharing.setOnClickListener(this);
        myAccount.setOnClickListener(this);

        return view;
    }

    @Override
    protected void onSuccess() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_info:
            case R.id.my_sharing:
                Intent intent = new Intent();
                intent.setClass(getActivity().getApplicationContext(),UserSharingActivity.class);
                startActivity(intent);
                break;
            case R.id.my_account:
                toast("hahah");
        }
    }
}
