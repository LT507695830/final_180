package cn.liu.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumASArticleDTO implements Serializable {

    private String id ;//id

    private String title ;//标题
    private String thumbnail ;//图片
    private  String  author ; //描述
    private  Integer type ; //唯一标识 ， 0为专辑，1为文章
    private Integer  set_count ;//专辑集数
    private Date create_date ; //创建时间

}
