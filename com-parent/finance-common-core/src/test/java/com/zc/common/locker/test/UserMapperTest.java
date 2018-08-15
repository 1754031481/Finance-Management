package com.zc.common.locker.test;

import com.zc.common.locker.domain.User;
import com.zc.common.locker.mapper.UserMapper;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class UserMapperTest {
	
	private static SqlSession sqlSession = null;
	private User user = new User();
	
	@BeforeClass
	public static void doInitTest() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		sqlSession = sqlSessionFactory.openSession(true);
	}
	
	// 每次测试前都将数据库中的id为100的User的version设置成100
	@Before
	public void initPojo() throws Exception {
		user.setId(100);
		user.setName("test");
		user.setPassword("test");
		user.setVersion(100L);
	}
	
	//@After
	public void resetDatabaseTest() {
		user.setId(100);
		user.setName("test");
		user.setPassword("test");
		user.setVersion(100L);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		userMapper.resetData(user);
	}

	/**
	 * Bean参数更新
	 */
	@Test
	public void updateUserPojoTest() {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Integer result = userMapper.updateUser(user);
		Assert.assertEquals(1L, Long.parseLong(result + ""));
		
//		打印结果如下：
//		DEBUG - ==> originalSql: update smart_user set name = ?, password = ?, version = ? where id = ?
//		DEBUG - ==>  Preparing: update smart_user set name = ?, password = ?, version = ? where id = ? and version = ? 
//		DEBUG - ==> Parameters: test(String), test(String), 101(Long), 100(Integer), 100(Long)
//		DEBUG - <==    Updates: 1
	}

	/**
	 * 多参数测试
	 */
	@Test
	public void updateUserAtParamTest() {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Integer result = userMapper.updateUser("test", "test", 100L, 100);
		Assert.assertEquals(1L, Long.parseLong(result + ""));
		
//		打印结果如下：
//		DEBUG - ==> originalSql: update smart_user set name = ?, password = ?, version = ? where id = ?
//		DEBUG - ==>  Preparing: update smart_user set name = ?, password = ?, version = ? where id = ? and version = ? 
//		DEBUG - ==> Parameters: test(String), test(String), 101(Long), 100(Integer), 100(Long)
//		DEBUG - <==    Updates: 1
	}

	/**
	 * map类型参数
	 */
	@Test
	public void updateUserMapTest() {
		Map<Object, Object> param = new HashMap<>();
		param.put("name", "test");
		param.put("password", "test");
		param.put("version", 100L);
		param.put("id", 100);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Integer result = userMapper.updateUser(param);
		Assert.assertEquals(1L, Long.parseLong(result + ""));
		
//		打印结果如下：
//		DEBUG - ==> originalSql: update smart_user set name = ?, password = ?, version = ? where id = ?
//		DEBUG - ==>  Preparing: update smart_user set name = ?, password = ?, version = ? where id = ? and version = ? 
//		DEBUG - ==> Parameters: test(String), test(String), 101(Long), 100(Integer), 100(Long)
//		DEBUG - <==    Updates: 1
	}

	/**
	 * 参数绑定错误
	 */
	@Test(expected = BindingException.class)
	public void updateUserErrorTest() {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Integer result = userMapper.updateUserError("test", "test", 100L, 100);
		Assert.assertEquals(1L, Long.parseLong(result + ""));
	}

	/**
	 * 无锁更新
	 */
	@Test
	public void updateUserNoVersionLockerTest() {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Integer result = userMapper.updateUserNoVersionLocker(user);
		Assert.assertEquals(1L, Long.parseLong(result + ""));
		
//		打印结果如下，原生sql未被修改：
//		DEBUG - ==>  Preparing: update smart_user set name = ?, password = ?, version = ? where id = ? 
//		DEBUG - ==> Parameters: test(String), test(String), 100(Long), 100(Integer)
//		DEBUG - <==    Updates: 1

	}
	
}
