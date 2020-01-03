package cn.liu.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.liu.entity.Admin;
import cn.liu.service.AdminService;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
@RequestMapping("Admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("login")
    public Object login(Admin admin, String code, HttpServletRequest request) {
        String Code = (String) request.getSession().getAttribute("Code");
        if (Code.equals(code)) {
            System.out.println(admin);
            Admin login = adminService.login(admin);
            if (login == null) {
                return "UPError";
            }

            request.getSession().setAttribute("Admin", login);
            return login;
        } else {
            return "CodeError";
        }


    }


    /*
     * 表格导出
     * poi
     * */
    @ResponseBody
    @RequestMapping("AdminExport")
    public void AdminExport() {
        List<Admin> selectAll = adminService.getSelectAll();
        //创建文件
        HSSFWorkbook sheets = new HSSFWorkbook();
        //设置表格字体及样式
        HSSFFont font = sheets.createFont();
        font.setFontHeightInPoints((short) 12);//设置字体大小
        font.setFontName("楷体");//设置字体
        font.setBold(true);//设置字体加粗
        font.setColor(Font.COLOR_RED);//设置字体颜色

        HSSFCellStyle cellStyle = sheets.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//设置边框格式

        //创建工作簿
        HSSFSheet sheet = sheets.createSheet("管理员空间");
        sheet.setColumnWidth(4,20*256);
        //创建行
        HSSFRow row = sheet.createRow(0);
        //第一行标题
        String[] title = {"ID", "账号", "密码"};
        //遍历放入表格
        for (int i = 0; i < title.length; i++) {
            row.createCell(i).setCellValue(title[i]);

        }

        //放入数据
        for (int i = 0; i < selectAll.size(); i++) {
            HSSFRow rows = sheet.createRow(i+1);//创建行
            rows.createCell(0).setCellValue(selectAll.get(i).getId());
            rows.createCell(1).setCellValue(selectAll.get(i).getUsername());
            rows.createCell(2).setCellValue(selectAll.get(i).getPassword());
        }

        //创建输出位置
        try {
            sheets.write(new File("D:/student.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sheets.close();
            } catch (Exception e) {
            }
        }




    }

}
