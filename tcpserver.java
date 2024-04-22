import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;
class tcpserver{
	public static void main(String[] args) throws Exception{
	String clientsentence;
	String capsen;
	ServerSocket welcomesocket=new ServerSocket(6700);
	while(true){
		Socket connectionSocket=welcomesocket.accept();
		BufferedReader informclient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		DataOutputStream outtoclient=new DataOutputStream(connectionSocket.getOutputStream());
		clientsentence=informclient.readLine();
		Scanner in =new Scanner(System.in);
		System.out.println("Message Recieved From Client:"+clientsentence);
		System.out.println("ENTER Y/N/Q");
		char ans=in.next().charAt(0);
		if(ans=='q'){
		System.exit(0);}
		else if(ans=='n'){
		System.out.println("Access Denied");}
		else{
		capsen=clientsentence.toUpperCase()+'\n';
		outtoclient.writeBytes(capsen);}
		}
	}
}