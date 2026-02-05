import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import module java.base;

public class QrCodeWriterFromTextMessageToFile {
    static String write(String inMessage) throws IOException {
        ByteArrayOutputStream outputStream = QRCode.from(inMessage).to(ImageType.PNG).stream();
        String fileName = "tmp.png";
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        fileOutputStream.write(outputStream.toByteArray());
        fileOutputStream.close();
        return Paths.get(fileName).toAbsolutePath().toString();
    }
}
