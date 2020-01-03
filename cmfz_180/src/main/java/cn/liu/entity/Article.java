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
public class Article implements Serializable {

    @Excel(name="文章ID")
    private String id ; //文章ID
    @Excel(name="文章名字")
    private String title ; //文章名字
    @Excel(name="文章作者")
    private String author ; //文章作者
    @Excel(name="文章类容")
    private String content ; //文章类容
    @Excel(name="文章所属上师")
    private String guru_id  ; //文章所属上师
    @Excel(name="文章发布日期", format = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date create_date ; //文章发布日期
    @Excel(name="文章状态",replace = { "展示_0", "不展示_1" })
    private Integer status ; //文章状态
    @Excel(name="预留字段")
    private String other ; //预留字段


}
