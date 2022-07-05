package database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import model.ReviewModel;

public class Review extends DB {
    DatabaseReference destination = instance().getReference("destination");
    String message = "";
    
    public String insertData(String destination_id, String name, String comment) {
        DatabaseReference reviews = destination.child(destination_id).child("reviews");
        reviews.push().setValueAsync(new ReviewModel(name, comment));
        return "success";
    }
    
    public ReviewModel getDetailData(String id, String destination_id) {
        DatabaseReference reviews = destination.child(destination_id).child("reviews");
        ReviewModel data = new ReviewModel();

        reviews.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data.setId(dataSnapshot.getKey());
                data.setName(dataSnapshot.child("name").getValue(String.class));
                data.setcomment(dataSnapshot.child("comment").getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return data;
    }
}
