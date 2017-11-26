package com.bestv.wechat.liteapp.premierleague.service.impl;

import com.bestv.wechat.liteapp.premierleague.dao.SinaTimeLineMapper;
import com.bestv.wechat.liteapp.premierleague.model.SinaTimeLine;
import com.bestv.wechat.liteapp.premierleague.service.ISinaTimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SinaTimeLineService implements ISinaTimeLineService{

    @Autowired
    private SinaTimeLineMapper sinaTimeLineMapper;

    @Override
    public ArrayList<SinaTimeLine> fetchSinaTimeLine() {
        return null;
    }

    @Override
    public int insertSinaTimeLine(ArrayList<SinaTimeLine> alSinaTimeLine) {
        return 0;
    }
}
