package cleverhouse.shms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity  implements CompoundButton.OnCheckedChangeListener , View.OnClickListener{

    //private TextView textView ;
    private Button btnConnect;
    private Switch switch3;

    String con1 , con2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textView = (TextView) findViewById(R.id.textView);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        switch3 = (Switch) findViewById(R.id.switch3);

        btnConnect.setOnClickListener(this);

        switch3.setText("Включить лампочку");
        switch3.setChecked(false);
        switch3.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Connect.class);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {switch3.setText("Выключить лампочку");}
        else
        {switch3.setText("Включить лампочку");}
    }
/*
    protected void onActivityResult(int requestCode, int resultCode, Intent data ) {
        if (data == null) {return;}
        con1 = data.getStringExtra("connectionInfo1");
        con2 = data.getStringExtra("connectionInfo2");
    }*/


}
