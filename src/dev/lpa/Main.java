package dev.lpa;

import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) {


  }
}

class StopWatch {

  private TimeUnit timeunit;

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
    for (int i = unitCount; i > 0; i--) {
      try {
        timeunit.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.printf("%s%s Thread : i = %d%n", color, threadName, i);
    }
  }
}
