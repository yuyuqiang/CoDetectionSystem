package cn.edu.nuc.codetectionsystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.edu.nuc.codetectionsystem.R;
import cn.edu.nuc.codetectionsystem.viewpage.ViewpageManage_Activity;

public class CoDataFragment extends BaseFragment {

    private Button view_btn = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.co_data_fragment,null);

        view_btn = view.findViewById(R.id.view_btn);
        view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), ViewpageManage_Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public static CoDataFragment newInstance(String param1) {
        CoDataFragment fragment = new CoDataFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void lazyLoad() {

    }


}
