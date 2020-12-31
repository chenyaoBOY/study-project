//package excel.parse.util.alibaba;
//
//import com.alibaba.excel.EasyExcel;
//
//import java.util.List;
//
///**
// * @author chenyao
// * @date 2020/7/10 18:21
// * @description
// */
//public class AlibabaEasyExcel {
//
//    public static void main(String[] args) {
//        String fileName = "E:\\Java\\file\\PayU.xlsx";
//        DemoDataListener listener = new DemoDataListener();
//        EasyExcel.read(fileName, DemoData.class,listener ).sheet().doRead();
//
//        List<DemoData> list = listener.getList();
//        for (DemoData demoData : list) {
//
//        }
//    }
//}
