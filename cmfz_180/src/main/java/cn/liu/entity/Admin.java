package cn.liu.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {

    @Excel(name = "ID")
    private String id ;
    @Excel(name ="账号")
    private String username ;
    @Excel(name = "密码")
    private String password ;
}
