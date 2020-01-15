package cdi.sample.dependencyinjection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import lombok.Getter;

@Getter
@ApplicationScoped
public class MyApp {
  @Inject @MyClientWithArgs private MyClient myClient1;
  @Inject @MyClientWithContainer private MyClient myClient2;
}
