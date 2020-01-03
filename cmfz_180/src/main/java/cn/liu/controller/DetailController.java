package cn.liu.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Component
@RequestMapping("detail")
public class DetailController {
    @ResponseBody
    @RequestMapping("test")
    public Map<String , List<Map<String ,Object>>> detail(String uid , String id ){



        return  null ;
    }
}
