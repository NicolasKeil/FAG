package fotoverwaltung;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.util.EnumSet;

public class Main {

    public static void main(String[] args) {
        // Specify the path to the folder containing the images
        String folderPath = "C:/Users/nicok/OneDrive/Pictures/Screenshots";

        FileAttributeGUI fag = new FileAttributeGUI();
//        try {
//            // Call the method to set all images in the folder to read-only
////            setImagesInFolderToReadOnly(folderPath);
//            setImagesInFolderToWritable(folderPath);
//        } catch (IOException e) {
//            System.err.println("Error changing file attributes: " + e.getMessage());
//        }
    }

    public static void setImagesInFolderToReadOnly(String folderPath) throws IOException {
        // Specify the file extension of the images you want to set to read-only
        String imageFileExtension = ".png"; // You can change this to the desired extension

        // Create a directory stream for the folder
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(folderPath))) {
            for (Path filePath : directoryStream) {
                // Check if the file is an image with the specified extension
                if (Files.isRegularFile(filePath) && filePath.toString().toLowerCase().endsWith(imageFileExtension)) {
                    // Get the DOS file attributes view
                    DosFileAttributeView dosView = Files.getFileAttributeView(filePath, DosFileAttributeView.class);

                    // Set the file attribute to read-only
                    dosView.setReadOnly(true);
                    System.out.println("File attribute changed to read-only: " + filePath);
                }
            }
        }
    }
    
    public static void setImagesInFolderToWritable(String folderPath) throws IOException {
        // Specify the file extension of the images you want to set to writable
        String imageFileExtension = ".png"; // You can change this to the desired extension

        // Create a directory stream for the folder
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(folderPath))) {
            for (Path filePath : directoryStream) {
                // Check if the file is an image with the specified extension
                if (Files.isRegularFile(filePath) && filePath.toString().toLowerCase().endsWith(imageFileExtension)) {
                    // Get the DOS file attributes view
                    DosFileAttributeView dosView = Files.getFileAttributeView(filePath, DosFileAttributeView.class);

                    // Set the file attribute to read-write
                    dosView.setReadOnly(false);
                    System.out.println("File attribute changed to read-write: " + filePath);
                }
            }
        }
    }
}
