package com.common.system.util;

import com.common.system.entity.RcMenu;

import java.util.Comparator;

/**
 * Created by Mr.Yangxiufeng on 2017/8/8.
 * Time:9:49
 * ProjectName:Common-admin
 * <p>升序</p>
 */
public class MenuComparator implements Comparator<RcMenu> {
    @Override
    public int compare(RcMenu o1, RcMenu o2) {
        if (o1 == null && o2 == null){
            return 0;
        }
        if (o1 != null && o2 == null){
            return -1;
        }
        if (o1 == null && o2 != null){
            return 1;
        }
        if (o1.getSort().compareTo(o2.getSort())>0){
            return 1;
        }
        return -1;
    }
}
