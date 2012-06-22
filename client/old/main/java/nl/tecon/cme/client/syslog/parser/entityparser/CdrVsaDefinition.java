package nl.tecon.cme.client.syslog.parser.entityparser;

import nl.tecon.cme.client.common.DateParserUtil;
import nl.tecon.cme.common.domain.cdr.CdrVsa;
import nl.tecon.cme.common.domain.cdr.FeatureName;
import nl.tecon.cme.common.domain.cdr.FeatureStatus;
import nl.tecon.cme.common.domain.cdr.ForwardingReason;

/**
 * @author thies (thies@te-con.nl)
 *         Date: 12/23/10 12:01 AM
 */
public enum CdrVsaDefinition implements SysLogColumnDefinition<CdrVsa>
{
    FEATURE_NAME("fn")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setName(FeatureName.valueOf(value));
                }
            },
    FEATURE_TIME("ft")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setFeatureTime(DateParserUtil.parseDateAndTimeNoTimeZone(value));
                }
            },
    LEG_ID("legID")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setLegId(value);
                }
            },
    FORWARDING_REASON("frson")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setForwardingReason(ForwardingReason.rawValueFor(value));
                }
            },
    FEATURE_STATUS("frs")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setStatus(FeatureStatus.rawValueFor(value));
                }
            },
    FEATURE_ID("fid")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setFeatureId(Long.parseLong(value));
                }
            },
    CONNECTION_ID("fcid")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setConnectionId(value);
                }
            },
    FORWARDED_NUMBER("fwdee")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setForwardedNumber(value);
                }
            },
    FORWARD_SOURCE_NUMBER("fwder")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setForwardSourceNumber(value);
                }
            },
    FORWARD_TO_NUMBER("fwdto")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setForwardToNumber(value);
                }
            },
    FORWARD_FROM_NUMBER("frm")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setForwardFromNumber(value);
                }
            },
    CALLED_NUMBER("cdn")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setCalledNumber(value);
                }
            },
    CALLING_NUMBER("cgn")
            {
                @Override
                public void setValue(String value, CdrVsa entity)
                {
                    entity.setCallingNumber(value);
                }
            };

    private String key;

    CdrVsaDefinition(String key)
    {
        this.key = key;
    }

    public String getKey()
    {
        return key;
    }
}
