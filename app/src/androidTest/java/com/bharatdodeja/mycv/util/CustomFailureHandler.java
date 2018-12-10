package com.bharatdodeja.mycv.util;

import android.content.Context;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.base.DefaultFailureHandler;
import android.support.test.runner.screenshot.Screenshot;
import android.view.View;

import org.hamcrest.Matcher;

public class CustomFailureHandler implements FailureHandler {
  private final FailureHandler delegate;

  public CustomFailureHandler(Context targetContext) {
    delegate = new DefaultFailureHandler(targetContext);
  }

  @Override
  public void handle(Throwable error, Matcher<View> viewMatcher) {
//    try {
      Screenshot.capture();
      delegate.handle(error, viewMatcher);
//    } catch (NoMatchingViewException e) {
//      Screenshot.capture();
//      throw new MySpecialException(e);
//    }
  }

  private static class MySpecialException extends Throwable {
    MySpecialException(
        NoMatchingViewException e) {
    }
  }
}

