package com.github.poserg.primefaces.dialog.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class KeyboardBean {

	private String value;

	public String getValue() {
		System.out.println("KeyboardBean::reading value: " + value);

		return value;
	}

	public void setValue(final String value) {
		System.out.println("KeyboardBean::setting value: " + value);

		this.value = value;
	}
}
