package com.clouway.rftwo.client.gin;

import com.clouway.rftwo.client.MainViewImpl;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@GinModules(SimpleGinModule.class)
public interface SimpleGinInjector extends Ginjector{
  MainViewImpl getMainViewImpl();
}
