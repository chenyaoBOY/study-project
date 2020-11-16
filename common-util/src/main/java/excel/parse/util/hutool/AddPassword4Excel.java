package excel.parse.util.hutool;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyao
 * @date 2020/8/3 13:38
 * @description 导出excel加密
 */
@SuppressWarnings("ALL")
public class AddPassword4Excel {
    public static void main(String[] args) throws Exception {

        ByteArrayOutputStream os =new ByteArrayOutputStream();
        ExcelWriter writer = ExcelUtil.getWriter("/demo.xlsx");
        writer.writeCellValue(0,0,"幺幺");
        writer.flush(os);

        InputStream inputStream = new ByteArrayInputStream(os.toByteArray());

        encryptExcel(inputStream,new FileOutputStream("E:\\Java\\file\\dd.xlsx"),"shein123456");
//        encryptExcel("E:\\Java\\file\\PayU.xlsx","E:\\Java\\file\\PayU.xlsx","111111");
    }

    public static void export(OutputStream printOs, String path) throws Exception {
        Encryptor enc = new EncryptionInfo(EncryptionMode.standard).getEncryptor();
        enc.confirmPassword("123456");

        POIFSFileSystem fs = new POIFSFileSystem();
        OPCPackage opc = OPCPackage.open(new File(path), PackageAccess.READ_WRITE);

        opc.save(enc.getDataStream(fs));
        fs.writeFilesystem(printOs);
    }

    public static void export2(Workbook wb, String path) throws Exception {
        FileOutputStream fileOut = new FileOutputStream(path);
        wb.write(fileOut);
        fileOut.close();
        POIFSFileSystem fs = new POIFSFileSystem();
        EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
        Encryptor enc = info.getEncryptor();
        enc.confirmPassword("123456");
        OPCPackage opc = OPCPackage.open(new File(path));
        OutputStream os = enc.getDataStream(fs);
        opc.save(os);
        opc.close();
        FileOutputStream fos = new FileOutputStream(path);
        fs.writeFilesystem(fos);
        fos.close();
    }

    public static void export3(String path) throws Exception {
        EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
        Encryptor enc = info.getEncryptor();
        enc.confirmPassword("111111");


//        POIFSFileSystem fs = new POIFSFileSystem();
//        OPCPackage opc = OPCPackage.open(in);
//        OutputStream os = enc.getDataStream(fs);
//        opc.save(os);
//        opc.flush();
//
//        fs.writeFilesystem(fos);
//        fos.flush();
    }


    /**
     * 加密excel文件
     *
     * @param oldFileName 待加密文件，带路径
     * @param newFileName 加密后文件，带路径，可覆盖原文件
     * @param password    密码
     */
    public static void encryptExcel(String oldFileName, String newFileName, String password) {

        InputStream in = null;
        OPCPackage opc = null;
        FileOutputStream fos = null;
        POIFSFileSystem fs = null;

        try {
            in = new FileInputStream(oldFileName);

            //创建POIFS文件系统，加密文件
            fs = new POIFSFileSystem();
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
            Encryptor enc = info.getEncryptor();
            enc.confirmPassword(password);

            //将输入流输入到OPCPackage里
            opc = OPCPackage.open(in);
            OutputStream os = enc.getDataStream(fs);
            opc.save(os);
            opc.flush();

            // 保存加密文件
            fos = new FileOutputStream(newFileName);
            fs.writeFilesystem(fos);
            fos.flush();

            System.out.println("加密并保存成功");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (opc != null) {
                    opc.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (in != null) {
                    in.close();
                }
                if (fs != null) {
                    fs.close();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }

    }
    public static void encryptExcel(InputStream inputStream, OutputStream outputStream, String password) {

        OPCPackage opc = null;
        POIFSFileSystem fs = null;

        try {
            //创建POIFS文件系统，加密文件
            fs = new POIFSFileSystem();
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
            Encryptor enc = info.getEncryptor();
            enc.confirmPassword(password);

            //将输入流输入到OPCPackage里
            opc = OPCPackage.open(inputStream);
            OutputStream os = enc.getDataStream(fs);
            opc.save(os);
            opc.flush();
            os.close();
            // 保存加密文件
            fs.writeFilesystem(outputStream);
            outputStream.flush();

            System.out.println("加密并保存成功");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (opc != null) {
                    opc.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fs != null) {
                    fs.close();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }

    }

    /**
     * 解密excel文件
     *
     * @param oldFileName 待解密文件，带路径
     * @param newFileName 解密后文件，带路径，可覆盖原文件
     * @param password    密码
     */
    public static void decriptExcel(String oldFileName, String newFileName, String password) {
        try {
            Workbook workbook = WorkbookFactory.create(new File(oldFileName), password);

            // 测试，输入文件里的内容
            System.out.println(workbook.getSheetAt(0).getRow(0).getCell(0));

            // 直接保存就是去除密码后的文件了
            workbook.write(new FileOutputStream(newFileName));

            System.out.println("解密并保存成功");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
