package project.thi_thu_java_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ThiThuJavaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThiThuJavaWebApplication.class, args);
    }

}
