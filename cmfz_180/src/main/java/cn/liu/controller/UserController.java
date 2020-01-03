package cn.liu.controller;

import cn.liu.dto.UserDTO;
import cn.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequestMapping("User")
public class UserController {


    @Autowired
    private UserService userService ;
    @RequestMapping("getMapData")
    @ResponseBody
    public List<Map<String , Object>> getMapData(){
        List<Map<String , Object>> list = new ArrayList<>() ;

        List<UserDTO> selectAdd = userService.getSelectAdd();
        for (UserDTO userDTO : selectAdd) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name" , userDTO.getAddress());
            map.put("value",userDTO.getCount());
            list.add(map);
        }




        return  list ;

    }
}
