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
Message ID: STATE_DATA(234)
--------------------------------------
%%~ Message that provides information about the state of the aircraft
--------------------------------------
*/
public class STATE_DATA_class //implements Loggable
{
	public static final int msgID = 234;
	public long	 usec;	 	// time
	public short	 num;		 	// Vehicle number, e.g. 1 for R1, 2 for R2, 3 for R3
	public short	 atloiter; 	// if 0, vehicle is not currently loitering, if 1, vehicle is loitering
	public short	 mode;	 	// mode: MANUAL=0, CIRCLE=1, STABILIZE=2, FLY_BY_WIRE_A=5, FLY_BY_WIRE_B=6, FLY_BY_WIRE_C=7, AUTO=10, RTL=11, LOITER=12, GUIDED=15, INITIALISING=16

	private packet rcvPacket;
	private packet sndPacket;

	public STATE_DATA_class()
	{
		rcvPacket = new packet(msgID);
		sndPacket = new packet(msgID);
	}

	public STATE_DATA_class(STATE_DATA_class o)
	{
		usec = o.usec;
		num = o.num;
		atloiter = o.atloiter;
		mode = o.mode;
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

			// int[] mavLen = {8, 1, 1, 1};
			// int[] javLen = {8, 2, 2, 2};

			usec		= rcvPacket.getLong();
			num		= rcvPacket.getShortB();
			atloiter	= rcvPacket.getShortB();
			mode		= rcvPacket.getShortB();
		}
		return(pstatus);
	}

	public byte[] encode()
	{
		return encode(
					  usec
					 ,num
					 ,atloiter
					 ,mode
					 );
	}

	public byte[] encode(
						 long v_usec
						,short v_num
						,short v_atloiter
						,short v_mode
						)
	{
		// int[] mavLen = {8, 1, 1, 1};
		// int[] javLen = {8, 2, 2, 2};

		sndPacket.setSndSeq();

		sndPacket.resetDataIdx();
		sndPacket.putLong(v_usec);	// Add "usec" parameter
		sndPacket.putByteS(v_num);	// Add "num" parameter
		sndPacket.putByteS(v_atloiter);	// Add "atloiter" parameter
		sndPacket.putByteS(v_mode);	// Add "mode" parameter

		// encode the checksum

		sndPacket.putChkSum();

		return sndPacket.getPacket();
	}

	public String getLogHeader()
	{
		String param = (
				  "  time"
 				+ ", STATE_DATA_usec"
 				+ ", STATE_DATA_num"
 				+ ", STATE_DATA_atloiter"
 				+ ", STATE_DATA_mode"
				);
		return param;
	}

    public String getLogData()
	{
		String param = (
				System.currentTimeMillis()
 				+ ", " + usec
 				+ ", " + num
 				+ ", " + atloiter
 				+ ", " + mode
				);
		return param;
	}
}
