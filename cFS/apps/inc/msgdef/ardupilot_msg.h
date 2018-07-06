/**
 * @file ardupilot_msg.h
 * @brief Ardupilot message definitions
 */

#ifndef COMMON_DEFS_H_
#define COMMON_DEFS_H_

#include <stdint.h>
#include <stdbool.h>
#include <string.h>

#include "cfe.h"



/**
 * @defgroup ARDUPILOT_MESSAGES
 * @brief Messages generated by the ardupilot application.
 * @ingroup ARDUPILOT
 * @ingroup MESSAGES
 * @{
 */

/**
 * @enum icarousControlMode_e
 * @brief Defines the mode of operation of Icarous
 */
typedef enum {
	_PASSIVE_,     ///< Passive mode, Icarous only monitors and logs data
	_ACTIVE_,      ///< Active mode, Icarous will interfere if conflicts are about to be violated.
	_INACTIVE_     ///< Inactive mode.
} icarousControlMode_e;

/**
 * @enum Icarous commands
 * @brief Commands that are set to the autopilot app from other applications
 */
typedef enum {
	_ARM_,           ///< Arm the motors. 1 parameter (arm_status:0/1 [disarm/arm])
	_TAKEOFF_,       ///< Start the takeoff sequence (used in quadcopters). 1 parameter (takeoff altitude [m])
	_SETMODE_,       ///< Set autopilot modes. 1 parameter (see icarousControlMode_e)
	_LAND_,          ///< Start the landing sequence
	_GOTOWP_,        ///< Goto waypoint. 1 parameter (waypoint index)
	_SETPOS_,        ///< Set position. 3 parameters (lat [deg], lon [deg], alt [m])
	_SETVEL_,        ///< Set velocity. 3 parameters (Vn [m/s], Ve [m/s], Vu [m/s])
	_SETYAW_,        ///< Set yaw. 4 parameters (target angle [deg], angular rate [deg/s], direction (clk,anit-clk) [1,-1], type (1/0) [relative/absolute] )
	_SETSPEED_,      ///< Set speed. 1 parameter (speed [m/s])
	_STATUS_,        ///< Status command
	_DITCH_,         ///< Start ditching status
} commandName_e;

/**
 * @enum geofenceType_e
 * @brief geofence type
 */
typedef enum{
    _KEEPIN_,  ///< Keep in fence
    _KEEPOUT_  ///< Keep out fence
}geofenceType_e;

/**
 * @enum objectType_e
 * @brief object type
 */
typedef enum{
    _TRAFFIC_,  ///< traffic
    _OBSTACLE_  ///< obstacle
}objectType_e;


/**
 * @struct waypoint_t
 * @brief message encoding waypoint information.
 *
 */
typedef struct{
    uint8_t TlmHeader[CFE_SB_TLM_HDR_SIZE]; /**< cFS header information */
    char planID[10];                        /**< string to identify the plan */
	int32_t totalWayPoints;                 /**< total number of waypoints in flight plan*/
	double position[50][3];                 /**< lat,lon,alt (deg,deg,m) */
	double speed[50];                       /**< speed between current and next wp  (m/s)*/
}flightplan_t;

/**
 * @struct missionItemReached_t
 * @brief message indicating a specific waypoint has been reached.
 *
 */
typedef struct{
    uint8_t TlmHeader[CFE_SB_TLM_HDR_SIZE]; /**< cFS header information */
	uint8_t reachedwaypoint;                /**< waypoint index that was reached */
    bool feedback;                          /**< true if from autopilot */
}missionItemReached_t;

/**
 * @struct geofence_t
 * @brief message encoding geofence vertex information.
 *
 */
typedef struct{
    uint8_t TlmHeader[CFE_SB_TLM_HDR_SIZE]; /**< cFS header information */
	uint8_t type;                           /**< geofence type: see geofence_type_t */
    uint16_t index;                         /**< geofence index */
	uint16_t totalvertices;                 /**< total vertices in this geofence */
    double vertices[50][2];                  /**< lat,lon (deg,deg) */
	double floor;                            /**< floor of geofence (m) */
	double ceiling;                          /**< roof of geofence (m) */
}geofence_t;

/**
 * @struct object_t
 * @brief message to represent information about an static/dynamic object
 *
 */
typedef struct{
    uint8_t TlmHeader[CFE_SB_TLM_HDR_SIZE];  /**< cFS header information */
	uint8_t type;                            /**< object type: see object_type_t */
	uint32_t index;                          /**< id of object */
	double latitude;                          /**< latitude (degrees) */
	double longitude;                         /**< longitude (degrees) */
	double altitude;                          /**< altitude (degrees) */
	double vx;                                /**< velocity East component */
	double vy;                                /**< velocity North component */
	double vz;                                /**< velocity Up component */
}object_t;

/**
 * @struct position_t
 * @brief position information of aircraft.
 *
 */
typedef struct{
    uint8_t TlmHeader[CFE_SB_TLM_HDR_SIZE];   /**< cFS header information */
    uint32_t aircraft_id;                     /**< aircraft id */
	double time_gps;                          /**< gps time */
	double latitude;                          /**< latitude (degrees) */
	double longitude;                         /**< longitude (degrees) */
	double altitude_abs;                      /**< absolution altitude, ASL (m)  */
	double altitude_rel;                      /**< relative altitude, AGL (m) */
	double vx;                                /**< velocity North component (m/s)*/
	double vy;                                /**< velocity East component (m/s)*/
	double vz;                                /**< velocity Down component (m/s)*/
    double hdg;                               /**< heading in degrees */
	double hdop;                              /**< GPS Horizontal Dilution of Precision */
	double vdop;                              /**< GPS Vertical Dilution of Precision */
	int numSats;                              /**< Total number of satellites being used for localization */
}position_t;


/**
 * @struct attitude_t
 * @brief aircraft attitude information.
 *
 */
typedef struct{
    uint8_t TlmHeader[CFE_SB_TLM_HDR_SIZE];   /**< cFS header information */
	double roll;                               /**< roll angle (degree) */
	double pitch;                              /**< pitch angle (degree) */
	double yaw;                                /**< yaw angle (degree) */
}attitude_t;

/**
 * @struct noArgsCmd_t
 * @brief Command without arguments
 *
 */
typedef struct{
    uint8_t TlmHeader[CFE_SB_CMD_HDR_SIZE];  /**< cFS header information */
	commandName_e name;                      /**< command name */
}noArgsCmd_t;

/**
 * @struct argsCmd_t
 * @brief Command with arguments
 */
typedef struct{
    uint8_t TlmHeader[CFE_SB_CMD_HDR_SIZE];  /**< cFS header information */
	commandName_e name;                      /**< command name: see command_name_t */
	double param1,param2;                    /**< command arguments */
	double param3,param4;                    /**< command arguments */
	double param5,param6;                    /**< command arguments */
	double param7,param8;                    /**< command arguments */
	char buffer[50];                         /**< command arguments */
}argsCmd_t;

/**
 * @struct cmdAck_t
 * @brief Command acknowledgement
 */
typedef struct{
    uint8_t TlmHeader[CFE_SB_CMD_HDR_SIZE];  /**< cFS header information */
	commandName_e name;                      /**< command name: see command_name_t */
	int result;                              /**< result */
}cmdAck_t;


/**
 * @struct status_t
 * @brief Message to provide status information
 */
typedef struct{
    uint8_t TlmHeader[CFE_SB_CMD_HDR_SIZE];   /**< cFS header information */
    char buffer[250];                         /**< status message */
}status_t;

/**@}*/

#endif /* ARDUPILOT_DEFS_H_ */
