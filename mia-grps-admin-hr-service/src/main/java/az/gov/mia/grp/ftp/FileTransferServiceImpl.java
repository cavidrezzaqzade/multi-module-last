package az.gov.mia.grp.ftp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileTransferServiceImpl implements FileTransferService {

    private Logger logger = LoggerFactory.getLogger(FileTransferServiceImpl.class);

//    @Value("${sftp.host}")
//    private String host;
//
//    @Value("${sftp.port}")
//    private Integer port;
//
//    @Value("${sftp.username}")
//    private String username;
//
//    @Value("${sftp.password}")
//    private String password;
//
//    @Value("${sftp.sessionTimeout}")
//    private Integer sessionTimeout;
//
//    @Value("${sftp.channelTimeout}")
//    private Integer channelTimeout;

    @Override
    public boolean uploadFile(String localFilePath, String remoteFilePath) {
//        ChannelSftp channelSftp = createChannelSftp();
//        try {
//            channelSftp.put(localFilePath, remoteFilePath);
//            return true;
//        } catch(SftpException ex) {
//            logger.error("Error upload file", ex);
//        } finally {
//            disconnectChannelSftp(channelSftp);
//        }

        return false;
    }

    @Override
    public boolean downloadFile(String localFilePath, String remoteFilePath) {
//        ChannelSftp channelSftp = createChannelSftp();
//        OutputStream outputStream;
//        try {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            File file = new File(localFilePath);
//            System.out.println("local file yaradiram");
//            outputStream = new FileOutputStream(file);
//
//            channelSftp.get(remoteFilePath, baos);
//            System.out.println("file creatden evvel");
//            System.out.println(baos);
////            file.createNewFile();
////            System.out.println("create new file");
//            return true;
//        } catch(SftpException | IOException ex) {
//            logger.error("Error download file", ex);
//        } finally {
//            disconnectChannelSftp(channelSftp);
//        }

        return false;
    }

    public byte[] getFromUrl(String remoteFile)  {
//        ChannelSftp channelSftp = createChannelSftp();
//        ByteArrayOutputStream baos = null;
////        try {
//        baos = new ByteArrayOutputStream();
//        channelSftp.get(remoteFile, baos);
//        System.out.println("byte goturdum ???????????????????????");
////        } catch(SftpException ex) {
////            System.out.println("Error download file   "  +  ex);
////            logger.error("Error download file", ex);
////        } finally {
////            disconnectChannelSftp(channelSftp);
////        }
//        return baos.toByteArray();
    return null;
    }

//    private   ChannelSftp createChannelSftp() {
//        try {
//            JSch jSch = new JSch();
//            Session session = jSch.getSession(username, host, port);
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.setPassword(password);
//            session.connect(sessionTimeout);
//            Channel channel = session.openChannel("sftp");
//            channel.connect(channelTimeout);
//            return (ChannelSftp) channel;
//        } catch(JSchException ex) {
//            logger.error("Create ChannelSftp error", ex);
//        }
//
//        return null;
//    }
//
//    private void disconnectChannelSftp(ChannelSftp channelSftp) {
//        try {
//            if( channelSftp == null)
//                return;
//
//            if(channelSftp.isConnected())
//                channelSftp.disconnect();
//
//            if(channelSftp.getSession() != null)
//                channelSftp.getSession().disconnect();
//
//        } catch(Exception ex) {
//            logger.error("SFTP disconnect error", ex);
//        }
//    }

}