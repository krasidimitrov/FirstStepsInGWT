package com.clouway.sampleRF.server;

import com.google.inject.Inject;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class PersonService {
  @Inject
  private PersonRepository personRepository;

  public void save(Person person) {
    personRepository.save(person);
  }

  public Person getPersonFromNick(String nick) {
    return personRepository.getPersonFromNick(nick);
  }

  public void update(Person person) {
    personRepository.update(person);
  }
}
