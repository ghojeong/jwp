package app;

import app.controller.LoginController;
import app.controller.RegisterController;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.handler.HandlerManager;

public class Application {
    public static void main(String[] args) {
        HandlerManager.setNotFoundLocation("/404.html");
        HandlerManager.addHandler(
                new LoginController(),
                new RegisterController()
        );
        new Tomcat().start();
    }
}
