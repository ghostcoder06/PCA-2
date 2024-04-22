import java.io.*;
import java.net.*;
class tcpclient{
	public static void main(String[] args)throws Exception{
	String sentence;
	String modsen;
	System.out.println("enter the sentence to capitalise it.");
	BufferedReader informuser=new BufferedReader(new InputStreamReader(System.in));
	Socket clientsocket=new Socket("127.0.0.1",6700);
	DataOutputStream outtoserver=new DataOutputStream(clientsocket.getOutputStream());
	BufferedReader informserver=new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
	sentence=informuser.readLine();
	outtoserver.writeBytes(sentence+'\n');
	modsen=informserver.readLine();
	System.out.println("From server this is the modified string"+"\""+modsen+"\"");
	clientsocket.close();
	}
}