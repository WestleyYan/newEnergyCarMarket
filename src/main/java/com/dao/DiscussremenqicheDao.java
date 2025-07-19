package com.dao;

import com.entity.DiscussremenqicheEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussremenqicheVO;
import com.entity.view.DiscussremenqicheView;


/**
 * Popular Cars评论表
 * 
 * @author 
 * @email 
 * @date 2025-06-03 21:49:05
 */
public interface DiscussremenqicheDao extends BaseMapper<DiscussremenqicheEntity> {
	
	List<DiscussremenqicheVO> selectListVO(@Param("ew") Wrapper<DiscussremenqicheEntity> wrapper);
	
	DiscussremenqicheVO selectVO(@Param("ew") Wrapper<DiscussremenqicheEntity> wrapper);
	
	List<DiscussremenqicheView> selectListView(@Param("ew") Wrapper<DiscussremenqicheEntity> wrapper);

	List<DiscussremenqicheView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussremenqicheEntity> wrapper);

	
	DiscussremenqicheView selectView(@Param("ew") Wrapper<DiscussremenqicheEntity> wrapper);
	

}
