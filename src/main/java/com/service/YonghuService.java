package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YonghuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YonghuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YonghuView;


/**
 * User
 *
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
public interface YonghuService extends IService<YonghuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YonghuVO> selectListVO(Wrapper<YonghuEntity> wrapper);
   	
   	YonghuVO selectVO(@Param("ew") Wrapper<YonghuEntity> wrapper);
   	
   	List<YonghuView> selectListView(Wrapper<YonghuEntity> wrapper);
   	
   	YonghuView selectView(@Param("ew") Wrapper<YonghuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YonghuEntity> wrapper);

   	

}

