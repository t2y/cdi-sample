package cdi.sample.dependencyinjection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Named
@ApplicationScoped
public class MyClient {

  private final int id;
  private final String name;

  @Inject
  public MyClient(@Named("anotherId") int id, String name) {
    this.id = id;
    this.name = name;
  }

  @PostConstruct
  public void afterCreate() {
    log.info("MyClient is created");
  }
}
