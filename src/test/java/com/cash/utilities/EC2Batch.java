package com.cash.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.jcraft.jsch.*;


public class EC2Batch {

	//public static void main(String[] arg) throws JSchException, InterruptedException{
	public void ec2Batch(String batch) throws JSchException, InterruptedException{

		JSch jsch=new JSch();

		String user = "vijaykumarmattaparthi98096";
		//String user = "ec2-user";
		String host = "172.29.3.211";//"172.31.3.44";//"172.16.1.191"; 172.31.3.44
		int port = 22;
		String privateKey = System.getProperty("user.dir")+"/src/test/resources/AWS_MUM_PPK.ppk"; //"C:\\Users\\vijaykumar.m\\Documents\\testenv.ppk";
		jsch.addIdentity(privateKey);
		System.out.println("identity added ");

		Session session = jsch.getSession(user, host, port);
		System.out.println("session created.");

		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);

		session.connect();
		Channel channel=session.openChannel("shell");
		Thread shellTrhead = null;

		try{
			//channel.connect();
			// channel.connect(3*1000);

			if(batch.contains("15And30Penalty")){
				channel.setInputStream(new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/15And30Penalty_Batch.txt")));

			}else if(batch.contains("PenaltyCalculation")){

				channel.setInputStream(new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/PenaltyCalculationJobTest.txt")));

			}else if(batch.contains("Reconciliation")){

				//channel.setInputStream(new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/ReconciliationJobBatch.txt")));
				channel.setInputStream(new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/ReconciliationJobBatch2.txt")));
				
			}else if(batch.contains("loanTransferSuccessUpdateKotak")) {
				channel.setInputStream(new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/loanTransferSuccessUpdateKotak.txt")));

			}else if(batch.contains("DefaultCustomer")) {
				channel.setInputStream(new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/DefaultCustomer.txt")));

			}else if(batch.contains("PaymentReminderJobTest")) {
				channel.setInputStream(new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/PaymentReminderJobTest.txt")));

			}else if(batch.contains("BadgeDegraderJobTest")) {
				channel.setInputStream(new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/BadgeDegraderJobTest.txt")));

			}else if(batch.contains("ReVerifyCustomerAfter180DaysJobTest")) {
				channel.setInputStream(new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/ReVerifyCustomerAfter180DaysJobTest.txt")));

			}else{ 
				System.out.println("None of the batch is running!!!");
			}
			// channel.setInputStream(new FileInputStream(new File("C:\\Users\\vijaykumar.m\\Documents\\AWS_Batches_Test_2.txt")));
			//channel.setInputStream(new FileInputStream(new File("C:\\Users\\vijaykumar.m\\Documents\\15And30Penalty_Batch.txt")));

			//channel.setInputStream(new FileInputStream(new File("C:\\Users\\vijaykumar.m\\Documents\\PenaltyCalculationJobTest.txt")));

			channel.setOutputStream(System.out);
			channel.connect(1*1000);
		}
		catch(Exception e){
			System.out.println(e);
		}finally{
			System.out.println("Batch disconnecting....");
			Thread.sleep(1000*60);
			System.out.println("Batch disconnecting....");

			channel.getSession().disconnect();
			
			System.out.println("Batch ran successfully");
		}
	}

	public void reconciliationBatch(){

	}
	public void penaltyCalculationBatch(){

	}
	public void penaltyCalculationBatch15And30(){

	}
}

class ShellJob implements Runnable {

	private Channel channel;
	public ShellJob(Channel channel) {
		this.channel=channel;
	}

	@Override
	public void run() {
		try {
			channel.setInputStream(new FileInputStream(new File("C:\\Users\\vijaykumar.m\\Documents\\AWS_Batches_Test_2.txt")));
			channel.setOutputStream(System.out);
			channel.connect(3*1000);
		} catch (FileNotFoundException | JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}