package com.common.system.service.impl;

import com.common.system.entity.RcMenu;
import com.common.system.entity.RcMenuExample;
import com.common.system.mapper.RcMenuMapper;
import com.common.system.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng on 2017/8/2.
 * Time:14:01
 * ProjectName:Common-admin
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private RcMenuMapper menuMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RcMenu record) throws Exception{
        return menuMapper.insert(record);
    }

    @Override
    public RcMenu selectByPrimaryKey(String id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(RcMenu record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }
    @Override
    public int updatePcode(String oldPcode,String newPcode) {
        return menuMapper.updatePcode(oldPcode,newPcode);
    }
    @Override
    public PageInfo<RcMenu> listForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        RcMenuExample example = new RcMenuExample();
        RcMenuExample.Criteria criteria = example.createCriteria();
//        criteria.andPIdEqualTo("000000000000000000");
//        example.setOrderByClause("p_id");
        List<RcMenu> list = menuMapper.selectByExample(example);
//        List<RcMenu> finalList = new ArrayList<>();
//        if (list !=null && list.size()>0){
//            for (RcMenu m:list
//                 ) {
//                List<RcMenu> childs = getByParentId(m.getId());
//                finalList.add(m);
//                finalList.addAll(childs);
//                m.setChild(childs);
//            }
//        }
        PageInfo<RcMenu> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<RcMenu> getMenu() {
        List<RcMenu> list = menuMapper.selectByExample(new RcMenuExample());
        return list;
    }

    @Override
    public RcMenu selectCode(String code) {
        RcMenuExample example = new RcMenuExample();
        RcMenuExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        List<RcMenu> menus = menuMapper.selectByExample(example);
        if (menus != null && menus.size() > 0){
            return menus.get(0);
        }
        return null;
    }

    @Override
    public List<RcMenu> selectInIds(List<String> ids,List<Integer> wantLevel) {
        RcMenuExample example = new RcMenuExample();
        RcMenuExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        if (wantLevel != null){
            criteria.andLevelIn(wantLevel);
        }
        example.setOrderByClause("sort desc");
        return menuMapper.selectByExample(example);
    }

    @Override
    public List<RcMenu> getByParentId(String pId) {
        RcMenuExample example = new RcMenuExample();
        RcMenuExample.Criteria criteria = example.createCriteria();
        criteria.andPIdEqualTo(pId);
        return menuMapper.selectByExample(example);
    }
}
