package com.dao;

import com.entity.RemenqicheEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.RemenqicheVO;
import com.entity.view.RemenqicheView;


/**
 * Popular Cars
 * 
 * @author 
 * @email 
 * @date 2025-06-03 21:49:04
 */
public interface RemenqicheDao extends BaseMapper<RemenqicheEntity> {
	
	List<RemenqicheVO> selectListVO(@Param("ew") Wrapper<RemenqicheEntity> wrapper);
	
	RemenqicheVO selectVO(@Param("ew") Wrapper<RemenqicheEntity> wrapper);
	
	List<RemenqicheView> selectListView(@Param("ew") Wrapper<RemenqicheEntity> wrapper);

	List<RemenqicheView> selectListView(Pagination page,@Param("ew") Wrapper<RemenqicheEntity> wrapper);

	
	RemenqicheView selectView(@Param("ew") Wrapper<RemenqicheEntity> wrapper);
	

}
