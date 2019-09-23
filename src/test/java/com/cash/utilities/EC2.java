package com.cash.utilities;

import java.io.IOException;

public class EC2 {

	public static void main(String[] args) throws IOException, InterruptedException {
		String myKey="C:\\Users\\vijaykumar.m\\Documents\\testenv.ppk";
		Runtime runtime = Runtime.getRuntime();
		String commande = "ssh -i "+myKey+" ubuntu@ec2-172.16.1.191.eu-west-1.compute.amazonaws.com 'bash /home/ec2-user/CasheBatch/LoanReconciliationJobScript.sh' -o StrictHostKeyChecking=no ";

		Process p = runtime.exec(commande);
		p.waitFor();

	}
}
