package cn.liu.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User  implements Serializable {
    private String  id ; //用户ID
    private String  phone_number ;//用户电话
    private String password ;//用户密码
    private String  name ; //用户姓名
    private String dharma ; //用户法名
    private String head_img ; //用户头像
    private String  sex ;//性别
    private String address  ; //用户地址
    private String sing ;  //个性签名
    private String guru_id ; //所属上师ID
    private Date last_date ; //最后登录的时间
    private Date create_date ; //创建的时间
    private Integer status ; //用户状态
    private  String  salt ; //盐值
    private String other ; //预留字段


}
