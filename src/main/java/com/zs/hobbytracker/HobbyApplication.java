package com.zs.hobbytracker;

import com.zs.hobbytracker.logger.Logger;
import com.zs.hobbytracker.utils.DatabaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class HobbyApplication {


    public static void main(String[] args) {
        DatabaseConnection.initialiseConnection();
        Logger.initialiseLogger();
        SpringApplication.run(HobbyApplication.class);
    }

}
