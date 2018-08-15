package com.zc.dao.checklog;

import com.zc.entity.checklog.CheckLog;
import com.zc.vo.CheckLogVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: shiyunshun
 * @date: 2018-06-05 12:47
 * @version: 1.0.0
 */
public interface CheckLogMapper extends BaseMapper<CheckLog> {

    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 列表查询
     */
    List<Map<String, Object>> findOrderList(CheckLog checkLog);

    /**
     * 根据id列表批量查询
     * @param chk_value
     * @return
     */

    List<CheckLogVO>  getBatchQuery(@Param("dramaIds")String[] chk_value);
    /**
     * 根据id列表批量查询
     * @param chk_value
     * @return
     */

    List<CheckLog>  getBatchQuery1(@Param("dramaIds")String[] chk_value);
    /**
     * 批量添加
     * @param checkLogListk
     * @return
     */
    Integer  putBatch(@Param("checkLogListk") List<CheckLogVO> checkLogListk);

    /**
     * 查询当前页面筛选id
     * @param checkLog
     * @return
     */
    String[] selectId(CheckLog checkLog);

    /**
     * 获取业务来源
     * @param
     * @return
     */
    List<String> getChannelSel();

    CheckLog entityByNumThirdPayType(String orderNum, String thirdPayType);
}
