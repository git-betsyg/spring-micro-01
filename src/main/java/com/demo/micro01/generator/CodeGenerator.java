package com.demo.micro01.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * MyBatis-Plus 代码生成器
 * 参考：https://baomidou.com/guides/new-code-generator/
 *
 * 在 IDE 中直接运行 main 方法即可生成代码
 */
public class CodeGenerator {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/spring_micro_01"
            + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC"
            + "&remarks=true&useInformationSchema=true&tinyInt1isBit=true"
            + "&forceConnectionTimeZoneToSession=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String AUTHOR = "demo";
    private static final String PARENT_PACKAGE = "com.demo.micro01";

    public static void main(String[] args) {
        Path projectPath = resolveProjectPath();

        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> builder
                        .author(AUTHOR)
                        .commentDate("yyyy-MM-dd")
                        .outputDir(projectPath.resolve("src", "main", "java").toString())
                )
                .packageConfig(builder -> builder
                        .parent(PARENT_PACKAGE)
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .pathInfo(Collections.singletonMap(
                                OutputFile.xml,
                                projectPath.resolve("src", "main", "resources", "mapper").toString()
                        ))
                )
                .strategyConfig((scanner, builder) -> builder
                        .addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔，输入 all 生成全部表：")))
                        .addTablePrefix("t_", "c_")
                        .entityBuilder()
                        .enableLombok()
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE)
                        )
                        .build()
                )
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }

    private static Path resolveProjectPath() {
        Path cwd = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
        Path moduleDir = cwd.resolve("spring-micro-01");
        if (moduleDir.resolve("pom.xml").toFile().isFile()) {
            return moduleDir;
        }
        return cwd;
    }

    /**
     * 处理 all 情况：空列表表示生成全部表
     */
    protected static List<String> getTables(String tables) {
        return "all".equalsIgnoreCase(tables.trim())
                ? Collections.emptyList()
                : Arrays.asList(tables.split(","));
    }
}
