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


import com.dao.DiscussxinnengyuanqicheDao;
import com.entity.DiscussxinnengyuanqicheEntity;
import com.service.DiscussxinnengyuanqicheService;
import com.entity.vo.DiscussxinnengyuanqicheVO;
import com.entity.view.DiscussxinnengyuanqicheView;

@Service("discussxinnengyuanqicheService")
public class DiscussxinnengyuanqicheServiceImpl extends ServiceImpl<DiscussxinnengyuanqicheDao, DiscussxinnengyuanqicheEntity> implements DiscussxinnengyuanqicheService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussxinnengyuanqicheEntity> page = this.selectPage(
                new Query<DiscussxinnengyuanqicheEntity>(params).getPage(),
                new EntityWrapper<DiscussxinnengyuanqicheEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussxinnengyuanqicheEntity> wrapper) {
		  Page<DiscussxinnengyuanqicheView> page =new Query<DiscussxinnengyuanqicheView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussxinnengyuanqicheVO> selectListVO(Wrapper<DiscussxinnengyuanqicheEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussxinnengyuanqicheVO selectVO(Wrapper<DiscussxinnengyuanqicheEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussxinnengyuanqicheView> selectListView(Wrapper<DiscussxinnengyuanqicheEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussxinnengyuanqicheView selectView(Wrapper<DiscussxinnengyuanqicheEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
