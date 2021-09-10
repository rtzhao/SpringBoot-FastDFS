package fun.rtzhao.springbootfastdfs.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/** @Author 邪王真眼是最强的 @Date 2021/9/9 17:44 @Version 1.0 */
public class FastDFSUtil {

  /*上传文件*/
  public static String[] upload(byte[] buffFile,String fileExtName) {

    TrackerServer trackerServer = null;
    StorageServer storageServer = null;

    try {
      /*读取Fastdfs配置文件，将所有的tracker的地址读到内存中去*/
      ClientGlobal.init("fastdfs.conf");
      TrackerClient trackerClient = new TrackerClient();
      trackerServer = trackerClient.getConnection();
      storageServer = trackerClient.getStoreStorage(trackerServer);
      // 定义Storage的客户端对象，用它完成具体的文件上传、下载和删除
      StorageClient storageClient = new StorageClient(trackerServer, storageServer);

      String[] result = storageClient.upload_file(buffFile, fileExtName, null);

      for(String str:result){
        System.out.println(str);
      }

      return result;

    } catch (IOException e) {
      e.printStackTrace();
    } catch (MyException e) {
      e.printStackTrace();
    }finally{
      if(storageServer!=null){
        try {
          storageServer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if(trackerServer!=null){
        try {
          trackerServer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }

  /*下载文件*/
  public static byte[] download(String groupName,String remoteFilename ) {

    TrackerServer trackerServer = null;
    StorageServer storageServer = null;

    try {
      /*读取Fastdfs配置文件，将所有的tracker的地址读到内存中去*/
      ClientGlobal.init("fastdfs.conf");
      TrackerClient trackerClient = new TrackerClient();
      trackerServer = trackerClient.getConnection();
      storageServer = trackerClient.getStoreStorage(trackerServer);
      // 定义Storage的客户端对象，用它完成具体的文件上传、下载和删除
      StorageClient storageClient = new StorageClient(trackerServer, storageServer);

      byte[] buffFile= storageClient.download_file(groupName,remoteFilename);

      return buffFile;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (MyException e) {
      e.printStackTrace();
    }finally{
      if(storageServer!=null){
        try {
          storageServer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if(trackerServer!=null){
        try {
          trackerServer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }

  /*删除文件*/
  public static void delect(String groupName,String remoteFilename) {

    TrackerServer trackerServer = null;
    StorageServer storageServer = null;

    try {
      /*读取Fastdfs配置文件，将所有的tracker的地址读到内存中去*/
      ClientGlobal.init("fastdfs.conf");
      TrackerClient trackerClient = new TrackerClient();
      trackerServer = trackerClient.getConnection();
      storageServer = trackerClient.getStoreStorage(trackerServer);
      // 定义Storage的客户端对象，用它完成具体的文件上传、下载和删除
      StorageClient storageClient = new StorageClient(trackerServer, storageServer);
      int result =
          storageClient.delete_file(groupName, remoteFilename);
      System.out.println(result);

    } catch (IOException e) {
      e.printStackTrace();
    } catch (MyException e) {
      e.printStackTrace();
    }finally{
      if(storageServer!=null){
        try {
          storageServer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if(trackerServer!=null){
        try {
          trackerServer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }


}
