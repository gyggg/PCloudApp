package imu.pcloud.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import imu.pcloud.app.R;
import imu.pcloud.app.activity.PlanCircleActivity;
import imu.pcloud.app.been.PlanCircle;
import imu.pcloud.app.model.PlanCircleList;
import imu.pcloud.app.adapter.MyAdspter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by acer on 2016/5/11.
 */
public class ZoneFragment extends HttpFragment {
    private ListView listView1;
    private List<PlanCircle> planCircles;
    private List<Map<String, Object>> list;
    MyAdspter myAdspter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zone_layout, container, false);
        listView1 = (ListView) view.findViewById(R.id.zone_listView);
        get("getPlanCircleList");
        initActionBar();
        return view;
    }

    @Override
    public void onResume() {
        //initActionBar();
        refreshData();
        super.onResume();
    }

    private void refreshData() {
        get("getPlanCircleList");
        //init();
    }

    private void initActionBar() {
        View actionbarLayout = LayoutInflater.from(this.getActivity()).inflate(
                R.layout.actionbar_fra_layout, null);
        TextView textview=(TextView) actionbarLayout.findViewById(R.id.acText);
        textview.setText("计划圈");
        getActivity().getActionBar().setCustomView(actionbarLayout);
    }

    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < planCircles.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", R.drawable.header);
            map.put("name",planCircles.get(i).getName());
            map.put("id",planCircles.get(i).getId());
            // map.put("info", "这是一个详细信息" + i);
            list.add(map);
        }
        return list;
    }

    public void onHiddenChanged(boolean hidden) {
        if(hidden == false) {
            initActionBar();
            refreshData();
        }
        super.onHiddenChanged(hidden);
    }

    private void  init(){
        list = getData();
        myAdspter = new MyAdspter(getActivity().getApplicationContext(), list);
        listView1.setAdapter(myAdspter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity().getApplicationContext(),PlanCircleActivity.class);
                intent.putExtra("planCircleID",(Integer) list.get(position).get("id"));
                intent.putExtra("planCircleName",(String) list.get(position).get("name"));
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onFailure() {
        toast("网络连接失败");
        super.onFailure();
    }

    @Override
    protected void onSuccess() {
        PlanCircleList planCircleList = getObject(PlanCircleList.class);
        if(planCircleList.getStatus() ==0) {
            planCircles = planCircleList.getPlanCircles();
            init();
        }
    }
}
