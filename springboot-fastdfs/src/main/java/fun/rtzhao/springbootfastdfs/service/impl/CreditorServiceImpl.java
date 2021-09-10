package fun.rtzhao.springbootfastdfs.service.impl;

import fun.rtzhao.springbootfastdfs.dao.CreditorInfoMapper;
import fun.rtzhao.springbootfastdfs.model.CreditorInfo;
import fun.rtzhao.springbootfastdfs.service.CreditorService;
import fun.rtzhao.springbootfastdfs.utils.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 邪王真眼是最强的
 * @Date 2021/9/9 20:05
 * @Version 1.0
 */
@Service
public class CreditorServiceImpl implements CreditorService {

    @Autowired
    private CreditorInfoMapper creditorInfoMapper;

    @Override
    public void updateFileInfo(CreditorInfo creditorInfo) {
            creditorInfoMapper.updateByPrimaryKeySelective(creditorInfo);
    }

    @Override
    public List<CreditorInfo> selectAll() {
        List<CreditorInfo> list = creditorInfoMapper.selectAll();
        return list;
    }

    @Override
    public CreditorInfo selectById(Integer id) {
        return creditorInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteFileById(Integer id) {
        CreditorInfo creditorInfo= creditorInfoMapper.selectByPrimaryKey(id);
        FastDFSUtil.delect(creditorInfo.getGroupName(),creditorInfo.getRemoteFilePath());
        creditorInfo.setRemoteFilePath("");
        creditorInfo.setGroupName("");
        creditorInfo.setOldFileName("");
        creditorInfo.setFileType("");
        creditorInfo.setFileSize(0L);
        creditorInfoMapper.updateByPrimaryKeySelective(creditorInfo);
    }


}
