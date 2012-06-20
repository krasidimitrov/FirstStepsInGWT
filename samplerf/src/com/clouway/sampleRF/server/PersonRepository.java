package com.clouway.sampleRF.server;

import com.clouway.sampleRF.client.PersonProxy;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface PersonRepository {

  void save(PersonProxy personProxy);
}
