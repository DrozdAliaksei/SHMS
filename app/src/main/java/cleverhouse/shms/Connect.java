package cleverhouse.shms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Connect extends AppCompatActivity implements View.OnClickListener , CompoundButton.OnCheckedChangeListener {

    EditText etName;
    EditText etName2;
    Button btnOK;
    TextView status;
    Switch statusConnection;

    WifiManager wifiManager;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           // int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, Integer.parseInt(WifiManager.EXTRA_PREVIOUS_WIFI_STATE));

            int wifiState = wifiManager.getWifiState();
            switch (wifiState){
                case WifiManager.WIFI_STATE_ENABLING:
                    Toast toastEnabling = Toast.makeText(getApplicationContext(),"WIFI_STATE_ENABLING",Toast.LENGTH_SHORT);
                    toastEnabling.show();
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    Toast toastEnabled = Toast.makeText(getApplicationContext(),"WIFI_STATE_ENABLED",Toast.LENGTH_SHORT);
                    toastEnabled.show();
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    Toast toastDisabling = Toast.makeText(getApplicationContext(),"WIFI_STATE_DISABLING",Toast.LENGTH_SHORT);
                    toastDisabling.show();
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    Toast toastDisabled = Toast.makeText(getApplicationContext(),"WIFI_STATE_ENABLING",Toast.LENGTH_SHORT);
                    toastDisabled.show();
                    break;
                case WifiManager.WIFI_STATE_UNKNOWN:
                    Toast toastUnknown = Toast.makeText(getApplicationContext(),"WIFI_STATE_UNKNOWN",Toast.LENGTH_SHORT);
                    toastUnknown.show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_connect);

        etName = (EditText) findViewById(R.id.etName);
        etName2 = (EditText) findViewById(R.id.etName2);
        btnOK = (Button) findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);
        status = (TextView) findViewById(R.id.status);
        statusConnection = (Switch) findViewById(R.id.statusConnection);
        statusConnection.setOnCheckedChangeListener(this);
        /*
        if (wifiManager.getWifiState()== WifiManager.WIFI_STATE_DISABLED){
            statusConnection.setChecked(false);
        }
        else
            statusConnection.setChecked(true);
        */
        statusConnection.setChecked(false);
        registerReceiver(receiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("connectInfo1", etName.getText().toString());
        intent.putExtra("connectInfo2", etName2.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked == false){
            statusConnection.setText("Wifi_OFF");
            statusConnection.setChecked(false);
            wifiManager.setWifiEnabled(false);
        }
        if(isChecked == true) {
            statusConnection.setText("Wifi_ON");
            statusConnection.setChecked(true);
            wifiManager.setWifiEnabled(true);
        }
    }

    /*
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED && isChecked == true) {
            status.setText("WIFI_ENABLED");
            wifiManager.setWifiEnabled(true);
        }
        if(wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED && isChecked == false) {
            status.setText("WIFI_DISABLED");
            wifiManager.setWifiEnabled(false);
        }
    }
    */

}
