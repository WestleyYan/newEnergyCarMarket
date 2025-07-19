package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.RemenqicheEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.RemenqicheVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.RemenqicheView;


/**
 * Popular Cars
 *
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
public interface RemenqicheService extends IService<RemenqicheEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<RemenqicheVO> selectListVO(Wrapper<RemenqicheEntity> wrapper);
   	
   	RemenqicheVO selectVO(@Param("ew") Wrapper<RemenqicheEntity> wrapper);
   	
   	List<RemenqicheView> selectListView(Wrapper<RemenqicheEntity> wrapper);
   	
   	RemenqicheView selectView(@Param("ew") Wrapper<RemenqicheEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<RemenqicheEntity> wrapper);

   	

}

