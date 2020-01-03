package cn.liu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BannerDTO implements Serializable {
    private String id  ;
    private  String  desc ; //文件描述
    private  String  thumbnail ; //文件路径




}
