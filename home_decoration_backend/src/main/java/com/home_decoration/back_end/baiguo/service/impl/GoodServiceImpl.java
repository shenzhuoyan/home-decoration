package com.home_decoration.back_end.baiguo.service.impl;

import com.home_decoration.back_end.baiguo.dao.GoodDao;
import com.home_decoration.back_end.baiguo.domain.Good;
import com.home_decoration.back_end.baiguo.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodDao goodDao;

    @Override
    public boolean add(Good good) {
        return goodDao.insert(good)==1;
    }

    @Override
    public List<Good> detailAll() {
        return goodDao.selectDetailAll();
    }

    @Override
    public Good getById(int id) {
        return goodDao.selectById(id);
    }

    @Override
    public List<Good> getAll() {
        return goodDao.selectAll();
    }

    @Override
    public boolean modify(Good good) {
        return goodDao.update(good)==1;
    }

    @Override
    public boolean del(int id) {
        return goodDao.delete(id)==1;
    }
}
