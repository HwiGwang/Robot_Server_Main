package com.example.robot_teaching_pendant_app.client;

public class Make_JogData {
	
   public static void Make_Jog_Command(Make_Jog receivedData) {

	   byte subCommandType = receivedData.getSubCommandType();
	   
	   if(subCommandType == 0x00) { //Smooth
		   Make_Jog_Smooth(receivedData);
	   } else if (subCommandType == 0x01) { //Tick
		   Make_Jog_Tick(receivedData);

	   }
	   
   }
   
   private static void Make_Jog_Smooth(Make_Jog receivedData) {
       Robo_Position roboPosition = receivedData.getRoboPosition();

       // 데이터 출력 또는 처리
       System.out.println("Main Command Type: " + receivedData.getMainCommandType());
       System.out.println("Sub Command Type: " + receivedData.getSubCommandType());

       if (receivedData.getMainCommandType() == 0x00) {
           System.out.println("Global_x: " + roboPosition.global_x);
           System.out.println("Global_y: " + roboPosition.global_y);
           System.out.println("Global_z: " + roboPosition.global_z);
           System.out.println("Global_Rx: " + roboPosition.global_Rx);
           System.out.println("Global_Ry: " + roboPosition.global_Ry);
           System.out.println("Global_Rz: " + roboPosition.global_Rz);
       } else if (receivedData.getMainCommandType() == 0x01) {
           System.out.println("Local_x: " + roboPosition.local_x);
           System.out.println("Local_y: " + roboPosition.local_y);
           System.out.println("Local_z: " + roboPosition.local_z);
           System.out.println("Local_Rx: " + roboPosition.local_Rx);
           System.out.println("Local_Ry: " + roboPosition.local_Ry);
           System.out.println("Local_Rz: " + roboPosition.local_Rz);
       } else if (receivedData.getMainCommandType() == 0x02) {
           System.out.println("User_x: " + roboPosition.user_x);
           System.out.println("User_y: " + roboPosition.user_y);
           System.out.println("User_z: " + roboPosition.user_z);
           System.out.println("User_Rx: " + roboPosition.user_Rx);
           System.out.println("User_Ry: " + roboPosition.user_Ry);
           System.out.println("User_Rz: " + roboPosition.user_Rz);
       }
   }

   private static void Make_Jog_Tick(Make_Jog receivedData) {
       Robo_Position roboPosition = receivedData.getRoboPosition();

       // 데이터 출력 또는 처리
       System.out.println("Main Command Type: " + receivedData.getMainCommandType());
       System.out.println("Sub Command Type: " + receivedData.getSubCommandType());

       if (receivedData.getMainCommandType() == 0x00) {
           System.out.println("Global_x: " + roboPosition.global_x);
           System.out.println("Global_y: " + roboPosition.global_y);
           System.out.println("Global_z: " + roboPosition.global_z);
           System.out.println("Global_Rx: " + roboPosition.global_Rx);
           System.out.println("Global_Ry: " + roboPosition.global_Ry);
           System.out.println("Global_Rz: " + roboPosition.global_Rz);
       } else if (receivedData.getMainCommandType() == 0x01) {
           System.out.println("Local_x: " + roboPosition.local_x);
           System.out.println("Local_y: " + roboPosition.local_y);
           System.out.println("Local_z: " + roboPosition.local_z);
           System.out.println("Local_Rx: " + roboPosition.local_Rx);
           System.out.println("Local_Ry: " + roboPosition.local_Ry);
           System.out.println("Local_Rz: " + roboPosition.local_Rz);
       } else if (receivedData.getMainCommandType() == 0x02) {
           System.out.println("User_x: " + roboPosition.user_x);
           System.out.println("User_y: " + roboPosition.user_y);
           System.out.println("User_z: " + roboPosition.user_z);
           System.out.println("User_Rx: " + roboPosition.user_Rx);
           System.out.println("User_Ry: " + roboPosition.user_Ry);
           System.out.println("User_Rz: " + roboPosition.user_Rz);
       }

       System.out.println("Dist: " + roboPosition.dist);
       System.out.println("Ori: " + roboPosition.ori);
       System.out.println("Joint: " + roboPosition.joint);
   }
}