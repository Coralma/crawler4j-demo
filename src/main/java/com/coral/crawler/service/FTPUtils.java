package com.coral.crawler.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * Created by CCC on 2017/3/17.
 */
public class FTPUtils {

    private String server;
    private String username;
    private String password;
    private int port = 21;
    private FTPClient ftpClient;

    private FTPUtils() {
    }

    public FTPUtils(String server, String username, String password){
        this.server = server;
        this.username = username;
        this.password = password;
        initConnection();
    }

    public FTPUtils(String server, String username, String password, int port){
        this.server = server;
        this.username = username;
        this.password = password;
        this.port = port;
        initConnection();
    }

    private void initConnection() {
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(this.server, this.port);
            ftpClient.login(this.username, this.password);
            ftpClient.setDataTimeout(60000);       //设置传输超时时间为60秒
            ftpClient.setConnectTimeout(60000);       //连接超时为60秒
            ftpClient.setDefaultTimeout(60000);
            ftpClient.setSoTimeout(30000);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadFiles(String[] filePaths, String targetDir){
        try {
            ftpClient.makeDirectory(targetDir);
            for(String filePath : filePaths){
                File localFile = new File(filePath);
                InputStream inputStream = new FileInputStream(localFile);
                String remoteName = String.format("%s/%s", targetDir, filePath.substring(filePath.lastIndexOf("/") + 1));
                System.out.println(remoteName);
                boolean finished = ftpClient.storeFile(remoteName, inputStream);
                System.out.println(finished);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean uploadFile(String filePath, String targetDir, String targetName) {
        boolean finished = false;
        try {
            ftpClient.makeDirectory(targetDir);
            /*if(StringUtils.isNotBlank(targetDir)){
                ftpClient.makeDirectory(targetDir);
            }*/
            File localFile = new File(filePath);
            InputStream inputStream =  new FileInputStream(localFile);
            String remoteName = targetName == null ? filePath.substring(filePath.lastIndexOf("\\") + 1) : targetName;
            String remoteFileName = String.format("%s/%s", targetDir, remoteName);
            finished = ftpClient.storeFile(remoteFileName, inputStream);
            if(inputStream.read()!=-1) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finished;
    }

    public boolean uploadFile(String filePath, String targetDir){
        return uploadFile(filePath, targetDir, null);
    }

    public boolean uploadFile(String filePath){
        return uploadFile(filePath, null);
    }

    public void closeConnection(){
        try {
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ftpClient.isConnected()){
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String[] paths = new String[]{"D:\\ashk_wnd.bmp", "D:\\dev_books\\cache\\Ehcache_API_Developer_Guide.pdf"
                ,"D:\\dev_books\\cache\\Ehcache_Replication_Guide.pdf", "D:\\dev_books\\Ionic_in_Action.pdf"
                ,"D:\\dev_books\\akka\\akka_in_action.pdf","D:\\dev_books\\rxjava\\RxJavaEssentials.pdf"
                ,"D:\\dev_books\\vaadin\\book-of-vaadin.pdf","D:\\dev_books\\zookeeper\\ZooKeeper.Distributed process coordination.2013.pdf"};
        FTPUtils ftpUtils = new FTPUtils("192.168.200.30", "ftp_test", "ftp_test");

        try{
            for(String path: paths){
                ftpUtils.uploadFile(path, "coral/images");
            }
        } finally {
            ftpUtils.closeConnection();
        }

    }
}
