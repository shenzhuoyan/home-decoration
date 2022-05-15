package com.home_decoration.back_end.baiguo.service;

import com.home_decoration.back_end.baiguo.domain.ZhaoPin;

import java.util.List;

public interface ZhaoPinService {
    boolean add(ZhaoPin zhaoPin);
    boolean rm(int id);
    boolean re(int id);

    List<ZhaoPin> getPage(int num);
    List<ZhaoPin> getDelPage(int num);

    int pageNumber(int status);


}
