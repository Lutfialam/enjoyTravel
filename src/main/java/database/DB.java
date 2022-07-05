package database;

import java.io.FileInputStream;
import java.util.function.Consumer;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class DB {
  public FirebaseDatabase instance() {
    try {
      if (FirebaseApp.getApps().size() <= 0) {
		    FileInputStream serviceAccount = new FileInputStream(
          System.getProperty("user.dir") +
          "/enjoytravel-da533-firebase-adminsdk-cb0tn-5b013f943a.json"
        );
	
        FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl("https://enjoytravel-da533-default-rtdb.asia-southeast1.firebasedatabase.app")
          .setStorageBucket("enjoytravel-da533.appspot.com")
          .build();
        FirebaseApp.initializeApp(options);
      }

      return FirebaseDatabase.getInstance();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
  
  public void valueListener(DatabaseReference ref, Consumer<DataSnapshot> callback) {
    ref.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        callback.accept(dataSnapshot);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        System.out.println("The read failed: " + databaseError.getCode());
        callback.accept(null);
      }
    });
  }

  public void singleValueListener(DatabaseReference ref, Consumer<DataSnapshot> callback) {
    ref.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        callback.accept(dataSnapshot);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        System.out.println("The read failed: " + databaseError.getCode());
        callback.accept(null);
      }
    });
  }
}
