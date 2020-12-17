package main;

public class aggregated {
	private String format;
	private String name;
	private String url;
	private int horasRegistradas;
	
	public String getFormat() {
		return format;
	}
	public String getName() {
		return name;
	}
	public String getUrl() {
		return url;
	}
	
	public int getHorasRegistradas() {
		return horasRegistradas;
	}
	public void setHorasRegistradas(int horasRegistradas) {
		this.horasRegistradas = horasRegistradas;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "aggregated [format=" + format + ", name=" + name + ", url=" + url + "]";
	}
	
	
}
