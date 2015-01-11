
/*
======================================================================
#  src file -- Robot.java
#  Desc     -- 
#  author   -- Joe DiCaro
#  date     -- 4-30-08
#  version  -- 1.00 
#  run      -- see usage below
#  required -- 
#              
#
# Procedure Outline.
#
# 1. Used to qc the a pre environement network configuration for the DAN.
#    It can also be used to qc the post SSH and /etc/host configuration of 
#    a RAC cluster. It uses ssh to build the universe of servers for testing
#    and then pings them however many times is desired. It is automated so 
#    that upon completion of a long running test it can be set to email 
#    the results.  
#    
#    Command Line Arguments 
#    -q QCMODE, default N, Y for qc RAC cluster network
#    -c PINGCNT, default 1 
#    -h HOSTS, at least one host must be given 
#    -s PKTSZ, default 0, optional argument, set to deterimine pkt size
#    -e MAILFLAG, default n, Y for email to be sent to MAILADDR
#
#  04/30/2008 - 1.00 - JD  - Initial Script.
#
# ======================================================================

# ======================================================================
# Global Variable Declaration Section
# ======================================================================
*/

package org.usfirst.frc.team3944.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }
    
	private TPAJoystick joystick;
    private TPARobotDrive robotDrive;
    private DriveTrainTester driveTrainTester;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	joystick = new TPAJoystick(RobotMap.joystickOnePort);
        robotDrive = new TPARobotDrive(RobotMap.frontLeftPort, RobotMap.rearLeftPort, RobotMap.frontRightPort, RobotMap.rearRightPort, joystick);
        robotDrive.setInvertedMotor(TPARobotDrive.MotorType.kFrontRight, true);
        robotDrive.setInvertedMotor(TPARobotDrive.MotorType.kRearRight, true);
        
        driveTrainTester = new DriveTrainTester(joystick, robotDrive);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	robotDrive.mecanumDrive_Polar();
    }
    
    
    public void testInit() {
        driveTrainTester.reset();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	driveTrainTester.run();

    }
    
}
