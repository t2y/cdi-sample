package cdi.sample;

import javax.enterprise.inject.se.SeContainerInitializer;

import org.jboss.weld.environment.se.Weld;

import cdi.sample.dependencyinjection.MyApp;
import cdi.sample.dependencyinjection.MyClient;
import cdi.sample.dependencyinjection.MyProducer;
import cdi.sample.observer.SimpleEvent;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  private static void initializeDependencyInjection() {
    val weld = new Weld().enableDiscovery();
    val container = weld.initialize();

    //val p = new MyProducer(container);

    val myClient = container.select(MyClient.class).get();
    log.info("id: " + String.valueOf(myClient.getId()));
    log.info("name: " + myClient.getName());
    log.info("------------------------------------------------------------------------");

    val myApp = container.select(MyApp.class).get();
    log.info("id: " + String.valueOf(myApp.getMyClient().getId()));
    log.info("------------------------------------------------------------------------");
  }

  private static void initializeObserver() {
    val containerInitializer = SeContainerInitializer.newInstance();
    try (val container = containerInitializer.initialize()) {
      container.getBeanManager().fireEvent(new SimpleEvent());
    }
    log.info("------------------------------------------------------------------------");
  }

  public static void main(String[] args) {
    log.info("start");
    initializeDependencyInjection();
    initializeObserver();
    log.info("end");
  }
}
