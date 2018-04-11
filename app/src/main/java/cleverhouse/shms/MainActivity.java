package cleverhouse.shms;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements CompoundButton.OnCheckedChangeListener , View.OnClickListener{

    private TextView textView ;
    private Button btnConnect;
    private Switch switch3;

    String con1 , con2;

    private final IntentFilter intentFilter = new IntentFilter();
   // WifiP2pManager.Channel mChannel;
   // WifiP2pManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        switch3 = (Switch) findViewById(R.id.switch3);

        btnConnect.setOnClickListener(this);

        switch3.setText("Включить лампочку");
        switch3.setChecked(false);
        switch3.setOnCheckedChangeListener(this);

/*
        // Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
  */
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Connect.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked == true)
        {switch3.setText("Выключить лампочку");}
        if(isChecked == false)
        {switch3.setText("Включить лампочку");}
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data ) {
        if (data == null) {return;}
        con1 = data.getStringExtra("connectionInfo1");
        con2 = data.getStringExtra("connectionInfo2");
    }


}
