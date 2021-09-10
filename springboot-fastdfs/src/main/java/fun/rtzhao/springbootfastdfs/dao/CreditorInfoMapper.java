package fun.rtzhao.springbootfastdfs.dao;

import fun.rtzhao.springbootfastdfs.model.CreditorInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreditorInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditorInfo record);

    int insertSelective(CreditorInfo record);

    CreditorInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditorInfo record);

    int updateByPrimaryKey(CreditorInfo record);

    List<CreditorInfo> selectAll();
}