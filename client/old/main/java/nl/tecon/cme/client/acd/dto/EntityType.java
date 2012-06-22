package nl.tecon.cme.client.acd.dto;

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 *         Created on: 12/23/10 - 1:43 PM
 */
public enum EntityType
{
    HUNTGROUP("HuntGp"),
    AGENT("Agent"),
    QUEUE("Queue");

    private String rawValue;

    EntityType(String rawValue)
    {
        this.rawValue = rawValue;
    }

    String getRawValue()
    {
        return rawValue;
    }

    public static EntityType rawValueFor(String rawValue)
    {
        for (EntityType type : EntityType.values())
        {
            if (type.getRawValue().equalsIgnoreCase(rawValue))
            {
                return type;
            }
        }

        return null;
    }
}
