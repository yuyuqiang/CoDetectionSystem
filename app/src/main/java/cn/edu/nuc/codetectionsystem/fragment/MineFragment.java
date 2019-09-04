package cn.edu.nuc.codetectionsystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.edu.nuc.codetectionsystem.R;
import cn.edu.nuc.codetectionsystem.login.Login_Activity;
import cn.edu.nuc.codetectionsystem.until.SaveUtils;

public class MineFragment extends Fragment {

    private TextView name_mine=null;
    private TextView phone_mine=null;
    private String username_mine;
    private String telephone_mine;
    private LinearLayout exit=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment,null);
        name_mine= view.findViewById(R.id.name_mine);
        phone_mine = view.findViewById(R.id.phone_mine);
        exit = view.findViewById(R.id.exit);
        init();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Login_Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void init(){
        username_mine = SaveUtils.getSettingNote(getContext(), "userInfo", "username");
        telephone_mine = SaveUtils.getSettingNote(getContext(), "userInfo", "number");
        Log.e("mine", "init: "+username_mine+telephone_mine );
        name_mine.setText(username_mine);
        phone_mine.setText(telephone_mine);

    }

    public static MineFragment newInstance(String param1) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }
}
