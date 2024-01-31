package com.example.robot_teaching_pendant_app.client;

public class Robo_Position {

    /**
     * 로봇의 현재 위치 정보를 저장하는 클래스입니다.
     * 설계 과정에 따라 변수가 추가될 수 있습니다.
     */

    // Global 좌표계의 좌표 값을 저장하는 변수 6개입니다.
    public static float global_x = 0.0f;
    public static float global_y = 0.0f;
    public static float global_z = 0.0f;
    public static float global_Rx = 0.0f;
    public static float global_Ry = 0.0f;
    public static float global_Rz = 0.0f;

    // Local 좌표계의 좌표 값을 저장하는 변수 6개입니다.
    public static float local_x = 0.0f;
    public static float local_y = 0.0f;
    public static float local_z = 0.0f;
    public static float local_Rx = 0.0f;
    public static float local_Ry = 0.0f;
    public static float local_Rz = 0.0f;

    // User 좌표계의 좌표 값을 저장하는 변수 6개입니다.
    public static float user_x = 0.0f;
    public static float user_y = 0.0f;
    public static float user_z = 0.0f;
    public static float user_Rx = 0.0f;
    public static float user_Ry = 0.0f;
    public static float user_Rz = 0.0f;

    // 관절 값을 저장하는 변수 4개입니다.
    public static float joint1 = 0.0f;
    public static float joint2 = 0.0f;
    public static float joint3 = 0.0f;
    public static float joint4 = 0.0f;
    
    public static float dist = 0.0f;
    public static float ori = 0.0f;
    public static float joint = 0.0f;
    
}