package lab6;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyPaster {
    private String sourcePath;

    public CopyPaster(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public void copyTo(String destinationPath) {
        try (
                FileInputStream fi = new FileInputStream(this.sourcePath);
                FileOutputStream fo = new FileOutputStream(destinationPath);
        ) {
            int data;
            while((data = fi.read()) != -1) {
                fo.write(data);
            }
            System.out.println("File " + this.sourcePath + " successfully copied into " + destinationPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
