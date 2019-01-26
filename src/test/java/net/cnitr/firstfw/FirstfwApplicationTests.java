package net.cnitr.firstfw;

import net.cnitr.firstfw.entity.User;
import net.cnitr.firstfw.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstfwApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
		List<User> list = userMapper.selectList(null);
		// 断言
//		Assert.assertEquals(5, list.size());
		list.forEach(System.out::println);
	}

}

