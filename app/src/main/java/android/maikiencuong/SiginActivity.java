package android.maikiencuong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SiginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText email, password;
    private Button btnSignin;
    private TextView textRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.signinEmail);
        password = findViewById(R.id.signinPassword);
        btnSignin = findViewById(R.id.btnSignin);
        textRegister = findViewById(R.id.textRegister);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()) {
                    String emailValue = email.getText().toString().trim();
                    String passvalue = password.getText().toString().trim();

                    auth.signInWithEmailAndPassword(emailValue, passvalue)
                            .addOnCompleteListener(SiginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(SiginActivity.this, FaceActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(SiginActivity.this, "????ng nh???p kh??ng th??nh c??ng: "
                                                + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SiginActivity.this, RegisterActivity.class));
                finish();
            }
        });

    }

    private boolean valid() {
        String emailValue = email.getText().toString().trim();
        String passvalue = password.getText().toString().trim();

        if (emailValue.equals("")) {
            email.setError("Email kh??ng ???????c ????? tr???ng");
            return false;
        }
        if (passvalue.equals("")) {
            password.setError("M???t kh???u kh??ng ???????c ????? tr???ng");
            return false;
        }

        return true;
    }
}