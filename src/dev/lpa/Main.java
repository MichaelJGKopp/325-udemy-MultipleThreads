package dev.lpa;

import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) {

    StopWatch greenWatch = new StopWatch(TimeUnit.SECONDS);
    StopWatch purpleWatch = new StopWatch(TimeUnit.SECONDS);
    StopWatch redWatch = new StopWatch(TimeUnit.SECONDS);
    Thread green = new Thread(greenWatch::countDown, ThreadColor.ANSI_GREEN.name());
    Thread purple = new Thread(() -> purpleWatch.countDown(7),
      ThreadColor.ANSI_PURPLE.name());
    Thread red = new Thread(redWatch::countDown, ThreadColor.ANSI_RED.name());
    green.start();
    purple.start();
    red.start();
  }
}

class StopWatch {

  private TimeUnit timeunit;
  private int i;

  public StopWatch(TimeUnit timeunit) {
    this.timeunit = timeunit;
  }

  public void countDown() {
    countDown(5);
  }

  public void countDown(int unitCount) {

    String threadName = Thread.currentThread().getName();

    ThreadColor threadColor = ThreadColor.ANSI_RESET;
    try {
      threadColor = ThreadColor.valueOf(threadName);
    } catch (IllegalArgumentException ignore) {
      // User may pass a bad color name, Will just ignore this error.
    }
    String color = threadColor.color();
    for (i = unitCount; i > 0; i--) {
      try {
        timeunit.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.printf("%s%s Thread : i = %d%n", color, threadName, i);
    }
  }
}
