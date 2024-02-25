import com.google.zxing.NotFoundException;

import java.io.IOException;

public class QrMain {
    public static void main(String[] args) throws IOException, NotFoundException {

        // Create QR code and export to file.
String qrGraphicFilePath = QrCodeWriterToFile.write("https://www.linkedin.com/in/rishirajopenminds/");

        // Read QR code from file and decode information out of it
      String decodedInformation =  QrCodeReaderFromFile.read(qrGraphicFilePath);
        System.out.println(decodedInformation);
    }

}
