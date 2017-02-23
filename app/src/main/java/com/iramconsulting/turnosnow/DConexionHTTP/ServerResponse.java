package com.iramconsulting.turnosnow.DConexionHTTP;

import java.io.Serializable;

public class ServerResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private MetaData metaData;
	private T data;

	public ServerResponse() {
		super();
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ServerResponse [MetaData=" + metaData + ", Data =" + data + "]";
	}

}
