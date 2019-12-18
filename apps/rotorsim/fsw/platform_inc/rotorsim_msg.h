/**
 * @file rotorsim_msg.h
 * @brief Defintion of messages used by rotorsim_msg.h
 */


#include "cfe.h"

/**
 * @defgroup ROTORSIM_MESSAGES
 * @brief Rotorsim message definitions
 * @ingroup ROTORSIM_MONITOR
 * @ingroup ROTORSIM
 * @{
 */

/**
 * @struct rotorsim_parameters_t
 * @brief data structure containing information about the parameters used by the rotorsim app
 */
typedef struct{
   uint8_t  TlmHeader[CFE_SB_TLM_HDR_SIZE];
   double speed;
}rotorsim_parameters_t;

/**@}*/