package cn.iocoder.springboot.labs;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * DemoMojo
 *
 * @author fw001
 * @date 2023/10/09 11:26
 **/
@Mojo(name = "demo")
public class DemoMojo extends AbstractMojo {

    /**
     * 要显示的问候语。
     */
    @Parameter(property = "greeting", defaultValue = "Hello World!")
    private String greeting;

    /**
     * My boolean.
     */
    @Parameter
    private boolean myBoolean;

    /**
     * My Integer.
     */
    @Parameter
    private Integer myInteger;

    /**
     * My File.
     */
    @Parameter
    private File myFile;

    public enum Color {
        GREEN,
        RED,
        BLUE
    }

    /**
     * My Enum
     */
    @Parameter
    private Color myColor;

    /**
     * My Array.
     */
    @Parameter
    private String[] myArray;

    /**
     * My List.
     */
    @Parameter
    private List myList;

    /**
     * My Map.
     */
    @Parameter
    private Map myMap;

    /**
     * My Properties.
     */
    @Parameter
    private Properties myProperties;

    public static class Person {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Parameter
    private Person person;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        this.getLog().info("hello my first demo maven plugin!");

        Field[] declaredFields = DemoMojo.class.getDeclaredFields();

        Arrays.stream(declaredFields).forEach(f -> {
            if (f.isAccessible()) {
                f.setAccessible(true);
            }
            try {
                this.getLog().info(f.getName() + ":" + f.get(this));
            } catch (IllegalAccessException e) {
                this.getLog().warn(e);
            }
        });
    }
}
