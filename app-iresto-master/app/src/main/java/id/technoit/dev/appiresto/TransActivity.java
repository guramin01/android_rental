package id.technoit.dev.appiresto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TransActivity extends AppCompatActivity {
    public static final String ROOT_URL = "http://192.168.43.139/project_rental/api_mobil/index.php/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
    }
}
