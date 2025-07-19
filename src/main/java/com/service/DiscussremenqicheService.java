package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussremenqicheEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussremenqicheVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussremenqicheView;


/**
 * Popular Cars评论表
 *
 * @author 
 * @email 
 * @date 2025-06-03 21:49:05
 */
public interface DiscussremenqicheService extends IService<DiscussremenqicheEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussremenqicheVO> selectListVO(Wrapper<DiscussremenqicheEntity> wrapper);
   	
   	DiscussremenqicheVO selectVO(@Param("ew") Wrapper<DiscussremenqicheEntity> wrapper);
   	
   	List<DiscussremenqicheView> selectListView(Wrapper<DiscussremenqicheEntity> wrapper);
   	
   	DiscussremenqicheView selectView(@Param("ew") Wrapper<DiscussremenqicheEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussremenqicheEntity> wrapper);

   	

}

