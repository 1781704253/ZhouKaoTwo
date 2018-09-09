package zhangyanran201800906.bwie.com.day07.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhangyanran201800906.bwie.com.day07.MyView.CustomView;
import zhangyanran201800906.bwie.com.day07.R;

public class LiuShi extends AppCompatActivity {

    private EditText edit;
    private Button but;
    private CustomView afl_cotent;
    private LayoutInflater layoutInflater;
    private TextView tvAttrTag;
    private List<String> list2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liu_shi);
        initView();
    }
    private void initView() {
        edit = findViewById(R.id.edt);
        but =  findViewById(R.id.but);
        afl_cotent =findViewById(R.id.afl_cotent);
        layoutInflater = LayoutInflater.from(this);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit.getText().toString();
                View item = layoutInflater.inflate(R.layout.sub_item, null);
                tvAttrTag = item.findViewById(R.id.tv_attr_tag);
                list2.add(s);
                for (int i = 0; i < list2.size(); i++) {
                    tvAttrTag.setText(list2.get(i));
                    tvAttrTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(LiuShi.this, ShowActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                afl_cotent.addView(item);

            }
        });
    }
}
