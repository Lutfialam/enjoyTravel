
package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import at.favre.lib.crypto.bcrypt.BCrypt;
import model.UserModel;

public class User extends DB {
    UserModel userModel = new UserModel();
    CountDownLatch countDownLatch = new CountDownLatch(1);
    DatabaseReference userRef = instance().getReference("users");

    private void waitListener() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<UserModel> getData() {
        List<UserModel> data = new ArrayList<UserModel>();
        valueListener(userRef, (snapshot) -> {
            for (DataSnapshot user : snapshot.getChildren()) {
                data.add(userModel.setUser(user));
            }
            countDownLatch.countDown(); 
        });
        waitListener();
        return data;
    }
    
    public boolean saveUser(UserModel user) {
        user.setLevel("guest");
        userRef.push().setValue(user, (error, ref) -> {
            countDownLatch.countDown();
        });
        waitListener();
        return true;
    }

    public UserModel login(String email, String password) {
        List<UserModel> userList = new ArrayList<UserModel>();
        valueListener(userRef, (snapshot) -> {
            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                char[] pw = password.toCharArray();
                UserModel user = userModel.setUser(userSnapshot);

                BCrypt.Result isFound = BCrypt.verifyer().verify(pw, user.getPassword());
                if (user.getEmail().equals(email) && isFound.verified) {
                    userList.add(user);
                    break;
                }
            }
            countDownLatch.countDown();
        });
        waitListener();
        return userList.size() > 0 ? userList.get(0) : new UserModel(); 
    }

    public UserModel getDetailData(String id) {
        UserModel data = new UserModel();
        singleValueListener(userRef.child(id), (snapshot) -> {
            UserModel user = userModel.setUser(snapshot);
            data.setId(user.getId());
            data.setName(user.getName());
            data.setEmail(user.getEmail());
            data.setLevel(user.getLevel());
            countDownLatch.countDown();
        });
        waitListener();
        return data;
    }

    public String insert(UserModel user) {
        String hashPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(hashPassword);
        userRef.push().setValueAsync(user);
        
        return "data berhasil di tambah";
    }

    public String update(UserModel user) {
        Map<String, Object> destiMap = new HashMap<>();
        destiMap.put(user.getId() + "/name", user.getName());

        userRef.updateChildrenAsync(destiMap);
        return "Data berhasil di tambah";
    }

    public String destroy(String key) {
        userRef.child(key).removeValueAsync();
        countDownLatch.countDown();
        waitListener();
        return "data berhasil di hapus";
    }
    
    public List<UserModel> searchData(String keyword) {
        List<UserModel> data = new ArrayList<UserModel>();
        valueListener(userRef, (snapshot) -> {
            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                UserModel user = userModel.setUser(userSnapshot);
                if (user.getName().contains(keyword) || user.getEmail().contains(keyword)) {
                    data.add(userModel);
                }
            }
            countDownLatch.countDown();
        });
        waitListener();
        return data;
    }
}
