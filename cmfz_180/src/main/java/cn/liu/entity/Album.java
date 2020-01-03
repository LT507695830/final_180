package cn.liu.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album implements Serializable {

    @Excel(name="专辑ID")
    private String id ; //专辑ID
    @Excel(name="专辑名字")
    private String title ;//专辑名字
    @Excel(name="专辑图片" ,type = 2)
    private String img ; //专辑图片
    @Excel(name="专辑评分")
    private String score ; //专辑评分
    @Excel(name="作者")
    private String author ; //作者
    @Excel(name="播音员")
    private String broadcaster ;//播音员
    @Excel(name="数量")
    private Integer count ; //数量
    @Excel(name="简介")
    private String brief ; //简介
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="添加时间" , format = "yyyy-MM-dd")
    private Date create_date ; //添加时间
    @Excel(name="状态",replace = { "激活_0", "冻结_1" })
    private Integer status ; //状态
    @Excel(name="预留字段")
    private String other ; //预留字段


}
