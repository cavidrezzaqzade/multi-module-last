package az.gov.mia.grp.ftp;

public interface FileTransferService {

    boolean uploadFile(String localFilePath, String remoteFilePath);

    boolean downloadFile(String localFilePath, String remoteFilePath);

    byte[] getFromUrl(String remoteFile);
}