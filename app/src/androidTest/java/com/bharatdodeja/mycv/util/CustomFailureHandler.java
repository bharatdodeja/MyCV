package com.bharatdodeja.mycv.util;

import android.content.Context;
import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.base.DefaultFailureHandler;
import androidx.test.runner.screenshot.Screenshot;
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

