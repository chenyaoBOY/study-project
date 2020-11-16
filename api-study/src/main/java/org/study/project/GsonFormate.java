package org.study.project;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * Author: chenyao
 * Date: 2019/1/27 eight:46
 * Description:
 */
public class GsonFormate {

    public static void main(String[] args) {

        Function<String,Integer> function =  s -> {
            System.out.println(s);
            return 1;
        };
        System.out.println(function.toString());;
        System.out.println(function.apply(" qw"));;

    }

    /**
     * text : MXCHIP won a prize
     * id : 1234
     *
     * detail : {"comp":"MXCHIP.Inc","from":"ShangHai","focus":"Internet of Things","module":[{"k":"EMW3165"},{"k":"EMW3166"},{"k":"EMW3031"},{"k":"EMW3239"}]}
     */

    private String name;
    private Date date;

    private String text;
    private int id;
    private DetailBean detail;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * comp : MXCHIP.Inc
         * from : ShangHai
         * focus : Internet of Things
         * module : [{"k":"EMW3165"},{"k":"EMW3166"},{"k":"EMW3031"},{"k":"EMW3239"}]
         */

        private String comp;
        private String from;
        private String focus;
        private List<ModuleBean> module;

        public String getComp() {
            return comp;
        }

        public void setComp(String comp) {
            this.comp = comp;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getFocus() {
            return focus;
        }

        public void setFocus(String focus) {
            this.focus = focus;
        }

        public List<ModuleBean> getModule() {
            return module;
        }

        public void setModule(List<ModuleBean> module) {
            this.module = module;
        }

        public static class ModuleBean {
            /**
             * k : EMW3165
             */

            private String k;

            public String getK() {
                return k;
            }

            public void setK(String k) {
                this.k = k;
            }
        }
    }
}
