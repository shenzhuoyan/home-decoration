package com.home_decoration.back_end.baiguo.service.impl;

import com.home_decoration.back_end.baiguo.dao.YingPinDao;
import com.home_decoration.back_end.baiguo.domain.YingPin;
import com.home_decoration.back_end.baiguo.service.YingPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YingPinServiceImpl implements YingPinService {

    @Autowired
    private YingPinDao yingPinDao;
    @Override
    public boolean add(YingPin yingPin) {
        return yingPinDao.insert(yingPin)==1;
    }

    @Override
    public List<YingPin> getPage(int num) {
        return yingPinDao.select((num - 1) * 5, 5);
    }

    @Override
    public int pageNumber() {
        int count = yingPinDao.count();
        return count % 5 > 0 ? count / 5 + 1 : count / 5;
    }

    @Override
    public List<YingPin> getByZhaoId(int zhaoId) {
        return yingPinDao.selectByZhaoId(zhaoId);
    }


}
