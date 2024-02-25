import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class QrCodeReaderFromFile {
    static String read(String filePath) throws IOException, NotFoundException {
        File file = new File(filePath);
        Result result = new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(file)))));
        return result.getText();
    }
}
