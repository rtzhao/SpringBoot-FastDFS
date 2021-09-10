package fun.rtzhao.springbootfastdfs.controller;

import fun.rtzhao.springbootfastdfs.model.CreditorInfo;
import fun.rtzhao.springbootfastdfs.service.CreditorService;
import fun.rtzhao.springbootfastdfs.utils.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/** @Author 邪王真眼是最强的 @Date 2021/9/9 19:29 @Version 1.0 */
@Controller
public class CreditorController {

  @Autowired private CreditorService creditorService;

  @RequestMapping("/")
  public String creator(Model model) {
    List<CreditorInfo> list = creditorService.selectAll();
    model.addAttribute("creditorList", list);
    return "index";
  }

  @GetMapping("/upload/{id}")
  public String toUpload(@PathVariable Integer id, Model model) {
    CreditorInfo creditorInfo = creditorService.selectById(id);
    model.addAttribute("creditorInfo", creditorInfo);
    return "upload";
  }

  @PostMapping("/upload")
  public String upload(Integer id, MultipartFile myFile, Model model) throws IOException {
    // 获取文件对应的字节数组
    byte[] bufFile = myFile.getBytes();
    // 获取文件名
    String fileName = myFile.getOriginalFilename();
    assert fileName != null;
    String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
    Long fileSize = myFile.getSize();
    String fileType = myFile.getContentType();
    String[] result = FastDFSUtil.upload(bufFile, fileExtName);

    CreditorInfo creditorInfo = new CreditorInfo();
    creditorInfo.setId(id);
    creditorInfo.setFileSize(fileSize);
    creditorInfo.setFileType(fileType);
    creditorInfo.setOldFileName(fileName);
    creditorInfo.setGroupName(result[0]);
    creditorInfo.setRemoteFilePath(result[1]);

    creditorService.updateFileInfo(creditorInfo);

    model.addAttribute("message", "文件上传成功，点击确定返回列表页面");
    model.addAttribute("url", "/");
    return "success";
  }

  @RequestMapping("/download/{id}")
  public ResponseEntity<byte[]> download(@PathVariable Integer id) {
    CreditorInfo creditorInfo = creditorService.selectById(id);
    byte[] buffFile =
        FastDFSUtil.download(creditorInfo.getGroupName(), creditorInfo.getRemoteFilePath());
    HttpHeaders httpHeaders = new HttpHeaders();
    // 设置响应类型为文件类型
    httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    httpHeaders.setContentLength(creditorInfo.getFileSize());
    //设置下载时的默认文件名
    httpHeaders.setContentDispositionFormData("attachment", creditorInfo.getOldFileName());
    ResponseEntity<byte[]> responseEntity =
        new ResponseEntity<byte[]>(buffFile, httpHeaders, HttpStatus.OK);
    return responseEntity;
  }

  @RequestMapping("/delete/{id}")
  public String delete(@PathVariable Integer id){
    creditorService.deleteFileById(id);
    return "redirect:/";
  }

}
