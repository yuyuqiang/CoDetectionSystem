package cn.edu.nuc.codetectionsystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.codetectionsystem.R;
import cn.edu.nuc.codetectionsystem.cars.Car;
import cn.edu.nuc.codetectionsystem.cars.CarAdapter;
import cn.edu.nuc.codetectionsystem.viewpage.ViewpageManage_Activity;

public class PhoneFragment extends BaseFragment {

    private static List<Car> carList = new ArrayList<>();
    public static CarAdapter adapter;
    private ListView car_listview;
    private TextView detile_tv;
    private ImageButton car_add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phone_fragment,null);

        init();

        adapter = new CarAdapter(getContext(), R.layout.car_item, carList);
        car_listview = (ListView) view.findViewById(R.id.car_listview);
        car_add =(ImageButton)view.findViewById(R.id.car_add);


        car_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car car = carList.get(position);

            }
        });
        car_listview.setAdapter(adapter);

        car_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(getContext(), Car_ADD_Activity.class);
                startActivity(intent1);
            }
        });


        return view;
    }

    public static PhoneFragment newInstance(String param1) {
        PhoneFragment fragment = new PhoneFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void lazyLoad() {

    }

    private void init(){

        for(int i =0;i<2;i++){
            carList.clear();
            Car car = new Car(R.drawable.theme3,R.drawable.ic_warning,"用户：","何德庆","电话:",
                    "1845678904","性别:","男",R.drawable.ic_license,"888",
                    R.drawable.ic_sensor,R.drawable.ic_sensor,"200","200","ppm","ppm","详情");
            carList.add(car);
            Car car1 = new Car(R.drawable.theme3,R.drawable.ic_warning,"用户：","何德庆","电话:",
                    "1845678904","性别:","男",R.drawable.ic_license,"888",
                    R.drawable.ic_sensor,R.drawable.ic_sensor,"200","200","ppm","ppm","详情");

            carList.add(car1);

        }

    }


}
