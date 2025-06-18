import com.google.zxing.NotFoundException;
import java.io.IOException;

public class QrMain {
    public static void main(String[] args) {

        // Create QR code and export to file.
        String qrGraphicFilePath = null;
        try {
            qrGraphicFilePath = QrCodeWriterToFile.write("https://www.linkedin.com/in/rishirajopenminds/");

        // Read QR code from file and decode information out of it
        String decodedInformation = QrCodeReaderFromFile.read(qrGraphicFilePath);
        System.out.println(decodedInformation); } catch (IOException e) {
            throw new RuntimeException("Error while converting the input text into QR code image or while scanning the QR code image from file.");
        } catch (NotFoundException e) {
            throw new RuntimeException("Unknown error while reading the QR code from file.");
        }
    }
}
