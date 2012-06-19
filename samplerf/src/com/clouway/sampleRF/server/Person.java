package com.clouway.sampleRF.server;

import javax.validation.constraints.Size;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
//@Entity
public class Person {

  @Size(min = 2, max = 15)
  private String realName;
  
  @Size(min = 1, max = 30)
  private String nickName;

//  @Id
//  @Column(name="id")
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  @Version
//  @Column(name="version")
  private Integer version;

  public Long getId() {
    return id;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }


//  public void save(PersonProxy personProxy){
//    DatabaseHelper databaseHelper = new DatabaseHelper();
//    databaseHelper.executeQuery("INSERT INTO Persons(username, nickname) VALUES (?,?);", personProxy.getRealName(), personProxy.getNickName());
//  }

//  public void save(String name, String nick){
//    DatabaseHelper databaseHelper = new DatabaseHelper();
//    databaseHelper.executeQuery("INSERT INTO Persons(username, nickname) VALUES (?,?);", name, nick);
//  }

  public Integer getVersion() {
    return version;
  }
}
