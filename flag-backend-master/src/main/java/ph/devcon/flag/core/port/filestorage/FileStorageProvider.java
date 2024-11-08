package ph.devcon.flag.core.port.filestorage;

import java.io.InputStream;

public interface FileStorageProvider {

    String saveFile(String base64EncodedString, String uniqueCode);

    InputStream getFile(String uniqueCode);

}
