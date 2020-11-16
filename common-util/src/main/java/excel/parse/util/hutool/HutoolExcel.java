package excel.parse.util.hutool;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author chenyao
 * @date 2020/7/10 18:21
 * @description
 */
public class HutoolExcel {

    public static void main(String[] args) throws FileNotFoundException {
//        hutoolReadExcel();
        hutoolWriterExcel();
    }

    private static void hutoolWriterExcel() {
        List<RowInfo> list =  new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RowInfo info =  new RowInfo();
            info.setAddress("BJ"+i);
            info.setAge(18+i);
            info.setName("陈瑶"+i);
            list.add(info);
        }

        ExcelWriter writer = ExcelUtil.getWriter("E:\\Java\\file\\bean.xlsx");

//        writer.addHeaderAlias("address","地址");
//        writer.addHeaderAlias("age","年龄");
//        writer.addHeaderAlias("name","名字");
        writer.write(list);
        writer.close();
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    public static boolean isNumeric2(String str){
        return str.matches("[0-9]+(\\.[0-9]*)?");
    }

    public static void hutoolReadExcel() throws FileNotFoundException {
        // 1.获取上传文件输入流
        InputStream inputStream = new FileInputStream("E:\\Java\\file\\PayU.xlsx");
        // 2.应用HUtool ExcelUtil获取ExcelReader指定输入流和sheet
        ExcelReader excelReader = ExcelUtil.getReader(inputStream, 0);
        // 可以加上表头验证
        // 3.读取第二行到最后一行数据
        List<List<Object>> read = excelReader.read(1);
        for (List<Object> data : read) {
            // objects.get(0),读取某行第一列数据
            // objects.get(1),读取某行第二列数据
            for (Object d : data) {
                if(d==null)continue;
//                System.out.println(d.toString());

            }
        }


    }
    public static class RowInfo{
        private String name;
        private Integer age;
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

}
