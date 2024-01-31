package com.example.robot_teaching_pendant_app.client;

public class Make_JointData {
	public static void Make_Joint_Command(Make_Joint receivedData) {
		   byte subCommandType = receivedData.getSubCommandType();
		   
		   if(subCommandType == 0x00) { //Smooth
			   Make_Joint_Smooth(receivedData);
		   } else if (subCommandType == 0x01) { //Tick
			   Make_Joint_Tick(receivedData);

		   }
	}
	
	private static void Make_Joint_Smooth(Make_Joint receivedData) {
	       Robo_Position roboPosition = receivedData.getRoboPosition();
	       // 데이터 출력 또는 처리
	        System.out.println("Main Command Type: " + receivedData.getMainCommandType());
	        System.out.println("Sub Command Type: " + receivedData.getSubCommandType());
	        System.out.println("Joint1: " + roboPosition.joint1);
	        System.out.println("Joint2: " + roboPosition.joint2);
	        System.out.println("Joint3: " + roboPosition.joint3);
	        System.out.println("Joint4: " + roboPosition.joint4);
      
	}
	
	private static void Make_Joint_Tick(Make_Joint receivedData) {
	       Robo_Position roboPosition = receivedData.getRoboPosition();

        
        System.out.println("Main Command Type: " + receivedData.getMainCommandType());
        System.out.println("Sub Command Type: " + receivedData.getSubCommandType());
        System.out.println("Joint1: " + roboPosition.joint1);
        System.out.println("Joint2: " + roboPosition.joint2);
        System.out.println("Joint3: " + roboPosition.joint3);
        System.out.println("Joint4: " + roboPosition.joint4);
        System.out.println("Dist: " + roboPosition.dist);
        System.out.println("Ori: " + roboPosition.ori);
        System.out.println("Joint: " + roboPosition.joint);
	}



}
