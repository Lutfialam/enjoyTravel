package service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import javax.servlet.http.Part;

public class FileStorage {
    String defaultFile = "default.jpg";
    String storagePath = System.getProperty("user.dir") + "/assets/images/";

    /**
     * Upload file to folder
     *
     * @param Part file
     * @return a string message of the upload status
     */
    public String upload(Part photoPart) {
        try {
            String date = String.valueOf(new Date().getTime());
            if (photoPart != null && photoPart.getSize() > 0) {
                String fileName = date + Paths.get(photoPart.getSubmittedFileName()).getFileName().toString();
                File savedPhoto = new File(storagePath, fileName);
                Files.copy(photoPart.getInputStream(), savedPhoto.toPath());
                return fileName;
            }

            return defaultFile;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return defaultFile;
        }
    }
    
    /**
     * Check file exist or not
     *
     * @param String file path
     * @return a string path of the file. Send default if file not found
     */
    public String hasFile(String path) {
        return new File(storagePath.concat(path)).exists() ? path : defaultFile;
    }
}
