package fun.rtzhao.springbootfastdfs.service;

import fun.rtzhao.springbootfastdfs.model.CreditorInfo;

import java.util.List;

/**
 * @Author 邪王真眼是最强的
 * @Date 2021/9/9 20:04
 * @Version 1.0
 */
public interface CreditorService {
    void updateFileInfo(CreditorInfo creditorInfo);

    List<CreditorInfo> selectAll();

    CreditorInfo selectById(Integer id);

    void deleteFileById(Integer id);
}
