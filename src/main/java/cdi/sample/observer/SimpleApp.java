package cdi.sample.observer;

import javax.enterprise.event.Observes;

public class SimpleApp {
  public void onEvent(@Observes SimpleEvent ignored, SimpleService service) {
    service.greet();
  }
}
