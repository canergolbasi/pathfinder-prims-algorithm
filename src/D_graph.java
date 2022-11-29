import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class D_graph {
	
	
	
	ArrayList<String> d_node=new ArrayList<String>();
	
	ArrayList<D_edge> d_edge=new ArrayList<D_edge>();
	
	HashMap<String, String> d_track=new HashMap<String, String>();  // tracking shortest path

	

	public void addVertex(String string) {
		d_node.add(string);
		
	}

	public void addEdge(String string, String string2, int i) {
		d_edge.add(new D_edge(string,string2,i));
		
	}
	

	public String Dijkstra(String string, String string2) {
		
		String result="";
		
		//d_distances insert
				ArrayList<D_edge> d_distances=new ArrayList<D_edge>();
				
				d_distances.add(new D_edge(d_node.get(0),0));
				for(int i=1;i<d_node.size();i++) {
					d_distances.add(new D_edge(d_node.get(i),Integer.MAX_VALUE));
				}
				
				//d_distances sorting
				Collections.sort(d_distances);
		
			// d_distances insert hashmap insert
		HashMap<String,Integer> d_distances_visited=new HashMap<String,Integer>();
		
		for(int i=0;i<d_node.size();i++) {
			d_distances_visited.put(d_node.get(i), null);
		}
		
		
		D_edge currentnode = null;
		
		while(!d_distances.isEmpty()) {
			
			//taking top element
			currentnode=d_distances.get(0); //take top element of sorted arraylist
			
			for(int i=0;i<d_edge.size();i++) {
				if(currentnode.v1.equals(d_edge.get(i).v1)) {
					for(int j=0;j<d_distances.size();j++) {
						if(d_edge.get(i).v2.equals(d_distances.get(j).v1)) {
							for(int k=0;k<d_distances.size();k++) {
								if(d_distances.get(k).v1.equals(d_edge.get(i).v1)) {
									if(d_distances.get(k).w + d_edge.get(i).w<d_distances.get(j).w) {
										d_distances.get(j).setW(d_distances.get(k).w + d_edge.get(i).w);
										d_track.put(d_edge.get(i).v2, d_edge.get(i).v1);
										
									}
								}
							}
						}
					}
				}
			}
			
			//hashmap weight update
			d_distances_visited.put(currentnode.v1, currentnode.w);
			
			//remove top element of d_distances
			d_distances.remove(0);
			
			// sorting d_distances
			Collections.sort(d_distances);

		}

		int spendingtime=d_distances_visited.get(string2);

		if(!(d_distances_visited.get(string2)==null)  ) {

			String node1=string2;
			result=node1;
			
			while(!(node1.equals(string))) {				
				if(d_track.get(node1)==null){  // if it come null, he also cannot reach leyla
					result="-1";
					pathFinder.ismarried=false;
					return result;
				}
				
				String node2=d_track.get(node1);
				result=node2+ " " + result;				
				node1=node2;				
			}
			
			if(pathFinder.fathertimelimit >=spendingtime) {
				pathFinder.ismarried=true;
			}else {
				pathFinder.ismarried=false;
			}			
		}else {
			result="-1";
			pathFinder.ismarried=false; // if it cant reach leyla also cant marry with her
		}

		return result;
	}
	
	
	
	
	

}
