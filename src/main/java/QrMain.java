import com.google.zxing.NotFoundException;

import module java.base;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class QrMain {
    public static void main(String[] args) throws IOException {
        String initialPrompt = "Enter 1 to read QR code or 2 to enter a text message to generate its QR code: ";
        String userChoice = JOptionPane.showInputDialog(null, initialPrompt);
        switch (userChoice) {
            case "1" -> convertQrImageFileToTextMessage();
            case "2" -> {
                // Get the message from user. This message is to be converted into QR code
                String promptForInput = "Type in your message or paste some copied text here to generate QR code for that: ";
                convertTextMessageToQrFile(promptForInput);
            }
            default -> System.exit(0);
        }
    }

    private static String convertQrImageFileToTextMessage() {
        // Read QR code from file and decode information out of it
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File sourceFile = fileChooser.getSelectedFile();
        if (null == sourceFile) throw new RuntimeException("Error while selecting the QR code file from device.");
        String decodedInformation= null;
        try {
            decodedInformation = QrCodeReaderFromFileToTextMessage.readFile(sourceFile);
            JOptionPane.showMessageDialog(null, decodedInformation, "QR message", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(sourceFile.getAbsolutePath()));
        } catch (IOException e) {
            throw new RuntimeException("The content of the file could not be scanned.");
        } catch (NotFoundException e) {
            throw new RuntimeException("Unknown error while scanning QR code from file.");
        }
        return decodedInformation;
    }

    static String convertTextMessageToQrFile(String promptForInput) {
        String decodedInformation = null;
        try {
            String inputTextMessage = JOptionPane.showInputDialog(null, promptForInput);
            if(inputTextMessage.isBlank()) inputTextMessage = "https://www.linkedin.com/in/rishirajopenminds/";

            // Create QR code and export to file.
            String qrGraphicFilePath = QrCodeWriterFromTextMessageToFile.write(inputTextMessage);
            // Read QR code from file and decode information out of it
            decodedInformation = QrCodeReaderFromFileToTextMessage.readFile(qrGraphicFilePath);
            JOptionPane.showMessageDialog(null, decodedInformation, "QR code and message", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(qrGraphicFilePath));
        } catch (IOException e) {
            //throw new RuntimeException("Error while converting the input text into QR code image or while scanning the QR code image from file.");
        } catch (NotFoundException e) {
            throw new RuntimeException("Unknown error while reading the QR code from file.");
        }
        return decodedInformation;
    }
}
