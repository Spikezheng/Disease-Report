package com.bushi.diseasereportserver.controller;

import com.bushi.diseasereportserver.dao.HospitalChartDAO;
import com.bushi.diseasereportserver.dao.HospitalDAO;
import com.bushi.diseasereportserver.pojo.Chart;
import com.bushi.diseasereportserver.pojo.Hospital;
import com.bushi.diseasereportserver.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class HospitalController {
    @Autowired
    HospitalDAO hospitalDAO;

    @Autowired
    HospitalChartDAO hospitalChartDAO;

    //医生登入
    @PostMapping(value = "api/hospitalLogin")
    @CrossOrigin
    @ResponseBody
    public Result login(@RequestBody Hospital hospital){
        Hospital newHospital = hospitalDAO.findByNameAndPassword(hospital.getName(), hospital.getPassword());

        Result result = new Result(404);
        if(newHospital != null)
            result = new Result(200);

        return result;
    }

    //获取医院提交表格
    @GetMapping(value = "api/getHospitalCharts")
    @CrossOrigin
    @ResponseBody
    public JSONArray getHospitalCharts(){
        ArrayList<Chart> charts = (ArrayList<Chart>) hospitalChartDAO.findAll();
        JSONArray chartsJsonArray = new JSONArray();
        chartsJsonArray = JSONArray.parseArray(JSONObject.toJSONString(charts));

        return chartsJsonArray;
    }

    //提交医院表格
    @PostMapping(value = "api/hospitalChartSubmit")
    @CrossOrigin
    @ResponseBody
    public Result hospitalChartSubmit(@RequestBody Chart chart){
        Optional<Chart> chartOptional = hospitalChartDAO.findById(chart.getId());
        if (chartOptional.isPresent()) {
            chart = chartOptional.get();
        }

        chart.setLevel(1);
        hospitalChartDAO.save(chart);

        Result result = new Result(200);

        return result;
    }

    //医生提交CDC表格
    @PostMapping(value = "api/hospitalChartSubmitCDC")
    @CrossOrigin
    @ResponseBody
    public Result hospitalChartSubmitCDC(@RequestBody Chart chart){
        Optional<Chart> chartOptional = hospitalChartDAO.findById(chart.getId());
        if (chartOptional.isPresent()) {
            chart = chartOptional.get();
        }

        chart.setSuperlevel(1);
        hospitalChartDAO.save(chart);

        Result result = new Result(200);

        return result;
    }

    //医生删除无效数据
    @PostMapping(value = "api/hospitalChartDel")
    @CrossOrigin
    @ResponseBody
    public Result hospitalChartDel(@RequestBody Chart chart){
        hospitalChartDAO.deleteById(chart.getId());

        Result result = new Result(200);

        return result;
    }

}
