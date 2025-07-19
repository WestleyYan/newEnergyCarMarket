package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DiscussremenqicheDao;
import com.entity.DiscussremenqicheEntity;
import com.service.DiscussremenqicheService;
import com.entity.vo.DiscussremenqicheVO;
import com.entity.view.DiscussremenqicheView;

@Service("discussremenqicheService")
public class DiscussremenqicheServiceImpl extends ServiceImpl<DiscussremenqicheDao, DiscussremenqicheEntity> implements DiscussremenqicheService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussremenqicheEntity> page = this.selectPage(
                new Query<DiscussremenqicheEntity>(params).getPage(),
                new EntityWrapper<DiscussremenqicheEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussremenqicheEntity> wrapper) {
		  Page<DiscussremenqicheView> page =new Query<DiscussremenqicheView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussremenqicheVO> selectListVO(Wrapper<DiscussremenqicheEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussremenqicheVO selectVO(Wrapper<DiscussremenqicheEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussremenqicheView> selectListView(Wrapper<DiscussremenqicheEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussremenqicheView selectView(Wrapper<DiscussremenqicheEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
