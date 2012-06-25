package nl.tecon.ucm.domain

import org.joda.time.DateTime

object FeatureName extends Enumeration {
  type FeatureName = Value
  val CFA, CFBY, CFNA, BXFER, CXFER, HOLD, RESUME, TWC = Value
}

object FeatureStatus extends Enumeration {
  type FeatureStatus = Value
  val SUCCESS = Value("1")
  val FAIL = Value("0")
}

object ForwardingReason extends Enumeration {
  type ForwardingReason = Value
  val UNKNOWN = Value("0")
  val CALL_FWD = Value("1")
  val CALL_FWD_BUSY = Value("2")
  val CALL_FWD_NO_REPLY = Value("3")
  val CALL_DEFLECTION = Value("4")
}

abstract class AbstractCdr(connectionId: String)

case class Cdr(connectionId: String,
               cdrId: Long,
               callLegType: Int,
               setUpTime: DateTime,
               peerAddress: String,
               peerSubAddress: String,
               disconnectCause: String,
               disconnectText: String,
               connectTime: DateTime,
               disconnectTime: DateTime,
               callOrigin: String,
               chargedUnits: String,
               infoType: String,
               transmitPackets: Long,
               transmitBytes: Long,
               receivedPackets: Long,
               receivedBytes: Long,
               originalRecord: String) extends AbstractCdr(connectionId)

//object Cdr {
//  def apply(connectionId: String,
//            cdrId: String,
//            callLegType: String,
//            setUpTime: String,
//            peerAddress: String,
//            peerSubAddress: String,
//            disconnectCause: String,
//            disconnectText: String,
//            connectTime: String,
//            disconnectTime: String,
//            callOrigin: String,
//            chargedUnits: String,
//            infoType: String,
//            transmitPackets: String,
//            transmitBytes: String,
//            receivedPackets: String,
//            receivedBytes: String,
//            originalRecord: String) = {
//    Cdr(connectionId = connectionId,
//        cdrId = cdrId.toLong,
//      callLegType = callLegType,
//      setUpTime = setUpTime,
//      peerAddress = peerAddress  ,
//      peerSubAddress = peerSubAddress ,
//      disconnectCause = disconnectCause,
//      disconnectText = disconnectText  ,
//      connectTime = connectTime,
//      disconnectTime = disconnectTime,
//      callOrigin = callOrigin,
//      chargedUnits = chargedUnits,
//      infoType = infoType,
//      transmitPackets = transmitPackets,
//      transmitBytes = transmitBytes,
//      receivedPackets = receivedPackets,
//      receivedBytes = receivedBytes,
//      originalRecord= originalRecord)
//
//  }
//}

import FeatureName._
import FeatureStatus._
import ForwardingReason._
case class CdrVsa(connectionId: String,
                  cdrVsaId: Long,
                  featureId: Long,
                  legId: String,
                  name: FeatureName,
                  forwardFromNumber: String,
                  status: FeatureStatus,
                  featureTime: DateTime,
                  forwardingReason: ForwardingReason,
                  forwardedNumber: String,
                  forwardSourceNumber: String,
                  forwardToNumber: String,
                  calledNumber: String,
                  callingNumber: String,
                  originalRecord: String) extends AbstractCdr(connectionId)
