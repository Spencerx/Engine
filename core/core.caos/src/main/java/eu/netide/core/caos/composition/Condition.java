
package eu.netide.core.caos.composition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


/**
 * Class for representing filters.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Condition", namespace = "http://netide.eu/schemas/compositionspecification/v1")
public class Condition {

    protected List<Events> events; /* Trigger event */
    protected String IN_PORT; /* Switch input port. */
    protected String ETH_DST; /* Ethernet destination address. */
    protected String ETH_SRC; /* Ethernet source address. */
    protected String ETH_TYPE; /* Ethernet frame type. */
    protected String IP_PROTO; /* IP protocol. */
    protected String IPV4_SRC; /* IPv4 source address. */
    protected String IPV4_DST; /* IPv4 destination address. */
    protected String TCP_SRC; /* TCP source port. */
    protected String TCP_DST; /* TCP destination port. */
    protected String UDP_SRC; /* UDP source port. */
    protected String UDP_DST; /* UDP destination port. */
    protected String IPV6_SRC; /* IPv6 source address. */
    protected String IPV6_DST; /* IPv6 destination address. */
    // IN_PHY_PORT; /* Switch physical input port. */
    // METADATA; /* Metadata passed between tables. */
    // VLAN_VID; /* VLAN id. */
    // VLAN_PCP; /* VLAN priority. */
    // IP_DSCP; /* IP DSCP (6 bits in ToS field). */
    // IP_ECN; /* IP ECN (2 bits in ToS field). */
    // SCTP_SRC; /* SCTP source port. */
    // SCTP_DST; /* SCTP destination port. */
    // ICMPV4_TYPE; /* ICMP type. */
    // ICMPV4_CODE; /* ICMP code. */
    // ARP_OP; /* ARP opcode. */
    // ARP_SPA; /* ARP source IPv4 address. */
    // ARP_TPA; /* ARP target IPv4 address. */
    // ARP_SHA; /* ARP source hardware address. */
    // ARP_THA; /* ARP target hardware address. */
    // IPV6_FLABEL; /* IPv6 Flow Label */
    // ICMPV6_TYPE; /* ICMPv6 type. */
    // ICMPV6_CODE; /* ICMPv6 code. */
    // IPV6_ND_TARGET; /* Target address for ND. */
    // IPV6_ND_SLL; /* Source link-layer for ND. */
    // IPV6_ND_TLL; /* Target link-layer for ND.
    // MPLS_LABEL; /* MPLS label. */
    // MPLS_TC; /* MPLS TC. */
    // MPLS_BOS; /* MPLS BoS bit. */
    // PBB_ISID; /* PBB I-SID. */
    // TUNNEL_ID; /* Logical Port Metadata. */
    // IPV6_EXTHDR; /* IPv6 Extension Header pseudo-field */

    @XmlAttribute(name = "events")
    public List<Events> getEvents() {
        return this.events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }

    @XmlAttribute(name = "inPort")
    public String getIN_PORT() {
        return IN_PORT;
    }

    public void setIN_PORT(String IN_PORT) {
        this.IN_PORT = IN_PORT;
    }

    @XmlAttribute(name = "ethDst")
    public String getETH_DST() {
        return ETH_DST;
    }

    public void setETH_DST(String ETH_DST) {
        this.ETH_DST = ETH_DST;
    }

    @XmlAttribute(name = "ethSrc")
    public String getETH_SRC() {
        return ETH_SRC;
    }

    public void setETH_SRC(String ETH_SRC) {
        this.ETH_SRC = ETH_SRC;
    }

    @XmlAttribute(name = "ethType")
    public String getETH_TYPE() {
        return ETH_TYPE;
    }

    public void setETH_TYPE(String ETH_TYPE) {
        this.ETH_TYPE = ETH_TYPE;
    }

    @XmlAttribute(name = "ipProto")
    public String getIP_PROTO() {
        return IP_PROTO;
    }

    public void setIP_PROTO(String IP_PROTO) {
        this.IP_PROTO = IP_PROTO;
    }

    @XmlAttribute(name = "ipv4Src")
    public String getIPV4_SRC() {
        return IPV4_SRC;
    }

    public void setIPV4_SRC(String IPV4_SRC) {
        this.IPV4_SRC = IPV4_SRC;
    }

    @XmlAttribute(name = "ipv4Dst")
    public String getIPV4_DST() {
        return IPV4_DST;
    }

    public void setIPV4_DST(String IPV4_DST) {
        this.IPV4_DST = IPV4_DST;
    }

    @XmlAttribute(name = "tcpSrc")
    public String getTCP_SRC() {
        return TCP_SRC;
    }

    public void setTCP_SRC(String TCP_SRC) {
        this.TCP_SRC = TCP_SRC;
    }

    @XmlAttribute(name = "tcpDst")
    public String getTCP_DST() {
        return TCP_DST;
    }

    public void setTCP_DST(String TCP_DST) {
        this.TCP_DST = TCP_DST;
    }

    @XmlAttribute(name = "udpSrc")
    public String getUDP_SRC() {
        return UDP_SRC;
    }

    public void setUDP_SRC(String UDP_SRC) {
        this.UDP_SRC = UDP_SRC;
    }

    @XmlAttribute(name = "udpDst")
    public String getUDP_DST() {
        return UDP_DST;
    }

    public void setUDP_DST(String UDP_DST) {
        this.UDP_DST = UDP_DST;
    }

    @XmlAttribute(name = "ipv6Src")
    public String getIPV6_SRC() {
        return IPV6_SRC;
    }

    public void setIPV6_SRC(String IPV6_SRC) {
        this.IPV6_SRC = IPV6_SRC;
    }

    @XmlAttribute(name = "ipv6Dst")
    public String getIPV6_DST() {
        return IPV6_DST;
    }

    public void setIPV6_DST(String IPV6_DST) {
        this.IPV6_DST = IPV6_DST;
    }
}