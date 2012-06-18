package com.clouway.sampleRF.server;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface PersonRepository {

  void save(String name, String nick);
}
