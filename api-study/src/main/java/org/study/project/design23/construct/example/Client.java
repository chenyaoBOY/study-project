package org.study.project.design23.construct.example;

/**
 * @author chenyao
 * @date 2019/8/26 16:14
 * @description
 */
public class Client {
        public static void main(String[] args)
        {
            Builder builder=new ProductBulider();
            Director director=new Director(builder);
            Product product=director.construct();
            product.show();
        }
}
