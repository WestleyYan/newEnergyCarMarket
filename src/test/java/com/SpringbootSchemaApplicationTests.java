package com;

import com.controller.UsersController;
import com.controller.YonghuController;
import com.entity.UsersEntity;
import com.entity.YonghuEntity;
import com.utils.PageUtils;
import com.utils.R;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class SpringbootSchemaApplicationTests {

	@Resource
	private YonghuController yonghuController;


	/**
	 * 测试注册
	 */
	@Test
	void registerTest() {
		YonghuEntity yonghuEntity = new YonghuEntity();
		yonghuEntity.setZhanghao("testUser001");
		yonghuEntity.setMima("testUser001");
		yonghuEntity.setXingming("TEST001");
		R result = yonghuController.register(yonghuEntity);
		System.out.println(result.toString());
	}

	/**
	 * 测试注册重复（会报错用户已存在）
	 */
	@Test
	void RepeatRegisterTest() {
		YonghuEntity yonghuEntity = new YonghuEntity();
		yonghuEntity.setZhanghao("testUser001");
		yonghuEntity.setMima("testUser001");
		yonghuEntity.setXingming("TEST001");
		R result = yonghuController.register(yonghuEntity);
		System.out.println(result.toString());
	}

	/**
	 * 测试登录
	 */
	@Test
	void loginTest() {
		YonghuEntity yonghuEntity = new YonghuEntity();
		yonghuEntity.setZhanghao("testUser001");
		yonghuEntity.setMima("testUser001");
		R result = yonghuController.login("testUser001", "testUser001", null, null);
		System.out.println(result.toString());
	}

	/**
	 * 测试密码重置
	 */
	@Test
	void resetPassTest() {
		R result = yonghuController.resetPass("testUser001", null);
		System.out.println(result);
	}

	/**
	 * 测试用户密码更新
	 */
	@Test
	void updateUsernameTest() {
		YonghuEntity yonghuEntity = new YonghuEntity();
		// id需要通过
		yonghuEntity.setId(1751683479701L);
		yonghuEntity.setZhanghao("testUser002");
		yonghuEntity.setMima("testUser001");
		yonghuEntity.setXingming("TEST002");
		R result = yonghuController.update(yonghuEntity, null);
		System.out.println(result.toString());
	}

	/**
	 * 测试查询所有用户
	 */
	@Test
	void userListTest() {
		HashMap<String , Object> query = new HashMap<>();
		query.put("page", "1");
		query.put("limit", "10");
		query.put("sort", "id");
		query.put("order", "desc");
		YonghuEntity yonghu = new YonghuEntity<>();
		yonghu.setZhanghao(null);
		R result = yonghuController.page(query,yonghu,null);
		PageUtils pageUtils = (PageUtils) result.get("data");
		List<YonghuEntity> yonghuEntities = (List<YonghuEntity>) pageUtils.getList();
		for (YonghuEntity entity : yonghuEntities) {
			System.out.println(entity.toString());
		}
	}
}
