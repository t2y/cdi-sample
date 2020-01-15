package cdi.sample.dependencyinjection;

import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import cdi.sample.Configuration;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class MyProducer {

  private WeldContainer container;

  public MyProducer() {
    val weld = new Weld().enableDiscovery();
    this.container = weld.initialize();
  }

  @Produces
  @Named
  public int getId() {
    log.info("called getId");
    return 10;
  }

  @Produces
  @Named("anotherId")
  public int getAnotherId() {
    log.info("called getAnotherId");
    return 20;
  }

  @Produces
  @Named("withArgsId")
  public int getWithArgsId() {
    log.info("called getWithArgsId");
    return 30;
  }

  @Produces
  @Named("tname")
  public String getName() {
    return "test";
  }

  @Produces
  @MyClientWithArgs
  public MyClient getNewMyClient(@Named("withArgsId") int id, @Named("tname") String name) {
    log.info("called getNewMyClient");
    return new MyClient(id, name);
  }

  @Produces
  @MyClientWithContainer
  public MyClient getSelectMyClient(Configuration config) {
    log.info("called getSelectMyClient");
    log.info("config.args: " + Arrays.toString(config.getArgs()));
    return this.container.select(MyClient.class).get();
  }
}
