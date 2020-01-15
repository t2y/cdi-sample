package cdi.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jboss.weld.environment.se.Weld;
import org.junit.jupiter.api.Test;

import cdi.sample.dependencyinjection.MyApp;
import cdi.sample.dependencyinjection.MyClient;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class MainTest {
  @Test
  void testSimple() {
    Main.setCONFIGURATION(new Configuration(new String[] {"ttt", "sss"}));
    val weld = new Weld().enableDiscovery();
    val container = weld.initialize();
    val myClient = container.select(MyClient.class).get();
    val myApp = container.select(MyApp.class).get();
    log.info("id1: " + myApp.getMyClient1().getId());
    log.info("hash1: " + myApp.getMyClient1().hashCode());
    assertEquals(myClient.hashCode(), myApp.getMyClient2().hashCode());
  }
}
