package com.example.robot_teaching_pendant_app.client;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Timer;
import java.util.TimerTask;


import com.example.robot_teaching_pendant_app.client.Robo_Position;
import com.example.robot_teaching_pendant_app.client.Make_JogData;
import com.example.robot_teaching_pendant_app.client.Make_Joint;
import com.example.robot_teaching_pendant_app.client.Make_JointData;

public class server {

    private static float jointt1 = 0.0f;
    private static float jointt2 = 0.0f;

    public static void main(String[] args) {
        ServerSocket serverSocket_1st = null;

        try {
            serverSocket_1st = new ServerSocket(8000);
            System.out.println("서버 대기 중...");

            final ServerSocket ServerSocket_2nd = new ServerSocket(8030);
            Robo_Position robo_Position = new Robo_Position(); // 초기화
            Socket clientSocket_2nd = ServerSocket_2nd.accept(); // 클라이언트와의 연결을 한 번만 수립합니다.

            Timer timer = new Timer();
            TimerTask sendDataTask = new TimerTask() {
                public void run() {
                    sendData(clientSocket_2nd, robo_Position, jointt1, jointt2); // 같은 소켓을 사용하여 데이터 전송
                }
            };

            timer.scheduleAtFixedRate(sendDataTask, 0, 5000);
        
            while (true) {
                Socket clientSocket = serverSocket_1st.accept();
                System.out.println("클라이언트 연결됨");

                // 클라이언트로부터 데이터를 읽기 위한 입력 스트림 생성
                InputStream in = clientSocket.getInputStream();

                byte[] receivedDataBytes = new byte[300];
                int readBytes;

                while ((readBytes = in.read(receivedDataBytes)) > 0) {
                    // 수신된 데이터 처리
                    ByteBuffer buffer = ByteBuffer.wrap(receivedDataBytes);
                    buffer.order(ByteOrder.LITTLE_ENDIAN);

                    byte mainCommandType = buffer.get();
                    byte subCommandType = buffer.get();
                    // ... 추가 데이터 추출 ...

                    if (mainCommandType >= 0x00 && mainCommandType <= 0x02) {
                        Make_Jog receivedData = new Make_Jog(receivedDataBytes);
                        Make_JogData.Make_Jog_Command(receivedData);
                    } else if (mainCommandType == 0x03) {
                        Make_Joint receivedData = new Make_Joint(receivedDataBytes);
                        Make_JointData.Make_Joint_Command(receivedData);
                    } else if (mainCommandType == 0x04) {
                        Make_Move receivedData = new Make_Move(receivedDataBytes);
                        Make_MoveData.Make_Move_Command(receivedData);}
                    else {
                        // Handle unknown command type
                    }
                }

                closeSocket(clientSocket);
                System.out.println("클라이언트 연결 종료");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeSocket(serverSocket_1st);
        }
    }

    private static byte currentMainCommandType = (byte) 0x9C;

    private static void sendData(Socket clientSocket, Robo_Position robo_Position, float jointt1, float jointt2) {
        try {

            byte mainCommandType;
            byte subCommandType;
            byte data;
            float joint3;
            float joint4;
            float global_x;
            float global_y;
            float global_z;
            float global_Rx;
            float global_Ry;
            float global_Rz;
            float local_x;
            float local_y;
            float local_z;
            float local_Rx;
            float local_Ry;
            float local_Rz;
            float user_x;
            float user_y;
            float user_z;
            float user_Rx;
            float user_Ry;
            float user_Rz;

            float DigitalOut;


            if (currentMainCommandType == (byte) 0x9C) {
                // mainCommandType 0x9C에 대한 데이터 설정
                mainCommandType = (byte) 0x9C;
                subCommandType = (byte) 0x00;
                data = (byte) 0x01;

                // mainCommandType 0x9C 또는 0x9D에 대한 데이터 설정
                ByteBuffer buffer = ByteBuffer.allocate(4);
                buffer.order(ByteOrder.LITTLE_ENDIAN);
                buffer.put(mainCommandType);
                buffer.put(subCommandType);
                buffer.put(data);
                buffer.put((byte) 0x00); // 예약된 바이트

                // 클라이언트에게 바이트 배열 전송
                OutputStream out = clientSocket.getOutputStream();

                // 소켓이 닫혀 있는지 확인 후 전송
                if (!clientSocket.isClosed()) {
                    out.write(buffer.array());
                    out.flush();
                    System.out.println("클라이언트로 값 전송: " +
                            "mainCommandType=" + mainCommandType +
                            ", subCommandType=" + subCommandType +
                            ", data=" + data);
                }
                currentMainCommandType = (byte) 0x9D; // 다음 전송을 위해 커맨드 타입 변경


            } else if(currentMainCommandType == (byte) 0x9D) {
                // mainCommandType 0x9D에 대한 데이터 설정
                mainCommandType = (byte) 0x9D;
                DigitalOut = 0.0f;

                jointt1 = jointt1;
                jointt2 = jointt2;
                joint3 = robo_Position.joint3;
                joint4 = robo_Position.joint4;

                global_x = robo_Position.global_x;
                global_y = robo_Position.global_y;
                global_z = robo_Position.global_z;
                global_Rx = robo_Position.global_Rx;
                global_Ry = robo_Position.global_Ry;
                global_Rz = robo_Position.global_Rz;

                // Update local values
                local_x = robo_Position.local_x;
                local_y = robo_Position.local_y;
                local_z = robo_Position.local_z;
                local_Rx = robo_Position.local_Rx;
                local_Ry = robo_Position.local_Ry;
                local_Rz = robo_Position.local_Rz;

                // Update user values
                user_x = robo_Position.user_x;
                user_y = robo_Position.user_y;
                user_z = robo_Position.user_z;
                user_Rx = robo_Position.user_Rx;
                user_Ry = robo_Position.user_Ry;
                user_Rz = robo_Position.user_Rz;


                // 반복문을 사용하여 subCommandType를 0x00부터 0x03까지 순차적으로 설정
                for (subCommandType = 0x00; subCommandType <= 0x03; subCommandType++) {
                    if(subCommandType == 0x00) {
                        // ByteBuffer를 사용하여 바이트 배열로 변환
                        ByteBuffer buffer = ByteBuffer.allocate(23);
                        buffer.order(ByteOrder.LITTLE_ENDIAN);

                        // robo_Position의 값을 sendBuffer에 넣어주기
                        buffer.put(mainCommandType);
                        buffer.put(subCommandType);
                        buffer.putFloat(jointt1);
                        buffer.putFloat(jointt2);
                        buffer.putFloat(joint3);
                        buffer.putFloat(joint4);
                        buffer.putFloat(DigitalOut); // DigitalOut 값이 필요한 경우에 넣어주세요.
                        buffer.put((byte) 0x00); // 예약된 바이트


                        OutputStream out = clientSocket.getOutputStream();

                        // 소켓이 닫혀 있는지 확인 후 전송
                        if (!clientSocket.isClosed()) {
                            out.write(buffer.array());
                            out.flush();
                            System.out.println("클라이언트로 값 전송: " +
                                    "mainCommandType=" + mainCommandType +
                                    ", subCommandType=" + subCommandType +
                                    ", joint1=" + jointt1 +
                                    ", joint2=" + jointt2 +
                                    ", joint3=" + joint3 +
                                    ", joint4=" + joint4);
                        }

                    }else if(subCommandType == 0x01) {
                        // ByteBuffer를 사용하여 바이트 배열로 변환
                        ByteBuffer buffer = ByteBuffer.allocate(31);
                        buffer.order(ByteOrder.LITTLE_ENDIAN);

                        // robo_Position의 값을 sendBuffer에 넣어주기
                        buffer.put(mainCommandType);
                        buffer.put(subCommandType);
                        buffer.putFloat(global_x);
                        buffer.putFloat(global_y);
                        buffer.putFloat(global_z);
                        buffer.putFloat(global_Rx);
                        buffer.putFloat(global_Ry);
                        buffer.putFloat(global_Rz);;
                        buffer.putFloat(DigitalOut); // DigitalOut 값이 필요한 경우에 넣어주세요.
                        buffer.put((byte) 0x00); // 예약된 바이트

                        OutputStream out = clientSocket.getOutputStream();

                        // 소켓이 닫혀 있는지 확인 후 전송
                        if (!clientSocket.isClosed()) {
                            out.write(buffer.array());
                            out.flush();
                            System.out.println("클라이언트로 값 전송: " +
                                    "mainCommandType=" + mainCommandType +
                                    ", subCommandType=" + subCommandType +
                                    ", global_x=" + global_x +
                                    ", global_y=" + global_y +
                                    ", global_z=" + global_z +
                                    ", global_Rx=" + global_Rx +
                                    ", global_Ry=" + global_Ry +
                                    ", global_Rz=" + global_Rz);
                        }

                    }else if(subCommandType == 0x02) {
                        ByteBuffer buffer = ByteBuffer.allocate(31);
                        buffer.order(ByteOrder.LITTLE_ENDIAN);

                        // robo_Position의 값을 sendBuffer에 넣어주기
                        buffer.put(mainCommandType);
                        buffer.put(subCommandType);
                        buffer.putFloat(local_x);
                        buffer.putFloat(local_y);
                        buffer.putFloat(local_z);
                        buffer.putFloat(local_Rx);
                        buffer.putFloat(local_Ry);
                        buffer.putFloat(local_Rz);
                        buffer.putFloat(DigitalOut); // DigitalOut 값이 필요한 경우에 넣어주세요.
                        buffer.put((byte) 0x00); // 예약된 바이트

                        OutputStream out = clientSocket.getOutputStream();

                        // 소켓이 닫혀 있는지 확인 후 전송
                        if (!clientSocket.isClosed()) {
                            out.write(buffer.array());
                            out.flush();
                            System.out.println("클라이언트로 값 전송: " +
                                    "mainCommandType=" + mainCommandType +
                                    ", subCommandType=" + subCommandType +
                                    ", local_x=" + local_x +
                                    ", local_y=" + local_y +
                                    ", local_z=" + local_z +
                                    ", local_Rx=" + local_Rx +
                                    ", local_Ry=" + local_Ry +
                                    ", local_Rz=" + local_Rz);
                        }

                    }else if(subCommandType == 0X03) {
                        // ByteBuffer를 사용하여 바이트 배열로 변환
                        ByteBuffer buffer = ByteBuffer.allocate(31);
                        buffer.order(ByteOrder.LITTLE_ENDIAN);

                        // robo_Position의 값을 sendBuffer에 넣어주기
                        buffer.put(mainCommandType);
                        buffer.put(subCommandType);
                        buffer.putFloat(user_x);
                        buffer.putFloat(user_y);
                        buffer.putFloat(user_z);
                        buffer.putFloat(user_Rx);
                        buffer.putFloat(user_Ry);
                        buffer.putFloat(user_Rz);
                        buffer.putFloat(DigitalOut); // DigitalOut 값이 필요한 경우에 넣어주세요.
                        buffer.put((byte) 0x00); // 예약된 바이트

                        OutputStream out = clientSocket.getOutputStream();

                        // 소켓이 닫혀 있는지 확인 후 전송
                        if (!clientSocket.isClosed()) {
                            out.write(buffer.array());
                            out.flush();
                            System.out.println("클라이언트로 값 전송: " +
                                    "mainCommandType=" + mainCommandType +
                                    ", subCommandType=" + subCommandType +
                                    ", user_x=" + user_x +
                                    ", user_y=" + user_y +
                                    ", user_z=" + user_z +
                                    ", user_Rx=" + user_Rx +
                                    ", user_Ry=" + user_Ry +
                                    ", user_Rz=" + user_Rz);
                        }
                    }


                }
                currentMainCommandType = (byte) 0x9C; // 다음 전송을 위해 커맨드 타입 변경

            }
            // 데이터 전송 후 소켓이 닫혀 있는지 확인
            if (!clientSocket.isClosed()) {
                OutputStream out = clientSocket.getOutputStream();
                // 여기에 ByteBuffer를 사용하여 데이터 포맷팅 및 전송 코드 추가
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeSocket(Socket socket) {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeSocket(ServerSocket serverSocket) {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}