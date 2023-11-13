package zn.app.znapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Branch_Enquiry_Display extends AppCompatActivity {

    ImageView bt_back;
    TextView te_br_name,te_enquiry,te_course,te_mobile_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_enquiry_display);
        bt_back = findViewById(R.id.im_back);

        te_br_name = findViewById(R.id.te_br_name);
        te_enquiry = findViewById(R.id.te_enquiry);
        te_course = findViewById(R.id.te_course);
        te_mobile_no = findViewById(R.id.te_mobile_no);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}