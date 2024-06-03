package mancala;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Saver {

    public Saver() {
        super();
    }

    //Use FILES library to save and load files

    /**
     * Save a serializable object to a file.
     * @param toSave
     * @param filename
     * @throws IOException if an I/O error occurs while saving the object
     */
    public void saveObject(Serializable toSave, String filename) throws IOException {

        Path filePath = getFilePath(filename);

        // Check if file exists, if not, create it
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        

        try {
            FileOutputStream fileOut = new FileOutputStream(filePath.toString());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(toSave);
            out.close();
            fileOut.close();
            System.out.println("Object saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Failed to save object to " + filename, e);
        }
    }

    
    /**
     * Load a serializable object from a file.
     * @param filename
     * @return
     * @throws IOException if an I/O error occurs while loading the object
     */
    public Serializable loadObject(String filename) throws IOException {

        if (checkFilePath(filename) == false) {
            throw new IOException("File does not exist.");
        }

        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Serializable loadedObject = (Serializable) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Object loaded from " + filename);
            return loadedObject;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Failed to load object from " + filename, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("Failed to load object from " + filename + ". Class not found.", e);
        }
    }


    /**
     * Helper method to get the absolute path of a file in the assets folder.
     * @param filename
     * @return absolute path of the file
     */
    private Path getFilePath(String filename) {
        // Get current directory
        Path currentDirectory = Paths.get(System.getProperty("user.dir"));

        // Define folder name to check for
        String folderName = "assets";

        // Create a path 'assets' folder
        Path assetsFolderPath = currentDirectory.resolve(folderName);

        // Check if directory exists, if not, create it
        if (!Files.exists(assetsFolderPath)) {
            try {
                Files.createDirectories(assetsFolderPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Resolve the file path
        Path filePath = assetsFolderPath.resolve(filename);

        // Check if file exists, if not, create it
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return filePath;
    }

    
    /**
     * Helper method to check if a file exists in the assets folder.
     * @param filename
     * @return true if the file exists, false otherwise
     */
    private boolean checkFilePath(String filename) {
        // Get current directory
        Path currentDirectory = Paths.get(System.getProperty("user.dir"));

        // Define folder name to check for
        String folderName = "assets";

        // Create a path 'assets' folder
        Path assetsFolderPath = currentDirectory.resolve(folderName);

        // Check if directory exists, if not, create it
        if (!Files.exists(assetsFolderPath)) {
            System.out.println("Assets folder does not exist. Please create it and try again.");
            return false;
        }

        // Resolve the file path
        Path filePath = assetsFolderPath.resolve(filename);

        // Check if file exists, if not, create it
        if (!Files.exists(filePath)) {
            System.out.println("File does not exist. Please create it and try again.");
            return false;
        }

        return true;
    }

}
