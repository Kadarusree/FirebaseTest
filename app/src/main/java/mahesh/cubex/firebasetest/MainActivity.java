package mahesh.cubex.firebasetest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference("Users");


      String token =  FirebaseInstanceId.getInstance().getToken();
        ///////////Inserting
        User mUser = new User("ABC K",2);
        mDatabaseReference.child("user2").setValue(mUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                }
                else {

                }
            }
        });

        //////////////////////

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot dp : dataSnapshot.getChildren()){
                    User user = dp.getValue(User.class);
                    Toast.makeText(getApplicationContext(), user.getName()+"=="+user.getId(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
