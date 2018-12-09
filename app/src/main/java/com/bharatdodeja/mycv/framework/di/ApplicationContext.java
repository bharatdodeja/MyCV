package com.bharatdodeja.mycv.framework.di;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Qualifier to define Application context
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ApplicationContext {
}
