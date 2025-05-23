import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class QrCodeWriterToFile {
    static String write(String inMessage) throws IOException {
        File file = new File("tmp.png");
        ByteArrayOutputStream outputStream = QRCode.from(inMessage).to(ImageType.PNG).stream();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(outputStream.toByteArray());
        fileOutputStream.close();
        return file.getAbsolutePath();
    }
}
