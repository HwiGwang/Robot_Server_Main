package com.example.robot_teaching_pendant_app.client;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class Make implements Serializable {
    private static final long serialVersionUID = 1L;
    private byte mainCommandType;

    public Make(byte mainCommandType) {
        this.mainCommandType = mainCommandType;
    }

    public byte getMainCommandType() {
        return mainCommandType;
    }

    // Abstract method for getting output based on MainCommandType
    public abstract byte getSubCommandType();

    public abstract byte[] getBytes();
}

class Make_Jog extends Make {
    private byte subCommandType;
    private Robo_Position robo_Position;


    public Make_Jog(byte[] readBytes) {
        super(readBytes[0]);

        ByteBuffer buffer = ByteBuffer.wrap(readBytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        buffer.get();

        this.subCommandType = buffer.get();
        this.robo_Position = new Robo_Position();

        if (getMainCommandType() == 0x00) {
            // 기존 global 변수 사용
            this.robo_Position.global_x = buffer.getFloat();
            this.robo_Position.global_y = buffer.getFloat();
            this.robo_Position.global_z = buffer.getFloat();
            this.robo_Position.global_Rx = buffer.getFloat();
            this.robo_Position.global_Ry = buffer.getFloat();
            this.robo_Position.global_Rz = buffer.getFloat();
        }else if(getMainCommandType() == 0x01) {
            this.robo_Position.local_x = buffer.getFloat();
            this.robo_Position.local_y = buffer.getFloat();
            this.robo_Position.local_z = buffer.getFloat();
            this.robo_Position.local_Rx = buffer.getFloat();
            this.robo_Position.local_Ry = buffer.getFloat();
            this.robo_Position.local_Rz = buffer.getFloat();
        }else if(getMainCommandType() == 0X02) {
            // user 변수로 변경
            this.robo_Position.user_x = buffer.getFloat();
            this.robo_Position.user_y = buffer.getFloat();
            this.robo_Position.user_z = buffer.getFloat();
            this.robo_Position.user_Rx = buffer.getFloat();
            this.robo_Position.user_Ry = buffer.getFloat();
            this.robo_Position.user_Rz = buffer.getFloat();
        }

        this.robo_Position.dist = buffer.getFloat();
        this.robo_Position.ori = buffer.getFloat();
        this.robo_Position.joint = buffer.getFloat();

    }
    @Override
    public byte getSubCommandType() {
        return subCommandType;
    }

    public Robo_Position getRoboPosition() {
        return robo_Position;
    }



    @Override
    public byte[] getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(39);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(getMainCommandType());
        buffer.put(getSubCommandType());

        if (getMainCommandType() == 0x00) {
            // 기존 global 변수 사용
            buffer.putFloat(robo_Position.global_x);
            buffer.putFloat(robo_Position.global_y);
            buffer.putFloat(robo_Position.global_z);
            buffer.putFloat(robo_Position.global_Rx);
            buffer.putFloat(robo_Position.global_Ry);
            buffer.putFloat(robo_Position.global_Rz);
        } else if (getMainCommandType() == 0x02) {
            // user 변수로 변경
            buffer.putFloat(robo_Position.user_x);
            buffer.putFloat(robo_Position.user_y);
            buffer.putFloat(robo_Position.user_z);
            buffer.putFloat(robo_Position.user_Rx);
            buffer.putFloat(robo_Position.user_Ry);
            buffer.putFloat(robo_Position.user_Rz);
        } else if (getMainCommandType() == 0x01) {
            buffer.putFloat(robo_Position.local_x);
            buffer.putFloat(robo_Position.local_y);
            buffer.putFloat(robo_Position.local_z);
            buffer.putFloat(robo_Position.local_Rx);
            buffer.putFloat(robo_Position.local_Ry);
            buffer.putFloat(robo_Position.local_Rz);
        }

        buffer.putFloat(robo_Position.dist);
        buffer.putFloat(robo_Position.ori);
        buffer.putFloat(robo_Position.joint);
        buffer.put((byte) 0x00); // 예약된 바이트

        return buffer.array();
    }

}

class Make_Joint extends Make {
    private byte subCommandType;
    private Robo_Position robo_Position;


    public Make_Joint(byte[] readBytes) {
        super(readBytes[0]);

        ByteBuffer buffer = ByteBuffer.wrap(readBytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        buffer.get();

        this.subCommandType = buffer.get();
        this.robo_Position = new Robo_Position();
        this.robo_Position.joint1 = buffer.getFloat();
        this.robo_Position.joint2 = buffer.getFloat();
        this.robo_Position.joint3 = buffer.getFloat();
        this.robo_Position.joint4 = buffer.getFloat();

        // 추가된 부분: dist, ori, joint 데이터를 읽음
        this.robo_Position.dist = buffer.getFloat();
        this.robo_Position.ori = buffer.getFloat();
        this.robo_Position.joint = buffer.getFloat();
    }

    @Override
    public byte getSubCommandType() {
        return subCommandType;
    }

    public Robo_Position getRoboPosition() {
        return robo_Position;
    }


    @Override
    public byte[] getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(31);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(getMainCommandType());
        buffer.put(getSubCommandType());
        buffer.putFloat(robo_Position.joint1);
        buffer.putFloat(robo_Position.joint2);
        buffer.putFloat(robo_Position.joint3);
        buffer.putFloat(robo_Position.joint4);
        buffer.putFloat(robo_Position.dist);
        buffer.putFloat(robo_Position.ori);
        buffer.putFloat(robo_Position.joint);
        buffer.put((byte) 0x00); // 예약된 바이트

        return buffer.array();
    }

}

class Make_Move extends Make {
    private byte subCommandType;
    private byte type;
    private float speed;
    private float acc;
    private float finish_at;
    private float stoppingat;
    private float x;
    private float y;
    private float z;
    private float rx;
    private float ry;
    private float rz;
    private float joint1;
    private float joint2;
    private float joint3;
    private float joint4;


    public Make_Move(byte[] readBytes) {
        super(readBytes[0]);

        ByteBuffer buffer = ByteBuffer.wrap(readBytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        buffer.get();

        this.subCommandType = buffer.get();
        this.type = buffer.get();
        this.speed = buffer.getFloat();
        this.acc = buffer.getFloat();
        this.finish_at = buffer.getFloat();
        this.stoppingat = buffer.getFloat();

        if (getMainCommandType() == 0x04) {
            if(subCommandType == 0x00) {
                if(type == 0x00)
                {
                    this.x = buffer.getFloat();
                    this.y = buffer.getFloat();
                    this.z = buffer.getFloat();
                    this.rx = buffer.getFloat();
                    this.ry = buffer.getFloat();
                    this.rz = buffer.getFloat();
                    this.joint1 = buffer.getFloat();
                    this.joint2 = buffer.getFloat();
                    this.joint3 = buffer.getFloat();
                    this.joint4 = buffer.getFloat();
                }else if (type == 0x01) {
                    this.joint1 = buffer.getFloat();
                    this.joint2 = buffer.getFloat();
                    this.joint3 = buffer.getFloat();
                    this.joint4 = buffer.getFloat();
                }else if (type == 0x02) {
                    this.joint1 = buffer.getFloat();
                    this.joint2 = buffer.getFloat();
                    this.joint3 = buffer.getFloat();
                    this.joint4 = buffer.getFloat();
                }
            }else if(subCommandType == 0x02) {
                if(type == 0x00) {
                    this.x = buffer.getFloat();
                    this.y = buffer.getFloat();
                    this.z = buffer.getFloat();
                    this.rx = buffer.getFloat();
                    this.ry = buffer.getFloat();
                    this.rz = buffer.getFloat();
                    this.joint1 = buffer.getFloat();
                    this.joint2 = buffer.getFloat();
                    this.joint3 = buffer.getFloat();
                    this.joint4 = buffer.getFloat();
                }else if (type == 0x01) {
                    this.joint1 = buffer.getFloat();
                    this.joint2 = buffer.getFloat();
                    this.joint3 = buffer.getFloat();
                    this.joint4 = buffer.getFloat();
                }else if (type == 0x02) {
                    this.joint1 = buffer.getFloat();
                    this.joint2 = buffer.getFloat();
                    this.joint3 = buffer.getFloat();
                    this.joint4 = buffer.getFloat();
                }else if (type == 0x03) {
                    this.joint1 = buffer.getFloat();
                    this.joint2 = buffer.getFloat();
                    this.joint3 = buffer.getFloat();
                    this.joint4 = buffer.getFloat();
                }

            }
        }

    }

    @Override
    public byte getSubCommandType() {
        return subCommandType;
    }

    public byte getType() {
        return type;
    }

    public float getSpeed() {
        return speed;
    }

    public float getAcc() {
        return acc;
    }

    public float getFinishAt() {
        return finish_at;
    }

    public float getStoppingAt() {
        return stoppingat;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getRx() {
        return rx;
    }

    public float getRy() {
        return ry;
    }

    public float getRz() {
        return rz;
    }

    public float getJoint1() {
        return joint1;
    }

    public float getJoint2() {
        return joint2;
    }

    public float getJoint3() {
        return joint3;
    }

    public float getJoint4() {
        return joint4;
    }

    @Override
    public byte[] getBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(60);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(getMainCommandType());
        buffer.put(getSubCommandType());
        buffer.put(type);
        buffer.putFloat(speed);
        buffer.putFloat(acc);
        buffer.putFloat(finish_at);
        buffer.putFloat(stoppingat);

        if (getMainCommandType() == 0x04) {
            if(getSubCommandType() == 0x00) {
                if(type == 0x00) {
                    buffer.putFloat(x);
                    buffer.putFloat(y);
                    buffer.putFloat(z);
                    buffer.putFloat(rx);
                    buffer.putFloat(ry);
                    buffer.putFloat(rz);
                    buffer.putFloat(joint1);
                    buffer.putFloat(joint2);
                    buffer.putFloat(joint3);
                    buffer.putFloat(joint4);
                }
                else if(type == 0x01) {
                    buffer.putFloat(joint1);
                    buffer.putFloat(joint2);
                    buffer.putFloat(joint3);
                    buffer.putFloat(joint4);
                }
                else if(type == 0x02) {
                    buffer.putFloat(joint1);
                    buffer.putFloat(joint2);
                    buffer.putFloat(joint3);
                    buffer.putFloat(joint4);
                }
            }else if(getSubCommandType() == 0x02) {
                if(type == 0x00) {
                    buffer.putFloat(x);
                    buffer.putFloat(y);
                    buffer.putFloat(z);
                    buffer.putFloat(rx);
                    buffer.putFloat(ry);
                    buffer.putFloat(rz);
                    buffer.putFloat(joint1);
                    buffer.putFloat(joint2);
                    buffer.putFloat(joint3);
                    buffer.putFloat(joint4);
                }
                else if(type == 0x01) {
                    buffer.putFloat(joint1);
                    buffer.putFloat(joint2);
                    buffer.putFloat(joint3);
                    buffer.putFloat(joint4);
                }
                else if(type == 0x02) {
                    buffer.putFloat(joint1);
                    buffer.putFloat(joint2);
                    buffer.putFloat(joint3);
                    buffer.putFloat(joint4);
                }else if(type == 0x03) {
                    buffer.putFloat(x);
                    buffer.putFloat(y);
                    buffer.putFloat(z);
                    buffer.putFloat(rx);
                    buffer.putFloat(ry);
                    buffer.putFloat(rz);
                }
            }
        }

        buffer.put((byte) 0x00); // 예약된 바이트

        return buffer.array();
    }

}