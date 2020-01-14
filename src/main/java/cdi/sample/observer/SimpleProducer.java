package cdi.sample.observer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class SimpleProducer {

  @Produces
  @Named("sid")
  public int getClientId() {
    log.info("called getClientId");
    return 222;
  }

  @Produces
  @SimpleClientWithArgs
  public SimpleClient newSimpleClient(@Named("sid") int id) {
    return new SimpleClient(id);
  }
}
