package com;

import com.controller.XinnengyuanqicheController;
import com.entity.XinnengyuanqicheEntity;
import com.utils.PageUtils;
import com.utils.R;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class CarInfoTest {

	@Resource
	private XinnengyuanqicheController xinnengyuanqicheController;


	/**
	 * 测试获取车辆列表
	 */
	@Test
	public void testGetCarList() {
		HashMap<String , Object> query = new HashMap<>();
		query.put("page", "1");
		query.put("limit", "10");
		query.put("sort", "id");
		query.put("order", "desc");
		XinnengyuanqicheEntity xinnengyuanqicheEntity = new XinnengyuanqicheEntity();
		R result = xinnengyuanqicheController.page(query, xinnengyuanqicheEntity, null, null, null);
		PageUtils pageUtils = (PageUtils) result.get("data");
		List<XinnengyuanqicheEntity> xinnengyuanqicheEntities = (List<XinnengyuanqicheEntity>) pageUtils.getList();
		for (XinnengyuanqicheEntity entity : xinnengyuanqicheEntities) {
			System.out.println(entity.toString());
		}
	}

	/**
	 * 测试添加车辆信息
	 */
	@Test
	public void testAddCarInfo() {
		XinnengyuanqicheEntity xinnengyuanqicheEntity = new XinnengyuanqicheEntity();
		xinnengyuanqicheEntity.setQichemingcheng("Test1");
		xinnengyuanqicheEntity.setTupian("https://picsum.photos/200/300");
		xinnengyuanqicheEntity.setQichefenlei("car");
		xinnengyuanqicheEntity.setGuige("1.5L");
		xinnengyuanqicheEntity.setTedian("test car");
		xinnengyuanqicheEntity.setQichecanshu("test");
		xinnengyuanqicheEntity.setOnelimittimes(1);
		xinnengyuanqicheEntity.setAlllimittimes(1);
		xinnengyuanqicheEntity.setThumbsupnum(1);
		xinnengyuanqicheEntity.setCrazilynum(1);
		xinnengyuanqicheEntity.setClicktime(new java.util.Date());
		xinnengyuanqicheEntity.setClicknum(1);
		xinnengyuanqicheEntity.setDiscussnum(1);
		xinnengyuanqicheEntity.setTotalscore(1.0);
		xinnengyuanqicheEntity.setPrice(1.0);
		xinnengyuanqicheEntity.setOnshelves(1);
		xinnengyuanqicheEntity.setStoreupnum(1);
		R result = xinnengyuanqicheController.save(xinnengyuanqicheEntity, null);
	}

	/**
	 * 测试修改车辆信息
	 */
	@Test
	public void testUpdateCarInfo() {
		XinnengyuanqicheEntity xinnengyuanqicheEntity = new XinnengyuanqicheEntity();
		xinnengyuanqicheEntity.setId(4L);
		xinnengyuanqicheEntity.setQichemingcheng("TEST2");
		xinnengyuanqicheEntity.setTupian("https://picsum.photos/200/300");
		xinnengyuanqicheEntity.setQichefenlei("car");
		xinnengyuanqicheEntity.setGuige("1.5L");
		xinnengyuanqicheEntity.setTedian("TEST car");
		xinnengyuanqicheEntity.setQichecanshu("TEST car");
		xinnengyuanqicheEntity.setOnelimittimes(1);
		xinnengyuanqicheEntity.setAlllimittimes(1);
		xinnengyuanqicheEntity.setThumbsupnum(1);
		xinnengyuanqicheEntity.setCrazilynum(1);
		xinnengyuanqicheEntity.setClicktime(new java.util.Date());
		xinnengyuanqicheEntity.setClicknum(1);
		xinnengyuanqicheEntity.setDiscussnum(1);
		xinnengyuanqicheEntity.setTotalscore(1.0);
		xinnengyuanqicheEntity.setPrice(1.0);
		xinnengyuanqicheEntity.setOnshelves(1);
		xinnengyuanqicheEntity.setStoreupnum(1);
		R result = xinnengyuanqicheController.update(xinnengyuanqicheEntity, null);
	}

	/**
	 * 测试删除
	 * @throws Exception
	 */
	@Test
	public void testDeleteCarInfo() throws Exception {
		Long[] ids = new Long[] {4L};
		R result = xinnengyuanqicheController.delete(ids);
	}

}
