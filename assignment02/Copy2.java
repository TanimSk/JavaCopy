import java.io.*;

public class Copy2 {
    public static void main(String[] args) {
        String sourcePath = args[0];
        String destinationPath = args[1];

        // Check if the source exists
        File srcFile = new File(sourcePath);
        if (!srcFile.exists()) {
            System.out.println("Error: The source file/directory does not exist.");
            return;
        }

        File destFile = new File(destinationPath);

        try {
            // Start copying, if directory, copy recursively, else copy the file
            copy(srcFile, destFile);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * This method copies files and directories recursively.
     **/
    public static void copy(File source, File destination) throws IOException {
        // If the source is a directory, we need to copy it recursively
        if (source.isDirectory()) {
            // If the destination directory does not exist, create it
            if (!destination.exists()) {
                destination.mkdirs();
            }

            // Get all the files in the source directory
            String[] children = source.list();

            if (children != null) {
                // Recursively copy each file/subdirectory
                for (String child : children) {
                    copy(new File(source, child), new File(destination, child));
                }
                System.out.println(source + " directory copied to " + destination + " successfully");
            }
        } else {
            // If the source is a file, copy it using streams
            copyFile(source, destination);
            System.out.println(source + " file copied to " + destination + " successfully");
        }
    }

    /**
     * This method copies a single file.
     **/
    private static void copyFile(File source, File destination) throws IOException, SecurityException {
        try {

            FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(destination);

            byte[] buffer = new byte[1024];
            int length = fis.read(buffer);

            // Read and write the file in chunks
            while (length > 0) {
                fos.write(buffer, 0, length);
                length = fis.read(buffer);
            }

            fis.close();
            fos.close();
        } catch (IOException e) {
            throw new IOException("Error copying file: " + e.getMessage());
        } catch (SecurityException e) {
            throw new SecurityException("Error copying file: " + e.getMessage());
        }
    }
}
