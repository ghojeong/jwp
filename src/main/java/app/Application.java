package app;

import org.apache.catalina.startup.Tomcat;

public class Application {
    public static void main(String[] args) {
        new Tomcat().start();
    }
}
