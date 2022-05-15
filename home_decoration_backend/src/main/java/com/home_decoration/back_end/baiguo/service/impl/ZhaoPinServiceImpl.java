package com.home_decoration.back_end.baiguo.service.impl;

import com.home_decoration.back_end.baiguo.dao.ZhaoPinDao;
import com.home_decoration.back_end.baiguo.domain.ZhaoPin;
import com.home_decoration.back_end.baiguo.service.ZhaoPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZhaoPinServiceImpl implements ZhaoPinService {
    @Autowired
    private ZhaoPinDao zhaoPinDao;
    @Override
    public boolean add(ZhaoPin zhaoPin) {
        return zhaoPinDao.insert(zhaoPin)==1;
    }

    @Override
    public boolean rm(int id) {
        return zhaoPinDao.update(id,0)==1;
    }

    @Override
    public boolean re(int id) {
        return zhaoPinDao.update(id,1)==1;
    }

    @Override
    public List<ZhaoPin> getPage(int num) {
        return zhaoPinDao.selectOnePage(1,(num - 1) * 5, 5);
    }

    @Override
    public List<ZhaoPin> getDelPage(int num){
        return zhaoPinDao.selectOnePage(0,(num-1)*5,5);
    }

    @Override
    public int pageNumber(int status) {
        int count = zhaoPinDao.count(status);
        return count % 5 > 0 ? count / 5 + 1 : count / 5;
    }
}
