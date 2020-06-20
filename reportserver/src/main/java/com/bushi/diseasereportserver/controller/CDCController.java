package com.bushi.diseasereportserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bushi.diseasereportserver.dao.CDCDAO;
import com.bushi.diseasereportserver.dao.HospitalChartDAO;
import com.bushi.diseasereportserver.pojo.CDC;
import com.bushi.diseasereportserver.pojo.Chart;
import com.bushi.diseasereportserver.pojo.Common;
import com.bushi.diseasereportserver.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class CDCController {
    @Autowired
    CDCDAO cdcDAO;

    @Autowired
    HospitalChartDAO hospitalChartDAO;

    //CDC用户登入
    @CrossOrigin
    @PostMapping(value = "api/cdcLogin")
    @ResponseBody
    public Result login(@RequestBody CDC cdc){
        CDC newCDC = cdcDAO.findByNameAndPassword(cdc.getName(), cdc.getPassword());

        Result result = new Result(404);
        if(newCDC != null)
            result = new Result(200);

        return result;
    }

    //获取CDC表格数据
    @GetMapping(value = "api/getCDCCharts")
    @CrossOrigin
    @ResponseBody
    public JSONArray getCDCCharts(){
        ArrayList<Chart> charts = (ArrayList<Chart>) hospitalChartDAO.findAllBySuperlevel(1);
        JSONArray chartsJsonArray = new JSONArray();
        chartsJsonArray = JSONArray.parseArray(JSONObject.toJSONString(charts));

        return chartsJsonArray;
    }
}
