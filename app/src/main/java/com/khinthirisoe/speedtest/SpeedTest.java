package com.khinthirisoe.speedtest;

/**
 * Created by khinthirisoe on 5/9/17.
 */

public class SpeedTest {

    private float downloadSpeed;
    private float uploadSpeed;
    private float pingSpeed;

    public SpeedTest() {
    }

    public float getDownloadSpeed() {
        return downloadSpeed;
    }

    public float getUploadSpeed() {
        return uploadSpeed;
    }

    public float getPingSpeed() {
        return pingSpeed;
    }

    public void setDownloadSpeed(float downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    public void setUploadSpeed(float uploadSpeed) {
        this.uploadSpeed = uploadSpeed;
    }

    public void setPingSpeed(float pingSpeed) {
        this.pingSpeed = pingSpeed;
    }

    public SpeedTest(float downloadSpeed, float uploadSpeed, float pingSpeed) {
        this.downloadSpeed = downloadSpeed;
        this.uploadSpeed = uploadSpeed;
        this.pingSpeed = pingSpeed;
    }
}
