package cdi.sample;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.se.SeContainerInitializer;

import org.jboss.weld.environment.se.Weld;

import cdi.sample.dependencyinjection.MyApp;
import cdi.sample.dependencyinjection.MyClient;
import cdi.sample.observer.SimpleEvent;
import lombok.Setter;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  @Setter private static Configuration CONFIGURATION;

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

    val myApp1 = container.select(MyApp.class).get();
    log.info("myApp1.hash: " + myApp1.hashCode());
    log.info("myApp1.client.id1: " + myApp1.getMyClient1().getId());
    log.info("myApp1.client.hash1: " + myApp1.getMyClient1().hashCode());
    log.info("myApp1.client.id2: " + myApp1.getMyClient2().getId());
    log.info("myApp1.client.hash2: " + myApp1.getMyClient2().hashCode());
    log.info("------------------------------------------------------------------------");

    val myApp2 = container.select(MyApp.class).get();
    log.info("myApp2.hash: " + myApp2.hashCode());
    log.info("myApp2.client.id1: " + myApp2.getMyClient1().getId());
    log.info("myApp2.client.hash1: " + myApp2.getMyClient1().hashCode());
    log.info("myApp2.client.id2: " + myApp2.getMyClient2().getId());
    log.info("myApp2.client.hash2: " + myApp2.getMyClient2().hashCode());
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
