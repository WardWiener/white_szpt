package com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 人员布控移动端信息
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_rybkyddxx")
public class PersonMobileInfo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;

    @Column(name = "sjhm")
    private String number;    //手机号码

    @Column(name = "mac")
    private String mac; //MAC地址

    @ManyToOne
    @JoinColumn(name = "rybk_id")
    private PersonExecuteControl personExecuteControl;  //人员布控id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public PersonExecuteControl getPersonExecuteControl() {
        return personExecuteControl;
    }

    public void setPersonExecuteControl(PersonExecuteControl personExecuteControl) {
        this.personExecuteControl = personExecuteControl;
    }
}
