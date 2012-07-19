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

case class Cdr(id: Option[Long] = None,
               connectionId: String,
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

import FeatureName._
import FeatureStatus._
import ForwardingReason._
case class CdrVsa(cdrVsaId: Option[Long] = None,
                  connectionId: String,
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
