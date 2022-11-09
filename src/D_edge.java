
public class D_edge implements Comparable<D_edge> {
	
	String v1;
	String v2;
	int w;
	
	public D_edge(String v1, String v2, int w) {
		
		this.v1 = v1;
		this.v2 = v2;
		this.w = w;
	}

	public D_edge(String v1, int w) {
		
		this.v1 = v1;
		this.w = w;
	}
	
	
	@Override                        // for player arraylist sorting
	public int compareTo(D_edge e) {
		int compare_w=((D_edge)e).w;
		
		// for ascending order
        return this.w-compare_w;
	}
	
	

	public String getV1() {
		return v1;
	}

	public void setV1(String v1) {
		this.v1 = v1;
	}

	public String getV2() {
		return v2;
	}

	public void setV2(String v2) {
		this.v2 = v2;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}
	
	
	
	

}
