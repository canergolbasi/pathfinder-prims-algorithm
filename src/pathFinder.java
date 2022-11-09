import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class pathFinder {
	
public static ArrayList<Edge> q=new ArrayList<Edge>(); // store edge object in this array
	
	public static ArrayList<String> nodes=new ArrayList<String>();
	
	public static ArrayList<Edge> process=new ArrayList<Edge>();
	
	public static HashMap<String,Integer> lastvalueofedges=new HashMap<String,Integer>(); 
	
	
	public static int fathertimelimit=0;
	
	public static int totalcost=0;
	
	public static boolean ismarried=false;

	public static void main(String[] args) throws IOException {
		
		
		// take inputs
		
		String inputFileName=args[0];
		String outputFileName=args[1];
		
		Scanner sc2 = null;
		try {
			sc2 = new Scanner(new File(inputFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner s2 = new Scanner(sc2.nextLine());
		fathertimelimit=Integer.parseInt(s2.nextLine());  
		
		Scanner s3=new Scanner(sc2.nextLine());
		int totalcitynumber=Integer.parseInt(s3.nextLine());  
		
		
		
		Scanner s4=new Scanner(sc2.nextLine());
		String source=s4.next();	  // string c1	
		String destination=s4.next();  // string c7
		
		
		int citynumber_d=Integer.parseInt(destination.substring(1));  
		
		D_graph graph = new D_graph();
		//dijsktra node construct
		for(int i=1;i<(citynumber_d+1); i++) {
			String insert="c";
			insert=insert+Integer.toString(i);
			graph.addVertex(insert);
		}
		
		//mst node construct
		nodes.add(destination);
		for(int i=1;i<(totalcitynumber-citynumber_d+1);i++) {
			String insert2="d";
			insert2=insert2+Integer.toString(i);
			nodes.add(insert2);
		}
		

		// c1-c7 read lines and add disktra graph.edge
		for(int i=0;i<(citynumber_d-1);i++) {
			Scanner s5=new Scanner(sc2.nextLine());
			String sourcecity_d=s5.next();    //c1 string
			while(s5.hasNext()) {
				String destinationcity_d=s5.next();  // c2 string
				String cityw_d=s5.next();
				int cityweight_d=Integer.parseInt(cityw_d); 			
				graph.addEdge(sourcecity_d, destinationcity_d, cityweight_d);
			}

		}
		
		//read for d values
		for(int i=0;i<(totalcitynumber-citynumber_d+1);i++) {
			Scanner s6=new Scanner(sc2.nextLine());
			String sourcecity_p=s6.next();       // source city			
			while(s6.hasNext()) {
				String destinationcity_p=s6.next();
				String cityw_p=s6.next();
				int cityweight_p=Integer.parseInt(cityw_p);				
				q.add(new Edge(sourcecity_p,destinationcity_p,cityweight_p));
			}			
		}


		String resultpath=graph.Dijkstra(source,destination);   

		int totaltax=0;
		int txtanswer=0;
		
		if(ismarried==true) {
			totaltax=2*PrimAlgoritm(q,nodes);			
			txtanswer=totaltax;
		}else if(ismarried==false) {			
			txtanswer=-1;
		}
		
		
		
		
		
		// writing to file
		FileWriter myWriter = null;
    	myWriter = new FileWriter(outputFileName);
    	myWriter.write(resultpath);
    	myWriter.write("\n");
    	myWriter.write(Integer.toString(txtanswer));

    	
    	myWriter.close();
		
	}// end of main
	
	
	
	


public static int PrimAlgoritm(ArrayList<Edge> tq ,ArrayList<String> tnodes) {
	
	//produce process arraylist and sort it
	Edge newedge=new Edge(nodes.get(0),0); 
	process.add(newedge);    // set first element weight 0 		
	for(int i=1;i<nodes.size();i++) {
		Edge newedge2=new Edge(nodes.get(i),Integer.MAX_VALUE);
		process.add(newedge2);      // set other all element weight infinity in arraylist
	}
	// sorting process arraylist 
	Collections.sort(process);
	
	//produce lastvalueofedges hashmap and give weights		
	lastvalueofedges.put(nodes.get(0), 0);
	for(int i=1;i<nodes.size();i++) {
		lastvalueofedges.put(nodes.get(i), null);   
	}
	

	while(!process.isEmpty()) {
		Edge e=process.get(0);  //take top element 
		
		
		//assign 
		for(int i=0;i<q.size();i++) {
			
			
			
			
			if(q.get(i).v1.equals(e.v1)) {
				int assignw=q.get(i).w;
				
				//assign this assigw if there is value at process
				for(int j=0;j<process.size();j++) {
					if(q.get(i).v2.equals(process.get(j).v1) && q.get(i).w<process.get(j).w && (process.get(j).w>assignw)){
						process.get(j).setW(assignw);
					}
				}
				
				
			}else if(q.get(i).v2.equals(e.v1)) {
				int assignw2=q.get(i).w;
				
				for(int k=0;k<process.size();k++) {
					if(q.get(i).v1.equals(process.get(k).v1) && q.get(i).w<process.get(k).w && ( process.get(k).w>assignw2)) {
						process.get(k).setW(assignw2);
					}
				}				
			}

		}  // end of for
		
		// updating hashmap weight value
		lastvalueofedges.put(e.v1, e.w);
		// remove top element of process arraylist
		process.remove(0);
		// sorting process arraylist again
		Collections.sort(process);
		
	}
	
	
	// control honeymoon route
	for(Integer i : lastvalueofedges.values()) {
		if(i==null || i==Integer.MAX_VALUE) {        // may be max value check is not necessary
			return -1;
		}else {
		totalcost=totalcost+i;	
		}
	}

	return totalcost;
	
}// prim algoritm end





	
	

}