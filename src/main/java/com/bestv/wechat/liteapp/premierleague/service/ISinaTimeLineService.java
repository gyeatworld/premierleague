package com.bestv.wechat.liteapp.premierleague.service;

import com.bestv.wechat.liteapp.premierleague.model.SinaTimeLine;

import java.util.ArrayList;

public interface ISinaTimeLineService {

    ArrayList<SinaTimeLine> fetchSinaTimeLine();

    int insertSinaTimeLine(ArrayList<SinaTimeLine> alSinaTimeLine);
}
