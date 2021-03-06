package imu.pcloud.app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import imu.pcloud.app.R;
import imu.pcloud.app.activity.*;
import imu.pcloud.app.model.ImageModel;
import imu.pcloud.app.model.UserModel;
import imu.pcloud.app.utils.ImageUtil;
import imu.pcloud.app.utils.Information;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 * Created by acer on 2016/5/11.
 */
public class SettingFragment extends HttpFragment implements View.OnClickListener, ImageUtil.OnLoadListener{
    View head;
    View mySharing;
    View myAccount;
    ImageView header;
    TextView nickName;
    TextView email;
    UserModel userModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userModel = getUserModel();
        View view = inflater.inflate(R.layout.individual_activity, container, false);

        head = view.findViewById(R.id.personal_info);
        mySharing = view.findViewById(R.id.my_sharing);
        myAccount = view.findViewById(R.id.my_account);
        header = (ImageView) view.findViewById(R.id.personal_header);
        head.setOnClickListener(this);
        header.setOnClickListener(this);
        mySharing.setOnClickListener(this);
        myAccount.setOnClickListener(this);
        nickName = (TextView) view.findViewById(R.id.nick_name);
        email = (TextView) view.findViewById(R.id.email);
        imageUtil.setOnLoadListener(this);
        initActionBar();
        //initView();
        return view;
    }

    @Override
    protected void onSuccess() {

    }

    public void initView() {
        header.setBackgroundDrawable(imageUtil.getHeader(getUserId(), userModel.getHeaderImageId()));
        nickName.setText(userModel.getUsername());
        email.setText(userModel.getEmail());
    }

    @Override
    public void onResume() {
        //initActionBar();
        initView();
        userModel = getUserModel();
        nickName.setText(userModel.getUsername());
        email.setText(userModel.getEmail());
        super.onResume();
    }

    private void initActionBar() {
        View actionbarLayout = LayoutInflater.from(this.getActivity()).inflate(
                R.layout.actionbar_fra_layout, null);
        TextView textview=(TextView) actionbarLayout.findViewById(R.id.acText);
        textview.setText("我");
        getActivity().getActionBar().setCustomView(actionbarLayout);
    }

    public void onHiddenChanged(boolean hidden) {
        if(hidden == false) {
            initActionBar();
            initView();
        }
        super.onHiddenChanged(hidden);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_info:
                Bundle data = new Bundle();
                startActivity(InformationActivity.class, data);
                break;
            case R.id.my_sharing:
                startActivity(UserSharingActivity.class);
                break;
            case R.id.my_account:
                startActivity(AccountActivity.class);
                break;
//            case R.id.personal_header:
//                showPopupWindow(header);
//                break;
        }
    }

    @Override
    public void onLoad(ImageModel imageModel) {
        header.setBackgroundDrawable(imageUtil.getHeader(getUserId(), userModel.getHeaderImageId()));
    }

}
