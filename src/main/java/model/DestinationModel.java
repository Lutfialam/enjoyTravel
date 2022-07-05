package model;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.io.Serializable;
import java.util.ArrayList;
import service.FileStorage;
import com.google.firebase.database.DataSnapshot;

public class DestinationModel implements Serializable {
    String id;
    String name;
    String detail;
    String search_key;
    String photo;
    String background;
    boolean favorite;
    List<ReviewModel> reviews;

    CountDownLatch countDownLatch = new CountDownLatch(1);

    private void waitListener() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public DestinationModel setDestination(DataSnapshot snapshot) {
        FileStorage fs = new FileStorage();
        DestinationModel destination = new DestinationModel();

        String photo = snapshot.child("photo").getValue(String.class);
        String background = snapshot.child("background").getValue(String.class);

        destination.setId(snapshot.getKey());
        destination.setName(snapshot.child("name").getValue(String.class));
        destination.setdetail(snapshot.child("detail").getValue(String.class));
        destination.setSearch_key(snapshot.child("search_key").getValue(String.class));
        destination.setPhoto(fs.hasFile(photo));
        destination.setBackground(fs.hasFile(background));

        if (snapshot.hasChild("favorite")) {
            destination.setFavorite(snapshot.child("favorite").getValue(Boolean.class));
        }

        if (snapshot.hasChild("reviews")) {
            List<ReviewModel> reviews = new ArrayList<ReviewModel>();
            for (DataSnapshot reviewSnapshot : snapshot.child("reviews").getChildren()) {
                ReviewModel review = new ReviewModel();
                review.setId(reviewSnapshot.getKey());
                review.setName(reviewSnapshot.child("name").getValue(String.class));
                review.setcomment(reviewSnapshot.child("comment").getValue(String.class));
                reviews.add(review);
            }
            destination.setReview(reviews);
        }
        countDownLatch.countDown();
        waitListener();
        return destination;
    }

    public DestinationModel() {
    }

    public DestinationModel(String id, String name, String detail, String search_key, boolean favorite) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.search_key = search_key;
        this.favorite = favorite;
    }

    public DestinationModel(String id, String name, String detail, String search_key, String photo, String background, List<ReviewModel> list, boolean favorite) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.search_key = search_key;
        this.photo = photo;
        this.background = background;
        this.reviews = list;
        this.favorite = favorite;
    }
    
    public DestinationModel(String name, String detail, String search_key, String photo, String background,
            List<ReviewModel> list, boolean favorite) {
        this.name = name;
        this.detail = detail;
        this.search_key = search_key;
        this.photo = photo;
        this.background = background;
        this.reviews = list;
        this.favorite = favorite;
    }
    
    public DestinationModel(String id, String name, String detail, String search_key, String photo, String background, List<ReviewModel> list) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.search_key = search_key;
        this.photo = photo;
        this.background = background;
        this.reviews = list;
    }
    
    public DestinationModel(String name, String detail, String search_key, String photo, String background, List<ReviewModel> list) {
        this.name = name;
        this.detail = detail;
        this.search_key = search_key;
        this.photo = photo;
        this.background = background;
        this.reviews = list;
    }

    public DestinationModel(String id, String name, String detail, String search_key, String photo, String background) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.search_key = search_key;
        this.photo = photo;
        this.background = background;
    }

    public DestinationModel(String name, String detail, String search_key, String photo, String background) {
        this.name = name;
        this.detail = detail;
        this.search_key = search_key;
        this.photo = photo;
        this.background = background;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getdetail() {
        return detail;
    }

    public void setdetail(String detail) {
        this.detail = detail;
    }

    public String getSearch_key() {
        return search_key;
    }

    public void setSearch_key(String search_key) {
        this.search_key = search_key;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public List<ReviewModel> getReview() {
        return reviews;
    }

    public void setReview(List<ReviewModel> reviews) {
        this.reviews = reviews;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
