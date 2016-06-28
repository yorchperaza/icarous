/*
 * Copyright (c) 2016 United States Government as represented by
 * the National Aeronautics and Space Administration.  No copyright
 * is claimed in the United States under Title 17, U.S.Code. All Other
 * Rights Reserved.
 */
//MavLink 1.0

//package gov.nasa.larc.AP;
//import gov.nasa.larc.serial.Loggable;

/**
Message ID: REQUEST_DATA_STREAM(66)
--------------------------------------
--------------------------------------
*/
public class REQUEST_DATA_STREAM_class //implements Loggable
{
	public static final int msgID = 66;
	public int		 req_message_rate; 	// The requested interval between two messages of this type
	public short	 target_system;	 	// The target requested to send the message stream.
	public short	 target_component; 	// The target requested to send the message stream.
	public short	 req_stream_id;	 	// The ID of the requested data stream
	public short	 start_stop;		 	// 1 to start sending, 0 to stop sending.

	private packet rcvPacket;
	private packet sndPacket;

	public REQUEST_DATA_STREAM_class()
	{
		rcvPacket = new packet(msgID);
		sndPacket = new packet(msgID);
		target_system = 1;
		target_component = 1;
	}

	public REQUEST_DATA_STREAM_class(REQUEST_DATA_STREAM_class o)
	{
		req_message_rate = o.req_message_rate;
		target_system = o.target_system;
		target_component = o.target_component;
		req_stream_id = o.req_stream_id;
		start_stop = o.start_stop;
	}

	public boolean parse(byte[] b)
	{
		return parse(b, false);
	}

	public boolean parse(byte[] b, boolean valid)
	{
		rcvPacket.load(b);

		boolean pstatus = valid || rcvPacket.isPacket();
		if (pstatus)
		{
			rcvPacket.updateSeqNum();

			// int[] mavLen = {2, 1, 1, 1, 1};
			// int[] javLen = {4, 2, 2, 2, 2};

			req_message_rate	= rcvPacket.getIntS();
			target_system	= rcvPacket.getShortB();
			target_component	= rcvPacket.getShortB();
			req_stream_id	= rcvPacket.getShortB();
			start_stop		= rcvPacket.getShortB();
		}
		return(pstatus);
	}

	public byte[] encode()
	{
		return encode(
					  req_message_rate
					 ,(short)1
					 ,(short)1
					 ,req_stream_id
					 ,start_stop
					 );
	}

	public byte[] encode(
						 int v_req_message_rate
						,short v_req_stream_id
						,short v_start_stop
						)
	{
		return encode(
					  v_req_message_rate
					 ,  (short)1
					 ,  (short)1
					 ,v_req_stream_id
					 ,v_start_stop
					 );
	}

	public byte[] encode(
						 int v_req_message_rate
						,short v_target_system
						,short v_target_component
						,short v_req_stream_id
						,short v_start_stop
						)
	{
		// int[] mavLen = {2, 1, 1, 1, 1};
		// int[] javLen = {4, 2, 2, 2, 2};

		sndPacket.setSndSeq();

		sndPacket.resetDataIdx();
		sndPacket.putShortI(v_req_message_rate);	// Add "req_message_rate" parameter
		sndPacket.putByteS(v_target_system);	// Add "target_system" parameter
		sndPacket.putByteS(v_target_component);	// Add "target_component" parameter
		sndPacket.putByteS(v_req_stream_id);	// Add "req_stream_id" parameter
		sndPacket.putByteS(v_start_stop);	// Add "start_stop" parameter

		// encode the checksum

		sndPacket.putChkSum();

		return sndPacket.getPacket();
	}

	public String getLogHeader()
	{
		String param = (
				  "  time"
 				+ ", REQUEST_DATA_STREAM_req_message_rate"
 				+ ", REQUEST_DATA_STREAM_target_system"
 				+ ", REQUEST_DATA_STREAM_target_component"
 				+ ", REQUEST_DATA_STREAM_req_stream_id"
 				+ ", REQUEST_DATA_STREAM_start_stop"
				);
		return param;
	}

    public String getLogData()
	{
		String param = (
				System.currentTimeMillis()
 				+ ", " + req_message_rate
 				+ ", " + target_system
 				+ ", " + target_component
 				+ ", " + req_stream_id
 				+ ", " + start_stop
				);
		return param;
	}
}
