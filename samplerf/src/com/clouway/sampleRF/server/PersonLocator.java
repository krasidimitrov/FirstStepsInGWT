package com.clouway.sampleRF.server;

import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class PersonLocator extends Locator<Person, Long>{


  private final PersonRepository personRepository;

  public PersonLocator(PersonRepository personRepository){
    this.personRepository = personRepository;
  }

  @Override
  public Person create(Class<? extends Person> clazz) {
    try {
      return Person.class.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } catch (IllegalAccessException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    return null;
  }

  @Override
  public Person find(Class<? extends Person> clazz, Long id) {
    //??????????
    return null;
  }

  @Override
  public Class<Person> getDomainType() {
    //????????
    return Person.class;
  }

  @Override
  public Long getId(Person domainObject) {
    return domainObject.getId();
  }

  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }

  @Override
  public Object getVersion(Person domainObject) {
    return domainObject.getVersion();
  }
  
  //===========================================
  

  
}
