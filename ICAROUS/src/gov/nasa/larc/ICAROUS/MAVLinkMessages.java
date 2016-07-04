/**
 * ICAROUS Interface
 * Contact: Swee Balachandran (swee.balachandran@nianet.org)
 * 
 * 
 * Copyright (c) 2011-2016 United States Government as represented by
 * the National Aeronautics and Space Administration.  No copyright
 * is claimed in the United States under Title 17, U.S.Code. All Other
 * Rights Reserved.
 */
package gov.nasa.larc.ICAROUS;;

import java.io.*;
import com.MAVLink.common.*;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.icarous.*;

public class MAVLinkMessages{

    public msg_heartbeat msgHeartbeat = null;
    public msg_sys_status msgSysStatus;
    public msg_system_time msgSystemTime;
    public msg_ping msgPing;
    public msg_change_operator_control msgChangeOperatorControl;
    public msg_change_operator_control_ack msgChangeOperatorControlAck;
    public msg_auth_key msgAuthKey;
    public msg_set_mode msgSetMode;
    public msg_param_request_read msgParamRequestRead;
    public msg_param_request_list msgParamRequestList;
    public msg_param_value msgParamValue;
    public msg_param_set msgParamSet;
    public msg_gps_raw_int msgGpsRawInt;
    public msg_gps_status msgGpsStatus;
    public msg_scaled_imu msgScaledImu;
    public msg_raw_imu msgRawImu;
    public msg_raw_pressure msgRawPressure;
    public msg_scaled_pressure msgScaledPressure;
    public msg_attitude msgAttitude;
    public msg_attitude_quaternion msgAttitudeQuaternion;
    public msg_local_position_ned msgLocalPositionNed;
    public msg_global_position_int msgGlobalPositionInt;
    public msg_rc_channels_scaled msgRcChannelsScaled;
    public msg_rc_channels_raw msgRcChannelsRaw;
    public msg_servo_output_raw msgServoOutputRaw;
    public msg_mission_request_partial_list msgMissionRequestPartialList;
    public msg_mission_write_partial_list msgMissionWritePartialList;
    public msg_mission_item msgMissionItem;
    public msg_mission_request msgMissionRequest;
    public msg_mission_set_current msgMissionSetCurrent;
    public msg_mission_current msgMissionCurrent;
    public msg_mission_request_list msgMissionRequestList;
    public msg_mission_count msgMissionCount;
    public msg_mission_clear_all msgMissionClearAll;
    public msg_mission_item_reached msgMissionItemReached;
    public msg_mission_ack msgMissionAck;
    public msg_set_gps_global_origin msgSetGpsGlobalOrigin;
    public msg_gps_global_origin msgGpsGlobalOrigin;
    public msg_param_map_rc msgParamMapRc;
    public msg_mission_request_int msgMissionRequestInt;
    public msg_safety_set_allowed_area msgSafetySetAllowedArea;
    public msg_safety_allowed_area msgSafetyAllowedArea;
    public msg_attitude_quaternion_cov msgAttitudeQuaternionCov;
    public msg_nav_controller_output msgNavControllerOutput;
    public msg_global_position_int_cov msgGlobalPositionIntCov;
    public msg_local_position_ned_cov msgLocalPositionNedCov;
    public msg_rc_channels msgRcChannels;
    public msg_request_data_stream msgRequestDataStream;
    public msg_data_stream msgDataStream;
    public msg_manual_control msgManualControl;
    public msg_rc_channels_override msgRcChannelsOverride;
    public msg_mission_item_int msgMissionItemInt;
    public msg_vfr_hud msgVfrHud;
    public msg_command_int msgCommandInt;
    public msg_command_long msgCommandLong;
    public msg_command_ack msgCommandAck;
    public msg_manual_setpoint msgManualSetpoint;
    public msg_set_attitude_target msgSetAttitudeTarget;
    public msg_attitude_target msgAttitudeTarget;
    public msg_set_position_target_local_ned msgSetPositionTargetLocalNed;
    public msg_position_target_local_ned msgPositionTargetLocalNed;
    public msg_set_position_target_global_int msgSetPositionTargetGlobalInt;
    public msg_position_target_global_int msgPositionTargetGlobalInt;
    public msg_local_position_ned_system_global_offset msgLocalPositionNedSystemGlobalOffset;
    public msg_hil_state msgHilState;
    public msg_hil_controls msgHilControls;
    public msg_hil_rc_inputs_raw msgHilRcInputsRaw;
    public msg_optical_flow msgOpticalFlow;
    public msg_global_vision_position_estimate msgGlobalVisionPositionEstimate;
    public msg_vision_position_estimate msgVisionPositionEstimate;
    public msg_vision_speed_estimate msgVisionSpeedEstimate;
    public msg_vicon_position_estimate msgViconPositionEstimate;
    public msg_highres_imu msgHighresImu;
    public msg_optical_flow_rad msgOpticalFlowRad;
    public msg_hil_sensor msgHilSensor;
    public msg_sim_state msgSimState;
    public msg_radio_status msgRadioStatus;
    public msg_file_transfer_protocol msgFileTransferProtocol;
    public msg_timesync msgTimesync;
    public msg_camera_trigger msgCameraTrigger;
    public msg_hil_gps msgHilGps;
    public msg_hil_optical_flow msgHilOpticalFlow;
    public msg_hil_state_quaternion msgHilStateQuaternion;
    public msg_scaled_imu2 msgScaledImu2;
    public msg_log_request_list msgLogRequestList;
    public msg_log_entry msgLogEntry;
    public msg_log_request_data msgLogRequestData;
    public msg_log_data msgLogData;
    public msg_log_erase msgLogErase;
    public msg_log_request_end msgLogRequestEnd;
    public msg_gps_inject_data msgGpsInjectData;
    public msg_gps2_raw msgGps2Raw;
    public msg_power_status msgPowerStatus;
    public msg_serial_control msgSerialControl;
    public msg_gps_rtk msgGpsRtk;
    public msg_gps2_rtk msgGps2Rtk;
    public msg_scaled_imu3 msgScaledImu3;
    public msg_data_transmission_handshake msgDataTransmissionHandshake;
    public msg_encapsulated_data msgEncapsulatedData;
    public msg_distance_sensor msgDistanceSensor;
    public msg_terrain_request msgTerrainRequest;
    public msg_terrain_data msgTerrainData;
    public msg_terrain_check msgTerrainCheck;
    public msg_terrain_report msgTerrainReport;
    public msg_scaled_pressure2 msgScaledPressure2;
    public msg_att_pos_mocap msgAttPosMocap;
    public msg_set_actuator_control_target msgSetActuatorControlTarget;
    public msg_actuator_control_target msgActuatorControlTarget;
    public msg_altitude msgAltitude;
    public msg_resource_request msgResourceRequest;
    public msg_scaled_pressure3 msgScaledPressure3;
    public msg_follow_target msgFollowTarget;
    public msg_control_system_state msgControlSystemState;
    public msg_battery_status msgBatteryStatus;
    public msg_autopilot_version msgAutopilotVersion;
    public msg_landing_target msgLandingTarget;
    public msg_estimator_status msgEstimatorStatus;
    public msg_wind_cov msgWindCov;
    public msg_gps_rtcm_data msgGpsRtcmData;
    public msg_vibration msgVibration;
    public msg_home_position msgHomePosition;
    public msg_set_home_position msgSetHomePosition;
    public msg_message_interval msgMessageInterval;
    public msg_extended_sys_state msgExtendedSysState;
    public msg_adsb_vehicle msgAdsbVehicle;
    public msg_v2_extension msgV2Extension;
    public msg_memory_vect msgMemoryVect;
    public msg_debug_vect msgDebugVect;
    public msg_named_value_float msgNamedValueFloat;
    public msg_named_value_int msgNamedValueInt;
    public msg_statustext msgStatustext;
    public msg_debug msgDebug;

    public msg_flightplan_info msgFlightplanInfo;
    public msg_geofence_info msgGeofenceInfo;
    public msg_pointofinterest msgPointofinterest;
    public msg_command_acknowledgement msgCommandAcknowledgement;
    public msg_combox_pulse msgComboxPulse = null;
    public msg_mission_start_stop msgMissionStartStop;
    
    public int FlightPlanUpdateInterrupt;
    public int GeoFenceUpdateInterrupt;
    public int TrafficUpdateInterrupt;
    public int ObstacleUpdateInterrupt;
    
    public void decode_message(MAVLinkPacket message){

	switch(message.msgid){
	
	case msg_heartbeat.MAVLINK_MSG_ID_HEARTBEAT:
	    msgHeartbeat = (msg_heartbeat) message.unpack();
	    break;
	    
	case msg_sys_status.MAVLINK_MSG_ID_SYS_STATUS:
	    msgSysStatus = (msg_sys_status) message.unpack();
	    break;
	    
	case msg_system_time.MAVLINK_MSG_ID_SYSTEM_TIME:
	    msgSystemTime = (msg_system_time) message.unpack();
	    break;
	    
	case msg_ping.MAVLINK_MSG_ID_PING:
	    msgPing = (msg_ping) message.unpack();
            break;
	    
	case msg_change_operator_control.MAVLINK_MSG_ID_CHANGE_OPERATOR_CONTROL:
	    msgChangeOperatorControl = (msg_change_operator_control) message.unpack();
            break;
	    
	case msg_change_operator_control_ack.MAVLINK_MSG_ID_CHANGE_OPERATOR_CONTROL_ACK:
	    msgChangeOperatorControlAck = (msg_change_operator_control_ack) message.unpack();
	    break;

	case msg_auth_key.MAVLINK_MSG_ID_AUTH_KEY:
	    msgAuthKey = (msg_auth_key) message.unpack();
	    break;
	    
	case msg_set_mode.MAVLINK_MSG_ID_SET_MODE:
	    msgSetMode = (msg_set_mode) message.unpack();
	    break;
	    
	case msg_param_request_read.MAVLINK_MSG_ID_PARAM_REQUEST_READ:
	    msgParamRequestRead = (msg_param_request_read) message.unpack();
	    break;
	    
	case msg_param_request_list.MAVLINK_MSG_ID_PARAM_REQUEST_LIST:
	    msgParamRequestList = (msg_param_request_list) message.unpack();
	    break;
	    
	case msg_param_value.MAVLINK_MSG_ID_PARAM_VALUE:
	    msgParamValue = (msg_param_value) message.unpack();
	    break;
	    
	case msg_param_set.MAVLINK_MSG_ID_PARAM_SET:
	    msgParamSet = (msg_param_set) message.unpack();
	    break;
	    
	case msg_gps_raw_int.MAVLINK_MSG_ID_GPS_RAW_INT:
	    msgGpsRawInt = (msg_gps_raw_int) message.unpack();
	    break;
	    
	case msg_gps_status.MAVLINK_MSG_ID_GPS_STATUS:
	    msgGpsStatus = (msg_gps_status) message.unpack();
	    break;
	    
	case msg_scaled_imu.MAVLINK_MSG_ID_SCALED_IMU:
	    msgScaledImu = (msg_scaled_imu) message.unpack();
	    break;
	    
	case msg_raw_imu.MAVLINK_MSG_ID_RAW_IMU:
	    msgRawImu = (msg_raw_imu) message.unpack();
	    break;
	    
	case msg_raw_pressure.MAVLINK_MSG_ID_RAW_PRESSURE:
	    msgRawPressure = (msg_raw_pressure) message.unpack();
	    break;
	    
	case msg_scaled_pressure.MAVLINK_MSG_ID_SCALED_PRESSURE:
	    msgScaledPressure = (msg_scaled_pressure) message.unpack();
	    break;
	    
	case msg_attitude.MAVLINK_MSG_ID_ATTITUDE:
	    msgAttitude = (msg_attitude) message.unpack();
	    break;

	case msg_attitude_quaternion.MAVLINK_MSG_ID_ATTITUDE_QUATERNION:
	    msgAttitudeQuaternion = (msg_attitude_quaternion) message.unpack();
	    break;
	    
	case msg_local_position_ned.MAVLINK_MSG_ID_LOCAL_POSITION_NED:
	    msgLocalPositionNed = (msg_local_position_ned) message.unpack();
	    break;
	    
	case msg_global_position_int.MAVLINK_MSG_ID_GLOBAL_POSITION_INT:
	    msgGlobalPositionInt = (msg_global_position_int) message.unpack();
	    break;
	    
	case msg_rc_channels_scaled.MAVLINK_MSG_ID_RC_CHANNELS_SCALED:
	    msgRcChannelsScaled = (msg_rc_channels_scaled) message.unpack();
	    break;

	case msg_rc_channels_raw.MAVLINK_MSG_ID_RC_CHANNELS_RAW:
	    msgRcChannelsRaw = (msg_rc_channels_raw) message.unpack();
	    break;

	case msg_servo_output_raw.MAVLINK_MSG_ID_SERVO_OUTPUT_RAW:
	    msgServoOutputRaw = (msg_servo_output_raw) message.unpack();
	    break;
	    
	case msg_mission_request_partial_list.MAVLINK_MSG_ID_MISSION_REQUEST_PARTIAL_LIST:
	    msgMissionRequestPartialList = (msg_mission_request_partial_list) message.unpack();
	    break;
	    
	case msg_mission_write_partial_list.MAVLINK_MSG_ID_MISSION_WRITE_PARTIAL_LIST:
	    msgMissionWritePartialList = (msg_mission_write_partial_list) message.unpack();
	    break;
	    
	case msg_mission_item.MAVLINK_MSG_ID_MISSION_ITEM:
	    msgMissionItem = (msg_mission_item) message.unpack();
	    break;
	    
	case msg_mission_request.MAVLINK_MSG_ID_MISSION_REQUEST:
	    msgMissionRequest = (msg_mission_request) message.unpack();
	    break;
	    
	case msg_mission_set_current.MAVLINK_MSG_ID_MISSION_SET_CURRENT:
	    msgMissionSetCurrent = (msg_mission_set_current) message.unpack();
	    break;
	    
	case msg_mission_current.MAVLINK_MSG_ID_MISSION_CURRENT:
	    msgMissionCurrent = (msg_mission_current) message.unpack();
	    break;
	    
	case msg_mission_request_list.MAVLINK_MSG_ID_MISSION_REQUEST_LIST:
	    msgMissionRequestList = (msg_mission_request_list) message.unpack();
	    break;
	    
	case msg_mission_count.MAVLINK_MSG_ID_MISSION_COUNT:
	    msgMissionCount = (msg_mission_count) message.unpack();
	    break;
	    
	case msg_mission_clear_all.MAVLINK_MSG_ID_MISSION_CLEAR_ALL:
	    msgMissionClearAll = (msg_mission_clear_all) message.unpack();
	    break;
	    
	case msg_mission_item_reached.MAVLINK_MSG_ID_MISSION_ITEM_REACHED:
	    msgMissionItemReached = (msg_mission_item_reached) message.unpack();
	    break;
	    
	case msg_mission_ack.MAVLINK_MSG_ID_MISSION_ACK:
	    msgMissionAck = (msg_mission_ack) message.unpack();
	    break;
	    
	case msg_set_gps_global_origin.MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN:
	    msgSetGpsGlobalOrigin = (msg_set_gps_global_origin) message.unpack();
	    break;
	    
	case msg_gps_global_origin.MAVLINK_MSG_ID_GPS_GLOBAL_ORIGIN:
	    msgGpsGlobalOrigin = (msg_gps_global_origin) message.unpack();
	    break;
	    
	case msg_param_map_rc.MAVLINK_MSG_ID_PARAM_MAP_RC:
	    msgParamMapRc = (msg_param_map_rc) message.unpack();
	    break;
	    
	case msg_mission_request_int.MAVLINK_MSG_ID_MISSION_REQUEST_INT:
	    msgMissionRequestInt = (msg_mission_request_int) message.unpack();
	    break;
	    
	case msg_safety_set_allowed_area.MAVLINK_MSG_ID_SAFETY_SET_ALLOWED_AREA:
	    msgSafetySetAllowedArea = (msg_safety_set_allowed_area) message.unpack();
	    break;
	    
	case msg_safety_allowed_area.MAVLINK_MSG_ID_SAFETY_ALLOWED_AREA:
	    msgSafetyAllowedArea = (msg_safety_allowed_area) message.unpack();
	    break;
	    
	case msg_attitude_quaternion_cov.MAVLINK_MSG_ID_ATTITUDE_QUATERNION_COV:
	    msgAttitudeQuaternionCov = (msg_attitude_quaternion_cov) message.unpack();
	    break;
	    
	case msg_nav_controller_output.MAVLINK_MSG_ID_NAV_CONTROLLER_OUTPUT:
	    msgNavControllerOutput = (msg_nav_controller_output) message.unpack();
	    break;
	    
	case msg_global_position_int_cov.MAVLINK_MSG_ID_GLOBAL_POSITION_INT_COV:
	    msgGlobalPositionIntCov= (msg_global_position_int_cov) message.unpack();
	    break;
	    
	case msg_local_position_ned_cov.MAVLINK_MSG_ID_LOCAL_POSITION_NED_COV:
	    msgLocalPositionNedCov = (msg_local_position_ned_cov) message.unpack();
	    break;
	    
	case msg_rc_channels.MAVLINK_MSG_ID_RC_CHANNELS:
	    msgRcChannels = (msg_rc_channels) message.unpack();
	    break;
	    
	case msg_request_data_stream.MAVLINK_MSG_ID_REQUEST_DATA_STREAM:
	    msgRequestDataStream = (msg_request_data_stream) message.unpack();
	    break;
	    
	case msg_data_stream.MAVLINK_MSG_ID_DATA_STREAM:
	    msgDataStream = (msg_data_stream) message.unpack();
	    break;
	    
	case msg_manual_control.MAVLINK_MSG_ID_MANUAL_CONTROL:
	    msgManualControl = (msg_manual_control) message.unpack();
	    break;
	    
	case msg_rc_channels_override.MAVLINK_MSG_ID_RC_CHANNELS_OVERRIDE:
	    msgRcChannelsOverride = (msg_rc_channels_override) message.unpack();
	    break;
	    
	case msg_mission_item_int.MAVLINK_MSG_ID_MISSION_ITEM_INT:
	    msgMissionItemInt = (msg_mission_item_int) message.unpack();
	    break;
	    
	case msg_vfr_hud.MAVLINK_MSG_ID_VFR_HUD:
	    msgVfrHud = (msg_vfr_hud) message.unpack();
	    break;
	    
	case msg_command_int.MAVLINK_MSG_ID_COMMAND_INT:
	    msgCommandInt = (msg_command_int) message.unpack();
	    break;
	    
	case msg_command_long.MAVLINK_MSG_ID_COMMAND_LONG:
	    msgCommandLong = (msg_command_long) message.unpack();
	    break;
	    
	case msg_command_ack.MAVLINK_MSG_ID_COMMAND_ACK:
	    msgCommandAck = (msg_command_ack) message.unpack();
	    break;
	    
	case msg_manual_setpoint.MAVLINK_MSG_ID_MANUAL_SETPOINT:
	    msgManualSetpoint = (msg_manual_setpoint) message.unpack();
	    break;
	    
	case msg_set_attitude_target.MAVLINK_MSG_ID_SET_ATTITUDE_TARGET:
	    msgSetAttitudeTarget = (msg_set_attitude_target) message.unpack();
	    break;
	    
	case msg_attitude_target.MAVLINK_MSG_ID_ATTITUDE_TARGET:
	    msgAttitudeTarget = (msg_attitude_target) message.unpack();
	    break;
	    
	case msg_set_position_target_local_ned.MAVLINK_MSG_ID_SET_POSITION_TARGET_LOCAL_NED:
	    msgSetPositionTargetLocalNed = (msg_set_position_target_local_ned) message.unpack();
	    break;
	    
	case msg_position_target_local_ned.MAVLINK_MSG_ID_POSITION_TARGET_LOCAL_NED:
	    msgPositionTargetLocalNed = (msg_position_target_local_ned) message.unpack();
	    break;
	    
	case msg_set_position_target_global_int.MAVLINK_MSG_ID_SET_POSITION_TARGET_GLOBAL_INT:
	    msgSetPositionTargetGlobalInt = (msg_set_position_target_global_int) message.unpack();
	    break;
	    
	case msg_position_target_global_int.MAVLINK_MSG_ID_POSITION_TARGET_GLOBAL_INT:
	    msgPositionTargetGlobalInt = (msg_position_target_global_int) message.unpack();
	    break;
	    
	case msg_local_position_ned_system_global_offset.MAVLINK_MSG_ID_LOCAL_POSITION_NED_SYSTEM_GLOBAL_OFFSET:
	    msgLocalPositionNedSystemGlobalOffset = (msg_local_position_ned_system_global_offset) message.unpack();
	    break;
	    
	case msg_hil_state.MAVLINK_MSG_ID_HIL_STATE:
	    msgHilState = (msg_hil_state) message.unpack();
	    break;
	    
	case msg_hil_controls.MAVLINK_MSG_ID_HIL_CONTROLS:
	    msgHilControls = (msg_hil_controls) message.unpack();
	    break;
	    
	case msg_hil_rc_inputs_raw.MAVLINK_MSG_ID_HIL_RC_INPUTS_RAW:
	    msgHilRcInputsRaw = (msg_hil_rc_inputs_raw) message.unpack();
	    break;
	    
	case msg_optical_flow.MAVLINK_MSG_ID_OPTICAL_FLOW:
	    msgOpticalFlow = ( msg_optical_flow) message.unpack();
	    break;
	    
	case msg_global_vision_position_estimate.MAVLINK_MSG_ID_GLOBAL_VISION_POSITION_ESTIMATE:
	    msgGlobalVisionPositionEstimate = (msg_global_vision_position_estimate) message.unpack();
	    break;
	    
	case msg_vision_position_estimate.MAVLINK_MSG_ID_VISION_POSITION_ESTIMATE:
	    msgVisionPositionEstimate = (msg_vision_position_estimate) message.unpack();
	    break;
	    
	case msg_vision_speed_estimate.MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE:
	    msgVisionSpeedEstimate = (msg_vision_speed_estimate) message.unpack();
	    break;
	    
	case msg_vicon_position_estimate.MAVLINK_MSG_ID_VICON_POSITION_ESTIMATE:
	    msgViconPositionEstimate = (msg_vicon_position_estimate) message.unpack();
	    break;
	    
	case msg_highres_imu.MAVLINK_MSG_ID_HIGHRES_IMU:
	    msgHighresImu = (msg_highres_imu) message.unpack();
	    break;
	    
	case msg_optical_flow_rad.MAVLINK_MSG_ID_OPTICAL_FLOW_RAD:
	    msgOpticalFlowRad = (msg_optical_flow_rad) message.unpack();
	    break;
	    
	case msg_hil_sensor.MAVLINK_MSG_ID_HIL_SENSOR:
	    msgHilSensor = (msg_hil_sensor) message.unpack();
	    break;
	    
	case msg_sim_state.MAVLINK_MSG_ID_SIM_STATE:
	    msgSimState = (msg_sim_state) message.unpack();
	    break;
	    
	case msg_radio_status.MAVLINK_MSG_ID_RADIO_STATUS:
	    msgRadioStatus = (msg_radio_status) message.unpack();
	    break;
	    
	case msg_file_transfer_protocol.MAVLINK_MSG_ID_FILE_TRANSFER_PROTOCOL:
	    msgFileTransferProtocol = (msg_file_transfer_protocol) message.unpack();
	    break;
	    
	case msg_timesync.MAVLINK_MSG_ID_TIMESYNC:
	    msgTimesync = (msg_timesync) message.unpack();
	    break;
	    
	case msg_camera_trigger.MAVLINK_MSG_ID_CAMERA_TRIGGER:
	    msgCameraTrigger = (msg_camera_trigger) message.unpack();
	    break;
	    
	case msg_hil_gps.MAVLINK_MSG_ID_HIL_GPS:
	    msgHilGps = (msg_hil_gps) message.unpack();
	    break;
	    
	case msg_hil_optical_flow.MAVLINK_MSG_ID_HIL_OPTICAL_FLOW:
	    msgHilOpticalFlow = (msg_hil_optical_flow) message.unpack();
	    break;
	    
	case msg_hil_state_quaternion.MAVLINK_MSG_ID_HIL_STATE_QUATERNION:
	    msgHilStateQuaternion = (msg_hil_state_quaternion) message.unpack();
	    break;
	    
	case msg_scaled_imu2.MAVLINK_MSG_ID_SCALED_IMU2:
	    msgScaledImu2 = (msg_scaled_imu2) message.unpack();
	    break;
	    
	case msg_log_request_list.MAVLINK_MSG_ID_LOG_REQUEST_LIST:
	    msgLogRequestList = (msg_log_request_list) message.unpack();
	    break;
	    
	case msg_log_entry.MAVLINK_MSG_ID_LOG_ENTRY:
	    msgLogEntry = (msg_log_entry) message.unpack();
	    break;
	    
	case msg_log_request_data.MAVLINK_MSG_ID_LOG_REQUEST_DATA:
	    msgLogRequestData = (msg_log_request_data) message.unpack();
	    break;
	    
	case msg_log_data.MAVLINK_MSG_ID_LOG_DATA:
	    msgLogData = (msg_log_data) message.unpack();
	    break;
	    
	case msg_log_erase.MAVLINK_MSG_ID_LOG_ERASE:
	    msgLogErase = (msg_log_erase) message.unpack();
	    break;
	    
	case msg_log_request_end.MAVLINK_MSG_ID_LOG_REQUEST_END:
	    msgLogRequestEnd = (msg_log_request_end) message.unpack();
	    break;
	    
	case msg_gps_inject_data.MAVLINK_MSG_ID_GPS_INJECT_DATA:
	    msgGpsInjectData = (msg_gps_inject_data) message.unpack();
	    break;
	    
	case msg_gps2_raw.MAVLINK_MSG_ID_GPS2_RAW:
	    msgGps2Raw = (msg_gps2_raw) message.unpack();
	    break;
	    
	case msg_power_status.MAVLINK_MSG_ID_POWER_STATUS:
	    msgPowerStatus = (msg_power_status) message.unpack();
	    break;
	    
	case msg_serial_control.MAVLINK_MSG_ID_SERIAL_CONTROL:
	    msgSerialControl = (msg_serial_control) message.unpack();
	    break;

	case msg_gps_rtk.MAVLINK_MSG_ID_GPS_RTK:
	    msgGpsRtk = (msg_gps_rtk) message.unpack();
	    break;
	    
	case msg_gps2_rtk.MAVLINK_MSG_ID_GPS2_RTK:
	    msgGps2Rtk = (msg_gps2_rtk) message.unpack();
	    break;
	    
	case msg_scaled_imu3.MAVLINK_MSG_ID_SCALED_IMU3:
	    msgScaledImu3 = (msg_scaled_imu3) message.unpack();
	    break;
	    
	case msg_data_transmission_handshake.MAVLINK_MSG_ID_DATA_TRANSMISSION_HANDSHAKE:
	    msgDataTransmissionHandshake = (msg_data_transmission_handshake) message.unpack();
	    break;
	    
	case msg_encapsulated_data.MAVLINK_MSG_ID_ENCAPSULATED_DATA:
	    msgEncapsulatedData = (msg_encapsulated_data) message.unpack();
	    break;
	    
	case msg_distance_sensor.MAVLINK_MSG_ID_DISTANCE_SENSOR:
	    msgDistanceSensor = (msg_distance_sensor) message.unpack();
	    break;
	    
	case msg_terrain_request.MAVLINK_MSG_ID_TERRAIN_REQUEST:
	    msgTerrainRequest = (msg_terrain_request) message.unpack();
	    break;
	    
	case msg_terrain_data.MAVLINK_MSG_ID_TERRAIN_DATA:
	    msgTerrainData = (msg_terrain_data) message.unpack();
	    break;
	    
	case msg_terrain_check.MAVLINK_MSG_ID_TERRAIN_CHECK:
	    msgTerrainCheck = (msg_terrain_check) message.unpack();
	    break;
	    
	case msg_terrain_report.MAVLINK_MSG_ID_TERRAIN_REPORT:
	    msgTerrainReport = (msg_terrain_report) message.unpack();
	    break;
	    
	case msg_scaled_pressure2.MAVLINK_MSG_ID_SCALED_PRESSURE2:
	    msgScaledPressure2 = (msg_scaled_pressure2) message.unpack();
	    break;
	    
	case msg_att_pos_mocap.MAVLINK_MSG_ID_ATT_POS_MOCAP:
	    msgAttPosMocap = (msg_att_pos_mocap) message.unpack();
	    break;
	    
	case msg_set_actuator_control_target.MAVLINK_MSG_ID_SET_ACTUATOR_CONTROL_TARGET:
	    msgSetActuatorControlTarget = (msg_set_actuator_control_target) message.unpack();
	    break;
	    
	case msg_actuator_control_target.MAVLINK_MSG_ID_ACTUATOR_CONTROL_TARGET:
	    msgActuatorControlTarget = (msg_actuator_control_target) message.unpack();
	    break;
	    
	case msg_altitude.MAVLINK_MSG_ID_ALTITUDE:
	    msgAltitude = (msg_altitude) message.unpack();
	    break;
	    
	case msg_resource_request.MAVLINK_MSG_ID_RESOURCE_REQUEST:
	    msgResourceRequest = (msg_resource_request) message.unpack();
	    break;
	    
	case msg_scaled_pressure3.MAVLINK_MSG_ID_SCALED_PRESSURE3:
	    msgScaledPressure3 = (msg_scaled_pressure3) message.unpack();
	    break;
	    
	case msg_follow_target.MAVLINK_MSG_ID_FOLLOW_TARGET:
	    msgFollowTarget = (msg_follow_target) message.unpack();
	    break;
	    
	case msg_control_system_state.MAVLINK_MSG_ID_CONTROL_SYSTEM_STATE:
	    msgControlSystemState = (msg_control_system_state) message.unpack();
	    break;
	    
	case msg_battery_status.MAVLINK_MSG_ID_BATTERY_STATUS:
	    msgBatteryStatus = (msg_battery_status) message.unpack();
	    break;
	    
	case msg_autopilot_version.MAVLINK_MSG_ID_AUTOPILOT_VERSION:
	    msgAutopilotVersion = (msg_autopilot_version) message.unpack();
	    break;
	    
	case msg_landing_target.MAVLINK_MSG_ID_LANDING_TARGET:
	    msgLandingTarget = (msg_landing_target) message.unpack();
	    break;
	    
	case msg_estimator_status.MAVLINK_MSG_ID_ESTIMATOR_STATUS:
	    msgEstimatorStatus = (msg_estimator_status) message.unpack();
	    break;
	    
	case msg_wind_cov.MAVLINK_MSG_ID_WIND_COV:
	    msgWindCov = (msg_wind_cov) message.unpack();
	    break;
	    
	case msg_gps_rtcm_data.MAVLINK_MSG_ID_GPS_RTCM_DATA:
	    msgGpsRtcmData = (msg_gps_rtcm_data) message.unpack();
	    break;
	    
	case msg_vibration.MAVLINK_MSG_ID_VIBRATION:
	    msgVibration = (msg_vibration) message.unpack();
	    break;
	    
	case msg_home_position.MAVLINK_MSG_ID_HOME_POSITION:
	    msgHomePosition = (msg_home_position) message.unpack();
	    break;
	    
	case msg_set_home_position.MAVLINK_MSG_ID_SET_HOME_POSITION:
	    msgSetHomePosition = (msg_set_home_position) message.unpack();
	    break;
	    
	case msg_message_interval.MAVLINK_MSG_ID_MESSAGE_INTERVAL:
	    msgMessageInterval = (msg_message_interval) message.unpack();
	    break;
	    
	case msg_extended_sys_state.MAVLINK_MSG_ID_EXTENDED_SYS_STATE:
	    msgExtendedSysState = (msg_extended_sys_state) message.unpack();
	    break;
	    
	case msg_adsb_vehicle.MAVLINK_MSG_ID_ADSB_VEHICLE:
	    msgAdsbVehicle = (msg_adsb_vehicle) message.unpack();
	    break;
	    
	case msg_v2_extension.MAVLINK_MSG_ID_V2_EXTENSION:
	    msgV2Extension = (msg_v2_extension) message.unpack();
	    break;
	    
	case msg_memory_vect.MAVLINK_MSG_ID_MEMORY_VECT:
	    msgMemoryVect = (msg_memory_vect) message.unpack();
	    break;
	    
	case msg_debug_vect.MAVLINK_MSG_ID_DEBUG_VECT:
	    msgDebugVect = (msg_debug_vect) message.unpack();
	    break;
	    
	case msg_named_value_float.MAVLINK_MSG_ID_NAMED_VALUE_FLOAT:
	    msgNamedValueFloat = (msg_named_value_float) message.unpack();
	    break;
	    
	case msg_named_value_int.MAVLINK_MSG_ID_NAMED_VALUE_INT:
	    msgNamedValueInt = (msg_named_value_int) message.unpack();
	    break;
	    
	case msg_statustext.MAVLINK_MSG_ID_STATUSTEXT:
	    msgStatustext = (msg_statustext) message.unpack();
	    break;
	    
	case msg_debug.MAVLINK_MSG_ID_DEBUG:
	    msgDebug = (msg_debug) message.unpack();
	    break;

	case msg_flightplan_info.MAVLINK_MSG_ID_FLIGHTPLAN_INFO:
	    msgFlightplanInfo = (msg_flightplan_info) message.unpack();
	    FlightPlanUpdateInterrupt  = 1;
	    break;

	case msg_pointofinterest.MAVLINK_MSG_ID_POINTOFINTEREST:
	    msgPointofinterest = (msg_pointofinterest) message.unpack();
	    break;

	case msg_geofence_info.MAVLINK_MSG_ID_GEOFENCE_INFO:
	    msgGeofenceInfo = (msg_geofence_info) message.unpack();
	    GeoFenceUpdateInterrupt = 1;
	    break;

	case msg_combox_pulse.MAVLINK_MSG_ID_COMBOX_PULSE:
	    msgComboxPulse = (msg_combox_pulse) message.unpack();
	    break;
	    
	    
	}
	
	
    }	

}

