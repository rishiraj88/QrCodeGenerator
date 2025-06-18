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
        switch (userChoice) {
            case "1" -> convertQrImageFileToTextMessage();
            case "2" -> convertTextMessageToQrFile();
        }
    }

    private static String convertQrImageFileToTextMessage() {
        // Read QR code from file and decode information out of it
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File sourceFile = fileChooser.getSelectedFile();
        if (null == sourceFile) throw new RuntimeException("Error while selecting the QR code file from device.");
        try {
            String decodedInformation = QrCodeReaderFromFileToTextMessage.readFile(sourceFile);
            System.out.println(decodedInformation);

            JOptionPane.showMessageDialog(null, decodedInformation, "QR message", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(sourceFile.getAbsolutePath()));
            return decodedInformation;
        } catch (IOException e) {
            throw new RuntimeException("The content of the file could not be scanned.");
        } catch (NotFoundException e) {
            throw new RuntimeException("Unknown error while scanning QR code from file.");
        }
    }

    static String convertTextMessageToQrFile() {
        try {
            // Get the message from user. This message is to be converted into QR code
            String promptForInput = "Type in your message or paste some copied text here to generate QR code for that: ";
            String inputTextMessage = JOptionPane.showInputDialog(null, promptForInput);
            if(inputTextMessage.isBlank()) inputTextMessage = "https://www.linkedin.com/in/rishirajopenminds/";

            // Create QR code and export to file.
            String qrGraphicFilePath = QrCodeWriterFromTextMessageToFile.write(inputTextMessage);
            // Read QR code from file and decode information out of it
            String decodedInformation = QrCodeReaderFromFileToTextMessage.readFile(qrGraphicFilePath);
            System.out.println(decodedInformation);
            JOptionPane.showMessageDialog(null, decodedInformation, "QR code and message", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(qrGraphicFilePath));
            return decodedInformation;
        } catch (IOException e) {
            throw new RuntimeException("Error while converting the input text into QR code image or while scanning the QR code image from file.");
        } catch (NotFoundException e) {
            throw new RuntimeException("Unknown error while reading the QR code from file.");
        }
    }

    static record FileAndMessage(Path filePath, String message) {}
}
