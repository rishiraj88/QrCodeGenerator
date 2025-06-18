import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class QrCodeReaderFromFileToTextMessage {
    static String readFile(String filePath) throws IOException, NotFoundException {
        File fileHandle = new File(filePath);
        return readFile(fileHandle);
    }

    static String readFile(File fileHandle) throws IOException, NotFoundException {
        return new MultiFormatReader()
                .decode(
                        new BinaryBitmap(
                                new HybridBinarizer(
                                        new BufferedImageLuminanceSource(
                                                ImageIO.read(fileHandle)
                                        )
                                )
                        )
                ).getText();
    }
}
