package com.zc.dao.pushqueryrecord;

import com.zc.entity.pushqueryrecord.PushQueryRecord;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: shiyunshun
 * @date: 2018-04-13 18:47
 * @version: 1.0.0
 */
public interface PushQueryRecordMapper extends BaseMapper<PushQueryRecord> {

    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 列表查询
     */
    List<Map<String, Object>> findOrderList(PushQueryRecord PushQueryRecord);

    /**
     * 获取业务来源
     * @param
     * @return
     */
    List<String> getChannelSel();

    /**
     * 根据id列表批量查询
     * @param chk_value
     * @return
     */
   List<PushQueryRecord>  getBatchQuery(@Param("dramaIds") String[] chk_value);


    /**
     * id查询
     * @param pushQueryRecord
     * @return
     */
    String[]  selectId(PushQueryRecord pushQueryRecord);





}
