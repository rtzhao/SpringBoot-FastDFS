package fun.rtzhao.springbootfastdfs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication()
public class SpringbootFastdfsApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootFastdfsApplication.class, args);
  }
}
