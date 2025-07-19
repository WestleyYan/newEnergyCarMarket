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


import com.dao.RemenqicheDao;
import com.entity.RemenqicheEntity;
import com.service.RemenqicheService;
import com.entity.vo.RemenqicheVO;
import com.entity.view.RemenqicheView;

@Service("remenqicheService")
public class RemenqicheServiceImpl extends ServiceImpl<RemenqicheDao, RemenqicheEntity> implements RemenqicheService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RemenqicheEntity> page = this.selectPage(
                new Query<RemenqicheEntity>(params).getPage(),
                new EntityWrapper<RemenqicheEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<RemenqicheEntity> wrapper) {
		  Page<RemenqicheView> page =new Query<RemenqicheView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<RemenqicheVO> selectListVO(Wrapper<RemenqicheEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public RemenqicheVO selectVO(Wrapper<RemenqicheEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<RemenqicheView> selectListView(Wrapper<RemenqicheEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public RemenqicheView selectView(Wrapper<RemenqicheEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
