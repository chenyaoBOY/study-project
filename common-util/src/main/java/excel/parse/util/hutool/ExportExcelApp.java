package excel.parse.util.hutool;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author chenyao
 * @date 2020/8/3 14:11
 * @description
 */
@SpringBootApplication
@RestController
public class ExportExcelApp {

    @PostMapping("/export.do")
    public void export(@RequestBody ExportVo vo, HttpServletResponse response) throws IOException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ExcelWriter writer = ExcelUtil.getWriter("/demo.xlsx");
        writer.writeCellValue(0, 0, "幺幺");
        writer.flush(os);
        InputStream inputStream = new ByteArrayInputStream(os.toByteArray());

        //让服务器告诉浏览器它发送的数据属于什么文件类型
        response.setHeader("content-Type", "application/vnd.ms-excel;charset=utf-8");
        //当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("export.csv", "UTF-8"));
        AddPassword4Excel.encryptExcel(inputStream,response.getOutputStream(), "123456");

    }


    public static void main(String[] args) {
        SpringApplication.run(ExportExcelApp.class);
    }
}
