package nl.tecon.cme.client.syslog.parser.entityparser;

import nl.tecon.cme.client.common.DateParserUtil;
import nl.tecon.cme.common.domain.cdr.Cdr;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/15/10 - 11:51 PM
 */
public enum CdrHistoryDefinition implements SysLogColumnDefinition<Cdr>
{
    CALL_LEG_TYPE("CallLegType")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setCallLegType(Integer.parseInt(value));
                }
            },
    CONNECTION_ID("ConnectionId")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setConnectionId(value);
                }
            },
    SETUP_TIME("SetupTime")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setSetUpTime(DateParserUtil.parseDateAndTime(value));
                }
            },
    PEER_ADDRESS("PeerAddress")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setPeerAddress(value);
                }
            },
    PEER_SUBADDRESS("PeerSubAddress")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setPeerSubAddress(value);
                }
            },
    DISCONNECT_CAUSE("DisconnectCause")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setDisconnectCause(value);
                }
            },
    DISCONNECT_TEXT("DisconnectText")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setDisconnectText(value);
                }
            },
    CONNECT_TIME("ConnectTime")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setConnectTime(DateParserUtil.parseDateAndTime(value));
                }
            },
    DISCONNECT_TIME("DisconnectTime")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setDisconnectTime(DateParserUtil.parseDateAndTime(value));
                }
            },
    CALL_ORIGIN("CallOrigin")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setCallOrigin(value);
                }
            },
    CHARGED_UNITS("ChargedUnits")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setChargedUnits(value);
                }
            },
    INFO_TYPE("InfoType")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setInfoType(value);
                }
            },
    TRANSMIT_PACKETS("TransmitPackets")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setTransmitPackets(Long.parseLong(value));
                }
            },
    TRANSMIT_BYTES("TransmitBytes")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setTransmitBytes(Long.parseLong(value));
                }
            },
    RECEIVE_PACKETS("ReceivePackets")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setReceivedPackets(Long.parseLong(value));
                }
            },
    RECEIVE_BYTES("ReceiveBytes")
            {
                @Override
                public void setValue(String value, Cdr entity)
                {
                    entity.setReceivedBytes(Long.parseLong(value));
                }
            };

    private String key;

    CdrHistoryDefinition(String key)
    {
        this.key = key;
    }

    public String getKey()
    {
        return key;
    }

}
