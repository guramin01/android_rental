package id.technoit.dev.appiresto.controllers;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.technoit.dev.appiresto.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtIDUser, txtNamaUser, txtPassword, txtKonfirmasiPassword, txtEmail,txtAlamat,txtNo,txtJenis,txtNik;
    private TextInputLayout validasiIDUser, validasiNamaUser,
            validasiPassword, validasiKonfirmasiPassword,validasiEmail,validasiAlamat,validasiNo,validasiJenis,validasiNik;
    private Button btnRegister;
    private ProgressBar loading;

    private static String URL = "http://192.168.43.139/project_rental/api_mobil/index.php/api/api_register";

    private String username, nama, password, konfirmasi_password, email,
            alamat, no_hp, jenis_kelamin, nik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtIDUser = findViewById(R.id.txtIDUser);
        txtNamaUser = findViewById(R.id.txtNamaUser);
        txtPassword = findViewById(R.id.txtPassword);
        txtKonfirmasiPassword = findViewById(R.id.txtKonfirmasiPassword);
        txtEmail = findViewById(R.id.txtEmail);
        txtAlamat = findViewById(R.id.txtAlamat);
        txtNo = findViewById(R.id.txtNo);
        txtNik = findViewById(R.id.txtNik);
        txtJenis= findViewById(R.id.txtJenis);

        validasiIDUser = findViewById(R.id.validasiIDUser);
        validasiNamaUser = findViewById(R.id.validasiNamaUser);
        validasiPassword = findViewById(R.id.validasiPassword);
        validasiKonfirmasiPassword = findViewById(R.id.validasiKonfirmasiPassword);
        validasiEmail= findViewById(R.id.validasiEmail);
        validasiAlamat= findViewById(R.id.validasiAlamat);
        validasiNo= findViewById(R.id.validasiNo);
        validasiNik= findViewById(R.id.validasiNik);
        validasiJenis = findViewById(R.id.validasiJenis);

        btnRegister = findViewById(R.id.btnRegister);
        loading = findViewById(R.id.loading);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = txtIDUser.getText().toString().trim();
                nama = txtNamaUser.getText().toString().trim();
                password = txtPassword.getText().toString().trim();
                konfirmasi_password = txtKonfirmasiPassword.getText().toString().trim();
                email = txtEmail.getText().toString().trim();
                alamat = txtAlamat.getText().toString().trim();
                no_hp = txtNo.getText().toString().trim();
                nik = txtNik.getText().toString().trim();
                jenis_kelamin = txtJenis.getText().toString().trim();
                if(username.isEmpty()) {
                    validasiIDUser.setError("ID. User harus diisi!");
                }else if(nama.isEmpty()){
                    validasiNamaUser.setError("Nama User harus diisi!");
                }else if(password.isEmpty()){
                    validasiPassword.setError("Password harus diisi!");
                }else if(email.isEmpty()){
                    validasiEmail.setError("Password harus diisi!");
                }else if(alamat.isEmpty()){
                    validasiAlamat.setError("Password harus diisi!");
                }else if(no_hp.isEmpty()){
                    validasiNo.setError("Password harus diisi!");
                }else if(nik.isEmpty()){
                    validasiNik.setError("Password harus diisi!");
                }else if(jenis_kelamin.isEmpty()){
                    validasiJenis.setError("Password harus diisi!");
                }else if(!konfirmasi_password.equals(password)){
                    validasiKonfirmasiPassword.setError("Konfirmasi password harus sama!");
                }else{
                    Registrasi();
                }

            }
        });
    }

    private void Registrasi() {
        loading.setVisibility(View.VISIBLE);
        btnRegister.setVisibility(View.GONE);
        username = this.txtIDUser.getText().toString().trim();
        nama= this.txtNamaUser.getText().toString().trim();
        password = this.txtPassword.getText().toString().trim();
        konfirmasi_password = this.txtKonfirmasiPassword.getText().toString().trim();
        alamat= this.txtAlamat.getText().toString().trim();
        email= this.txtEmail.getText().toString().trim();
        nik= this.txtNik.getText().toString().trim();
        no_hp= this.txtNo.getText().toString().trim();
        jenis_kelamin= this.txtJenis.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("sukses");
                            if(success.equals("1")){
                                Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);

                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register Error : " +e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btnRegister.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Register Error : " +error.toString(), Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
                btnRegister.setVisibility(View.VISIBLE);
            }
        })
        {
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("nama", nama);
                params.put("alamat", alamat);
                params.put("no_hp", no_hp);
                params.put("nik", nik);
                params.put("email", email);
                params.put("jenis_kelamin", jenis_kelamin);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
