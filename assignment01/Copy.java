import java.io.*;

public class Copy {
    public static void main(String[] args) {
        String sourceFile = args[0];
        String destinationFile = args[1];

        // check the source file
        File srcFile = new File(sourceFile);
        if (!srcFile.exists()) {
            System.out.println("The source file does not exist.");
            return;
        }

        // create the destination file
        File destFile = new File(destinationFile);
        try {
            destFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }

        // read the source file and write to the destination file in binary
        try {
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int length = fis.read(buffer);

            while (length > 0) {
                length = fis.read(buffer);
                fos.write(buffer, 0, length);
            }
            fis.close();
            fos.close();

            // Source file copied to destination successfully.
            System.out.println(
                    srcFile + " file copied to " + destFile + " successfully.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }

    }

}