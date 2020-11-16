package org.study.project.annotation;

import java.lang.reflect.Method;

/**
 * @author chenyao
 * @date 2019/12/12 11:33
 * @description
 * 注解的本质：
 *      public interface Hello extends Annotation{
 * 	        public abstract String value();
 *      }
 * 反编译看注解 就是一个继承接口Annotation的接口
 *
 * 当注解的作用域为runtime时，通过反射获取到注解，然后获取注解的值
 * 这个过程的原理是 jvm对注解进行代理产生一个代理实例，然后执行的时候实际是执行InvocationHandler#invoke方法
 * 这个接口的实例是AnnotationInvocationHandler ，里面维护了一个Map集合用于存储 方法名和方法值
 *
 * https://www.cnblogs.com/yangming1996/p/9295168.html
 *
 * 首先，我们通过键值对的形式可以为注解属性赋值，像这样：@Hello（value = "hello"）。
 * 接着，你用注解修饰某个元素，编译器将在编译期扫描每个类或者方法上的注解，会做一个基本的检查，你的这个注解是否允许作用在当前位置，最后会将注解信息写入元素的属性表。
 * 然后，当你进行反射的时候，虚拟机将该注解的方法名和方法值取出来放到一个 map 中，并创建一个 AnnotationInvocationHandler 实例，把这个 map 传递给它。
 * 最后，虚拟机将采用 JDK 动态代理机制生成一个目标注解的代理类，并初始化好处理器。
 * 那么这样，一个注解的实例就创建出来了，它本质上就是一个代理类，你应当去理解好 AnnotationInvocationHandler 中 invoke 方法的实现逻辑，这是核心。一句话概括就是，通过方法名返回注解属性值。
 */
public class HelloTest {

    @Hello("hello")
    public static void main(String[] args) throws NoSuchMethodException {
        Method main = HelloTest.class.getMethod("main", String[].class);
        Hello hello = main.getAnnotation(Hello.class);
        System.out.println(hello.value());
    }
}
