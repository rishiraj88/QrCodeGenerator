import com.google.zxing.NotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class QrMain {
    public static void main(String[] args) throws IOException {
        String initialPrompt = "Enter 1 to scan QR code from file else 2 to enter a text message to generate its QR code: ";
        String userChoice = JOptionPane.showInputDialog(null, initialPrompt);
        //switch (System.console().readln("Enter 1 to scan QR code from file else 2 to enter a text message to generate its QR code: ")){
        switch (userChoice) {
            case "1" -> {
                convertQrImageFileToTextMessage();
            }
            case "2" -> {
                convertTextMessageToQrFile();
            }
        }
    }

    private static String convertQrImageFileToTextMessage() {
        // Read QR code from file and decode information out of it
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File sourceFile = fileChooser.getSelectedFile();
        if (null == sourceFile) throw new RuntimeException("Error while selecting the QR code file from device.");
        try {
            String decodedInformation = null;
            decodedInformation = QrCodeReaderFromFileToTextMessage.readFile(sourceFile);
            System.out.println(decodedInformation);
            //JOptionPane.showMessageDialog(null, decodedInformation);
            JOptionPane.showMessageDialog(null, decodedInformation, "QR message", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(sourceFile.getAbsolutePath()));
            return decodedInformation;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static String convertTextMessageToQrFile() {
        try {
            // Create QR code and export to file.
            String qrGraphicFilePath = null;
            qrGraphicFilePath = QrCodeWriterFromTextMessageToFile.write("https://www.linkedin.com/in/rishirajopenminds/");
            // Read QR code from file and decode information out of it
            String decodedInformation = QrCodeReaderFromFileToTextMessage.readFile(qrGraphicFilePath);
            System.out.println(decodedInformation);
            return decodedInformation;
        } catch (IOException e) {
            throw new RuntimeException("Error while converting the input text into QR code image or while scanning the QR code image from file.");
        } catch (NotFoundException e) {
            throw new RuntimeException("Unknown error while reading the QR code from file.");
        }
    }

    static record FileAndMessage(Path filePath, String message) {
    }
}
