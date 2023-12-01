class CallMe{
	void call(String msg){
		System.out.print("["+msg);
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			System.out.println("Interrupted");
		}
		System.out.println("]");
	}
}

class Caller implements Runnable{
	String msg;
	CallMe target;
	Thread t;
	public Caller(CallMe targ, String s){
		target = targ;
		msg = s;
		t = new Thread(this);
		t.start();
	}
	public void run(){
		synchronized(target){
			target.call(msg);
		}
	}
}

class Synch{
	public static void main(String args[]){
		CallMe target = new CallMe();
		Caller ob1 = new Caller(target,"Hello"),
			ob2 = new Caller(target,"Synchronized"),
			ob3 = new Caller(target,"World");
		try{
			ob1.t.join();
			ob2.t.join();
			ob3.t.join();
		}catch(InterruptedException e){
			System.out.println("Interrupted");
		}
	}
}