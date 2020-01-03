package cn.liu.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guru  implements Serializable {
    private  String  id ; //上师ID
    private  String head_img ; //上师头像
    private String  name ; //上师名字
    private  String  dharma ; //上师法名
    private  String sex  ; //上师性别

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern =  "yyyy-MM-dd")
    private Date create_date; //上师注册日期
    private Integer status ; //上师状态
    private String other ;




}
