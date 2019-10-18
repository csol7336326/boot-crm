package com.gz.dao;
import java.util.List;
import com.gz.po.BaseDict;
/**
 * 数据字典
 */
public interface BaseDictDao {

    public List<BaseDict> selectBaseDictByTypeCode(String typecode);
}
