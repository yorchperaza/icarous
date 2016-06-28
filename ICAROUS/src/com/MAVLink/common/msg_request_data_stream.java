/*
 * Copyright (c) 2016 United States Government as represented by
 * the National Aeronautics and Space Administration.  No copyright
 * is claimed in the United States under Title 17, U.S.Code. All Other
 * Rights Reserved.
 */
/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE REQUEST_DATA_STREAM PACKING
package com.MAVLink.common;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
        
/**
* THIS INTERFACE IS DEPRECATED. USE SET_MESSAGE_INTERVAL INSTEAD.
*/
public class msg_request_data_stream extends MAVLinkMessage{

    public static final int MAVLINK_MSG_ID_REQUEST_DATA_STREAM = 66;
    public static final int MAVLINK_MSG_LENGTH = 6;
    private static final long serialVersionUID = MAVLINK_MSG_ID_REQUEST_DATA_STREAM;


      
    /**
    * The requested message rate
    */
    public int req_message_rate;
      
    /**
    * The target requested to send the message stream.
    */
    public short target_system;
      
    /**
    * The target requested to send the message stream.
    */
    public short target_component;
      
    /**
    * The ID of the requested data stream
    */
    public short req_stream_id;
      
    /**
    * 1 to start sending, 0 to stop sending.
    */
    public short start_stop;
    

    /**
    * Generates the payload for a mavlink message for a message of this type
    * @return
    */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH);
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_REQUEST_DATA_STREAM;
              
        packet.payload.putUnsignedShort(req_message_rate);
              
        packet.payload.putUnsignedByte(target_system);
              
        packet.payload.putUnsignedByte(target_component);
              
        packet.payload.putUnsignedByte(req_stream_id);
              
        packet.payload.putUnsignedByte(start_stop);
        
        return packet;
    }

    /**
    * Decode a request_data_stream message into this class fields
    *
    * @param payload The message to decode
    */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
              
        this.req_message_rate = payload.getUnsignedShort();
              
        this.target_system = payload.getUnsignedByte();
              
        this.target_component = payload.getUnsignedByte();
              
        this.req_stream_id = payload.getUnsignedByte();
              
        this.start_stop = payload.getUnsignedByte();
        
    }

    /**
    * Constructor for a new message, just initializes the msgid
    */
    public msg_request_data_stream(){
        msgid = MAVLINK_MSG_ID_REQUEST_DATA_STREAM;
    }

    /**
    * Constructor for a new message, initializes the message with the payload
    * from a mavlink packet
    *
    */
    public msg_request_data_stream(MAVLinkPacket mavLinkPacket){
        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.msgid = MAVLINK_MSG_ID_REQUEST_DATA_STREAM;
        unpack(mavLinkPacket.payload);        
    }

              
    /**
    * Returns a string with the MSG name and data
    */
    public String toString(){
        return "MAVLINK_MSG_ID_REQUEST_DATA_STREAM -"+" req_message_rate:"+req_message_rate+" target_system:"+target_system+" target_component:"+target_component+" req_stream_id:"+req_stream_id+" start_stop:"+start_stop+"";
    }
}
        