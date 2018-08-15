package com.zc.common.core.util;

import com.zc.common.core.utils.IdWorker;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:zhangcan
 * @Description:
 * @Date:Created in 14:18 2018/1/26
 * @Modified By:
 */
public class IdWorkerTest {

    @Test
    public void testId(){
        IdWorker idWorker = new IdWorker(0, 0);
        System.out.println(idWorker.nextId());
    }
}
