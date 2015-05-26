package cn.edu.tju.test;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.tju.entity.User;
import cn.edu.tju.service.NameOrPwdException;
import cn.edu.tju.service.NullParamException;
import cn.edu.tju.service.UserService;
public class TestCase {
	@Test
	public void TestService() throws NameOrPwdException, NullParamException{
		String cfg = "spring-mvc.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(cfg);
		UserService service = ac.getBean("userService", UserService.class);
		User user = service.login("Tom", "123");
		System.out.println(user);
	}
}
