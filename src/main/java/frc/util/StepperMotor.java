package frc.util;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StepperMotor {
    private int currentStep = 0;

    private DigitalOutput stepPin;
    private DigitalOutput dirPin;

    public StepperMotor(int motorPinPL, int motorPinDIR) {
        this.stepPin = new DigitalOutput(motorPinPL);
        this.dirPin = new DigitalOutput(motorPinDIR);
    }

    public void step(boolean clockwise) {
        dirPin.set(!clockwise);

        stepPin.set(true); // Send a high signal to step pin
        try {
            Thread.sleep(0, 50000); // Short delay for pulse duration
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (clockwise) {
            currentStep++;
        } else {
            currentStep--;
        }

        stepPin.set(false); // Send a low signal to step pin
        try {
            Thread.sleep(0, 50000); // Short delay for pulse duration
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * @param steps Number of steps to take (2ms each) 
     */
    public void stepMulti(int steps) {
        int absSteps = Math.abs(steps);
        
        for (int i = 0; i < absSteps; i++) {
            step(steps >= 0);
        }

        SmartDashboard.putNumber("Steps [SM]", absSteps);
    }

    public int getSteps() {
        return currentStep;
    }




}
