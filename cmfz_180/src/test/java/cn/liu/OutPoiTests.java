package cn.liu;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

import cn.liu.entity.Admin;
import cn.liu.entity.Album;
import cn.liu.service.AdminService;
import cn.liu.service.AlbumService;
import cn.liu.service.UserService;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OutPoiTests {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AlbumService albumService;

    @Test
    public void contextLoads() {
        //1. 创建 excle 文件
        HSSFWorkbook workbook = new HSSFWorkbook();

        //2. 创建工作簿
        HSSFSheet sheet = workbook.createSheet("sheet1");

        //3. 创建行 0: 代表的是第一行
        HSSFRow row = sheet.createRow(0);

        //4. 创建 单元格
        HSSFCell cell = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);

        //5. 单元格设值
        cell.setCellValue("Hello");
        cell1.setCellValue(true);
        cell2.setCellValue(10.0);
        //6. 指定excle 输出的位置以及文件名
        try {
            workbook.write(new File("D:/test.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void contextLoads1() throws IOException {

        //1.excle文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //
        HSSFFont font = workbook.createFont();
        //加粗
        font.setBold(true);
        //红色
        font.setColor(Font.COLOR_RED);
        //字体
        font.setFontName("楷体");
        font.setFontHeightInPoints((short) 20);

        //自定义日期类型
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        //单元格的样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setDataFormat(format);
        cellStyle.setFont(font);
        //居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //        //2.工作簿
        HSSFSheet sheet = workbook.createSheet("每一秒");
        //设值单元格的左右长度
        /*
         *  java后台于excle  需要 *256
         * */
        sheet.setColumnWidth(0, 70 * 256);
        //3.行
        HSSFRow row = sheet.createRow(0);
        //4.单元格
        HSSFCell cell = row.createCell(0);
        //5.设值
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);
        //6.写出
        workbook.write(new File("D:/myCat.xls"));
        workbook.close();
    }


    @Test
    public void test2() {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("管理员", "管理员空间"),
                Admin.class, adminService.getSelectAll());
        try {
            workbook.write(new FileOutputStream("D:/cmfz.xls"));
        } catch (Exception e) {

        } finally {
            try {
                workbook.close();
            } catch (Exception e) {
            }
        }
    }


    @Test
    public void tets3() {
        List<Album> all = albumService.getSelectAll();
        for (Album album : all) {
            album.setImg("D:\\final_180\\cmfz_180\\src\\main\\webapp\\upload\\video\\img\\" + album.getImg());
        }


        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑", "专辑空间"),
                Album.class, all);
        try {
            workbook.write(new FileOutputStream("D:/cmfz-album.xls"));
        } catch (Exception e) {

        } finally {
            try {
                workbook.close();
            } catch (Exception e) {
            }
        }
    }

    @Autowired
    private UserService userService;

    @Test
    public void test1() throws ClientException {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
//替换成你的AK
        final String accessKeyId = "LTAI4FxfMtnBrpmRiXT99sCf";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "UTFhjrf42RMTAK5FyGSDLv8BlV9vIV";//你的accessKeySecret，参考本文档步骤2
//初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
//组装请求对象
        SendSmsRequest request = new SendSmsRequest();
//使用post提交
        request.setMethod(MethodType.POST);
//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers("15892774612,13477418142");
//必填:短信签名-可在短信控制台中找到
        request.setSignName("小黑来了");
//必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_181670268");
//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"123\"}");
//可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
//request.setSmsUpExtendCode("90997");
//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
//请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
        }


    }
}
