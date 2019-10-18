package com.gz.service;
import java.util.List;
import com.gz.po.BaseDict;

public interface BaseDictService {
	//根据类别代码查询数据字典
	public List<BaseDict> findBaseDictByTypeCode(String typecode);	
}
