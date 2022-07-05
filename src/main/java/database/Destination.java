package database;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import model.DestinationModel;

public class Destination extends DB {
    DatabaseReference destinationRef = instance().getReference("destination");
    DestinationModel destinationModel = new DestinationModel();
    CountDownLatch countDownLatch = new CountDownLatch(1);
    private String message = "";

    private void waitListener() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a list of destination data
     *
     * @return the list of DestinationModel
     */
    public List<DestinationModel> getData() {
        List<DestinationModel> destinations = new ArrayList<DestinationModel>();
        valueListener(destinationRef, (dataSnapshot) -> {
            List<DestinationModel> temp = new ArrayList<DestinationModel>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                temp.add(destinationModel.setDestination(snapshot));
            }
            destinations.addAll(temp);
        });
        countDownLatch.countDown();
        waitListener();
        return destinations;
    }

    /**
     * Get a highlight destination data
     *
     * @return the list of DestinationModel highlight
     */
    public List<DestinationModel> getHighlightData() {
        List<DestinationModel> data = new ArrayList<DestinationModel>();
        valueListener(destinationRef, (snapshot) -> {
            for (DataSnapshot destinationSnapshot : snapshot.getChildren()) {
                data.add(destinationModel.setDestination(destinationSnapshot));
                if (data.size() >= 8) {
                    break;
                }
            }
            countDownLatch.countDown();
        });
        waitListener();
        return data;
    }
    
    /**
     * Get a detail destination data
     *
     * @param id the id of the location
     * @return the DestinationModel with the given id
     */
    public DestinationModel getDetailData(String id) {
        DestinationModel data = new DestinationModel();
        singleValueListener(destinationRef.child(id), (snapshot) -> {
            DestinationModel ds = destinationModel.setDestination(snapshot);
            data.setId(ds.getId());
            data.setName(ds.getName());
            data.setdetail(ds.getdetail());
            data.setPhoto(ds.getPhoto());
            data.setBackground(ds.getBackground());
            data.setFavorite(ds.isFavorite());
            data.setReview(ds.getReview());
            data.setSearch_key(ds.getSearch_key());

            countDownLatch.countDown();
        });
        waitListener();
        return data;
    }

    /**
     * Insert a new destination data
     *
     * @param DestinationModel the data that want to insert
     * @return a string message of the insert status
     */
    public String insert(DestinationModel destination) {
        destinationRef.push().setValueAsync(destination);
        return "data berhasil di tambah";
    }

    /**
     * Update a destination data
     *
     * @param DestinationModel the data that want to update
     * @return a string message of the update status
     */
    public String update(DestinationModel destination) {
        Map<String, Object> destiMap = new HashMap<>();
        destiMap.put(destination.getId() + "/name", destination.getName());
        destiMap.put(destination.getId() + "/detail", destination.getdetail());
        destiMap.put(destination.getId() + "/search_key", destination.getSearch_key());
        
        if (destination.getPhoto() != null && destination.getPhoto().length() > 0) {
            destiMap.put(destination.getId() + "/photo", destination.getPhoto());
        }

        if (destination.getBackground() != null && destination.getBackground().length() > 0) {
            destiMap.put(destination.getId() + "/background", destination.getBackground());
        }

        destinationRef.updateChildrenAsync(destiMap);
        return "Data berhasil di tambah";
    }

    /**
     * Destroy a destination data
     *
     * @param String id of data that want to destroy
     * @return a string message of the destroy status
     */
    public String destroy(String id) {
        message = "Data berhasil di hapus";
        destinationRef.child(id).removeValueAsync();
        countDownLatch.countDown();
        waitListener();
        return message;
    }

    public List<DestinationModel> SearchData(String keyword) {
        List<DestinationModel> list = new ArrayList<DestinationModel>();
        return list;
    }
}
