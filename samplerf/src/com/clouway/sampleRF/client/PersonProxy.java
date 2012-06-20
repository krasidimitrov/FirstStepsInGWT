package com.clouway.sampleRF.client;

import com.clouway.sampleRF.server.Person;
import com.clouway.sampleRF.server.PersonLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@ProxyFor(value = Person.class, locator = PersonLocator.class)
public interface PersonProxy extends EntityProxy {

  public String getRealName();

  public String getNickName();

  public String getPhone();

  public String getOccupation();

  public void setRealName(String realName);

  public void setNickName(String nickName);

  public void setPhone(String phone);

  public void setOccupation(String occupation);

}
