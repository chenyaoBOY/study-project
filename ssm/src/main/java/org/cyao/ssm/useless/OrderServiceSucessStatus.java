package org.cyao.ssm.useless;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * User: lixin
 * Date: 15-6-3 上午11:32
 * Time:
 */
public class OrderServiceSucessStatus extends HttpServlet {
    private static String startTime = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            out.println("ok" + "<br> ");
            out.println("ip:" + request.getLocalAddr() + " startTime:" + startTime + "<br> ");
            out.println("<br><br>");

            String result = " err";
            String filePath = null;
            try {

                filePath = System.getProperty("APP_ROOT");
                filePath = filePath + "/webapps/ROOT.war";
                File file = new File(filePath);
                if (file.exists()) {
                    Path path = Paths.get(filePath);
                    Path real = null;
                    if (Files.isSymbolicLink(path)) {
                        real = Files.readSymbolicLink(path);
                    }
                    if (real != null) {
                        result = real.toString();
                    }

                }

            } catch (IOException e) {
                out.println("IOException " + e.getMessage());
            }

            out.println("filePath ," + filePath + "<br> ");
            out.println("war file ," + result + "<br> ");

        } catch (Exception e) {
            out.println("read File has err" + e.getMessage());
        }

        out.flush();
        out.close();
    }

    public static void readFileByLines(String fileName, PrintWriter out) {
        File file = new File(fileName);
        if (file.exists()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                while ((tempString = reader.readLine()) != null) {
                    out.println(tempString + "	<br> ");
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
        } else {
            out.println(fileName + " 不存在");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
//        startTime = DateUtil.getNormalDateStr();
    }
}
