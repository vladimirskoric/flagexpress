package ph.devcon.flag.infrastructure.objectstorage;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import ph.devcon.flag.core.component.donor.domain.FileStorageProviderException;
import ph.devcon.flag.core.port.filestorage.FileStorageProvider;

import javax.enterprise.context.ApplicationScoped;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

@ApplicationScoped
public class MinioObjectStorageProvider implements FileStorageProvider {

    @ConfigProperty(name = "app.object_storage.endpoint")
    String endpoint;

    @ConfigProperty(name = "app.object_storage.bucket_name")
    String bucketName;

    @ConfigProperty(name = "app.object_storage.access_key")
    String accessKey;

    @ConfigProperty(name = "app.object_storage.secret_key")
    String secretKey;

    @Override
    public String saveFile(String base64EncodedString, String uniqueCode) {
        byte[] fileByteArray = Base64.getDecoder().decode(
                base64EncodedString.substring(
                        base64EncodedString.indexOf(',') + 1));// The encoded string from the browser usually includes the meta information as the prefix
        InputStream fis = new ByteArrayInputStream(fileByteArray);

        try {
            MinioClient minioClient = new MinioClient(this.endpoint, this.accessKey, this.secretKey);

            PutObjectOptions options = new PutObjectOptions(fis.available(), -1);
            String bucketname = this.bucketName;
            String objectname = uniqueCode + '.' + this.extractFileExtension(base64EncodedString);
            minioClient.putObject(bucketname, objectname, fis, options);
            fis.close();
            return objectname;
        } catch(Exception e) {
            throw new FileStorageProviderException(e.getMessage());// TODO better message
        }
    }

    @Override
    public InputStream getFile(String uniqueCode) {
        return null;// TODO if needed
    }

    /**
     * This assumes that the encoded string includes the correct meta information as the prefix.
     *
     * @param base64EncodedString The encoded string from the browser
     * @return The file extension
     */
    private String extractFileExtension(String base64EncodedString) {
        return base64EncodedString.substring(0, base64EncodedString.indexOf(';')).split(":")[1].split("/")[1];
    }
}
