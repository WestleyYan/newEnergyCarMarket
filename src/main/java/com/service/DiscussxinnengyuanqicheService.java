package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussxinnengyuanqicheEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussxinnengyuanqicheVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussxinnengyuanqicheView;


/**
 * New Energy Vehicles评论表
 *
 * @author 
 * @email 
 * @date 2025-06-03 21:49:05
 */
public interface DiscussxinnengyuanqicheService extends IService<DiscussxinnengyuanqicheEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussxinnengyuanqicheVO> selectListVO(Wrapper<DiscussxinnengyuanqicheEntity> wrapper);
   	
   	DiscussxinnengyuanqicheVO selectVO(@Param("ew") Wrapper<DiscussxinnengyuanqicheEntity> wrapper);
   	
   	List<DiscussxinnengyuanqicheView> selectListView(Wrapper<DiscussxinnengyuanqicheEntity> wrapper);
   	
   	DiscussxinnengyuanqicheView selectView(@Param("ew") Wrapper<DiscussxinnengyuanqicheEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussxinnengyuanqicheEntity> wrapper);

   	

}

