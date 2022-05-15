package com.home_decoration.back_end.baiguo.service;

import com.home_decoration.back_end.baiguo.domain.Good;

import java.util.List;

public interface GoodService {
    boolean add(Good good);

    List<Good> detailAll();
    Good getById(int id);

    List<Good> getAll();

    boolean modify(Good good);

    boolean del(int id);
}
