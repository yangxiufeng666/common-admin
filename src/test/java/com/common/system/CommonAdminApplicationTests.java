package com.common.system;

import com.common.system.entity.RcBaseArea;
import com.common.system.service.RcBaseAreaService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonAdminApplicationTests {

	@Autowired
	private RcBaseAreaService baseAreaService;

	@Test
	public void contextLoads() {
	}
	@Test
	public void Insert(){
		System.out.println("sdadada");
		JsonObject main = new JsonParser().parse(AreaData.AREA).getAsJsonObject();
		JsonArray jsonArray = main.getAsJsonArray("children");
		RcBaseArea baseArea = new Gson().fromJson(AreaData.AREA,new TypeToken<RcBaseArea>(){}.getType());
		//第一级
		baseArea.setCreateTime(new Date());
		baseArea.setLevel(1);
		baseArea.setParentCode("0");
		baseAreaService.insert(baseArea);
		//第二级
		List<RcBaseArea> childList  = baseArea.getChildren();
		if (childList != null){
			for (RcBaseArea child:childList
					) {
				child.setCreateTime(new Date());
				child.setParentCode(baseArea.getCode());
				child.setLevel(2);
				baseAreaService.insert(child);
				//第三集
				List<RcBaseArea> sunList = child.getChildren();
				if (sunList != null){
					for (RcBaseArea sun:sunList
							) {
						sun.setCreateTime(new Date());
						sun.setParentCode(child.getCode());
						sun.setLevel(3);
						baseAreaService.insert(sun);
					}
				}

			}
		}


	}
}
