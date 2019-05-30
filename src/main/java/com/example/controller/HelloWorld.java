package com.example.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Controller
@RestController
public class HelloWorld {
	
	
	//Java under linux
	@RequestMapping("/script")
		 public  void Execute() throws IOException 
		 {
			 //Process p;
			// String[] cmd1 = {"sh","/home/ahmedmn/Bureau/param.sh"};
			//Runtime.getRuntime()ec("/home/ahmedmn/Bureau/param.sh");
		Runtime.getRuntime().exec("/home/ahmedmn/Bureau/Menu/version.sh");
		//Runtime.getRuntime().exec("ping 192.169.188.133");
		 }
	
	@RequestMapping("/install")
	public void addInstalMod()  {
	/*try{
    	
    	java.util.Properties config = new java.util.Properties(); 
    	config.put("StrictHostKeyChecking", "no");
    	JSch jsch = new JSch();
    	Session session=jsch.getSession("ahmedmn", "192.168.188.135", 22);
    	session.setPassword("ahmed=");
    	session.setConfig(config);
    	session.connect();
    	System.out.println("Connected");
    	//Runtime.getRuntime().exec("ls");
    	Channel channel=session.openChannel("exec");
   // Channel	channel =  session.openChannel("shell");
     
       //((ChannelExec) channel).setCommand(". /home/user/Bureau/port.sh user 192.168.126.190");
       ((ChannelExec) channel).setCommand("ls");
       channel.setInputStream(null);
       
    // ((ChannelShell)channel).setExtOutputStream(System.err);
      ((ChannelExec)channel).setErrStream(System.err);
        channel.connect();
       
       
        channel.disconnect();
        session.disconnect();
    }catch(Exception e){
    	e.printStackTrace();
    }
    */
		JSch jsch = new JSch();

		try
		{
		  Session session = jsch.getSession("ahmedmn", "192.168.188.135", 22);
		  session.setPassword("ahmed=");
		  java.util.Properties config = new java.util.Properties();
		  config.put("StrictHostKeyChecking", "no");
		  session.setConfig(config);
		  session.connect();
		  Channel channel = session.openChannel("exec");
		  //". ./Install/InstallGen/gen.sh"  serveur de generation
		  //". ./Install/InstallBD/installbd.sh "
		  //". ./Install/InstallPres/installpres.sh "
		  // ". ./Install/InstallTrait/mtbp1.sh "
		  //". ./Install/InstallTrait/servPTA2.sh  "
		  //". ./Install/InstallTrait/compilation.sh  "
		  //". ./Clonnage/BDclone/bdclone.sh  "
		  //". ./Clonnage/Genclone/genclone.sh  "
		  //". ./Clonnage/Presclone/presclone.sh  "
		  //". ./Clonnage/Traitclone/traitclone2.sh  "
		  //. ./Clonnage/Traitclone/traitclone3.sh
		 // ((ChannelExec) channel).setCommand(". ./Clonnage/Traitclone/test.sh ");
		  
//=================Verification====================//
		  
		  ((ChannelExec) channel).setCommand(". ./Menu/user/user.sh ");
		  ((ChannelExec) channel).setCommand(". ./Menu/package/package.sh ");
		  ((ChannelExec) channel).setCommand(". ./Menu/fs/fs.sh ");
		  ((ChannelExec) channel).setCommand(". ./Menu/service/servicepta.sh ");
		  
		  
		  
		  
		  
		  channel.setInputStream(null);
		  ((ChannelExec) channel).setErrStream(System.err);
		  InputStream in = channel.getInputStream();
		  channel.connect();

		  byte[] tmp = new byte[1024];
		  while (true)
		  {
		    while (in.available() > 0)
		    {
		      int i = in.read(tmp, 0, 1024);
		      if (i < 0)
		        break;
		      System.out.print(new String(tmp, 0, i));
		    }
		    if (channel.isClosed())
		    {
		      System.out.println("exit-status: " + channel.getExitStatus());
		      break;
		    }
		    try
		    {
		      Thread.sleep(1000);
		    }
		    catch (Exception ee)
		    {
		    }
		  }

		  channel.disconnect();
		  session.disconnect();
		}
		catch (Exception e)
		{
		  System.out.println(e.getMessage());
		}
	}
	
	
	@RequestMapping("/read")
	public void readfile()
	{
		try{

	        if ((new File("d:\\Profiles\\amounani\\Desktop\\Paramfile\\paramfile")).exists()) {

	            Process p = Runtime
	               .getRuntime()
	               .exec("rundll32 url.dll,FileProtocolHandler d:\\Profiles\\amounani\\Desktop\\Paramfile\\paramfile");
	            p.waitFor();

	        } else {
	            System.out.println("File does not exist");
	        }
	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }
	}
	
	
	
	
	@RequestMapping("/copy")
	public void copy() 
	{
		try {
			JSch jsch = new JSch();
			Session session = null;
			session = jsch.getSession("ahmedmn","192.168.188.135",22);
			session.setPassword("ahmed=");
			session.setConfig("StrictHostKeyChecking", "no");
			    session.connect();
			ChannelSftp channel = null;
			channel = (ChannelSftp)session.openChannel("sftp");
			channel.connect();
			    File localFile = new File("d:\\Profiles\\amounani\\Desktop\\Paramfile\\paramfile");
			    //If you want you can change the directory using the following line.
			  channel.cd("/home/ahmedmn/Install/InstallGen");
			channel.put(new FileInputStream(localFile),localFile.getName());
			    channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	
	
	
	
	
	
}
