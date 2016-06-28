/*
 * Copyright (c) 2014-2016 United States Government as represented by
 * the National Aeronautics and Space Administration.  No copyright
 * is claimed in the United States under Title 17, U.S.Code. All Other
 * Rights Reserved.
 */
package gov.nasa.larcfm.Util;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public final class DebugSupport {

	// THIS SHOULD NOT CONTAIN ANY REFERENCES TO SWING OR AWT CLASSES, EVEN INDIRECTLY.  THOSE GO IN "GuiUtil.java".


	public static void dumpPlan(Plan plan, String str) {		
		String  dumpFileName = "1dump_"+str+".txt";
		PlanUtil.savePlan(plan, dumpFileName);
		f.pln(" ............. dumpPlan: created file "+dumpFileName);
	}

	/** Write the plans to the given file */
	public static void dumpPlans(Plan own, Plan traffic, String fileName, double D, double H) {//TODO: use PlanWriter
		PrintWriter dumpFile;
		fileName = "1dump_"+fileName;
		try {
			dumpFile = new PrintWriter(new FileWriter(fileName));
		}
		catch (IOException e) {
			System.out.println("Can't open file " + fileName + "!");
			System.out.println("Error: " + e);
			return;        // End the program.
		}
		dumpFile.println(" D = "+Units.str("NM",D));
		dumpFile.println(" H = "+Units.str("ft",H));
		dumpFile.println(own.getOutputHeader(true));

		if (own.getName().equals("")) {
			own.setName("own");
		}
		String s = own.toOutput(0, 12, true, false);
		dumpFile.println(s);

		if (traffic.getName().equals("")) {
			traffic.setName("traf");
		}
		s = traffic.toOutput(0, 12, true, false);
		dumpFile.println(s);
		dumpFile.close();
		f.pln(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+fileName+" written!");
	}


	public static void dumpDensityGrid(DensityGrid dGrid, String str) {		
		String  dumpFileName = "1dGrid_"+str+".txt";
		PlanWriter pw = new PlanWriter();
		pw.setPrecision(12);
		Plan p = new Plan("GRID");
		pw.open(dumpFileName);
		double time = 0;
		for (int y = dGrid.sz_y-1; y >= 0; y--) {
			for (int x=0; x < dGrid.sz_x; x++) {
				Position pxy = dGrid.getPosition(x, y);
				NavPoint np = new NavPoint(pxy,time);
				np = np.makeLabel(""+dGrid.getWeight(x, y));
				time++;
				//f.pln(" $$$$ dumpDensityGrid: ADD np = "+np);
				p.add(np);
			}
		}		
		pw.writePlan(p,true);
		pw.close();
		if (pw.hasMessage()) {
			f.dln(pw.getMessage());
		}						
		f.pln(" ............. dumpPlan: created file "+dumpFileName);
	}

	public static void dumpDensityGrid(DensityGridTimed dGrid, String str, double t) {		
		String  dumpFileName = "1dGrid_"+str+".txt";
		PlanWriter pw = new PlanWriter();
		pw.setPrecision(12);
		Plan p = new Plan("GRID");
		pw.open(dumpFileName);
		double time = 0;
		for (int y = dGrid.sz_y-1; y >= 0; y--) {
			for (int x=0; x < dGrid.sz_x; x++) {
				//                Position pxy = dGrid.getPosition(x, y);
				//                NavPoint np = new NavPoint(pxy,time);
				//                np = np.makeLabel(""+dGrid.getWeight(x, y, t));
				//                p.add(np);

				Position pxy2 = dGrid.center(x, y);
				NavPoint np2 = new NavPoint(pxy2,time+0.5);
				np2 = np2.makeLabel(""+dGrid.getSearchedWeight(x, y));
				p.add(np2);              
				time++;
			}
		}		

		ParameterData pd = new ParameterData();
		pd.set("ui_grid_square_size",dGrid.squareDist,"m");
		pw.setParameters(pd);

		pw.writePlan(p,true);
		pw.close();
		if (pw.hasMessage()) {
			f.dln(pw.getMessage());
		}						
		f.pln(" ............. dumpPlan: created file "+dumpFileName);
	}

	
	public static String SimplePolyToOutput(SimplePoly p, String str) {	
		String headerAlt2 = ", alt2";
		String s = "";
		s += "Name, ";
		if (p.isLatLon()) {
			s += "Lat, Lon, Alt";
		} else {
			s += "SX, SY, SZ";
		}
		s += ", Time, ";
		s += "Label";
		s += headerAlt2+"\n";
		s = s  + p.toOutput(str, 4, false);
        return s;
	}

	
	
	public static void dumpPoly(SimplePoly p, String str) {	
		String  dumpFileName = "1dump_"+str+".txt";
//		String headerAlt2 = ", alt2";
//		String s = "";
//		s += "Name, ";
//		if (p.isLatLon()) {
//			s += "Lat, Lon, Alt";
//		} else {
//			s += "SX, SY, SZ";
//		}
//		s += ", Time, ";
//		if (false) {
//			s += "type, trk, gs, vs, tcp_trk, accel_trk, tcp_gs, accel_gs, tcp_vs, accel_vs, ";
//			if (p.isLatLon()) {
//				s += "src_lat, src_lon, src_alt, ";
//			} else {
//				s += "src_x, src_y, src_z, ";				
//			}
//			s += "src_time, ";
//		}
//		s += "Label";
//		s += headerAlt2+"\n";
//		s = s  + p.toOutput(str, 4, false);
		String s = SimplePolyToOutput(p,str);
		try {
			java.io.PrintWriter pw = 
					new java.io.PrintWriter(new java.io.BufferedWriter(new java.io.FileWriter(dumpFileName)));
			pw.println(s);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			f.pln(" ERROR: "+e.toString());
		}
		f.pln(" ............. dumpSimplePoly: created file "+dumpFileName);

	}

	public static String pointAsUnitTest(NavPoint p, int j) {
		return positionAsUnitTest(p.position(),j);
	}

	/**  Create a line for a unit test, the int j is used to name the point
	 * 
	 * @param p
	 * @param j
	 * @return
	 */
	public static String positionAsUnitTest(Position p, int j) {
		String rtn = "";
		if (p.isLatLon()) {
			rtn = "  Position p"+j+"  = new Position(LatLonAlt.make("
					+ f.Fm6(Units.to("deg",p.lat()))
					+", "+f.Fm6(Units.to("deg",p.lon()))
					+", "+f.Fm6(Units.to("ft",p.alt()))+"));";
		} else {
			rtn = "  Position p"+j+"  = Position.makeXYZ("
					+(f.Fm6(Units.to("NM",p.x()))+", "
							+f.Fm6(Units.to("NM",p.y()))+", "
							+f.Fm6(Units.to("ft",p.z()))+");");
		}
		return rtn;
	}

	public static String velocityAsUnitTest(Velocity v, int j) {
		String rtn = "";
		rtn = "  Velocity v"+j+"  = Velocity.mkTrkGsVs("  +f.Fm6(v.trk())+", "+f.Fm6(v.gs())+", "+f.Fm6(v.vs())+"); ";
		return rtn;
	}



	public static void dumpSimplePolyAsUnitTest(SimplePoly p, int i) {					
		f.pln(" SimplePoly sPoly"+i+" = new SimplePoly("+p.getBottom()+","+p.getTop()+");   ");
		for (int j = 0; j < p.size(); j++) {
			Position np = p.getVertex(j);
			int ix = 100*i+j;
			f.p(positionAsUnitTest(np,ix));
			f.pln("     sPoly"+i+".addVertex(p"+ix+");");
		}
	}

	public static void dumpPolyPathAsUnitTest(PolyPath pp0, String str) {
		f.pln(" PolyPath pp = new PolyPath(\""+str+"\");");
		for (int j = 0; j < pp0.size(); j++) {
			//DebugSupport.dumpSimplePoly(pp0.getPoly(j),"polypath_"+j);
			DebugSupport.dumpSimplePolyAsUnitTest(pp0.getPoly(j),j);
		}
		for (int j = 0; j < pp0.size(); j++) {
			Velocity vj = pp0.initialVelocity(j);
			f.pln(velocityAsUnitTest(vj,j));
			f.pln(" pp.addPolygon(sPoly"+j+",v"+j+","+pp0.getTime(j)+"); ");
		}
		f.pln(" pp.setPathMode(PolyPath.PathMode."+pp0.getPathMode()+");");
	}
	
	
//	public static void dumpPolyPath(PolyPath pp0, String str) {
//		PrintWriter dumpFile;
//		String  dumpFileName = "1dump_"+str+".txt";
//		try {
//			// Create the output stream.
//			dumpFile = new PrintWriter(new FileWriter(dumpFileName));
//		}
//		catch (IOException e) {
//			System.out.println("Can't open file " + dumpFileName + "!");
//			System.out.println("Error: " + e);
//			return;        // End the program.
//		}
//		//f.pln(" dumpPolyPath pp = new PolyPath(\"Weather\");  pp0.size() = "+pp0.size());
//		dumpFile.println("pathMode = "+pp0.getPathMode());
//		for (int j = 0; j < pp0.size(); j++) {
//			dumpFile.print(SimplePolyToOutput(pp0.getPoly(j),"polypath_"+j));
//		}
//		for (int j = 0; j < pp0.size(); j++) {
//			Velocity vj = pp0.initialVelocity(j);
//			//f.pln(velocityAsUnitTest(vj,j));
//			//f.pln(" pp.addPolygon(sPoly"+j+",v"+j+","+pp0.getTime(j)+"); ");
//		}
//		f.pln(" pp.setPathMode(PolyPath.PathMode."+pp0.getPathMode()+");");
//		dumpFile.close();
//	}

	public static String polyPathToOutput(PolyPath pp) {	
		String headerAlt2 = ", alt2";
		String s = "";
		s = s + "pathMode = "+pp.getPathMode()+"\n";
		s += "Name, ";
		if (pp.isLatLon()) {
			s += "Lat, Lon, Alt ";
		} else {
			s += "SX, SY, SZ";
		}
		s += ", Time, Label, trk, gs, vs";
		s += headerAlt2+"\n";
		s = s  + pp.toOutput(); //str, 4, false);
        return s;
	}

	
	public static void dumpPolyPath(PolyPath pp0, String str) {
		PrintWriter dumpFile;
		String  dumpFileName = "1dump_"+str+".txt";
		try {
			// Create the output stream.
			dumpFile = new PrintWriter(new FileWriter(dumpFileName));
		}
		catch (IOException e) {
			System.out.println("Can't open file " + dumpFileName + "!");
			System.out.println("Error: " + e);
			return;        // End the program.
		}
		//f.pln(" dumpPolyPath pp = new PolyPath(\"Weather\");  pp0.size() = "+pp0.size());
		dumpFile.print(polyPathToOutput(pp0));
		dumpFile.close();
		f.pln(" ............. dumpPolyPath: created file "+dumpFileName);
	}
	
	public static void dumpPolyPaths(ArrayList<PolyPath> pps, String str) {
		PrintWriter dumpFile;
		String  dumpFileName = "1dump_"+str+".txt";
		try {
			// Create the output stream.
			dumpFile = new PrintWriter(new FileWriter(dumpFileName));
		}
		catch (IOException e) {
			System.out.println("Can't open file " + dumpFileName + "!");
			System.out.println("Error: " + e);
			return;        // End the program.
		}
		//f.pln(" dumpPolyPath pp = new PolyPath(\"Weather\");  pp0.size() = "+pp0.size());
		for (int j= 0; j < pps.size(); j++) {
		    if (j == 0) dumpFile.print(polyPathToOutput(pps.get(j)));
		    else dumpFile.println(pps.get(j).toOutput());
		}
		dumpFile.close();
		f.pln(" ............. dumpPolyPaths: created file "+dumpFileName);
	}



	/** Write the plan to the given file */
	public static void dumpConflictState(Position sop, Velocity vop, Position sip, Velocity vip, String dumpFileName) {//TODO: use PlanWriter
		PrintWriter dumpFile;
		try {
			dumpFile = new PrintWriter(new FileWriter(dumpFileName));
		}
		catch (IOException e) {
			System.out.println("Can't open file " + dumpFileName + "!");
			System.out.println("Error: " + e);
			return;        // End the program.
		}	
		dumpFile.println("NAME     lat   lon      alt     trk   gs     vs       time");
		dumpFile.println("own "+sop.toStringNP(8)+" "+vop.toStringNP(8)+" 0");
		dumpFile.println("traf"+sip.toStringNP(8)+" "+vip.toStringNP(8)+" 0");
		dumpFile.close();
		f.pln(" ............. dumpConflictState: created file "+dumpFileName);
	}

	public static void dumpAsUnitTest(Plan plan) {
		dumpAsUnitTest(plan,false);
	}
	
	

	public static void dumpAsUnitTest(Plan plan, boolean asserts) {
		for (int j = 0; j < plan.size(); j++) {
			//			if (plan.isLatLon()) {
			//				f.p("  Position p"+j+"  = new Position(LatLonAlt.make(");
			//				f.pln(f.Fm6(Units.to("deg",plan.point(j).lat()))
			//						+", "+f.Fm6(Units.to("deg",plan.point(j).lon()))
			//						+", "+f.Fm6(Units.to("ft",plan.point(j).alt()))+"));");
			//			} else {
			//				f.p("  Position p"+j+"  = Position.makeXYZ(");
			//				f.pln(f.Fm6(Units.to("NM",plan.point(j).x()))+", "
			//			         +f.Fm6(Units.to("NM",plan.point(j).y()))+", "
			//					 +f.Fm6(Units.to("ft",plan.point(j).z()))+");");
			//			}			
			f.pln(pointAsUnitTest(plan.point(j),j));			
		}
		for (int j = 0; j < plan.size(); j++) {
			NavPoint np = plan.point(j);
			if (np.isTCP()) {				
				Velocity vin = np.velocityIn();
				f.pln("  Velocity vin"+j+" = Velocity.mkTrkGsVs("+f.Fm6(vin.trk())+","+f.Fm6(vin.gs())+","+f.Fm6(vin.vs())+");");
				f.pln("  NavPoint src"+j+" =  NavPoint.makeLatLonAlt("+np.sourcePosition().toStringNP(8)+", "+f.Fm6(np.sourceTime())+");");
				if (np.isBOT()) {
					f.p("  NavPoint np"+j+" = src"+j+".makeBOT(p"+j+", "+f.Fm6(np.time())+", "+f.Fm6(np.trkAccel())+", vin"+j+");");
				} else if (np.isEOT()) {
					f.p("  NavPoint np"+j+" = src"+j+".makeEOT(p"+j+", "+f.Fm6(np.time())+", vin"+j+");");				
				} else if (np.isBGS()) {
					f.p("  NavPoint np"+j+" = src"+j+".makeBGS(p"+j+", "+f.Fm6(np.time())+", "+f.Fm6(np.gsAccel())+", vin"+j+");");				
				} else if (np.isEGS()) {
					f.p("  NavPoint np"+j+" = src"+j+".makeEGS(p"+j+", "+f.Fm6(np.time())+", vin"+j+");");				
				} else if (np.isBVS()) {
					f.p("  NavPoint np"+j+" = src"+j+".makeBVS(p"+j+", "+f.Fm6(np.time())+", "+f.Fm6(np.vsAccel())+", vin"+j+");");				
				} else if (np.isEVS()) {
					f.p("  NavPoint np"+j+" = src"+j+".makeEVS(p"+j+", "+f.Fm6(np.time())+", vin"+j+");");				

				} else {
					f.p("  NavPoint np"+j+" = NavPoint.make???()");
				}
			} else {
				String label = np.label();
				if (label.equals(""))
				   f.p("  NavPoint np"+j+" = new NavPoint(p"+j+","+f.Fm6(np.time())+");");
				else
				   f.p("  NavPoint np"+j+" = new NavPoint(p"+j+","+f.Fm6(np.time())+").makeLabel(\""+label+"\");");
			}
			f.pln("    \t lpc.add(np"+j+");");
		}
		if (asserts) {
			f.pln(" assertEquals("+plan.size()+",plan.size());");
			for (int j = 0; j < plan.size(); j++) {
				NavPoint np = plan.point(j);
 				f.pln(" assertEquals(plan.point("+j+").time(), "+f.Fm4(np.time())+", 0.001);");
				f.pln(" assertEquals(plan.point("+j+").lat(), "+f.Fm4(np.lat())+", 0.001);");
				f.pln(" assertEquals(plan.point("+j+").lon(), "+f.Fm4(np.lon())+", 0.001);");
				f.pln(" assertEquals(plan.point("+j+").alt(), "+f.Fm4(np.alt())+", 0.001);");
				if (np.isBOT())	f.pln(" assertTrue(plan.point("+j+").isBOT());");
				if (np.isEOT())	f.pln(" assertTrue(plan.point("+j+").isEOT());");
				if (np.isBGS())	f.pln(" assertTrue(plan.point("+j+").isBGS());");
				if (np.isEGS())	f.pln(" assertTrue(plan.point("+j+").isEGS());");
				if (np.isBVS())	f.pln(" assertTrue(plan.point("+j+").isBVS());");
				if (np.isEVS())	f.pln(" assertTrue(plan.point("+j+").isEVS());");
			}
		}
	}



	public static List<String> get_header(SeparatedInput si) {
		ArrayList<String> ret = new ArrayList<String>(si.size()*2);
		for (int i = 0; i < si.size(); i++) {
			ret.add(si.getHeading(i).toUpperCase());
			ret.add(si.getUnit(i));
		}
		return ret;
	}

	public static List<String> get_line(SeparatedInput si) {
		ArrayList<String> ret = new ArrayList<String>(si.size());
		for (int i = 0; i < si.size(); i++) {
			ret.add(si.getColumnString(i).toUpperCase());
		}
		return ret;
	}

	/** 
	 * Reads in a plan file stored in file "fileName" and creates a new file with just the data for
	 *  aircraft "aircraftName".  The output file name is  "dump_"+aircraftName+str+".txt".
	 */
	public static void dumpAircraftFromFile(String fileName, String aircraftName, String str, boolean sorted) {		
		FileReader inFile;
		StateWriter dumpFile;
		String dumpFileName = "dump_"+aircraftName+str+".txt";
		dumpFile = new StateWriter();
		dumpFile.open(dumpFileName);
		dumpFile.setVelocity(false);
		if (dumpFile.hasError()) {
			f.dln(dumpFile.getMessage());
			dumpFile.close();
			return;
		}
		SeparatedInput execFile = new SeparatedInput();
		boolean latlon = true;
		int[] head = {-1,-2,-3,-4,-5,-6,-7, -8}; // array size of number of elements (vector in C++)
		// we store the heading indices in the following order:
		final int NAME = 0;
		final int LAT_SX = 1;
		final int LON_SY = 2;
		final int ALT_SZ = 3;
		final int TM_CLK = 7;
		try {    
			inFile = new FileReader(fileName);
			execFile = new SeparatedInput(inFile);
			execFile.setCaseSensitive(false);            // headers & parameters are lower case
			boolean hasRead = false;
			List<String> lastLine = null;
			Position lastPos = Position.ZERO_LL;
			double lastTm = 0;
			while ( ! execFile.readLine()) {
				if ( ! hasRead) {
					latlon = (execFile.findHeading("lat", "lon", "long", "latitude") >= 0);
					head[NAME] =   execFile.findHeading("name", "aircraft", "id");
					head[LAT_SX] = execFile.findHeading("sx", "lat", "latitude");
					head[LON_SY] = execFile.findHeading("sy", "lon", "long", "longitude");
					head[ALT_SZ] = execFile.findHeading("sz", "alt", "altitude");
					head[TM_CLK] = execFile.findHeading("time", "tm", "st");
					//					dumpFile.addHeading(get_header(execFile));
					//					if (sorted) {
					//						dumpFile.addHeading("trk","deg");
					//						dumpFile.addHeading("gs","kn");
					//						dumpFile.addHeading("vs","fpm");
					//					}
					hasRead = true;
				}
				String acName = execFile.getColumnString(head[NAME]);
				if (acName.equals(aircraftName)) {
					List<String> currentLine = get_line(execFile);
					double tm = execFile.getColumn(head[TM_CLK], "s");
					if (sorted) {
						Position pos;
						if (latlon) {
							pos = new Position(LatLonAlt.mk(execFile.getColumn(head[LAT_SX], "deg"), 
									execFile.getColumn(head[LON_SY], "deg"), 
									execFile.getColumn(head[ALT_SZ], "ft")));
						} else {
							pos = new Position(new Vect3(execFile.getColumn(head[LAT_SX], "nmi"), 
									execFile.getColumn(head[LON_SY], "nmi"), 
									execFile.getColumn(head[ALT_SZ], "ft")));
						}
						if ( lastLine != null) {
							double dt = tm - lastTm;
							if (dt < 0) f.pln("DEBUGSUPPORT $$$$$$$$$$$$$$$$$$$$$$$$$$ AGH dt < 0");
							Velocity v = lastPos.initialVelocity(pos,dt);
							//							dumpFile.addColumn(lastLine);
							//							dumpFile.addColumn(v.toStringList());
							//							dumpFile.writeLine();
							dumpFile.writeState(execFile.getColumnString(head[NAME]),lastTm,lastPos,v);
						}
						lastLine = currentLine;
						lastPos = pos;
						lastTm = tm;
					} else {
						dumpFile.writeState(execFile.getColumnString(head[NAME]),lastTm,lastPos);
						//						dumpFile.addColumn(currentLine);
						//						dumpFile.writeLine();
					}
				}

			}	// while
			inFile.close();
		} catch (IOException e) {
			f.pln("File "+fileName+" read protected or not found");
			System.exit(0);
		}
		dumpFile.close();
		//f.pln(" ............. dumpAircraftFromFile: created file "+dumpFileName);
	}

	/** 
	 * Reads in a plan file from the file "fileName" and creates a new file with an estimate of the 
	 * velocity at each point.  The output file name is  fileName+"Vel.txt".
	 */
	public static void dumpFileWithVelocities(String fileName) {
		FileReader inFile;
		StateWriter outFile;
		String  outFileName = fileName+".Vel.txt";
		outFile = new StateWriter();
		outFile.open(outFileName);
		if (outFile.hasError()) {
			f.dln(outFile.getMessage());
			outFile.close();
			return;
		}
		SeparatedInput execFile = new SeparatedInput();
		boolean latlon = true;
		int[] head = {-1,-2,-3,-4,-5,-6,-7, -8}; // array size of number of elements (vector in C++)
		// we store the heading indices in the following order:
		final int NAME = 0;
		final int LAT_SX = 1;
		final int LON_SY = 2;
		final int ALT_SZ = 3;
		final int TM_CLK = 7;
		try {    
			inFile = new FileReader(fileName);
			execFile = new SeparatedInput(inFile);
			execFile.setCaseSensitive(false);            // headers & parameters are lower case
			boolean hasRead = false;
			List<String> currentline = null;
			List<String> lastline = null;
			Position lastPos = Position.ZERO_LL;
			double lastTm = 0;
			while ( ! execFile.readLine()) {
				if (!hasRead) {
					latlon = (execFile.findHeading("lat", "lon", "long", "latitude") >= 0);
					head[NAME] =   execFile.findHeading("name", "aircraft", "id");
					head[LAT_SX] = execFile.findHeading("sx", "lat", "latitude");
					head[LON_SY] = execFile.findHeading("sy", "lon", "long", "longitude");
					head[ALT_SZ] = execFile.findHeading("sz", "alt", "altitude");
					head[TM_CLK] = execFile.findHeading("time", "tm", "st");
					//					outFile.addHeading(get_header(execFile));
					//					outFile.addHeading("trk","deg");
					//					outFile.addHeading("gs","kn");
					//					outFile.addHeading("vs","fpm");
					hasRead = true;
				}
				double tm = execFile.getColumn(head[TM_CLK], "s");
				Position pos;
				if (latlon) {
					pos = new Position(LatLonAlt.mk(execFile.getColumn(head[LAT_SX], "deg"), 
							execFile.getColumn(head[LON_SY], "deg"), 
							execFile.getColumn(head[ALT_SZ], "ft")));
				} else {
					pos = new Position(new Vect3(execFile.getColumn(head[LAT_SX], "nmi"), 
							execFile.getColumn(head[LON_SY], "nmi"), 
							execFile.getColumn(head[ALT_SZ], "ft")));
				}
				currentline = get_line(execFile);
				if ( lastline != null) {
					double dt = tm - lastTm;
					if (dt < 0) f.pln("DEBUGSUPPORT $$$$$$$$$$$$$$$$$$$$$$$$$$ AGH dt < 0");
					Velocity v = lastPos.initialVelocity(pos,dt);
					//					outFile.addColumn(lastline);
					//					outFile.addColumn(v.toStringList());
					//					outFile.writeLine();
					outFile.writeState(execFile.getColumnString(head[NAME]), tm, pos, v,"");
				}
				lastline = currentline;
				lastPos = pos;
				lastTm = tm;
			}	// while
			inFile.close();
		} catch (IOException e) {
			f.pln("File "+fileName+" read protected or not found");
			System.exit(0);
		}
		outFile.close();
		f.pln(" ............. dumpFileWithVelocities: created file "+outFileName);
	}

	
	public static void dumpPolyScenerio(Plan own, PolyPath pp, boolean tcps, String fileName) {
		PrintWriter dumpFile;
		fileName = "1dump_"+fileName;
		try {
			dumpFile = new PrintWriter(new FileWriter(fileName));
		}
		catch (IOException e) {
			System.out.println("Can't open file " + fileName + "!");
			System.out.println("Error: " + e);
			return;        // End the program.
		}
		dumpFile.println(own.getOutputHeader(tcps)+", alt2");

		if (own.getName().equals("")) {
			own.setName("own");
		}
		String s = own.toOutput(0, 12, tcps, false);
		dumpFile.println(s);

		if (pp.getName().equals("")) {
			pp.setName("poly");
		}
		s = pp.toOutput(6,tcps);
		dumpFile.println(s);
		dumpFile.close();
		f.pln(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+fileName+" written!");	
	}
	
	public static void halt(String str) {
		System.out.println(str);
		if (Constants.allowDebugHalt) {
			Thread.dumpStack();
			System.exit(1);
		}
	}

	public static void halt() {
		halt("");	
	}

}
