package p1;

public class confirm {
	int cid;
	int tid;
	String name;
	String address;
	String phone;
	String source;
	String destination;
	String sdate;
	int distance;
	int fee;
	// public  confirm(int cid,int tid,String name,String address,String phone,String source,String destination,String sdate) {
	  //  	this.cid=cid;
		//	this.tid=tid;
			//this.name=name;
		//	this.address=address;
			//this.phone=phone;
			//this.source=source;
		//	this.destination=destination;
			//this.sdate=sdate;
	    	
	   public confirm(int cid2, int tid2, String string, String string2, String string3, String string4, String string5,
			String string6) {
		// TODO Auto-generated constructor stub
		   cid=cid2;
		   tid=tid2;
		   name=string;
		   address=string2;
		   phone=string3;
		   source=string4;
		   destination=string5;
		   sdate=string6;
		   
	}

			// }
	   		public int getcid() {
	    	return cid;
	    }
		
}
