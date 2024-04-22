import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPEchoServer{
	private static final int BUFSIZE=32;
	public static void main(String[] args)throws IOException{
		if(args.length!=1)
			throw new IllegalArgumentException("Parameter(s):6700");
		int servPort=Integer.parseInt(args[0]);
		ServerSocket servSock = new ServerSocket(servPort);
		int recvMsgSize;
		byte[] receiveBuf=new byte[BUFSIZE];
		while(true){
			Socket clntSock=servSock.accept();
			SocketAddress clientAddress=clntSock.getRemoteSocketAddress();
			System.out.println ("Handling client at"+clientAddress);
			InputStream in=clntSock.getInputStream();
			OutputStream out=clntSock.getOutputStream();
			
			while((recvMsgSize=in.read(receiveBuf))!=-1){
				String s=new String(receiveBuf,0,recvMsgSize);
				System.out.println("Message Recieved From Client:"+s);
				System.out.println("ENTER Y/N/Q");
				Scanner a =new Scanner(System.in);
				char ans=a.next().charAt(0);
				if(ans=='q'){
					System.exit(0);}
				else if(ans=='n'){
					System.out.println("Access Denied");}
				else{
					String u=s.toUpperCase();
					out.write(u.getBytes(),0,u.length());
				}
			}
			clntSock.close();
		}
	}
}