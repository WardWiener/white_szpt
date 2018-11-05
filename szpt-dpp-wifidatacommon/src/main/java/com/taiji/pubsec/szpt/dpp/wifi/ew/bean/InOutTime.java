package com.taiji.pubsec.szpt.dpp.wifi.ew.bean;

import java.io.Serializable;

public class InOutTime implements Serializable {
	private static final long serialVersionUID = -3774547033110772774L;
	
	private Long in;
	private Long out;
	
	public static final InOutTime EMPTY_VALUE = new InOutTime();
	
	public InOutTime(Long in, Long out) {
		this.in = in;
		this.out = out;
	}
	
	public InOutTime(){
		in = Long.valueOf(-1);
		out = Long.valueOf(-1);
	}
	
	public static boolean emptyValue(InOutTime inout){
		if(inout.getIn() == -1 && inout.getOut() == -1){
			return true;
		}
		return false;
	}
	
	public InOutTime intersect(InOutTime m){
		if(null == m){
			return EMPTY_VALUE;
		}
		if(out < m.getIn()){
			return EMPTY_VALUE;
		}
		if(in > m.getOut()){
			return EMPTY_VALUE;
		}
		if(in <= m.getIn() && out >= m.out){
			return m;
		}
		if(m.getIn() < in && out < m.getOut()){
			return this;
		}
		if(in <= m.getIn() && m.getIn() <= out && out <= m.getOut()){
			return new InOutTime(m.getIn(), out);
		}
		if(m.getIn() <= in && in <= m.getOut() && m.getOut() < out){
			return new InOutTime(in, m.getOut());
		}
		return EMPTY_VALUE;
	}
	
	public Long getIn() {
		return in;
	}
	public Long getOut() {
		return out;
	}

	public void setIn(Long in) {
		this.in = in;
	}

	public void setOut(Long out) {
		this.out = out;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((in == null) ? 0 : in.hashCode());
		result = prime * result + ((out == null) ? 0 : out.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InOutTime other = (InOutTime) obj;
		if (in == null) {
			if (other.in != null)
				return false;
		} else if (!in.equals(other.in))
			return false;
		if (out == null) {
			if (other.out != null)
				return false;
		} else if (!out.equals(other.out))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InOutTime [in=" + in + ", out=" + out + "]";
	}
	
}
