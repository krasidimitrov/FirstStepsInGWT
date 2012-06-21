package com.clouway.sampleRF.server;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface PersonRepository {

  void save(Person person);
  
  Person getPersonFromNick(String nick);
  
  void update(Person person);
}
