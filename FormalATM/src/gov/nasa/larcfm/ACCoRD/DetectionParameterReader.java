/*
 * Copyright (c) 2015-2016 United States Government as represented by
 * the National Aeronautics and Space Administration.  No copyright
 * is claimed in the United States under Title 17, U.S.Code. All Other
 * Rights Reserved.
 */
package gov.nasa.larcfm.ACCoRD;

import gov.nasa.larcfm.Util.ParameterData;
import gov.nasa.larcfm.Util.Triple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;

/**
 * This class contains static methods to read in Detection3D and DetectionPolygon instance definitions from a ParameterData object.
 */
public class DetectionParameterReader {
  
  /**
   * This parses parameters involved with Detection3D objects (if present) and returns a list of Detection3D objects loaded, and the one chosen by set_det_core_detection and set_res_core_detection, 
   * if successfully specified.  The list will be sorted by the instance identifiers.
   * 
   * Specifically this looks for parameters:
   * load_core_detection_XXX : create a Detection3D instance of the specified class with label XXX
   * XXX_* : parameters associated with object with label XXX
   * set_det_core_detection : instance for return.second
   * set_res_core_detection : instance for return.third
   * 
   * The user still needs to assign these to the appropriate object(s).
   * If no alternates are loaded, return the empty list for the first part of the pair. 
   * If no det or res core detection is set, return null for the second and/or third part of the pair.
   * @param params
   * @return triple of all Detection3D objects loaded, the "det" Detection3D object, and the "res" Detection3D object
   * Note that the "det" and "res" (if defined) will be references to list entries.
   * 
   * If verbose is false (default true), suppress all status messages except exceptions
   */
  public static Triple<ArrayList<Detection3D>,Detection3D,Detection3D> readCoreDetection(ParameterData params) {
    return readCoreDetection(params,false);
  }
  
  public static Triple<ArrayList<Detection3D>,Detection3D,Detection3D> readCoreDetection(ParameterData params, boolean verbose) {
    ArrayList<Detection3D> cdlist = new ArrayList<Detection3D>();
    Detection3D chosenD = null;
    Detection3D chosenR = null;
    List<String> mlist = params.matchList("load_core_detection_");
    Collections.sort(mlist);
    for (String pname : mlist) {
      String instanceName = pname.substring(20);
      String dname = params.getString(pname);
      try {
        Object obj = Class.forName(dname).newInstance();
        if (obj instanceof Detection3D) {
          Detection3D d = (Detection3D) obj;
          if (verbose) System.out.println(">>>>> Core detection "+dname+" ("+instanceName+") loaded <<<<<");
          ParameterData instpd = params.extractPrefix(instanceName+"_");
          if (instpd.size() > 0) {
            d.setParameters(instpd);
            if (verbose) System.out.println(">>>>> Core detection parameters for "+instanceName+" set <<<<<");
          }
          // set instance identifier if it was not already set explicitly as a parameter
          if (d.getIdentifier().equals("")) {
            d.setIdentifier(instanceName);
          }
          if (params.contains("set_det_core_detection") && params.getString("set_det_core_detection").equalsIgnoreCase(instanceName)) {
            chosenD = d;
//            if (verbose) System.out.println(">>>>> Core detection det set to "+pname+" <<<<<");

          }
          if (params.contains("set_res_core_detection") && params.getString("set_res_core_detection").equalsIgnoreCase(instanceName)) {
            chosenR = d;
//            if (verbose) System.out.println(">>>>> Core detection res set to "+pname+" <<<<<");
          }
          cdlist.add(d);
        } else {
          throw new InstantiationException("Error: "+dname+" is not a known instance of Detection3D");
        }
      } catch (ClassNotFoundException e) {
        System.out.println("Error loading core detection: Class "+dname+" not found on classpath");
      } catch (Exception e) {
        System.out.println("Error loading core detection: "+e);                 
      }
    }
    if (params.contains("set_det_core_detection")  && chosenD == null) {
      if (verbose) System.out.println(">>>>> Core detection "+params.getString("set_det_core_detection")+" does not appear to be loaded, det cannot be set <<<<<");
    }
    if (params.contains("set_res_core_detection")  && chosenR == null) {
      if (verbose) System.out.println(">>>>> Core detection "+params.getString("set_res_core_detection")+" does not appear to be loaded, res cannot be set <<<<<");
    }
    return new Triple<ArrayList<Detection3D>,Detection3D,Detection3D>(cdlist,chosenD,chosenR);
  }

  /**
   * Return a ParameterData suitable to be read by readCoreDetection() based on the supplied Detection3D instances. 
   * @param dlist list of Detection3D instances -- this may be empty.  det and res will automatically be included if they are not already
   * @param det detection instance -- this may be null
   * @param res resolution instance -- this may be null
   * Note: this may modify the instance identifiers if they are not already unique.
   */
  public static ParameterData writeCoreDetection(List<Detection3D> dlist, Detection3D det, Detection3D res) {
    // make sure det and res are in the list, if necessary
    if (det != null && !dlist.contains(det)) {
      dlist.add(det);
    }
    if (res != null && !dlist.contains(res)) {
      dlist.add(res);
    }
    ParameterData p = new ParameterData();
    HashSet<String> names = new HashSet<String>();
    int counter = 1;
    for (Detection3D cd : dlist) {
      //make sure each instance has a unique name
      while (cd.getIdentifier().equals("") || names.contains(cd.getIdentifier())) {
        cd.setIdentifier(cd.getCanonicalClassName()+"_instance_"+counter);
        counter++;
      }
      String id = cd.getIdentifier();
      names.add(id);
      p.set("load_core_detection_"+id+" = "+cd.getCanonicalClassName());
      p.copy(cd.getParameters().copyWithPrefix(id+"_"),true);
    }
    if (det != null) { 
      p.set("set_det_core_detection = "+det.getIdentifier());
    }
    if (res != null) { 
      p.set("set_res_core_detection = "+res.getIdentifier());
    }
    return p;
  }
  
  /**
   * This parses parameters involved with DetectionPolygon objects (if present) and returns a list of DetectionPolygon objects loaded, and the ones chosen by set_det_polygon_detection and set_res_polygon_detection, 
   * if successfully specified.
   * 
   * Specifically this looks for parameters:
   * load_polygon_detection_XXX : create a DetectionPolygon instance of the specified class with label XXX
   * XXX_* : parameters associated with object with label XXX
   * set_det_polygon_detection : instance for return.second
   * set_res_polygon_detection : instance for return.third
   * 
   * The user still needs to assign these to the appropriate object(s).
   * If no alternates are loaded, return the empty list for the first part of the pair. 
   * If no polygon detection is set, return null for the second part of the pair.
   * @param params
   * @return triple of all DetectionPolygon objects loaded, the "det" DetectionPolygon object, and the "res" DetectionPolygon object
   * Note that the "det" and "res" (if defined) will be references to list entries.
   * 
   * If verbose is false (default true), suppress all status messages except exceptions
   */
  public static Triple<ArrayList<DetectionPolygon>,DetectionPolygon,DetectionPolygon> readPolygonDetection(ParameterData params) {
    return readPolygonDetection(params,false);
  }
  
  public static Triple<ArrayList<DetectionPolygon>,DetectionPolygon,DetectionPolygon> readPolygonDetection(ParameterData params, boolean verbose) {
    ArrayList<DetectionPolygon> cdlist = new ArrayList<DetectionPolygon>();
    DetectionPolygon chosenD = null;
    DetectionPolygon chosenR = null;
    List<String> mlist = params.matchList("load_polygon_detection_");
    Collections.sort(mlist);
    for (String pname : mlist) {
      String instanceName = pname.substring(23);
      String dname = params.getString(pname);
      try {
        Object obj = Class.forName(dname).newInstance();
        if (obj instanceof DetectionPolygon) {
          DetectionPolygon d = (DetectionPolygon) obj;
          if (verbose) System.out.println(">>>>> Polygon detection "+dname+" ("+instanceName+") loaded <<<<<");
          ParameterData instpd = params.extractPrefix(instanceName+"_");
          if (instpd.size() > 0) {
            d.setParameters(instpd);
            if (verbose) System.out.println(">>>>> Polygon detection parameters for "+instanceName+" set <<<<<");
          }
          // set instance identifier if it was not already set explicitly as a parameter
          if (d.getIdentifier().equals("")) {
            d.setIdentifier(instanceName);
          }
          if (params.contains("set_det_polygon_detection") && params.getString("set_det_polygon_detection").equalsIgnoreCase(instanceName)) {
            chosenD = d;
//            if (verbose) System.out.println(">>>>> Polygon detection det set to "+instanceName+" <<<<<");

          }
          if (params.contains("set_res_polygon_detection") && params.getString("set_res_polygon_detection").equalsIgnoreCase(instanceName)) {
            chosenR = d;
//            if (verbose) System.out.println(">>>>> Polygon detection res set to "+instanceName+" <<<<<");
          }
          cdlist.add(d);
        } else {
          throw new InstantiationException("Error: "+dname+" is not a known instance of DetectionPolygon");
        }
      } catch (ClassNotFoundException e) {
        System.out.println("Error loading polygon detection: Class "+dname+" not found on classpath");
      } catch (Exception e) {
        System.out.println("Error loading polygon detection: "+e);                 
      }
    }
    if (params.contains("set_det_polygon_detection")  && chosenD == null) {
      if (verbose) System.out.println(">>>>> Polygon detection "+params.getString("set_det_polygon_detection")+" does not appear to be loaded, det cannot be set <<<<<");
    }
    if (params.contains("set_res_polygon_detection")  && chosenR == null) {
      if (verbose) System.out.println(">>>>> Polygon detection "+params.getString("set_res_polygon_detection")+" does not appear to be loaded, res cannot be set <<<<<");
    }
    return new Triple<ArrayList<DetectionPolygon>,DetectionPolygon,DetectionPolygon>(cdlist,chosenD,chosenR);
  } 
  
  
  /**
   * Return a ParameterData suitable to be read by readPolygonDetection() based on the supplied DetectionPolygon instances. 
   * @param dlist list of DetectionPolygon instances -- this may be empty.  det and res will automatically be included if they are not already
   * @param det detection instance -- this may be null
   * @param res resolution instance -- this may be null
   * Note: this may modify the instance identifiers if they are not already unique.
   */
  public static ParameterData writePolygonDetection(List<DetectionPolygon> dlist, DetectionPolygon det, DetectionPolygon res) {
    // make sure det and res are in the list, if necessary
    if (det != null && !dlist.contains(det)) {
      dlist.add(det);
    }
    if (res != null && !dlist.contains(res)) {
      dlist.add(res);
    }
    ParameterData p = new ParameterData();
    HashSet<String> names = new HashSet<String>();
    int counter = 1;
    for (DetectionPolygon cd : dlist) {
      //make sure each instance has a unique name
      while (cd.getIdentifier().equals("") || names.contains(cd.getIdentifier())) {
        cd.setIdentifier(cd.getClassName()+"_instance_"+counter);
        counter++;
      }
      String id = cd.getIdentifier();
      names.add(id);
      p.set("load_polygon_detection_"+id+" = "+cd.getClassName());
      p.copy(cd.getParameters().copyWithPrefix(id+"_"),true);
    }
    if (det != null) { 
      p.set("set_det_polygon_detection = "+det.getIdentifier());
    }
    if (res != null) { 
      p.set("set_res_polygon_detection = "+res.getIdentifier());
    }
    return p;
  }
  
}
