/**
 * Copyright (c) 2014, NetIDE Consortium (Create-Net (CN), Telefonica Investigacion Y Desarrollo SA (TID), Fujitsu 
 * Technology Solutions GmbH (FTS), Thales Communications & Security SAS (THALES), Fundacion Imdea Networks (IMDEA),
 * Universitaet Paderborn (UPB), Intel Research & Innovation Ireland Ltd (IRIIL) )
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Authors:
 *     ...
 */
package net.floodlightcontroller.interceptor;

import net.floodlightcontroller.packet.Ethernet;
import net.floodlightcontroller.packet.IPv4;

import org.json.JSONObject;
import org.openflow.protocol.OFFlowMod;
import org.openflow.protocol.OFPacketOut;
import org.openflow.protocol.OFStatisticsRequest;
import org.openflow.protocol.action.OFAction;
import org.openflow.protocol.action.OFActionDataLayerDestination;
import org.openflow.protocol.action.OFActionDataLayerSource;
import org.openflow.protocol.action.OFActionNetworkLayerDestination;
import org.openflow.protocol.action.OFActionNetworkLayerSource;
import org.openflow.protocol.action.OFActionNetworkTypeOfService;
import org.openflow.protocol.action.OFActionOutput;
import org.openflow.protocol.action.OFActionTransportLayer;
import org.openflow.protocol.statistics.OFStatisticsType;
import org.openflow.util.U8;

/**
 * Describe your class here...
 *
 * @author aleckey
 *
 */
public class MessageSerializer {

	public MessageSerializer() { }
	
	public static String serializeMessage(long switchID, OFStatisticsRequest statsRequest) {
		String retString="";
		switch (statsRequest.getStatisticType()) {
			case DESC: 
				break;
			case FLOW:
				retString = "[\"flow_stats_request\", 3]" + "\n";
				break;
			case AGGREGATE:
			case PORT:
			case TABLE:
			case VENDOR:
			case QUEUE:
		}
		return retString;
	}
	
	public static String serializeMessage(long switchID, OFPacketOut packetOut) {
		//["packet", {"outport": 1, "protocol": 2, "header_len": 14, "inport": 2, 
		// "dstip": [49, 48, 46, 48, 46, 48, 46, 49], 
		// "srcmac": [99, 101, 58, 97, 56, 58, 100, 100, 58, 99, 102, 58, 49, 99, 58, 97, 101], "dstmac": [99, 101, 58, 97, 54, 58, 99, 51, 58, 100, 100, 58, 56, 57, 58, 99, 51], 
		// "raw": [206, 166, 195, 221, 137, 195, 206, 168, 221, 207, 28, 174, 8, 6, 0, 1, 8, 0, 6, 4, 0, 2, 206, 168, 221, 207, 28, 174, 10, 0, 0, 2, 206, 166, 195, 221, 137, 195, 10, 0, 0, 1], 
		// "payload_len": 42, "switch": 1, "ethtype": 2054, "srcip": [49, 48, 46, 48, 46, 48, 46, 50] }] + TERM_CHAR  
		JSONObject json = new JSONObject();
		json.put("switch", switchID); 
		json.put("buf", packetOut.getBufferId());
		json.put("inport", packetOut.getInPort());
		json.put("raw", packetOut.getPacketData());
		json.put("header_len", packetOut.getActionsLength());
		JSONObject[] actionArray = new JSONObject[packetOut.getActions().size()];
		for (int i=0; i<packetOut.getActions().size(); i++) {
			actionArray[i] = getAction(packetOut.getActions().get(i));
			
		}
		json.put("actions", actionArray);
		
		StringBuilder sb = new StringBuilder("[\"packet\", ");
		sb.append(json.toString());
		sb.append("]\n");
		return sb.toString();
	}

	public static String serializeMessage(long switchID, OFFlowMod flowMod) {
		//["install",  
		//  {"dstip": [49, 48, 46, 48, 46, 48, 46, 49], "srcip": [49, 48, 46, 48, 46, 48, 46, 50],
		//   "dstmac": [54, 97, 58, 50, 100, 58, 55, 102, 58, 50, 57, 58, 99, 56, 58, 54, 49], "srcmac": [49, 50, 58, 57, 51, 58, 50, 99, 58, 52, 97, 58, 52, 56, 58, 50, 52],
		//   "dstport": 0, "srcport": 0
		//   "protocol": 1,  "tos": 0, "inport": 1, "switch": 2, "ethtype": 2048}, 0, [{"outport": 2}],]
		
		JSONObject json = new JSONObject();
		for (OFAction action: flowMod.getActions()) {
			switch (action.getType()) {
				case SET_DL_DST:
					json.put("dstmac", flowMod.getMatch().getDataLayerDestination());
					break;
				case SET_DL_SRC:
					json.put("srcmac", flowMod.getMatch().getDataLayerSource());
					break;
				case SET_NW_DST:
					//String ipDst = cidrToString(flowMod.getMatch().getNetworkDestination(), flowMod.getMatch().getNetworkDestination());
					String ipDst = IPv4.fromIPv4Address(flowMod.getMatch().getNetworkDestination());
					json.put("dstip", ipDst.getBytes());
					break;
				case SET_NW_SRC:
					//String ipSrc = cidrToString(flowMod.getMatch().getNetworkSource(), flowMod.getMatch().getNetworkSource());
					String ipSrc = IPv4.fromIPv4Address(flowMod.getMatch().getNetworkSource());
					json.put("srcip", ipSrc.getBytes());
					break;
				case SET_TP_DST:
					json.put("dstport", flowMod.getMatch().getTransportDestination());
					break;
				case SET_TP_SRC:
					json.put("srcport", flowMod.getMatch().getTransportSource());
					break;
				case SET_NW_TOS:
					json.put("tos", flowMod.getMatch().getNetworkTypeOfService());
					break;
				default:
			}
		}
		json.put("switch", switchID);
		json.put("inport", flowMod.getMatch().getInputPort());
		json.put("nw_proto", Integer.parseInt(Byte.toString(flowMod.getMatch().getNetworkProtocol())));
		json.put("ethtype", flowMod.getMatch().getDataLayerType());	
		
		StringBuilder sb = new StringBuilder("[\"");
		//COMMAND
		switch (flowMod.getCommand()) {
			case OFFlowMod.OFPFC_ADD: 
				sb.append("install"); 
				break;
			case OFFlowMod.OFPFC_DELETE: 
				sb.append("delete"); 
				break;
			case OFFlowMod.OFPFC_MODIFY: 
				sb.append("modify"); 
				break;
			default:
		}
		sb.append("\", ");
		//MATCH
		sb.append(json.toString());
		//PRIORITY
		sb.append(", ").append(flowMod.getPriority());
		//ACTION LIST
		String[] actionArray = new String[flowMod.getActions().size()];
		for (int i=0; i<flowMod.getActions().size(); i++) {
			actionArray[i] = flowMod.getActions().get(i).getType().toString();
		}
//		JSONArray jArray = new JSONArray(actionArray);
//		sb.append(", ")
//		  .append(jArray)
//		  .append("");
		
		sb.append(", [{\"outport\": ")
		  .append(flowMod.getOutPort())
		  .append("}]");
		sb.append("]\n");
		return sb.toString();
	}
	
	public static JSONObject getAction(OFAction action) {
		JSONObject json = new JSONObject();
		switch (action.getType()) {
			case OUTPUT:
				json.append("outport", ((OFActionOutput)action).getPort());
				break;
			case SET_DL_DST:
				json.append("dstmac", ((OFActionDataLayerDestination)action).getDataLayerAddress());
				break;
			case SET_DL_SRC:
				json.append("srcmac", ((OFActionDataLayerSource)action).getDataLayerAddress());
				break;
			case SET_NW_DST:
				int ipDst = ((OFActionNetworkLayerDestination)action).getNetworkAddress();
				json.append("dstip", IPv4.toIPv4AddressBytes(ipDst));
				break;
			case SET_NW_SRC:
				int ipSrc = ((OFActionNetworkLayerSource)action).getNetworkAddress();
				json.append("dstmac", IPv4.toIPv4AddressBytes(ipSrc));
				break;
			case SET_NW_TOS:
				json.append("tos", ((OFActionNetworkTypeOfService)action).getNetworkTypeOfService());
			case SET_TP_DST:
				json.append("dstport", ((OFActionTransportLayer)action).getTransportPort());
				break;
			case SET_TP_SRC:
				json.append("srcport", ((OFActionTransportLayer)action).getTransportPort());
				break;
			case SET_VLAN_ID:
			case SET_VLAN_PCP:
			case STRIP_VLAN:
			case VENDOR:
			case OPAQUE_ENQUEUE:
		}
		return json;
	}
	
	public static String cidrToString(int ip, int prefix) {
        String str;
        if (prefix >= 32) {
            str = ipToString(ip);
        } else {
            // use the negation of mask to fake endian magic
            int mask = ~((1 << (32 - prefix)) - 1);
            str = ipToString(ip & mask) + "/" + prefix;
        }

        return str;
	}
	public static String ipToString(int ip) {
        return Integer.toString(U8.f((byte) ((ip & 0xff000000) >> 24))) + "."
                + Integer.toString((ip & 0x00ff0000) >> 16) + "."
                + Integer.toString((ip & 0x0000ff00) >> 8) + "."
                + Integer.toString(ip & 0x000000ff);
    }
	
}