package cdi.sample;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.se.SeContainerInitializer;

import org.jboss.weld.environment.se.Weld;

import cdi.sample.dependencyinjection.MyApp;
import cdi.sample.dependencyinjection.MyClient;
import cdi.sample.observer.SimpleEvent;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  private static Configuration CONFIGURATION;

  @Produces
  public static Configuration getConfiguration() {
    log.info("called getConfiguration");
    return CONFIGURATION;
  }

  private static void initializeDependencyInjection() {
    val weld = new Weld().enableDiscovery();
    val container = weld.initialize();

    val myClient1 = container.select(MyClient.class).get();
    log.info("id: " + myClient1.getId());
    log.info("name: " + myClient1.getName());
    log.info("hash: " + myClient1.hashCode());
    log.info("------------------------------------------------------------------------");

    val myApp = container.select(MyApp.class).get();
    log.info("id1: " + myApp.getMyClient1().getId());
    log.info("hash1: " + myApp.getMyClient1().hashCode());
    log.info("id2: " + myApp.getMyClient2().getId());
    log.info("hash2: " + myApp.getMyClient2().hashCode());
    log.info("------------------------------------------------------------------------");

    val myClient2 = container.select(MyClient.class).get();
    log.info("id: " + myClient2.getId());
    log.info("name: " + myClient2.getName());
    log.info("hash: " + myClient2.hashCode());
    log.info("------------------------------------------------------------------------");
    weld.shutdown();
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

    CONFIGURATION = new Configuration(args);

    initializeDependencyInjection();
    initializeObserver();
    log.info("end");
  }
}
