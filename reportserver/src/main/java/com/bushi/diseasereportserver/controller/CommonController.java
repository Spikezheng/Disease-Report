package com.bushi.diseasereportserver.controller;
import com.bushi.diseasereportserver.dao.CommonDAO;
import com.bushi.diseasereportserver.dao.HospitalChartDAO;
import com.bushi.diseasereportserver.pojo.Chart;
import com.bushi.diseasereportserver.pojo.Common;
import com.bushi.diseasereportserver.pojo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class CommonController {
    @Autowired
    CommonDAO commonDAO;

    @Autowired
    HospitalChartDAO hospitalChartDAO;

    //一般用户登入
    @CrossOrigin
    @PostMapping(value = "api/commonLogin")
    @ResponseBody
    public Result login(@RequestBody Common common){
        Common newCommon = commonDAO.findByNameAndPassword(common.getName(), common.getPassword());

        Result result = new Result(404);
        if(newCommon != null)
            result = new Result(200);

        return result;
    }

    //一般用户提交表格
    @CrossOrigin
    @PostMapping(value = "api/chartSubmit")
    @ResponseBody
    public Result submit(@RequestBody Chart chart){
        hospitalChartDAO.save(chart);

        Result result = new Result(200);
        return result;
    }
}
