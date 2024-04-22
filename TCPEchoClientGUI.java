import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.*;
import java.util.Scanner;
public class TCPEchoClientGUI extends JFrame{
	public static void main(String[] args){
		if((args.length<1)||(args.length>2)){
			throw new IllegalArgumentException("Parameter(s):127.0.0.1 [6700]");
		}
		String server=args[0];
		int servPort=(args.length==2)?Integer.parseInt(args[1]):7;
		
		JFrame frame=new TCPEchoClientGUI(server, servPort);
		frame.setVisible(true);
	}
	public TCPEchoClientGUI(String server, int servPort){
		super("TCP Echo Client");
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JTextField echoSend=new JTextField();
		getContentPane().add(echoSend,"South");
		
		final JTextArea echoReply=new JTextArea(8,20);
		echoReply.setEditable(false);
		JScrollPane scrollPane=new JScrollPane(echoReply);
		getContentPane().add(scrollPane,"Center");
		final Socket socket;
		final DataInputStream in;
		final OutputStream out;
		try{
			socket=new Socket(server, servPort);
			in=new DataInputStream(socket.getInputStream());
			out=socket.getOutputStream();
			echoSend.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					if(event.getSource()==echoSend){
						byte[] byteBuffer=echoSend.getText().getBytes();
						try{
							out.write(byteBuffer);
							in.readFully(byteBuffer);
							echoReply.append(new String(byteBuffer)+"\n");
							echoSend.setText("");
						}catch (IOException e){
							echoReply.append("ERROR\n");
						}
					}
				}
			});
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					try{
						socket.close();
					}catch(Exception exception){
					}
					System.exit(0);
				}
			});
		}catch (IOException exception){
			echoReply.append(exception.toString()+"\n");
		}
	}
}