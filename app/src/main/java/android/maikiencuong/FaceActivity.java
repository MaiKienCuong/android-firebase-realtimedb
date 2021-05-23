package android.maikiencuong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FaceActivity extends AppCompatActivity {

    private ImageButton btnSmile, btnAngry, btnBored;
    private Button btnFinish, btnSignout;

    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private String uid = "";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
        getSupportActionBar().hide();

        btnAngry = findViewById(R.id.btnAngry);
        btnSmile = findViewById(R.id.btnSmile);
        btnBored = findViewById(R.id.btnBored);
        btnFinish = findViewById(R.id.btnFinish);
        btnSignout = findViewById(R.id.btnSignout);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(FaceActivity.this, MainActivity.class));
            finish();
        }
        uid = auth.getCurrentUser().getUid();
        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    user.setBored(user.getBored() + 1);
                    databaseReference.child(uid).child("bored").setValue(user.getBored());
                    Toast.makeText(FaceActivity.this, "bored: " + user.getBored(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSmile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    user.setSmile(user.getSmile() + 1);
                    databaseReference.child(uid).child("smile").setValue(user.getSmile());
                    Toast.makeText(FaceActivity.this, "smile: " + user.getSmile(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    user.setAngry(user.getAngry() + 1);
                    databaseReference.child(uid).child("angry").setValue(user.getAngry());
                    Toast.makeText(FaceActivity.this, "angry: " + user.getAngry(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(FaceActivity.this, MainActivity.class));
                finish();
            }
        });
    }

}