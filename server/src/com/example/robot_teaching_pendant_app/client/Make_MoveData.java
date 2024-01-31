package com.example.robot_teaching_pendant_app.client;

public class Make_MoveData {
	public static void Make_Move_Command(Make_Move receivedData) {
		
		byte subCommandType = receivedData.getSubCommandType();
		byte type = receivedData.getType();

        
        if (subCommandType == 0x00) { // MoveJ
            Make_MoveJ(receivedData, type);
        } else if (subCommandType == 0x02) { // MoveL
            Make_MoveL(receivedData, type);
        } else {
            System.out.println("SubCommandType 오류");
        }
    }
	

	private static void Make_MoveJ(Make_Move receivedData, byte type) {

	    System.out.println("Main Command Type: " + receivedData.getMainCommandType());
	    System.out.println("Sub Command Type: " + receivedData.getSubCommandType());
	    System.out.println("Type: " + receivedData.getType());
	    System.out.println("Speed: " + receivedData.getSpeed());
	    System.out.println("Acc: " + receivedData.getAcc());
	    System.out.println("Finish At: " + receivedData.getFinishAt());
	    System.out.println("Stopping At: " + receivedData.getStoppingAt());
	    
	    if (type == 0x00) {
		    System.out.println("X: " + receivedData.getX());
		    System.out.println("Y: " + receivedData.getY());
		    System.out.println("Z: " + receivedData.getZ());
		    System.out.println("Rx: " + receivedData.getRx());
		    System.out.println("Ry: " + receivedData.getRy());
		    System.out.println("Rz: " + receivedData.getRz());
		    System.out.println("Joint1: " + receivedData.getJoint1());
		    System.out.println("Joint2: " + receivedData.getJoint2());
		    System.out.println("Joint3: " + receivedData.getJoint3());
		    System.out.println("Joint4: " + receivedData.getJoint4());  
	    }else if(type == 0x01) {
		    System.out.println("Joint1: " + receivedData.getJoint1());
		    System.out.println("Joint2: " + receivedData.getJoint2());
		    System.out.println("Joint3: " + receivedData.getJoint3());
		    System.out.println("Joint4: " + receivedData.getJoint4()); 
	    }else if(type == 0x02) {
		    System.out.println("변하는 Joint1: " + receivedData.getJoint1());
		    System.out.println("변하는 Joint2: " + receivedData.getJoint2());
		    System.out.println("변하는 Joint3: " + receivedData.getJoint3());
		    System.out.println("변하는 Joint4: " + receivedData.getJoint4()); 
	    }
	    
	    
	}
	
	 private static void Make_MoveL(Make_Move receivedData, byte type) {
		    System.out.println("Main Command Type: " + receivedData.getMainCommandType());
		    System.out.println("Sub Command Type: " + receivedData.getSubCommandType());
		    System.out.println("Type: " + receivedData.getType());
		    System.out.println("Speed: " + receivedData.getSpeed());
		    System.out.println("Acc: " + receivedData.getAcc());
		    System.out.println("Finish At: " + receivedData.getFinishAt());
		    System.out.println("Stopping At: " + receivedData.getStoppingAt());
		    
		    if (type == 0x00) {
			    System.out.println("X: " + receivedData.getX());
			    System.out.println("Y: " + receivedData.getY());
			    System.out.println("Z: " + receivedData.getZ());
			    System.out.println("Rx: " + receivedData.getRx());
			    System.out.println("Ry: " + receivedData.getRy());
			    System.out.println("Rz: " + receivedData.getRz());
			    System.out.println("Joint1: " + receivedData.getJoint1());
			    System.out.println("Joint2: " + receivedData.getJoint2());
			    System.out.println("Joint3: " + receivedData.getJoint3());
			    System.out.println("Joint4: " + receivedData.getJoint4());  
		    }else if(type == 0x01) {
			    System.out.println("Joint1: " + receivedData.getJoint1());
			    System.out.println("Joint2: " + receivedData.getJoint2());
			    System.out.println("Joint3: " + receivedData.getJoint3());
			    System.out.println("Joint4: " + receivedData.getJoint4()); 
		    }else if(type == 0x02) {
			    System.out.println("변하는 Joint1: " + receivedData.getJoint1());
			    System.out.println("변하는 Joint2: " + receivedData.getJoint2());
			    System.out.println("변하는 Joint3: " + receivedData.getJoint3());
			    System.out.println("변하는 Joint4: " + receivedData.getJoint4()); 
		    }else if(type == 0x03) {
			    System.out.println("X: " + receivedData.getX());
			    System.out.println("Y: " + receivedData.getY());
			    System.out.println("Z: " + receivedData.getZ());
			    System.out.println("Rx: " + receivedData.getRx());
			    System.out.println("Ry: " + receivedData.getRy());
			    System.out.println("Rz: " + receivedData.getRz());
		    }
		    
		}
	 
}