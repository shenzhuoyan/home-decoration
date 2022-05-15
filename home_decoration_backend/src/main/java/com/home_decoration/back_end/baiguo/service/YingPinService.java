package com.home_decoration.back_end.baiguo.service;

import com.home_decoration.back_end.baiguo.domain.YingPin;

import java.util.List;

public interface YingPinService {
    boolean add(YingPin yingPin);

    List<YingPin> getPage(int num);

    int pageNumber();

    List<YingPin> getByZhaoId(int zhaoId);
}
