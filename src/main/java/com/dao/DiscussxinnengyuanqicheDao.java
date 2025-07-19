package com.dao;

import com.entity.DiscussxinnengyuanqicheEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussxinnengyuanqicheVO;
import com.entity.view.DiscussxinnengyuanqicheView;


/**
 * New Energy Vehicles评论表
 * 
 * @author 
 * @email 
 * @date 2025-06-03 21:49:05
 */
public interface DiscussxinnengyuanqicheDao extends BaseMapper<DiscussxinnengyuanqicheEntity> {
	
	List<DiscussxinnengyuanqicheVO> selectListVO(@Param("ew") Wrapper<DiscussxinnengyuanqicheEntity> wrapper);
	
	DiscussxinnengyuanqicheVO selectVO(@Param("ew") Wrapper<DiscussxinnengyuanqicheEntity> wrapper);
	
	List<DiscussxinnengyuanqicheView> selectListView(@Param("ew") Wrapper<DiscussxinnengyuanqicheEntity> wrapper);

	List<DiscussxinnengyuanqicheView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussxinnengyuanqicheEntity> wrapper);

	
	DiscussxinnengyuanqicheView selectView(@Param("ew") Wrapper<DiscussxinnengyuanqicheEntity> wrapper);
	

}
